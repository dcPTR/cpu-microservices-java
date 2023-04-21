package org.example.processor.controller;

import lombok.AllArgsConstructor;
import org.example.processor.dto.CreateProcessorRequest;
import org.example.processor.dto.GetProcessorResponse;
import org.example.processor.dto.GetProcessorsResponse;
import org.example.processor.dto.UpdateProcessorRequest;
import org.example.processor.entity.Processor;
import org.example.processor.service.ProcessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/processors")
public class ProcessorController {

    private ProcessorService processorService;


    @GetMapping
    public ResponseEntity<GetProcessorsResponse> getProcessors() {
        return ResponseEntity
                .ok(GetProcessorsResponse.entityToDtoMapper().apply(processorService.findAll()));
    }


    @GetMapping("{pid}")
    public ResponseEntity<GetProcessorResponse> getProcessor(@PathVariable("pid") Long pid) {
        return processorService.find(pid)
                .map(value -> ResponseEntity
                        .ok(GetProcessorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable("pid") Long pid) {
        Optional<Processor> processor = processorService.find(pid);
        if (processor.isPresent()) {
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
        Processor processor = CreateProcessorRequest.dtoToEntityMapper().apply(request);
        processorService.create(processor);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "processors", "{pid}")
                        .buildAndExpand(processor.getId()).toUri())
                .build();
    }


    @PutMapping("{pid}")
    public ResponseEntity<Void> updateCharacter(@PathVariable("pid") Long pid,
                                                @RequestBody UpdateProcessorRequest request) {
        Optional<Processor> processor = processorService.find(pid);
        if (processor.isPresent()) {
            UpdateProcessorRequest.dtoToEntityUpdater().apply(processor.get(), request);
            processorService.update(processor.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    @PutMapping("{pid}")
//    public ResponseEntity<Void> updateProcessor(@RequestBody UpdateProcessorRequest request, @PathVariable("pid") Long pid {
//        Optional<Processor> processor = processorService.find(pid);
//        if (processor.isPresent()) {
//            UpdateProcessorRequest.dtoToEntityUpdater().apply(processor.get(), request, processor.get());
//            processorService.update(processor.get());
//            return ResponseEntity
//                    .accepted()
//                    .build();
//        } else {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//    }

//    @PutMapping("")
//    public ResponseEntity<Void> updateProcessor(@RequestBody UpdateProcessorRequest request, UriComponentsBuilder builder) {
//        Processor processor = UpdateProcessorRequest.dtoToEntityMapper().apply(request);
//        processorService.update(processor);
//        return ResponseEntity
//                .created(builder
//                        .pathSegment("api", "processors", "{pid}")
//                        .buildAndExpand(processor.getId()).toUri())
//                .build();
//    }


}
