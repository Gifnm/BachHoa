package com.spring.main.model;
import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "delivery_note")
public class DeliveryNote {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "timeCreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreate;

    @Column(name = "timeCompleted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCompleted;

    @ManyToOne
    @JoinColumn(name = "storeID")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    // Constructors, getters, and setters
}
