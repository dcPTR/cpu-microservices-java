package org.example.processor.service;

import org.example.event.repository.ProcessorEventRepository;
import org.example.processor.entity.Processor;
import org.example.processor.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {
    private ProcessorRepository repository;
    private ProcessorEventRepository eventRepository;

    @Autowired
    public ProcessorService(ProcessorRepository repository, ProcessorEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Processor> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Processor> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Processor> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Processor processor) {
        repository.delete(processor);
        eventRepository.delete(processor);
    }

    @Transactional
    public void create(Processor processor) {
        repository.save(processor);
        eventRepository.create(processor);
    }

    @Transactional
    public void update(Processor processor) {
        repository.save(processor);
    }

}
