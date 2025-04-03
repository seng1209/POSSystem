package com.example.possystem.api.service.sale;

import com.example.possystem.api.mapper.SaleMapper;
import com.example.possystem.api.model.customer.Customer;
import com.example.possystem.api.model.sale.Sale;
import com.example.possystem.api.model.sale.dto.CreateSaleDto;
import com.example.possystem.api.model.sale.dto.SaleDto;
import com.example.possystem.api.model.sale.dto.UpdateSaleDto;
import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.repository.CustomerRepository;
import com.example.possystem.api.repository.SaleRepository;
import com.example.possystem.api.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

    private final SaleMapper saleMapper;
    private final SaleRepository saleRepository;
    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;

    @Override
    public SaleDto createSale(CreateSaleDto createSaleDto) {

        Staff staff = staffRepository.findByUuid(createSaleDto.staffUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found")
        );

        Customer customer = customerRepository.findByPhone(createSaleDto.customerPhone()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")
        );

        Sale sale = saleMapper.formSaleDto(createSaleDto);
        sale.setUuid(UUID.randomUUID().toString());
        sale.setSaleDate(LocalDate.now());
        sale.setStaff(staff);
        sale.setCustomer(customer);
        sale.setTotalAmount(BigDecimal.valueOf(0));
        sale.setIsDeleted(false);

        saleRepository.save(sale);

        return saleMapper.toSaleDto(sale);
    }

    @Override
    public SaleDto updateByUuid(String uuid, UpdateSaleDto updateSaleDto) {

        if (saleRepository.existsByUuid(uuid)){
            Sale sale = saleRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale UUID not found.")
            );

            saleMapper.fromUpdateSaleDto(sale, updateSaleDto);

            if (updateSaleDto.customerPhone() != null){
                Customer newCustomer = customerRepository.findByPhone(updateSaleDto.customerPhone()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.")
                );
                sale.setCustomer(newCustomer);
            }

            if (updateSaleDto.staffUuid() != null){
                Staff newStaff = staffRepository.findByUuid(updateSaleDto.staffUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
                );
                sale.setStaff(newStaff);
            }

            saleRepository.save(sale);

            return saleMapper.toSaleDto(sale);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale UUID not found.");

    }

    @Transactional
    @Override
    public void deleteByUuid(String uuid) {
        if (saleRepository.existsByUuid(uuid)){
            saleRepository.deleteByUuid(uuid);
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale UUID not found.");
    }

    @Override
    public List<SaleDto> findAll() {
        List<Sale> saleList = saleRepository.findAllByIsDeletedIsFalse();
        return saleMapper.toSaleDtoList(saleList);
    }

    @Override
    public SaleDto findByUuid(String uuid) {
        Sale sale = saleRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale UUID not found.")
        );
        return saleMapper.toSaleDto(sale);
    }

    @Override
    public List<SaleDto> findByDate(LocalDate saleDate) {
        List<Sale> saleList = saleRepository.findAllBySaleDateAndIsDeletedIsFalse(saleDate);
        return saleMapper.toSaleDtoList(saleList);
    }
}
