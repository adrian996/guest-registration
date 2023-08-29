-- Insert mock data into Event table
INSERT INTO events (date, venue, additional_information) VALUES
    ('2023-09-01', 'Conference Hall A', 'Annual Conference'),
    ('2023-10-15', 'Grand Hotel Ballroom', 'Product Launch'),
    ('2023-11-20', 'Community Center', 'Workshop on Innovation');

-- Insert mock data into Company table
INSERT INTO companies (legal_name, registry_code, number_of_participants, additional_information) VALUES
    ('ABC Corporation', '12345678', 50, 'Leading technology company'),
    ('XYZ Enterprises', '87654321', 30, 'Global manufacturing company'),
    ('QuickStart Solutions', '98765432', 20, 'Software development startup');

-- Insert mock data into Person table
INSERT INTO persons (first_name, last_name, id_code, payment_method, additional_information) VALUES
    ('John', 'Doe', '12345', 'BANK_TRANSFER', 'Regular attendee'),
    ('Jane', 'Smith', '67890', 'CASH', 'VIP guest'),
    ('Michael', 'Johnson', '54321', 'BANK_TRANSFER', 'Speaker'),
    ('Emily', 'Williams', '98765', 'BANK_TRANSFER', 'Guest from out of town'),
    ('David', 'Brown', '45678', 'CASH', 'First-time attendee'),
    ('Sarah', 'Davis', '87654', 'BANK_TRANSFER', 'Returning participant'),
    ('James', 'Wilson', '23456', 'CASH', 'Student'),
    ('Jennifer', 'Taylor', '76543', 'BANK_TRANSFER', 'Networking enthusiast'),
    ('Robert', 'Anderson', '34567', 'BANK_TRANSFER', 'Event sponsor'),
    ('Elizabeth', 'Miller', '65432', 'CASH', 'Early bird registration'),
    ('William', 'Martinez', '56789', 'BANK_TRANSFER', 'Tech enthusiast');



-- Insert mock data into Event_Participant table
-- Associations between events and companies
INSERT INTO event_participant (event_id, associated_id, associated_type) VALUES
    (1, 1, 'COMPANY'),
    (2, 2, 'COMPANY'),
    (3, 3, 'COMPANY');

-- Associations between events and persons
INSERT INTO event_participant (event_id, associated_id, associated_type) VALUES
    (1, 4, 'PERSON'),
    (2, 5, 'PERSON'),
    (3, 6, 'PERSON');

