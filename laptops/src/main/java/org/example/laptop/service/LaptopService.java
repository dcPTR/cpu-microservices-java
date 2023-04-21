package org.example.laptop.service;

import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;
import org.example.laptop.repository.LaptopRepository;
import org.example.laptop.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    private LaptopRepository repository;
    private ProcessorRepository processorRepository;

    @Autowired
    public LaptopService(LaptopRepository repository, ProcessorRepository processorRepository){
        this.repository = repository;
        this.processorRepository = processorRepository;
    }

    public Optional<Laptop> find(Long id) { return repository.findById(id); }

    public Optional<Laptop> find(Long pid, Long lid) {
        Optional<Processor> cpu = processorRepository.findById(pid);
        if(cpu.isPresent()){
            return repository.findByIdAndCpu(lid, cpu.get());
        }
        else{
            return Optional.empty();
        }
    }

    public List<Laptop> findAll() { return repository.findAll(); }

    public List<Laptop> findAll(Processor processor) { return repository.findAllByCpu(processor); }

    @Transactional
    public void delete(Laptop laptop) { repository.delete(laptop); }

    @Transactional
    public Laptop create(Laptop laptop) { return repository.save(laptop); }

    @Transactional
    public void update(Laptop laptop) { repository.save(laptop); }

}
