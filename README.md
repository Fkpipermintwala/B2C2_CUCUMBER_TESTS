# B2C2 Website Automation Tests

This project contains automated UI tests for validating key navigation functionalities on the [B2C2](https://www.b2c2.com/) public website using **Java**, **Selenium WebDriver**, and **Cucumber**.

---

## Technologies Used

- **Java** (JDK 17+)
- **Selenium WebDriver** (v4.20.0)
- **Cucumber BDD** (v7.14.0)
- **JUnit** (v4.13.2)
- **Maven**
- **IntelliJ IDEA** (or any Java IDE)

---

## Test Scenarios

The test suite validates **two key functionalities** that reflect core navigation behaviors on the B2C2 website.

### Scenario 1: Navigate to "About Us" Page

**Objective:**  
Ensure the user can access the "About Us" page from the main navigation menu.

**Steps:**
- Open homepage
- Hover on "About"
- Click "About Us"
- Assert text presence & correct URL

---

### Scenario 2: Return to Homepage via Logo

**Objective:**  
Validate that clicking the B2C2 logo redirects users back to the homepage.

**Steps:**
- Open the "About Us" page
- Click the B2C2 logo (desktop or mobile version)
- Assert redirection to homepage

---

## Project Structure


---

## How to Build & Run

### Prerequisites:
- Java JDK (17 or later)
- Maven installed
- Chrome browser installed
- IntelliJ IDEA

### Steps to Run:
1. Clone the repo or download the code
2. Open the project in IntelliJ
3. Run the file:  
   `src/test/java/runners/CucumberTestRunner.java`

Or via terminal:
```bash
mvn test

