package com.example.possystem.api.model.staff;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(columnDefinition = "TEXT")
    private String image;
    @Column(length = 60)
    @Nationalized
    private String nameKh;
    @Column(length = 50)
    private String nameEng;
    @Column(length = 6)
    private String gender;
    private LocalDate birthDate;
    @Column(length = 8)
    private String phone;
    @Email(message = "Email is not valid", regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String position;
    private BigDecimal salary;
    private LocalDate hiredDate;
    private Boolean isDeleted;

}
