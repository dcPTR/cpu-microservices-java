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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@RestController
@RequestMapping("api/processors/{pid}/laptops")
public class ProcessorLaptopController {

    private LaptopService laptopService;
    private ProcessorService processorService;

    @GetMapping
    public ResponseEntity<GetLaptopsResponse> getLaptops(@PathVariable("pid") Long pid) {
        Optional<Processor> user = processorService.find(pid);
        return user.map(value -> ResponseEntity.ok(GetLaptopsResponse.entityToDtoMapper().apply(laptopService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{lid}")
    public ResponseEntity<GetLaptopResponse> getLaptop(@PathVariable("pid") Long pid,
                                                       @PathVariable("lid") Long lid) {
        return laptopService.find(pid, lid)
                .map(value -> ResponseEntity.ok(GetLaptopResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createLaptop(@PathVariable("pid") Long pid,
                                             @RequestBody CreateLaptopRequest request,
                                             UriComponentsBuilder builder) {

        Optional<Processor> processor = processorService.find(pid);
        if (processor.isPresent()) {
            Laptop laptop = CreateLaptopRequest.dtoToEntityMapper(new Function<Long, Processor>() {
                public Processor apply(Long id) {
                    return processorService.find(id).orElseThrow();
                }
            }).apply(request);
            laptop = laptopService.create(laptop);
            return ResponseEntity.created(builder.pathSegment("api", "processors", "{pid}", "laptops", "{lid}")
                    .buildAndExpand(processor.get().getId(), laptop.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{lid}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable("pid") Long pid,
                                             @PathVariable("lid") Long lid) {
        Optional<Laptop> laptop = laptopService.find(pid, lid);
        System.out.println("Deleting the laptop");
        System.out.println(laptop);
        if (laptop.isPresent()) {
            laptopService.delete(laptop.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{lid}")
    public ResponseEntity<Void> updateLaptop(@PathVariable("pid") Long pid,
                                                @RequestBody UpdateLaptopRequest request,
                                                @PathVariable("lid") Long lid) {
        Optional<Laptop> laptop = laptopService.find(pid, lid);
        if (laptop.isPresent()) {
            UpdateLaptopRequest.dtoToEntityUpdater().apply(laptop.get(), request);
            laptopService.update(laptop.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
