package com.adrian.guestregistration.service;

import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.repo.CompanyRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final EntityValidator entityValidator;

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepo.findById(id);
    }

    @Transactional
    public Company createCompany(Company company) {
        entityValidator.validateCompany(company);
        return companyRepo.save(company);
    }

    @Transactional
    public Company updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = companyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        entityValidator.validateCompany(updatedCompany);

        existingCompany.setLegalName(updatedCompany.getLegalName());
        existingCompany.setPaymentMethod(updatedCompany.getPaymentMethod());
        existingCompany.setRegistryCode(updatedCompany.getRegistryCode());
        existingCompany.setNumberOfParticipants(updatedCompany.getNumberOfParticipants());
        existingCompany.setAdditionalInformation(updatedCompany.getAdditionalInformation());

        return companyRepo.save(existingCompany);
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepo.deleteById(id);
    }
}
