package org.example.processor.dto;

import lombok.*;
import org.example.processor.entity.Processor;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateProcessorRequest {

    private String brand;
    private String name;
    private double cpuClockSpeed;

    public static Function<CreateProcessorRequest, Processor> dtoToEntityMapper() {
        return request -> Processor.builder()
                .brand(request.getBrand())
                .name(request.getName())
                .cpuClockSpeed(request.getCpuClockSpeed())
                .build();
    }
};