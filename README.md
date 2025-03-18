# Personal Loan Application : LoanEase

A **Personal Loan Application** that enables users to instantly apply for personal loans from prestigious loan-providing banks. The app provides real-time interest rate information based on the user's **CIBIL score** and **monthly income**, ensuring that users get personalized loan offers.

## Features

- **Loan Application**: Users can apply for personal loans directly through the app.
- **CIBIL Score Integration**: Based on the user-provided CIBIL score, the app calculates the **interest rate** for the loan.
- **Dynamic Interest Rates**: The app fetches real-time interest rates from partnered banks. These rates vary based on **CIBIL score** and **monthly income**.
- **Prestigious Loan Banks**: Partnered with top-tier banks for offering personal loans to users.
- **Loan Calculators**: Provides loan eligibility and repayment schedule based on different interest rates and amounts.
- **Secure Data Handling**: User data like CIBIL score, monthly income, and loan details are securely processed and stored.

## Tech Stack

- **Frontend**: (soon)
  - VueJS
  - Bootstrap
- **Backend**:
  - Spring Boot (Java)
  - RESTful API design
  - JWT Authentication for secure access
- **Database**:
  - PostgreSQL
- **External Integrations**:
  - Real-time CIBIL score check (integration with external service)
  - Loan interest rates API (integration with partnered banks)
- **Authentication**:
  - JWT Token-based Authentication
- **Deployment**: (soon)
  - Docker
  - AWS EC2 / Heroku for hosting

## How It Works

1. **User Registration**: Users first register and log in to the platform.
2. **CIBIL and Income Input**: After logging in, users input their **CIBIL score** and **monthly income**.
3. **Interest Rate Calculation**: Based on the **CIBIL score** and **monthly income**, the system fetches the dynamic interest rate from partnered banks.
4. **Loan Eligibility**: The app calculates whether the user is eligible for a loan based on their inputs. The eligibility criteria vary depending on the banks’ policies.
5. **Loan Application**: If eligible, users can apply for a personal loan, select the loan amount, and check the repayment schedule.
6. **Loan Processing**: After the application, the loan processing status is available to the user. Loan approval happens based on criteria set by the bank.

## Prerequisites

Before running the application locally, make sure you have:
either
- Docker (for containerization)
or
- Java 17 (for backend)
- Maven (for managing dependencies)
- PostgreSQL instance


## Getting Started

### 1. Clone the repository

Clone this repository to your local machine:

```bash
git clone https://github.com/Priyaksheemahanta/spring-boot-docker.git
```

### 2. Docker Setup (Optional)

To run the application in Docker, follow the steps below:

- **Build Docker Image**:

  ```bash
  docker build -t spring-boot-docker-app .
  ```



This will run both the backend and frontend in separate containers.

## API Endpoints

### 1. **POST /api/users/signup**
- **Description**: Register a new user.
- **Request**: 
  ```json
  {
    "email": "user@example.com",
    "password": "password123",
    "name": "John Doe"
  }
  ```
- **Response**:
  ```json
  {
    "message": "User registered successfully"
  }
  ```

### 2. **POST /api/users/login**
- **Description**: Login a user and get a JWT token.
- **Request**: 
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```
- **Response**:
  ```json
  {
    "token": "jwt_token_here"
  }
  ```

### 3. **POST /api/loan/calculate-interest**
- **Description**: Calculate loan interest based on CIBIL score and monthly income.
- **Request**:
  ```json
  {
    "cibilScore": 750,
    "monthlyIncome": 50000
  }
  ```
- **Response**:
  ```json
  {
    "loanAmount": 200000,
    "interestRate": 12.5
  }
  ```

### 4. **POST /api/loan/apply**
- **Description**: Apply for a loan.
- **Request**:
  ```json
  {
    "loanAmount": 200000,
    "interestRate": 12.5,
    "loanTerm": 36
  }
  ```
- **Response**:
  ```json
  {
    "message": "Loan application submitted successfully"
  }
  ```

## Future Improvements

- **Integrate with Actual CIBIL API**: Integrate the app with a real-time CIBIL score API for fetching actual scores.
- **Partnered Banks Integration**: Directly fetch loan interest rates from partnered banks via API.
- **Mobile Application**: Convert the app to a mobile application using React Native or Flutter.
- **Payment Gateway Integration**: Integrate payment gateway APIs for direct loan disbursement.

## Contributing

We welcome contributions! Please feel free to open issues or submit pull requests with improvements, bug fixes, or new features.

### How to Contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a new Pull Request.

---
