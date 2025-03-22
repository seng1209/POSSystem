package com.example.possystem.api.model.supplier;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private  String uuid;
    private String supplierName;
    @Column(unique = true, nullable = false)
    private String contactPhone;
    @Column(columnDefinition = "TEXT")
    private String contactAddress;


}
