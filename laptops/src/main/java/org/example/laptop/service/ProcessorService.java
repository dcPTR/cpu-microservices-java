package org.example.laptop.service;

import org.example.laptop.entity.Processor;
import org.example.laptop.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {
    private ProcessorRepository repository;

    @Autowired
    public ProcessorService(ProcessorRepository repository){ this.repository = repository; }

    public Optional<Processor> find(Long id) { return repository.findById(id); }

    public Optional<Processor> findByName(String name) { return repository.findByName(name); }

    public List<Processor> findAll() { return repository.findAll(); }

    @Transactional
    public void delete(Processor processor) { repository.delete(processor); }

    @Transactional
    public void create(Processor processor) { repository.save(processor); }

    @Transactional
    public void update(Processor processor) { repository.save(processor); }

}
