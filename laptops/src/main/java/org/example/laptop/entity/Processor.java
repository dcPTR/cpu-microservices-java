package org.example.laptop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "processors")
public class Processor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p-gen")
    @SequenceGenerator(name = "p-gen", sequenceName = "processorSequence", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    private String name;

    double cpuClockSpeed; // in GHz
    String brand;
}