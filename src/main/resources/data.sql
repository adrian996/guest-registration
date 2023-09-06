INSERT INTO events (name, date, venue, additional_information) VALUES
    ('Conference 2023', '2023-09-01', 'Conference Hall A', 'Annual Conference'),
    ('iPhone 15 Launch event', '2023-10-15', 'Grand Hotel Ballroom', 'Product Launch'),
    ('Sales workshop', '2023-11-20', 'Community Center', 'Workshop on Innovation');

INSERT INTO companies (legal_name, registry_code, number_of_participants, payment_method, additional_information) VALUES
    ('ABC Corporation', '12345678', 50, 'BANK_TRANSFER', 'Leading technology company'),
    ('XYZ Enterprises', '87654321', 30, 'BANK_TRANSFER', 'Global manufacturing company'),
    ('QuickStart Solutions', '98765432', 20, 'BANK_TRANSFER', 'Software development startup');

INSERT INTO persons (first_name, last_name, id_code, payment_method, additional_information) VALUES
    ('John', 'Doe', '39602020259', 'BANK_TRANSFER', 'Regular attendee'),
    ('Jane', 'Smith', '47801304913', 'CASH', 'VIP guest'),
    ('Michael', 'Johnson', '46801300150', 'BANK_TRANSFER', 'Speaker'),
    ('Emily', 'Williams', '44310264916', 'BANK_TRANSFER', 'Guest from out of town'),
    ('Michael', 'Johnson', '39606052784', 'BANK_TRANSFER', 'Speaker'),
    ('Emily', 'Williams', '47901304916', 'BANK_TRANSFER', 'Guest from out of town');

INSERT INTO event_company (event_id, company_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO event_person (event_id, person_id) VALUES
    (1, 4),
    (2, 5),
    (3, 6);


