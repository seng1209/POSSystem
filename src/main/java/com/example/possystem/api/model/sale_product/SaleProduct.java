package com.example.possystem.api.model.sale_product;

import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.model.sale.Sale;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sales_products")
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sales;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product products;

    private Integer quantity;
    private BigDecimal amount;
    private Boolean isDeleted;

}
