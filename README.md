# Razorpay Backend Clone

> 🚧 **Work in Progress**: This project is actively under development. The current focus is on implementing business logic and transactional workflows at the **Service Layer**.

A scalable payment gateway backend clone inspired by Razorpay, built with **Java 17**, **Spring Boot**, and **PostgreSQL**.

---

## 📌 Project Overview

This repository houses the core backend services simulating an online payment gateway ecosystem. The project models real-world transactional workflows, including merchant authentication, order generation, payment processing, and transaction status verification.

---

## 🚦 Current Development Status

The foundational data models and database access layers are complete, and active development is centered on the **Service Layer**:

- [x] **Entity & Database Schema Design**: Completed domain entities (`Merchant`, `Payment`, `Vault`) and relationships.
- [x] **Repository Layer**: Set up Spring Data JPA repositories with custom query methods.
- [🔄] **Service Layer (In Progress)**:
  - Implementing merchant sign up and app user.
  - Implementing order creation logic with idempotency checks.
  - Building the payment processing and transaction state transition engine.
  - Adding signature verification and validation rules.
- [ ] **Controller Layer & REST APIs**: Pending completion of service-layer unit testing.
- [ ] **Webhook Handling**: Mock signature verification and event publishing to be implemented.
- [ ] **Integration Testing**: End-to-end API tests using MockMvc and Testcontainers.

---

## 🛠️ Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Security)
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Security**: JWT & Role-Based Access Control (RBAC)
- **Testing**: JUnit 5, Mockito

---

## 🏗️ Project Architecture

```text
src/main/java/com/razorpay/clone/
├── config/             # Security and application configurations
├── entity/             # JPA entities (Order, Payment, Merchant, etc.)
├── repository/         # Spring Data JPA repositories
├── service/            # [CURRENT FOCUS] Core business logic & payment orchestration
│   ├── impl/           # Service implementations
│   ├── OrderService.java
│   └── PaymentService.java
├── controller/         # REST API endpoints (Upcoming)
├── dto/                # Request & Response Data Transfer Objects
└── exception/          # Custom exceptions and global handlers
