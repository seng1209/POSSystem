package com.example.possystem.api.model.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(length = 60)
    @Nationalized
    private String nameKh;
    @Column(length = 50)
    private String nameEng;
    @Column(unique = true, length = 8, nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;
    private String customerType;
    @Column(columnDefinition = "TEXT")
    private String address;

}
