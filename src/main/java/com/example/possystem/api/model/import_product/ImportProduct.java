package com.example.possystem.api.model.import_product;

import com.example.possystem.api.model.imports.Import;
import com.example.possystem.api.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "imports_prodcuts")
public class ImportProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "imports_id")
    private Import imports;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product products;

    private Integer quantity;
    private BigDecimal amount;

    private Boolean isDeleted;

}
