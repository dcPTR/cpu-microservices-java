package org.example.laptop.controller;

import lombok.AllArgsConstructor;
import org.example.laptop.dto.CreateProcessorRequest;
import org.example.laptop.dto.GetProcessorsResponse;
import org.example.laptop.entity.Laptop;
import org.example.laptop.service.LaptopService;
import org.example.laptop.service.ProcessorService;
import org.example.laptop.entity.Processor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/processors")
public class ProcessorController {

    private ProcessorService processorService;
    private LaptopService laptopService;

    @GetMapping
    public ResponseEntity<GetProcessorsResponse> getProcessors() {
        System.out.println("PROCESSOR controller - getting all the processors.");
        System.out.println(ResponseEntity
                .ok(GetProcessorsResponse.entityToDtoMapper().apply(processorService.findAll())));
        return ResponseEntity
                .ok(GetProcessorsResponse.entityToDtoMapper().apply(processorService.findAll()));
    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable("pid") Long pid) {

        Optional<Processor> processor = processorService.find(pid);
        System.out.println(processor);
        if (processor.isPresent()) {
            List<Laptop> laptops = laptopService.findAll(processor.get());
            for (Laptop l : laptops) {
                laptopService.delete(l);
            }
            processorService.delete(processor.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createProcessor(@RequestBody CreateProcessorRequest request, UriComponentsBuilder builder) {
        System.out.println("Creating a processor");
        System.out.println(request);
        Processor processor = CreateProcessorRequest.dtoToEntityMapper().apply(request);
        processorService.create(processor);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "processors", "{pid}")
                        .buildAndExpand(processor.getId()).toUri())
                .build();
    }
}