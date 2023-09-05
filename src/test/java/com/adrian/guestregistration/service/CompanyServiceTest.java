package com.adrian.guestregistration.service;

import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.repo.CompanyRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepo companyRepo;

    @Mock
    private EntityValidator entityValidator;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllCompanies() {
        // Mock data
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        companies.add(company);

        when(companyRepo.findAll()).thenReturn(companies);

        List<Company> result = companyService.getAllCompanies();

        assertEquals(1, result.size());
    }

    @Test
    void shouldGetCompanyById() {
        // Mock data
        Company company = new Company();
        company.setId(1L);

        when(companyRepo.findById(1L)).thenReturn(Optional.of(company));

        Optional<Company> result = companyService.getCompanyById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void shouldCreateCompany() {
        // Mock data
        Company newCompany = new Company();
        when(companyRepo.save(newCompany)).thenReturn(newCompany);

        Company createdCompany = companyService.createCompany(newCompany);
        assertNotNull(createdCompany);
        verify(entityValidator).validateCompany(newCompany);
    }

    @Test
    void shouldUpdateCompany() {
        // Mock data
        Company existingCompany = new Company();
        existingCompany.setId(1L);
        when(companyRepo.findById(1L)).thenReturn(Optional.of(existingCompany));

        Company updatedCompany = new Company();
        updatedCompany.setLegalName("UpdatedLegalName");

        when(companyRepo.save(existingCompany)).thenReturn(updatedCompany);

        Company result = companyService.updateCompany(1L, updatedCompany);
        assertEquals("UpdatedLegalName", result.getLegalName());
        verify(entityValidator).validateCompany(updatedCompany);
    }

    @Test
    void shouldDeleteCompany() {
        companyService.deleteCompany(1L);
        verify(companyRepo).deleteById(1L);
    }
}
