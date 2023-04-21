package org.example.configuration;

import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;
import org.example.laptop.service.LaptopService;
import org.example.laptop.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final LaptopService laptopService;
    private final ProcessorService processorService;


    @Autowired
    public InitializedData(LaptopService laptopService, ProcessorService processorService) {
        this.laptopService = laptopService;
        this.processorService = processorService;
    }

    @PostConstruct
    private synchronized void init() {

        if (processorService.find(1L).isEmpty()) {
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

            Laptop l1 = Laptop.builder()
                    .brand("Lenovo")
                    .model("Flex 2 15")
                    .screenSize(15.6)
                    .ram(8)
                    .cpu(p1)
                    .build();

            Laptop l2 = Laptop.builder()
                    .brand("Lenovo")
                    .model("Ideapad 320 15-ikb")
                    .screenSize(15.6)
                    .ram(8)
                    .cpu(p2)
                    .build();

            laptopService.create(l1);
            laptopService.create(l2);
        }
    }
}
