INSERT INTO patient (id, cin, first_name, last_name, birth_date, credit, waiting_room_date, waiting_room_status) VALUES
(1, 'A123456', 'John', 'Doe', '1990-05-14', 200.0, '2024-11-26', 'WAITING'),
(2, 'B654321', 'Jane', 'Smith', '1985-07-20', 50.0, '2024-11-26', 'COMPLETED'),
(3, 'C789123', 'Alice', 'Brown', '1993-03-10', 120.0, '2024-11-26', 'CANCELLED'),
(4, 'D456789', 'Bob', 'Johnson', '1988-09-12', 0.0, '2024-11-26', 'WAITING'),
(5, 'E147258', 'Emma', 'Williams', '1995-01-25', 300.0, NULL, 'NOT_IN_WAITING_ROOM'),
(6, 'F369852', 'Liam', 'Davis', '1992-02-18', 400.0, '2024-11-26', 'COMPLETED'),
(7, 'G741852', 'Olivia', 'Miller', '1997-12-06', 250.0, '2024-11-26', 'WAITING'),
(8, 'H963852', 'Noah', 'Wilson', '1991-08-23', 0.0, '2024-11-26', 'CANCELLED'),
(9, 'I123789', 'Sophia', 'Anderson', '1996-11-15', 100.0, NULL, 'NOT_IN_WAITING_ROOM');

INSERT INTO appointment (id, appointment_date_time, description, patient_id) VALUES
(1, '2024-11-26 10:00:00', 'Routine Checkup', 1),
(2, '2024-11-26 11:30:00', 'Dental Cleaning', 2),
(3, '2024-11-26 09:00:00', 'Vision Test', 3),
(4, '2024-11-26 14:00:00', 'Annual Checkup', 4),
(5, '2024-11-27 08:00:00', 'Follow-up Visit', 5),
(6, '2024-11-26 10:30:00', 'Vaccination', 6),
(7, '2024-11-26 15:00:00', 'Consultation', 7),
(8, '2024-11-26 16:00:00', 'Orthopedic Checkup', 8),
(9, '2024-11-28 12:00:00', 'ENT Consultation', 9);

INSERT INTO consultation (id, consultation_date, price, left_to_pay, total_paid, status, patient_id) VALUES
(1, '2024-11-26 12:00:00', 150.0, 50.0, 100.0, 'NOT_PAID', 2),
(2, '2024-11-26 11:00:00', 90.0, 0.0, 90.0, 'PAID', 6);

INSERT INTO payment (id, amount, payment_date, patient_id, consultation_id) VALUES
(1, 100.0, '2024-11-26', 2, 1),
(2, 90.0, '2024-11-26', 6, 2);

INSERT INTO prescription (id, date, prescription_text, consultation_id, patient_id) VALUES
(1, '2024-11-26', 'Medication C: 1 capsule every evening\nMedication D: Apply once daily', 1, 2),
(2, '2024-11-26', 'Medication H: Apply on skin once a week', 2, 6);