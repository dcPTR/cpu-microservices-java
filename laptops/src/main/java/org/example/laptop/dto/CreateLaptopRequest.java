package org.example.laptop.dto;


import lombok.*;
import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateLaptopRequest {

    String brand;
    String model;
    double screenSize;
    double ram;
    Long processorId;


    public static Function<CreateLaptopRequest, Laptop> dtoToEntityMapper(
            Function<Long, Processor> processorFunction) {
        return request -> Laptop.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .screenSize(request.getScreenSize())
                .ram(request.getRam())
                .cpu(processorFunction.apply(request.getProcessorId()))
                .build();
    }
//
//    public static <R> Function<CreateLaptopRequest,R> dtoToEntityMapper(Object o, Object get) {
//    }
}
