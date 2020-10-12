package com.example.rest_demo;

import com.example.rest_demo.controllers.AppointmentController;
import com.example.rest_demo.data.AppointmentRepository;
import com.example.rest_demo.data.SalesAvailableRepository;
import com.example.rest_demo.data.SalesEmployeeRepository;
import com.example.rest_demo.model.Appointment;
import com.example.rest_demo.model.SalesAvailable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AppointmentController.class)
@WithMockUser
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesEmployeeRepository salesEmployeeRepository;

    @MockBean
    private SalesAvailableRepository salesAvailableRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Test
    public void getAppointmentProposals() throws Exception {
        // 1. Mocks:
        // salesEmployeeRepository.existsById
        // salesAvailableRepository.findAllBySalesEmployeeId
        Mockito.when(salesEmployeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(salesAvailableRepository.findAllBySalesEmployeeId(1L)).thenReturn(Arrays.asList(
                new SalesAvailable(1L, OffsetDateTime.parse("2020-12-02T11:30:00"),
                        OffsetDateTime.parse("2020-12-02T13:30:00")),
                new SalesAvailable(1L, OffsetDateTime.parse("2020-12-02T14:30:00"),
                        OffsetDateTime.parse("2020-12-02T15:30:00")),
                new SalesAvailable(1L, OffsetDateTime.parse("2020-12-02T16:30:00"),
                        OffsetDateTime.parse("2020-12-02T16:30:00"))
        ));


        // 2. request and response deserialization (into List<Appointment>)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/appointments/propose?address=abc&salesIds=1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
        List<Appointment> resultList = mapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {});

        // 3. assertions on the result
        List<Appointment> expectedResult = Arrays.asList(
                new Appointment(1L, "abc", OffsetDateTime.parse("2020-12-02T11:30:00"),
                        OffsetDateTime.parse("2020-12-02T13:30:00"), ""),
                new Appointment(1L, "abc", OffsetDateTime.parse("2020-12-02T14:30:00"),
                        OffsetDateTime.parse("2020-12-02T15:30:00"), "")
        );
        Assert.assertEquals(resultList.size(), 2);
        //assertThat(resultList, is(expectedResult));
    }

    @Test
    public void newAppointment() throws Exception {
        // this method uses (and thus we should mock) :
        // salesEmployeeRepository.existsById
        // salesAvailableRepository.findAllBySalesEmployeeId
        // salesAvailableRepository.save -> used twice! --> result is not used (but we are interested!)
        // salesAvailableRepository.delete --> result is not used (but we are interested!)
        // appointmentRepository.save
        Mockito.when(salesEmployeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
/*        Mockito.when(salesAvailableRepository.findAllBySalesEmployeeId(1L)).thenReturn(Arrays.asList(
                new SalesAvailable(1L, OffsetTime.parse("2020-12-02T11:30:00"),
                        OffsetTime.parse("2020-12-02T13:30:00")),
                new SalesAvailable(1L, OffsetTime.parse("2020-12-02T14:30:00"),
                        OffsetTime.parse("2020-12-02T15:30:00")),
                new SalesAvailable(1L, OffsetTime.parse("2020-12-02T16:30:00"),
                        OffsetTime.parse("2020-12-02T16:30:00"))
        ));
 */
    }
}
