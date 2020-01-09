package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    TimeEntryRepository timeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }

    @Bean
    DistributionSummary distributionSummary() {
        return new DistributionSummary();
    }

    @Bean
    Counter counter() {
        return new Counter();
    }

    @Bean
    MeterRegistry meterRegistry(DistributionSummary timeEntrySummary, Counter actionCounter) {
        return new MeterRegistry(timeEntrySummary, actionCounter);
    }
}
