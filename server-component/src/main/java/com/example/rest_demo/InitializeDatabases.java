package com.example.rest_demo;

import com.example.rest_demo.data.SalesAvailableRepository;
import com.example.rest_demo.data.SalesEmployeeRepository;
import com.example.rest_demo.model.SalesAvailable;
import com.example.rest_demo.model.SalesEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

@Configuration
public class InitializeDatabases {

    private static final Logger log = LoggerFactory.getLogger(InitializeDatabases.class);

    @Bean
    CommandLineRunner initDatabase2(SalesEmployeeRepository repository1,
                                    SalesAvailableRepository repository2) {
        return args -> {
            log.info("Preloading " + repository1.save(new SalesEmployee("Robin Hood")));
            log.info("Preloading " + repository1.save(new SalesEmployee("Indi János")));

            OffsetDateTime dt1 = OffsetDateTime.parse("2020-10-23T11:30:00+01:00");
            OffsetDateTime dt2 = OffsetDateTime.parse("2020-10-23T12:30:00+01:00");
            OffsetDateTime dt3 = OffsetDateTime.parse("2020-10-23T13:30:00+01:00");
            log.info("Preloading " + repository2.save(new SalesAvailable(2L, dt1, dt2)));
            log.info("Preloading " + repository2.save(new SalesAvailable(2L, dt2, dt3)));
        };
    }

}
