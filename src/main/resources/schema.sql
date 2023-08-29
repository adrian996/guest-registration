-- Create the Event table
CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    venue VARCHAR(255) NOT NULL,
    additional_information VARCHAR(5000)
);

-- Create the Company table
CREATE TABLE companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    legal_name VARCHAR(255) NOT NULL,
    registry_code VARCHAR(255) NOT NULL,
    number_of_participants INT NOT NULL,
    additional_information VARCHAR(5000)
);

-- Create the Person table
CREATE TABLE persons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    id_code VARCHAR(255) NOT NULL,
    payment_method ENUM('BANK_TRANSFER', 'CASH') NOT NULL,
    additional_information VARCHAR(1500)
);

-- Create the generalized join table for Event and associated entities
CREATE TABLE event_participant (
    event_id BIGINT NOT NULL,
    associated_id BIGINT NOT NULL,
    associated_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (event_id, associated_id),
    FOREIGN KEY (event_id) REFERENCES events(id),
    CHECK (associated_type IN ('COMPANY', 'PERSON'))
);
