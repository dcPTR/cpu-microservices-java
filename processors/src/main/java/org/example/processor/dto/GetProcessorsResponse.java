package org.example.processor.dto;

import lombok.*;
import org.example.processor.entity.Processor;

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
public class GetProcessorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class ProcessorInner {
        private String name;
        private Long id;
    }

    @Singular
    private List<ProcessorInner> processors;

    public static Function<Collection<Processor>, GetProcessorsResponse> entityToDtoMapper() {
        return processors -> {
            GetProcessorsResponseBuilder response = GetProcessorsResponse.builder();
            processors.stream()
                    .map(processor -> ProcessorInner.builder()
                            .name(processor.getName())
                            .id(processor.getId())
                            .build())
                    .forEach(response::processor);
            return response.build();
        };
    }
};

