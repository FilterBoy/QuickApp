package com.example.rest_demo.data;

import com.example.rest_demo.model.SalesAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesAvailableRepository extends JpaRepository<SalesAvailable, Long> {
    @Query("select x from SalesAvailable x where x.salesEmployeeId = ?1")
    List<SalesAvailable> findAllBySalesEmployeeId(Long salesEmployeeId);
}
