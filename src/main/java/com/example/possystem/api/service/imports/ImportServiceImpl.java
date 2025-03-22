package com.example.possystem.api.service.imports;

import com.example.possystem.api.mapper.ImportMapper;
import com.example.possystem.api.model.imports.Import;
import com.example.possystem.api.model.imports.dto.CreateImportDto;
import com.example.possystem.api.model.imports.dto.ImportDto;
import com.example.possystem.api.model.imports.dto.UpdateImportDto;
import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.supplier.Supplier;
import com.example.possystem.api.repository.ImportRepository;
import com.example.possystem.api.repository.StaffRepository;
import com.example.possystem.api.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportServiceImpl implements ImportService {

    private final ImportRepository importRepository;
    private final ImportMapper importMapper;
    private final StaffRepository staffRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public ImportDto createImport(CreateImportDto createImportDto) {

        Staff staff = staffRepository.findByUuid(createImportDto.staffUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
        );

        Supplier supplier = supplierRepository.findByUuid(createImportDto.supplierUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
        );

        Import imports = importMapper.formImportDto(createImportDto);
        imports.setUuid(UUID.randomUUID().toString());
        imports.setStaff(staff);
        imports.setSupplier(supplier);
        imports.setImportDate(LocalDate.now());
        imports.setIsDeleted(false);

        importRepository.save(imports);

        return importMapper.toImportDto(imports);
    }

    @Override
    public ImportDto updateImportByUuid(String uuid, UpdateImportDto updateImportDto) {

        if (importRepository.existsByUuid(uuid)){
            Import imports = importRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found.")
            );

            importMapper.formUpdateImportDto(imports, updateImportDto);

            log.info("Staff UUID : {}", updateImportDto.staffUuid());
            log.info("Supplier UUID : {}", updateImportDto.supplierUuid());

            if (updateImportDto.staffUuid() != null){
                Staff newStaff = new Staff();
                newStaff.setUuid(updateImportDto.staffUuid());
                imports.setStaff(newStaff);
            }

            if (updateImportDto.supplierUuid() != null){
                Supplier newSupplier = new Supplier();
                newSupplier.setUuid(updateImportDto.supplierUuid());
                imports.setSupplier(newSupplier);
            }

            importRepository.save(imports);

            return importMapper.toImportDto(imports);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found.");
    }

    @Transactional
    @Override
    public void deleteImportByUuid(String uuid) {
        if (importRepository.existsByUuid(uuid)){
            importRepository.deleteImportByUuid(uuid);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found.");
    }

    @Override
    public ImportDto findByUuid(String uuid) {
        Import imports = importRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found.")
        );
        return importMapper.toImportDto(imports);
    }

    @Override
    public List<ImportDto> findAllByImportDate(LocalDate importDate) {
        List<Import> importDtoList = importRepository.findAllByImportDateAndIsDeletedIsFalse(importDate);
        return importMapper.toImportDtoList(importDtoList);
    }

    @Override
    public List<ImportDto> findAll() {
        List<Import> importList = importRepository.findAllByIsDeletedIsFalse();
        return importMapper.toImportDtoList(importList);
    }
}
