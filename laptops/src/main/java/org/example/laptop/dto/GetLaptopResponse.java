package org.example.laptop.dto;

import lombok.*;
import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetLaptopResponse {

    Long id;
    String brand;
    String model;
    double screenSize;
    double ram;
    String processorName;
    double processorClockSpeed;


    public static Function<Laptop, GetLaptopResponse> entityToDtoMapper() {
        return laptop -> GetLaptopResponse.builder()
                .id(laptop.getId())
                .brand(laptop.getBrand())
                .model(laptop.getModel())
                .screenSize(laptop.getScreenSize())
                .ram(laptop.getRam())
                .processorName(laptop.getCpu().getName())
                .processorClockSpeed(laptop.getCpu().getCpuClockSpeed())
                .build();
    }
}
