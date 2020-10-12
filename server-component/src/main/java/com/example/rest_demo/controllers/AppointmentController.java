package com.example.rest_demo.controllers;

import com.example.rest_demo.InvalidParameterException;
import com.example.rest_demo.data.AppointmentRepository;
import com.example.rest_demo.data.SalesAvailableRepository;
import com.example.rest_demo.data.SalesEmployeeRepository;
import com.example.rest_demo.model.Appointment;
import com.example.rest_demo.model.SalesAvailable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AppointmentController {

    private final SalesAvailableRepository salesAvailableRepository;
    private final AppointmentRepository appointmentRepository;
    private final SalesEmployeeRepository salesEmployeeRepository;

    private AppointmentController(SalesAvailableRepository salesAvailableRepository,
                                  AppointmentRepository appointmentRepository,
                                  SalesEmployeeRepository salesEmployeeRepository) {
        this.salesAvailableRepository = salesAvailableRepository;
        this.appointmentRepository = appointmentRepository;
        this.salesEmployeeRepository = salesEmployeeRepository;
    }

    @GetMapping("/appointments/{id}")
    List<Appointment> getBySalesId(@PathVariable Long id) {
        return appointmentRepository.findAllBySalesEmployeeId(id);
    }

    @GetMapping("/appointments/propose")
    List<Appointment> getProposals(@RequestParam String address,
                                   @RequestParam List<Long> salesIds) {

        // 1. verify input (valid address + salesEmployees exist)
        if (address.isBlank() || salesIds.isEmpty() ||
                !salesIds.stream().allMatch(salesEmployeeRepository::existsById)) {
            throw new InvalidParameterException("At least one Parameter yields an invalid value!");
        }

        // 2. query SalesAvailable times for salesEmployees
        List<SalesAvailable> availables = new ArrayList<>();
        salesIds.forEach(salesId -> availables.addAll(salesAvailableRepository.findAllBySalesEmployeeId(salesId)));

        // 3. create one appointment proposal for every SalesAvailable, which is big enough
        List<Appointment> results = availables.stream()
                //.filter(available -> ChronoUnit.MINUTES.between(available.getBegin(), available.getEnd()) >= 15)
                .map(available -> new Appointment(available.getSalesEmployeeId(),
                address, available.getBegin(), available.getEnd(), ""))
                .collect(Collectors.toList());

        return results;
    }

    @PostMapping("/appointments")
    Appointment newAppointment(@RequestBody Appointment appointment) {
        // 1. verify input (valid salesID? valid begin & end for appointment?)
        if (!salesEmployeeRepository.existsById(appointment.getSalesEmployeeId()) ||
                !appointment.getBegin().isBefore(appointment.getEnd())) {
            throw new InvalidParameterException("At least one Parameter yields an invalid value!");
        }

        //    is the selected SalesEmployee available at the proposed time?)
        Optional<SalesAvailable> salesAvailableOptional = salesAvailableRepository.findAllBySalesEmployeeId(appointment.getSalesEmployeeId())
                .stream().filter(s ->
                        (s.getBegin().compareTo(appointment.getBegin()) <= 0) &&
                        (s.getEnd().compareTo(appointment.getEnd()) >= 0))
                .findFirst();
        if (salesAvailableOptional.isEmpty()) {
            throw new InvalidParameterException("Sales employee with id: " + appointment.getSalesEmployeeId()
                    + " is not available at the selected time");
        }

        // 2. Update SalesAvailableRepository (delete original SalesAvailable, maybe create new ones before/after appointment)
        SalesAvailable salesAvailable = salesAvailableOptional.get();
        salesAvailableRepository.delete(salesAvailable);

        if (appointment.getBegin().isAfter(salesAvailable.getBegin())) {
            SalesAvailable beforeAppointment = new SalesAvailable(salesAvailable.getSalesEmployeeId(),
                    salesAvailable.getBegin(), appointment.getBegin());
            salesAvailableRepository.save(beforeAppointment);
        }

        if (appointment.getEnd().isBefore(salesAvailable.getEnd())) {
            SalesAvailable afterAppointment = new SalesAvailable(salesAvailable.getSalesEmployeeId(),
                    appointment.getEnd(), salesAvailable.getEnd());
            salesAvailableRepository.save(afterAppointment);
        }

        // 3. save Appointment in database
        return appointmentRepository.save(appointment);
    }
}
