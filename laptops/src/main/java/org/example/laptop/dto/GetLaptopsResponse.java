package org.example.laptop.dto;


import lombok.*;
import org.example.laptop.entity.Laptop;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetLaptopsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class LaptopsInner {
        private Long id;
        private String model;
    }

    @Singular
    private List<LaptopsInner> laptops;

    public static Function<Collection<Laptop>, GetLaptopsResponse> entityToDtoMapper() {
        return laptops -> {
            GetLaptopsResponseBuilder response = GetLaptopsResponse.builder();
            laptops.stream()
                    .map(laptop -> LaptopsInner.builder()
                            .id(laptop.getId())
                            .model(laptop.getModel())
                            .build())
                    .forEach(response::laptop);
            return response.build();
        };
    }
}
