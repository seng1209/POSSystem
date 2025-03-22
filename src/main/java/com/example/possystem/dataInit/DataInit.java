package com.example.possystem.dataInit;

import com.example.possystem.api.model.category.Category;
import com.example.possystem.api.model.customer.Customer;
import com.example.possystem.api.model.imports.Import;
import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.supplier.Supplier;
import com.example.possystem.api.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final ImportRepository importRepository;

    @PostConstruct
    public void init(){
        if (categoryRepository.count() == 0){
            Category coffee = new Category();
            coffee.setUuid("1");
            coffee.setCategoryName("Coffee");

            Category drink = new Category();
            drink.setUuid("2");
            drink.setCategoryName("Drink");

            categoryRepository.saveAll(List.of(coffee, drink));
        }

        if (customerRepository.count() == 0){
            Customer customer1 = new Customer();
            customer1.setUuid("1");
            customer1.setCustomerType("Normal");
            customer1.setAddress("Phnom Penh");
            customer1.setNameEng("Nich");
            customer1.setNameKh("ស្រីនិច");
            customer1.setEmail("nich@fake.com");
            customer1.setPhone("11111111");

            Customer customer2 = new Customer();
            customer2.setUuid("2");
            customer2.setCustomerType("Normal");
            customer2.setAddress("Phnom Penh");
            customer2.setNameEng("Leap");
            customer2.setNameKh("លៀប");
            customer2.setEmail("leap@fake.com");
            customer2.setPhone("22222222");

            customerRepository.saveAll(List.of(customer2, customer1));
        }

        if (productRepository.count() == 0){

            Category c_coffee = categoryRepository.findByCategoryName("Coffee").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
            );

            Category c_drink = categoryRepository.findByCategoryName("Drink").orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
            );

            Product milk = new Product();
            milk.setUuid("1");
            milk.setImage("milk.png");
            milk.setName("Milk");
            milk.setPrice(BigDecimal.valueOf(1));
            milk.setDescription("milk..................................");
            milk.setCategory(c_drink);
            milk.setInStock(true);
            milk.setIsDeleted(false);

            Product coffee = new Product();
            coffee.setUuid("2");
            coffee.setImage("coffee.png");
            coffee.setName("Coffee");
            coffee.setPrice(BigDecimal.valueOf(2.5));
            coffee.setDescription("coffee..................................");
            coffee.setCategory(c_coffee);
            coffee.setInStock(true);
            coffee.setIsDeleted(false);

            productRepository.saveAll(List.of(coffee, milk));
        }

        if (supplierRepository.count() == 0){
            Supplier amazon = new Supplier();
            amazon.setUuid("1");
            amazon.setSupplierName("Amazon");
            amazon.setContactPhone("+18843843");
            amazon.setContactAddress("USA");

            Supplier google = new Supplier();
            google.setUuid("2");
            google.setSupplierName("Google");
            google.setContactPhone("+1003843");
            google.setContactAddress("USA");

            Supplier microsoft = new Supplier();
            microsoft.setUuid("3");
            microsoft.setSupplierName("Microsoft");
            microsoft.setContactPhone("+8438263");
            microsoft.setContactAddress("India");

            supplierRepository.saveAll(List.of(amazon, google, microsoft));
        }

        if (staffRepository.count() == 0){
            Staff veasna = new Staff();
            veasna.setUuid("1");
            veasna.setImage("veasna.png");
            veasna.setNameKh("វាស្នា");
            veasna.setNameEng("Vea Sna");
            veasna.setGender("Male");
            veasna.setBirthDate(LocalDate.parse("2003-10-31"));
            veasna.setPhone("09473623");
            veasna.setEmail("veasna@gmail.com");
            veasna.setAddress("Phnom Penh");
            veasna.setPosition("Graphic Design");
            veasna.setSalary(BigDecimal.valueOf(500));
            veasna.setHiredDate(LocalDate.parse("2022-04-15"));
            veasna.setIsDeleted(false);

            Staff sokchan = new Staff();
            sokchan.setUuid("2");
            sokchan.setImage("sokchan.png");
            sokchan.setNameKh("សុខច័ន្ទ");
            sokchan.setNameEng("Sok Chan");
            sokchan.setGender("Male");
            sokchan.setBirthDate(LocalDate.parse("2001-02-23"));
            sokchan.setPhone("04884623");
            sokchan.setEmail("sokchan321@gmail.com");
            sokchan.setAddress("Phnom Penh");
            sokchan.setPosition("Sale");
            sokchan.setSalary(BigDecimal.valueOf(650));
            sokchan.setHiredDate(LocalDate.parse("2019-09-30"));
            sokchan.setIsDeleted(false);

            staffRepository.saveAll(List.of(veasna, sokchan));
        }

        if (importRepository.count() == 0){
            List<Supplier> supplierList = supplierRepository.findAll();

            Supplier amazon = supplierList.get(0);

            Supplier google = supplierList.get(1);

            Supplier microsoft = supplierList.get(2);

            List<Staff> staffList = staffRepository.findAllByIsDeletedIsFalse();

            Staff veasna = staffList.get(0);

            Staff sreynich = staffList.get(1);

            Import import1 = new Import();
            import1.setUuid("1");
            import1.setImportDate(LocalDate.parse("2024-12-31"));
            import1.setStaff(veasna);
            import1.setSupplier(amazon);
            import1.setTotalAmount(BigDecimal.valueOf(500));
            import1.setIsDeleted(false);

            Import import2 = new Import();
            import2.setUuid("2");
            import2.setImportDate(LocalDate.parse("2024-12-31"));
            import2.setStaff(sreynich);
            import2.setSupplier(microsoft);
            import2.setTotalAmount(BigDecimal.valueOf(50000));
            import2.setIsDeleted(false);

            importRepository.saveAll(List.of(import1, import2));

        }
    }

}
