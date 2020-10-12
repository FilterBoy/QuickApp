package com.example.callcenter_client;

import io.swagger.api.ApiException;
import io.swagger.api.AppointmentsApiController;
import io.swagger.model.Appointment;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;

import java.util.Arrays;

public class CreateAppointmentUIController {

    @FXML Label headingLabel;
    @FXML DatePicker datePicker;
    @FXML ChoiceBox<Integer> hourChoiceBox;
    @FXML ChoiceBox<Integer> minChoiceBox;
    @FXML TextField durationTextField;
    @FXML Button okButton;

    Appointment proposal;

    AppointmentsApiController appointmentApi;

    private static final Logger log = LoggerFactory.getLogger(MainUIController.class);

    @FXML
    void initialize() {
        hourChoiceBox.setItems(FXCollections.observableList(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17)));
        minChoiceBox.setItems(FXCollections.observableList(Arrays.asList(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55)));

        StringConverter<Integer> stringConverter = new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return String.format("%02d", integer);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.valueOf(s);
            }
        };
        hourChoiceBox.setConverter(stringConverter);
        minChoiceBox.setConverter(stringConverter);

        okButton.setOnAction(actionEvent -> {
            //understand: proposal.begin is of type org.threeten.bp.OffsetDateTime and not java.date.OffsetDateTime
            OffsetDateTime begin = OffsetDateTime.of(LocalDate.parse(datePicker.getValue().toString()),
                    LocalTime.of(hourChoiceBox.getValue(), minChoiceBox.getValue()),
                    proposal.getBegin().getOffset());

            proposal.setBegin(begin);
            proposal.setEnd(begin.plusMinutes(Integer.parseInt(durationTextField.getText())));

            //try {
                ResponseEntity<Appointment> response = appointmentApi.newAppointmentUsingPOST(proposal);
                if (response.getStatusCodeValue() == 200) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appointment Created!").show();
                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
                else {
                    log.error(response.getStatusCode().toString());
                }
            /*} catch (ApiException e) {
                String message = e.getMessage();
                if (e.getCode() == 0) {
                    message = "connection to server could not be established";
                }
                else if (e.getCode() == 400) {
                    message = "invalid input: " + e.getResponseBody();
                } else {
                    message = "unknown error. check log for further information";
                    log.error(e.getCode() + ": " + e.getMessage());
                    log.error(e.getResponseHeaders().toString());
                    log.error(e.getResponseBody());
                }
                new Alert(Alert.AlertType.ERROR, message).show();

            }*/

        });
    }

    public void initData(Appointment app, String employeeName) {
        this.proposal = app;

        headingLabel.setText(String.format("create appointment for %s at %s",
                employeeName, app.getAddress()));
        datePicker.setValue(java.time.LocalDate.ofYearDay(app.getBegin().getYear(), app.getBegin().getDayOfYear()));
        hourChoiceBox.setValue(app.getBegin().getHour());
        minChoiceBox.setValue(app.getBegin().getMinute());
    }
}
