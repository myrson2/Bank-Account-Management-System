# 🏦 Bank Account Management System

## 📘 Pure Object-Oriented Design Specification

This document defines the **complete system design** of the Bank Account Management System implemented using **Core Java OOP concepts only**, with **no database, JDBC, or external persistence**. The focus is on **object modeling, business rules, polymorphism, and defensive programming**.

This version is intentionally designed as a **clean OOP foundation** that can later be extended with persistence (JDBC / JPA) without refactoring the core domain.

---

## 🎯 System Goals

* Master Object-Oriented Programming fundamentals
* Model real-world banking behavior accurately
* Prevent illegal object states
* Use clean separation of responsibilities
* Emphasize logic and design over infrastructure

---

## 🧱 High-Level Architecture (OOP Only)

```
CLI Layer (User Interaction)
        ↓
Service Layer (Business Rules)
        ↓
Domain Model (Pure Objects)
```

⚠️ No database, no files, no frameworks

---

## 🧠 Package Responsibilities

### 1️⃣ app

**Purpose:** System entry point

* Starts the application
* Initializes core services
* Launches CLI

**Key Class:**

* `BankApplication`

---

### 2️⃣ cli (Controller Layer)

**Purpose:** User interaction only

**Responsibilities:**

* Display menus
* Read input from user
* Call service layer methods
* Catch and display exceptions

**Key Class:**

* `BankCLI`

**Functions Provided:**

* Create user
* Open account
* Deposit
* Withdraw
* Transfer
* View account details
* View transaction history

---

### 3️⃣ model (Domain Layer)

This package contains **pure business objects** with no external dependencies.

---

#### User

**Description:** Represents a bank customer

**Fields:**

* `id : long`
* `fullName : String`
* `email : String`

**Rules:**

* Email must be unique within the system

---

#### Account (Abstract Class)

**Description:** Base abstraction for all account types

**Fields:**

* `accountNumber : String`
* `owner : User`
* `balance : BigDecimal`
* `transactions : List<Transaction>`

**Core Behaviors:**

* `deposit(amount)`
* `withdraw(amount)`
* `getBalance()`

**Invariants:**

* Balance must never be invalid for the account type
* All balance changes must create a transaction

---

#### SavingsAccount

**Description:** Standard savings account

**Additional Fields:**

* `interestRate : double`

**Rules:**

* Withdrawals cannot exceed balance
* Interest calculation is supported

---

#### CurrentAccount

**Description:** Account with overdraft support

**Additional Fields:**

* `overdraftLimit : BigDecimal`

**Rules:**

* Balance may go negative up to overdraft limit
* Withdrawals beyond limit are forbidden

---

#### Transaction

**Description:** Immutable record of a balance change

**Fields:**

* `type : TransactionType`
* `amount : BigDecimal`
* `timestamp : LocalDateTime`
* `description : String`

**Rules:**

* Transactions cannot be modified after creation

---

#### TransactionType (Enum)

```
DEPOSIT
WITHDRAWAL
TRANSFER
```

---

### 4️⃣ service (Business Logic Layer)

This layer coordinates objects and enforces business rules.

---

#### AccountService

**Responsibilities:**

* Create users
* Open accounts
* Handle deposits and withdrawals
* Validate all operations

**Key Methods:**

* `createUser()`
* `openSavingsAccount()`
* `openCurrentAccount()`
* `deposit()`
* `withdraw()`

---

#### TransferService

**Responsibilities:**

* Transfer funds between accounts
* Ensure atomicity at object level

**Transfer Logic:**

1. Validate both accounts
2. Withdraw from source
3. Deposit to destination
4. Roll back manually if failure occurs

---

#### TransactionService

**Responsibilities:**

* Retrieve transaction history
* Filter transactions using Java Streams

**Examples:**

* Transactions by date range
* Transactions by type

---

### 5️⃣ exception

**Purpose:** Prevent illegal system states

| Exception                  | When Thrown                      |
| -------------------------- | -------------------------------- |
| InsufficientFundsException | Withdrawal exceeds allowed funds |
| InvalidAccountException    | Account does not exist           |
| IllegalOperationException  | Invalid business action          |

---

### 6️⃣ util

#### InputValidator

**Responsibilities:**

* Validate monetary values
* Validate text input
* Centralize validation logic

---

## 🔄 Core Functionalities

### 👤 User Creation

* Validate unique email
* Instantiate `User` object

---

### 🏦 Account Creation

* Generate unique account number
* Assign owner
* Enforce valid initial balance

---

### 💰 Deposit

* Amount must be positive
* Balance updated
* Transaction recorded

---

### 💸 Withdrawal

* Enforce account-specific rules
* Throw exception on failure
* Transaction recorded

---

### 🔁 Transfer

* Withdraw from source
* Deposit to destination
* Create two transaction records
* Maintain consistency

---

### 📊 Analytics (Streams API)

* Filter transactions by date
* Filter transactions by type
* Calculate total deposits / withdrawals

---
# 📁 Project File Structure

```
com.bank
├── app
│   └── BankApplication.java
│
├── cli
│   └── BankCLI.java
│
├── model
│   ├── user
│   │   └── User.java
│   │
│   ├── account
│   │   ├── Account.java
│   │   ├── SavingsAccount.java
│   │   └── CurrentAccount.java
│   │
│   └── transaction
│       ├── Transaction.java
│       └── TransactionType.java
│
├── service
│   ├── AccountService.java
│   ├── TransferService.java
│   └── TransactionService.java
│
├── exception
│   ├── InsufficientFundsException.java
│   ├── InvalidAccountException.java
│   └── IllegalOperationException.java
│
└── util
    └── InputValidator.java
```

---

## 🛡️ OOP Principles Enforced

* **Encapsulation:** Private fields, controlled methods
* **Abstraction:** Abstract account behavior
* **Polymorphism:** Same operations, different rules
* **Composition:** Account owns transactions
* **Single Responsibility:** Each class has one reason to change

---

## ✅ Completion Criteria

The system is complete when:

* All business rules are enforced
* No illegal state is possible
* Code is readable and extensible
* Behavior matches real banking logic

---

## 🚀 Future Extension Path

This design allows easy extension to:

* JDBC persistence
* Spring Boot REST APIs
* JPA/Hibernate

**No changes required in the domain layer.**

---

**This document represents a clean, professional OOP-only system design.**
