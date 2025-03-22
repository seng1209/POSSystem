package com.example.possystem.api.model.imports;

import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "imports")
public class Import {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    private LocalDate importDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private BigDecimal totalAmount = BigDecimal.valueOf(0);
    private Boolean isDeleted;

}
