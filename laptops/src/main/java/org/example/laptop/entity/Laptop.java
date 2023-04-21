package org.example.laptop.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.computer.entity.Computer;


import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "laptops")
public class Laptop extends Computer {

    double screenSize;
    private String model;
    
    @ManyToOne
    @JoinColumn(name = "cpu")
    private Processor cpu;
}
