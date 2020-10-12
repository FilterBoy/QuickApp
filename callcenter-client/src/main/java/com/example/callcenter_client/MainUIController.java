package com.example.callcenter_client;

import io.swagger.client.ApiException;
import io.swagger.client.api.AppointmentControllerApi;
import io.swagger.client.api.SalesEmployeeControllerApi;
import io.swagger.client.model.Appointment;
import io.swagger.client.model.SalesEmployee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainUIController {

    @FXML TextField addressTextField;
    @FXML Button calculateBtn;
    @FXML GridPane salesEmployeeGrid;
    @FXML ListView<Appointment> outputList;

    Map<SalesEmployee, CheckBox> salesEmployeeMap= new HashMap<>();

    SalesEmployeeControllerApi salesEmployeeApi = new SalesEmployeeControllerApi();
    AppointmentControllerApi appointmentApi = new AppointmentControllerApi();

    private static final Logger log = LoggerFactory.getLogger(MainUIController.class);

    /* was required for publishing a StageReadyEvent [must MainUIController be annotated with @component to parse?]
    @Autowired
    private ApplicationContext springContext;
    */

    @FXML
    void initialize() throws ApiException {

        populateSalesEmployeeList();

        setCalculateBtnClickedAction();

        customizeOutputList();
    }

    private void customizeOutputList() {
        outputList.setPlaceholder(new Label("no appointment proposals match the criteria"));

        // create a custom representation for the appointments
        outputList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Appointment app, boolean empty) {
                super.updateItem(app, empty);

                if (empty || app == null) {
                    setText(null);
                    setGraphic(null);
                    // everything above is required by the specification of the updateItem method!
                } else {
                    setText(String.format("%s: %d.%02d.%02d %02d:%02d - %02d:%02d",
                            findSalesEmployee(app.getSalesEmployeeId()).getName(),
                            app.getBegin().getYear(), app.getBegin().getMonthValue(), app.getBegin().getDayOfMonth(),
                            app.getBegin().getHour(), app.getBegin().getMinute(),
                            app.getEnd().getHour(), app.getEnd().getMinute()));
                }
            }
        });

        outputList.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2) {
                Appointment app = outputList.getSelectionModel().getSelectedItem();
                log.info("Appointment clicked");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/createAppointmentUI.fxml"));
                    Stage stage = new Stage(StageStyle.UNIFIED);
                    stage.setScene(new Scene(loader.load()));

                    CreateAppointmentUIController controller = loader.getController();
                    controller.initData(app, findSalesEmployee(app.getSalesEmployeeId()).getName());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setCalculateBtnClickedAction() {
        calculateBtn.setOnAction(actionEvent -> {
            List<Long> salesEmployeeIds = salesEmployeeMap.entrySet().stream()
                    .filter(entry -> entry.getValue().isSelected())
                    .map(entry -> entry.getKey().getId())
                    .collect(Collectors.toList());
            String address = addressTextField.getText();

            log.info("address: " + address + ", salesIds: " + salesEmployeeIds.toString());

            try {
                List<Appointment> appointments = appointmentApi.getProposalsUsingGET(address, salesEmployeeIds);
                outputList.setItems(FXCollections.observableList(appointments));
            } catch (ApiException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getResponseBody());
                new Alert(Alert.AlertType.ERROR, "API exception: invalid parameters").show();
                // we should parse the ApiException to see what went wrong!
                // e.g: distinguish between java.net.ConnectException and "400/Bad Request" response from API
            }

        });
    }

    private void populateSalesEmployeeList() throws ApiException {
        List<SalesEmployee> salesEmployeeList = salesEmployeeApi.allUsingGET();
        for (int i = 0; i < salesEmployeeList.size(); i++) {
            CheckBox cb = new CheckBox(salesEmployeeList.get(i).getName());
            salesEmployeeGrid.add(cb, i % 4, i / 4);
            //todo-ui-test: add more than 4 salesmen
            salesEmployeeMap.put(salesEmployeeList.get(i), cb);
        }
    }

    private SalesEmployee findSalesEmployee(Long id) {
        return salesEmployeeMap.keySet().stream()
                .filter(salesEmployee -> Objects.equals(salesEmployee.getId(), id))
                .findFirst()
                .orElseGet(() -> {throw new RuntimeException("given id is not occupied");});
    }
}
