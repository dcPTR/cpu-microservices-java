package org.example.configuration;

import org.example.processor.entity.Processor;
import org.example.processor.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class InitializedData {
    private final ProcessorService processorService;


    @Autowired
    public InitializedData(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @PostConstruct
    private synchronized void init() {

        if(processorService.find(1L).isEmpty()) {
            Processor p1 = Processor.builder()
                    .brand("Intel")
                    .name("i5-4210u")
                    .cpuClockSpeed(2.7)
                    .id(1L)
                    .build();

            Processor p2 = Processor.builder()
                    .brand("Intel")
                    .name("i5-7200u")
                    .cpuClockSpeed(3.1)
                    .id(2L)
                    .build();

            processorService.create(p1);
            processorService.create(p2);
        }
    }
}
