package org.example.laptop.dto;

import lombok.*;
import org.example.laptop.entity.Laptop;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateLaptopRequest {

    String brand;
    String model;
    double screenSize;
    double ram;

    public static BiFunction<Laptop, UpdateLaptopRequest, Laptop> dtoToEntityUpdater() {
        return (laptop, request) -> {
            laptop.setBrand(request.getBrand());
            laptop.setModel(request.getModel());
            laptop.setScreenSize(request.getScreenSize());
            laptop.setRam(request.getRam());
            return laptop;
        };
    }
}
