package org.example.event.dto;

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

    private Long id;
    private String brand;
    private String name;
    private double cpuClockSpeed;

    public static Function<Processor, CreateProcessorRequest> entityToDtoMapper() {
        return entity -> CreateProcessorRequest.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .name(entity.getName())
                .cpuClockSpeed(entity.getCpuClockSpeed())
                .build();
    }

}
