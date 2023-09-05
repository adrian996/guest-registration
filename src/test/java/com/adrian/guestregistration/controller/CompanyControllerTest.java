package com.adrian.guestregistration.controller;

import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        CompanyController companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllCompanies() throws Exception {
        List<Company> companies = new ArrayList<>();
        when(companyService.getAllCompanies()).thenReturn(companies);

        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getCompanyById() throws Exception {
        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(company));

        mockMvc.perform(get("/api/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(companyId));
    }

    @Test
    void createCompany() throws Exception {
        Company company = new Company();
        when(companyService.createCompany(any(Company.class))).thenReturn(company);

        mockMvc.perform(post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateCompany() throws Exception {
        Long companyId = 1L;
        Company updatedCompany = new Company();
        updatedCompany.setId(companyId);
        when(companyService.updateCompany(eq(companyId), any(Company.class))).thenReturn(updatedCompany);

        mockMvc.perform(put("/api/companies/{id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCompany)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(companyId));
    }

    @Test
    void deleteCompany() throws Exception {
        Long companyId = 1L;
        doNothing().when(companyService).deleteCompany(companyId);

        mockMvc.perform(delete("/api/companies/{id}", companyId))
                .andExpect(status().isNoContent());
    }
}
