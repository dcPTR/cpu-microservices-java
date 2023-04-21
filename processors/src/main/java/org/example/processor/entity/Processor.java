package org.example.processor.entity;

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
public class Processor implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    private String name;
    double cpuClockSpeed; // in GHz
    String brand;
}