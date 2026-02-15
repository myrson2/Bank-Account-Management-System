Here’s a cleaner, more readable version of your specification formatted as a polished Markdown document.

---

# 🏦 Bank Account Management System

## 📘 Pure Object-Oriented Design Specification

This document defines the **complete design blueprint** of the **Bank Account Management System**, implemented using **Core Java Object-Oriented Programming (OOP) principles only**.

> ⚠️ **Scope Constraint:**
> No database, JDBC, file storage, frameworks, or external persistence mechanisms.
> The focus is strictly on **object modeling, business rules, polymorphism, and defensive programming**.

This system is intentionally designed as a **pure OOP foundation**, allowing future integration of persistence technologies **without refactoring the domain model**.

---

## 🎯 System Objectives

✔ Master Java OOP fundamentals
✔ Model realistic banking behaviors
✔ Enforce valid object states
✔ Maintain clean separation of concerns
✔ Prioritize design & logic over infrastructure

---

## 🧱 High-Level Architecture

```
CLI Layer (User Interaction)
        ↓
Service Layer (Business Logic)
        ↓
Domain Model (Pure Objects)
```

**Key Principle:**
Each layer has a **single responsibility** and does not leak concerns into others.

---

## 📦 Package Responsibilities

---

### 1️⃣ `app` – Application Entry Point

**Purpose:** Bootstraps the system

**Responsibilities:**

* Start the application
* Initialize services
* Launch CLI interface

**Key Class:**

* `BankApplication`

---

### 2️⃣ `cli` – User Interaction Layer

**Purpose:** Handles input/output only

**Responsibilities:**

* Display menus
* Read user input
* Invoke service methods
* Catch & display exceptions

**Key Class:**

* `BankCLI`

**Supported Actions:**

* Create user
* Open account
* Deposit funds
* Withdraw funds
* Transfer funds
* View account details
* View transaction history

---

### 3️⃣ `model` – Domain Layer (Core Business Objects)

Contains **pure OOP classes** with **no infrastructure dependencies**.

---

#### 👤 `User`

**Description:** Represents a bank customer

**Fields:**

* `id : long`
* `fullName : String`
* `email : String`

**Rules:**

* Email must be unique

---

#### 🏦 `Account` (Abstract Base Class)

**Description:** Base abstraction for all accounts

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

* Balance must always remain valid
* Every balance change creates a transaction

---

#### 💰 `SavingsAccount`

**Additional Field:**

* `interestRate : double`

**Rules:**

* Cannot withdraw beyond available balance
* Supports interest calculation

---

#### 💳 `CurrentAccount`

**Additional Field:**

* `overdraftLimit : BigDecimal`

**Rules:**

* Balance may go negative
* Cannot exceed overdraft limit

---

#### 🧾 `Transaction`

**Description:** Immutable balance record

**Fields:**

* `type : TransactionType`
* `amount : BigDecimal`
* `timestamp : LocalDateTime`
* `description : String`

**Rules:**

* Cannot be modified after creation

---

#### 🔖 `TransactionType` (Enum)

```
DEPOSIT
WITHDRAWAL
TRANSFER
```

---

### 4️⃣ `service` – Business Logic Layer

Coordinates domain objects and enforces rules.

---

#### ⚙️ `AccountService`

**Responsibilities:**

* Create users
* Open accounts
* Handle deposits & withdrawals
* Validate business operations

**Core Fields:**

* `List<User> users`
* `List<Account> accounts`

**Key Methods:**

* `createUser()`
* `openSavingsAccount()`
* `openCurrentAccount()`
* `deposit()`
* `withdraw()`
* `findAccountByNumber()`

---

#### 🔁 `TransferService`

**Responsibilities:**

* Transfer funds between accounts
* Maintain logical consistency

**Transfer Workflow:**

1. Validate accounts
2. Withdraw from source
3. Deposit to destination
4. Rollback manually if failure occurs

**Key Methods:**

* `transfer(source, target, amount)`

---

#### 📊 `TransactionService`

**Responsibilities:**

* Retrieve transaction history
* Apply filters via Streams API

**Example Methods:**

* `getTransactionsByType()`
* `getTransactionsByDateRange()`
* `calculateTotalDeposits()`

---

### 5️⃣ `exception` – Domain Safety Layer

Prevents illegal system states.

| Exception Name                 | Trigger Condition                                                  | TODOs / Requirements                                                                                                              |
| ------------------------------ | ------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------- |
| **InsufficientFundsException** | Attempt to withdraw or transfer more funds than allowed in account | - Throw when withdrawal/transfer exceeds available balance<br>- Must carry a descriptive message<br>- Must be a checked exception |
| **InvalidAccountException**    | Account number does not exist or cannot be found                   | - Throw when any account lookup fails<br>- Must carry a descriptive message<br>- Must be a checked exception                      |
| **IllegalOperationException**  | Any business rule violation (e.g., negative deposit)               | - Throw when a business rule is violated<br>- Must carry a descriptive message<br>- Must be a checked exception                   |


---

### 6️⃣ `util` – Utility Layer

---

#### ✅ `InputValidator`

**Responsibilities:**

* Validate monetary inputs
* Validate text inputs
* Centralize reusable validations

---

## 🔄 Core Functionalities

---

### 👤 User Creation

✔ Validate unique email
✔ Instantiate User

---

### 🏦 Account Creation

✔ Generate unique account number
✔ Assign owner
✔ Enforce valid initial balance

---

### 💰 Deposit

✔ Amount must be positive
✔ Update balance
✔ Record transaction

---

### 💸 Withdrawal

✔ Enforce account rules
✔ Throw exception if invalid
✔ Record transaction

---

### 🔁 Transfer

✔ Withdraw from source
✔ Deposit to target
✔ Record dual transactions
✔ Maintain consistency

---

### 📊 Analytics (Streams API)

✔ Filter by date
✔ Filter by type
✔ Aggregate totals

---

## 📁 Project Structure

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

## 🛡️ Enforced OOP Principles

✔ **Encapsulation** – Private fields & controlled mutations
✔ **Abstraction** – Common account behavior
✔ **Polymorphism** – Different rules, same interface
✔ **Composition** – Accounts own transactions
✔ **Single Responsibility** – One reason to change per class

---

## ✅ Completion Criteria

The system is considered complete when:

✔ All business rules are enforced
✔ No illegal state is possible
✔ Code is readable & extensible
✔ Behavior reflects real banking logic

---

## 🚀 Future Extension Path

This architecture supports seamless upgrades:

* JDBC Persistence
* Spring Boot REST API
* JPA / Hibernate

> ✅ **No domain model refactoring required**

---

**This document represents a clean, extensible, OOP-only system design.**

---