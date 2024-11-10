INSERT INTO patient (id, cin, first_name, last_name, birth_date, credit, waiting_room_status, waiting_room_date)
VALUES
    (1, 'AB12345', 'John', 'Doe', '1990-06-15', 100.00, 'WAITING', CURDATE()),
    (2, 'CD67890', 'Jane', 'Smith', '1985-12-01', 200.50, 'COMPLETED', CURDATE()),
    (3, 'EF23456', 'Alice', 'Johnson', '1992-03-27', 300.00, 'CANCELLED', CURDATE()),
    (4, 'GH34567', 'Bob', 'Brown', '1978-04-13', 150.75, 'WAITING', CURDATE()),
    (5, 'IJ45678', 'Sara', 'Davis', '2000-11-09', 250.00, 'NOT_IN_WAITING_ROOM', NULL),
    (6, 'KL56789', 'Tom', 'Wilson', '1995-02-20', 120.00, 'WAITING', CURDATE()),
    (7, 'MN67890', 'Emma', 'White', '1988-08-16', 175.50, 'COMPLETED', CURDATE()),
    (8, 'OP78901', 'Liam', 'Black', '1983-10-30', 225.00, 'WAITING', CURDATE()),
    (9, 'QR89012', 'Olivia', 'Green', '1999-07-25', 90.00, 'CANCELLED', CURDATE());
