package org.example.laptop.controller;


import lombok.AllArgsConstructor;
import org.example.laptop.dto.CreateLaptopRequest;
import org.example.laptop.dto.GetLaptopResponse;
import org.example.laptop.dto.GetLaptopsResponse;
import org.example.laptop.dto.UpdateLaptopRequest;
import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;
import org.example.laptop.service.LaptopService;
import org.example.laptop.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@RestController
@RequestMapping("api/laptops")
public class LaptopController {
    private final LaptopService laptopService;
    private final ProcessorService processorService;

    @GetMapping
    public ResponseEntity<GetLaptopsResponse> getCharacters() {
        List<Laptop> all = laptopService.findAll();
        Function<Collection<Laptop>, GetLaptopsResponse> mapper = GetLaptopsResponse.entityToDtoMapper();
        GetLaptopsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetLaptopResponse> getLaptop(@PathVariable("id") long id) {
        return laptopService.find(id)
                .map(value -> ResponseEntity.ok(GetLaptopResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createLaptop(@RequestBody CreateLaptopRequest request, UriComponentsBuilder builder) {
        Laptop laptop = CreateLaptopRequest.dtoToEntityMapper(new Function<Long, Processor>() {
            public Processor apply(Long id) {
                return processorService.find(id).orElseThrow();
            }
        }).apply(request);

        laptop = laptopService.create(laptop);
        return ResponseEntity.created(builder.pathSegment("api", "laptops", "{id}")
                .buildAndExpand(laptop.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable("id") long id) {
        Optional<Laptop> laptop = laptopService.find(id);
        if (laptop.isPresent()) {
            laptopService.delete(laptop.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLaptop(@RequestBody UpdateLaptopRequest request, @PathVariable("id") long id) {
        Optional<Laptop> laptop = laptopService.find(id);
        if (laptop.isPresent()) {
            UpdateLaptopRequest.dtoToEntityUpdater().apply(laptop.get(), request);
            laptopService.update(laptop.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
