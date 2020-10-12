package com.example.rest_demo.data;

import com.example.rest_demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select x from Appointment x where x.salesEmployeeId = ?1")
    List<Appointment> findAllBySalesEmployeeId(Long salesEmployeeId);
}
