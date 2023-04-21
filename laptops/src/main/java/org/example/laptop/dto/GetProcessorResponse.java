package org.example.laptop.dto;


import lombok.*;
import org.example.laptop.entity.Processor;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetProcessorResponse {
    private String name;
    double cpuClockSpeed;
    String brand;

    public static Function<Processor, GetProcessorResponse> entityToDtoMapper() {
        return cpu -> GetProcessorResponse.builder()
                .name(cpu.getName())
                .cpuClockSpeed(cpu.getCpuClockSpeed())
                .brand(cpu.getBrand())
                .build();
    }
}
