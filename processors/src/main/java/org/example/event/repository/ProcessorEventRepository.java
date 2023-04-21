package org.example.event.repository;

import org.example.event.dto.CreateProcessorRequest;
import org.example.processor.entity.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProcessorEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public ProcessorEventRepository(@Value("${lab.laptops.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Processor processor) {
        System.out.println(processor);
        System.out.println(processor.getId());
        restTemplate.delete("/processors/{pid}", processor.getId());
    }

    public void create(Processor processor) {
        System.out.println(processor);
        restTemplate.postForLocation("/processors", CreateProcessorRequest.entityToDtoMapper().apply(processor));
    }
}
