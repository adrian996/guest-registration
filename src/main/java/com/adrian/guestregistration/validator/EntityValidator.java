package com.adrian.guestregistration.validator;

import lombok.RequiredArgsConstructor;
import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.model.Person;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityValidator {

    public void validatePerson(Person person) {
        //TODO validation logic for person entity
        System.out.println("Person " + person.getFirstName() + "validated");
    }

    public void validateCompany(Company company) {
        //TODO validation logic for company entity
        System.out.println("Company " + company.getLegalName() + " validated");
    }
}
