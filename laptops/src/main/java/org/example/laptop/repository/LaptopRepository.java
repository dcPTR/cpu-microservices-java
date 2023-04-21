package org.example.laptop.repository;

import org.example.laptop.entity.Laptop;
import org.example.laptop.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    Optional<Laptop> findById(Long id);
    Optional<Laptop> findByIdAndCpu(Long id, Processor cpu);
    List<Laptop> findAllByBrand(String brand);
    List<Laptop> findAllByCpu(Processor processor);
}
