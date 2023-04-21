package org.example.processor.dto;

import lombok.*;
import org.example.processor.entity.Processor;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateProcessorRequest {

    private String brand;
    private String name;
    private double cpuClockSpeed;

    public static BiFunction<Processor, UpdateProcessorRequest, Processor> dtoToEntityUpdater() {
        return (processor, request) -> {
            processor.setBrand(request.getBrand());
            processor.setName(request.getName());
            processor.setCpuClockSpeed(request.getCpuClockSpeed());
            return processor;
        };
    }

};