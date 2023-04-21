package org.example.laptop.repository;

import org.example.laptop.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long>{
    Optional<Processor> findById(Long id);
    Optional<Processor> findByName(String name);
    List<Processor> findAllByBrand(String brand);

}
