-- Create the Event table
CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    venue VARCHAR(255) NOT NULL,
    additional_information VARCHAR(5000)
);

-- Create the Company table
CREATE TABLE companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    legal_name VARCHAR(255) NOT NULL,
    registry_code VARCHAR(255) UNIQUE NOT NULL,
    number_of_participants INT NOT NULL,
    payment_method ENUM('BANK_TRANSFER', 'CASH') NOT NULL,
    additional_information VARCHAR(5000)
);

-- Create the Person table
CREATE TABLE persons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    id_code VARCHAR(255) UNIQUE NOT NULL,
    payment_method ENUM('BANK_TRANSFER', 'CASH') NOT NULL,
    additional_information VARCHAR(1500)
);

-- Create the join table for Event and Person
CREATE TABLE event_person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

-- Create the join table for Event and Company
CREATE TABLE event_company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    company_id BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);
