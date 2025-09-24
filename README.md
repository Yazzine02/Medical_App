# Medical Office Management Application

A comprehensive desktop application designed to streamline operations in a medical office. This application provides functionality to manage patient records, schedule appointments, handle consultations, and manage billing directly from a user-friendly interface. Built with Java Spring Boot for the backend, a MySQL database for data persistence, and a responsive front end.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Usage](#usage)
6. [Database Structure](#database-structure)
7. [Entity Relationships](#entity-relationships)
8. [Screenshots](#screenshots)
9. [Technologies Used](#technologies-used)
10. [Future Enhancements](#future-enhancements)
11. [Contributors](#contributors)

---

## Project Overview

This application is designed to help medical offices manage daily operations, including patient tracking, appointment scheduling, billing, and consultation records. It provides an intuitive interface to enhance efficiency and reduce administrative overhead, ensuring that patient care is the primary focus.

---

## Features

- **Patient Management**: Add, view, update, and delete patient records with attributes like ID, CIN, name, birthdate, and credit balance.
- **Appointment Scheduling**: Schedule, view, and manage patient appointments.
- **Consultation Records**: Track consultations, including the ability to add notes and relevant patient data.
- **Waiting Room Management**: Dynamically manage the waiting room with patient statuses (WAITING, COMPLETED, CANCELLED).
- **Billing**: Manage billing for consultations and other services.
- **Dashboard**: A collapsible sidebar with quick links to different application sections.

---

## Installation

### Prerequisites

- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [Docker](https://www.docker.com/) (optional but recommended for database setup)
- [Spring Boot CLI](https://spring.io/projects/spring-boot)
- [Node.js & npm](https://nodejs.org/) (if front-end assets require additional setup)

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/medical-office-app.git
   cd medical-office-app
   ````
2. **Configure Database**
    ````
   docker-compose up -d
   ````
3. **Configure Application Properties Update**
   src/main/resources/application.properties with your MySQL database credentials:
    ````
    spring.datasource.url=jdbc:mysql://localhost:3306/medical_app
    spring.datasource.username=yourUsername
    spring.datasource.password=yourPassword
    spring.jpa.hibernate.ddl-auto=update
    ````
4. **Build and Run**
    ````
   ./mvnw spring-boot:run
   ````

---

## Configuration

Customize settings in `application.properties`, such as:
- Database configurations
- Logging levels
- Server port

---

## Usage

1. **Starting the Application**: Once the application is running, navigate to `http://localhost:8080` to access the dashboard.
2. **Using the Sidebar**:
    - **Waiting Room**: View and manage the list of patients currently waiting.
    - **Patients**: Manage patient records.
    - **Appointments**: Schedule and view appointments.
    - **Agenda**: Access a calendar view for managing daily appointments.
    - **Consultations**: Track consultation records and add notes.

3. **Adding Patients to the Waiting Room**: Use the "Add Patient to Waiting Room" button in the dashboard.

---

## Database Structure

Key tables and their fields:
- **Patient**: `id`, `cin`, `first_name`, `last_name`, `birth_date`, `credit`, `waiting_room_status`
- **Appointment**: `id`, `patient_id`, `appointment_date`, `status`
- **Consultation**: `id`, `appointment_id`, `notes`
- **Payment**: `id`, `amount`, `patient_id`, `appointment_id`, `status`

---

## Entity Relationships

### 1. Patient - Appointment (One-to-Many)
- Each patient can have multiple appointments.

### 2. Appointment - Consultation (One-to-One)
- Each appointment has a corresponding consultation.

### 3. Patient - Payment (Many-to-One)
- Payments are linked to both `Appointment` and `Patient`.

### 4. Waiting Room Status
- **Enum**: `WAITING`, `COMPLETED`, `CANCELLED`, `NOT_IN_WAITING_ROOM`

---

## Screenshots

### Agenda showing all weekly appointments:
[!alt text](https://github.com/Yazzine02/Medical_App/blob/main/screenshots/agenda.png "Agenda")
### Waiting room list showing current people in the waiting room:
[!alt text](https://github.com/Yazzine02/Medical_App/blob/main/screenshots/waiting-room.png "Waiting Room")
### List of All Patients:
[!alt text](https://github.com/Yazzine02/Medical_App/blob/main/screenshots/patients.png "Patients")

---

## Technologies Used

- **Backend**: Java Spring Boot, Spring Data JPA
- **Database**: MySQL (with Docker for deployment)
- **Frontend**: HTML, CSS, Bootstrap, Thymeleaf (for templating)
- **Icons**: Font Awesome
- **Other Tools**: Maven for project management

---

## Future Enhancements

- **Enhanced Reporting**: Add report generation for consultations, billing, and patient history.
- **Role-Based Access Control**: Differentiate features accessible to administrative and medical staff.
- **Email Notifications**: Reminders for upcoming appointments or bills due.
- **Statistics Dashboard**: Display insights on patient visits, popular appointment times, etc.
- **Integration with External APIs**: Integration for services like telemedicine or insurance claims processing.
