INSERT INTO patient (id, cin, first_name, last_name, birth_date, credit, waiting_room_status, waiting_room_date)
VALUES
    (1, 'AB12345', 'John', 'Doe', '1990-06-15', 100.00, 'WAITING', CURDATE()),
    (2, 'CD67890', 'Jane', 'Smith', '1985-12-01', 200.50, 'COMPLETED', CURDATE()),
    (3, 'EF23456', 'Alice', 'Johnson', '1992-03-27', 300.00, 'CANCELLED', CURDATE()),
    (4, 'GH34567', 'Bob', 'Brown', '1978-04-13', 150.75, 'WAITING', CURDATE()),
    (5, 'IJ45678', 'Sara', 'Davis', '2000-11-09', 250.00, 'NOT_IN_WAITING_ROOM', CURDATE()),
    (6, 'KL56789', 'Tom', 'Wilson', '1995-02-20', 120.00, 'WAITING', CURDATE()),
    (7, 'MN67890', 'Emma', 'White', '1988-08-16', 175.50, 'COMPLETED', CURDATE()),
    (8, 'OP78901', 'Liam', 'Black', '1983-10-30', 225.00, 'WAITING', CURDATE()),
    (9, 'QR89012', 'Olivia', 'Green', '1999-07-25', 90.00, 'CANCELLED', CURDATE());

INSERT INTO appointment (id, patient_id, appointment_date_time, description)
VALUES
    (1, 1, '2024-11-15 09:00:00', 'General consultation'),
    (2, 2, '2024-11-16 10:30:00', 'Routine check-up'),
    (3, 3, '2024-11-17 14:00:00', 'Follow-up visit'),
    (4, 4, '2024-11-18 11:00:00', 'Specialist consultation'),
    (5, 5, '2024-11-19 15:30:00', 'Physical therapy'),
    (6, 6, '2024-11-20 08:30:00', 'General consultation'),
    (7, 7, '2024-11-21 12:45:00', 'Routine check-up'),
    (8, 8, '2024-11-22 10:15:00', 'Follow-up visit'),
    (9, 9, '2024-11-23 16:00:00', 'Emergency visit');

CREATE TABLE patient (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     first_name VARCHAR(50) NOT NULL,
    ->     last_name VARCHAR(50) NOT NULL,
    ->     birth_date DATE,
    ->     credit DOUBLE,
    ->     cin VARCHAR(7) UNIQUE NOT NULL,
    ->     waiting_room_date DATETIME,
    ->     waiting_room_status ENUM('WAITING', 'COMPLETED', 'CANCELLED', 'NOT_IN_WAITING_ROOM')
    -> );

CREATE TABLE appointment (
    ->         id INT AUTO_INCREMENT PRIMARY KEY,
    ->         appointment_date_time DATETIME UNIQUE NOT NULL,
    ->         description VARCHAR(255),
    ->         patient_id INT,
    ->         FOREIGN KEY (patient_id) REFERENCES patient(id)
    ->         );

CREATE TABLE payment (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     total DOUBLE NOT NULL,
    ->     amount_paid DOUBLE NOT NULL,
    ->     payment_date DATETIME NOT NULL,
    ->     appointment_id INT,
    ->     patient_id INT,  -- Foreign key for Patient
    ->     FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE CASCADE,
    ->     FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
    -> );

create table consultation(id int auto_increment primary key);