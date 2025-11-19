# Academic Manager CLI 🧑‍🎓

[![Made with Java](https://img.shields.io/badge/Java-25%2B-blue.svg)](https://www.java.com/)
[![Built with CLI](https://img.shields.io/badge/Interface-CLI-yellowgreen)](https://en.wikipedia.org/wiki/Command-line_interface)

## 💡 Project Description

Command-Line Interface (CLI) application developed in Java to simulates an **Academic Management System**. The project focuses on practicing **Object-Oriented Programming (OOP)** and efficient data manipulation using the **Collections Framework** and the **Stream API**.

---

## 🚀 Key Features

The system offers a complete menu with 10 operations, organized into three categories:

### 1. Record Management (CRUD)
* ➕ **Add Student**
* ➕ **Add Course**
* 🗑️ **Delete Student:** Complete and secure removal from the system.
* 🗑️ **Delete Course:** Complete and secure removal from the system.

### 2. Enrollments
* ✅ **Enroll Student in Course**
* ❌ **Remove Student from Course**

### 3. Reports and Queries
* 📄 **View Student's Courses**
* 📄 **View Course's Students**
* 📄 **List All Students**
* 📄 **List All Courses**

---

## 🛠️ Design and Technical Robustness

The project was built focusing on **data integrity** and **clean code**, applying the following concepts:

### Layered Architecture (Layered Design)
* **Separation of Concerns:** The code is divided into packages (`entities`, `services`, `application`), isolating Business Logic (Service) from the User Interface (Application) and Data Modeling (Entities).
* **Robust Entities:** Use of **`equals()`** and **`hashCode()`** based on the ID to ensure correct object identification within collections.

### Integrity and Validation
* **Custom Exceptions:** Throwing domain-specific exceptions (`BlankNameException`, `NonExistentIdException`, `EnrollmentException`) to handle business errors.
* **Validation in the Service Layer:** Business rules, such as ID validation and prevention of duplicate enrollments, are strictly enforced in the **`AcademicService`**.
* **Referential Integrity:** Deleting a Student or Course ensures that all related enrollments are automatically removed from the system.

### Data Structure
* **Relationship Many-to-Many:** The relationship between **`Student`** and **`Course`** is efficiently modeled using the structure: **`Map<Student, Set<Course>>`**. This ensures:
    * **Fast lookup** by student.
    * **Unique Enrollment** (guaranteed by the **`Set<Course>`**).

---

## 💻 Technologies Used 

* **Java 25**
* **IntelliJ IDEA**
* **Streams API**
* **Collection Framework**
* **Git & GitHub** for version control.

---

## 🧠 Concepts Practiced

* **Object-Oriented Programming (OOP):** Applied fundamental concepts like Encapsulation and Entity Modeling.
* **Layered Architecture:** Clear Separation of Concerns between the UI, Service, and Data layers.
* **Java Collections Framework:** Effective use of Maps and Sets for efficient data storage and relationship modeling (M:N).
* **Java Stream API:** Utilizing Streams for declarative data querying (filtering and reporting).
* **Data Integrity & Validation:** Implementation of Custom Exceptions to enforce business rules.

---

## 🏁 How to Run
1.  Open your **Terminal** (or `Git Bash` on Windows).
2.  Navigate to the directory (folder) where you want to save the project.
3.  **Copy and paste** the following command into your terminal and press **Enter**:

    ```bash
    git clone https://github.com/MatheusLeiteCarneiro/academic-manager-cli
    ```
    *(This will create a new folder named `academic-manager-cli` with all the project files.)*

4. Open the project in your Java IDE.
5. Locate and run the `Program.java` file (under `src/application/Program.java`).

---

Author: **Matheus Leite Carneiro**