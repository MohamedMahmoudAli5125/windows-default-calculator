<div align="center">

# 🧮 Advanced Calculator Application


<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/angular/angular-original.svg" width="60"/>
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="60"/>
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="60"/>
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/typescript/typescript-original.svg" width="60"/>

### *A Windows Calculator replica in Standard Mode - built with modern web technologies*

[Features](#-features) • [Architecture](#-architecture) • [Installation](#-installation) • [Usage](#-usage) • [API Documentation](#-api-documentation)

---

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Core Components](#-core-components)
- [Installation](#-installation)
- [Usage](#-usage)
- [API Documentation](#-api-documentation)
- [State Management](#-state-management)
- [Error Handling](#-error-handling)

---

## 🎯 Overview

This is a **full-stack Windows Calculator replica** that faithfully implements the Standard Mode calculator with advanced mathematical operations and a sophisticated state management system. The application follows **object-oriented design principles** and maintains calculation state through a robust backend API, mimicking the exact behavior and logic of the Windows 10/11 Calculator in Standard Mode.

### Key Highlights

- 🎨 **Windows Calculator UI/UX** - Faithful replica of Standard Mode
- 🔧 **RESTful API** powered by Spring Boot
- 📊 **State-Driven Architecture** - Matches Windows Calculator behavior exactly
- 🛡️ **Comprehensive Error Handling** with user-friendly messages
- 🔢 **16-Digit Precision** with intelligent formatting
- ⚡ **Real-time Calculation** processing
- 🪟 **100% Windows Calculator Logic** - Same operations, same results

---

## ✨ Features

### 🪟 Windows Calculator Standard Mode Features

This application replicates all Standard Mode features of the Windows Calculator:

### 🔢 Basic Operations
- ➕ **Addition** - Standard arithmetic addition
- ➖ **Subtraction** - Standard arithmetic subtraction
- ✖️ **Multiplication** - Standard arithmetic multiplication
- ➗ **Division** - Division with zero-division protection

### 🧪 Advanced Functions
- 📐 **Square Root (√)** - Calculate square roots with negative number protection
- 🔄 **Reciprocal (1/x)** - Calculate multiplicative inverse
- ² **Square (x²)** - Quick squaring operation

### 💯 Percentage Calculations
Intelligent percentage handling based on operation context (matches Windows Calculator logic):
- `a + b%` → `a + (a × b / 100)`
- `a - b%` → `a - (a × b / 100)`
- `a × b%` → `a × (b / 100)`
- `a ÷ b%` → `a ÷ (b / 100)`
- Post-result percentages with operation-specific logic (Windows behavior)

### 🎛️ Control Features
- **Clear (C)** - Reset calculator to initial state
- **Clear Entry (CE)** - Clear current entry
- **Backspace** - Smart digit/operator removal
- **Decimal Point** - Single decimal point per number
- **16-Digit Input Limit** - Prevents overflow

### 🖥️ Display Features
- **Dual Display System** (Just like Windows Calculator):
  - Display 1: Shows full expression (`200 + 10% =`)
  - Display 2: Shows current number or result
- **Thousands Separator** - Comma-separated formatting (#,###)
- **Decimal Precision** - Up to 10 decimal places
- **Active Input Indication** - Shows trailing decimal point (5.)
- **Windows-style Number Formatting** - Matches Windows Calculator exactly

---

## 🛠️ Tech Stack

### Backend

| Technology | Purpose | Version |
|------------|---------|---------|
| ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white) | REST API Framework | 2.x+ |
| ![Java](https://img.shields.io/badge/-Java-ED8B00?logo=openjdk&logoColor=white) | Core Language | 11+ |
| ![Maven](https://img.shields.io/badge/-Maven-C71A36?logo=apache-maven&logoColor=white) | Dependency Management | 3.x |

### Frontend

| Technology | Purpose | Version |
|------------|---------|---------|
| ![Angular](https://img.shields.io/badge/-Angular-DD0031?logo=angular&logoColor=white) | UI Framework | 12+ |
| ![TypeScript](https://img.shields.io/badge/-TypeScript-007ACC?logo=typescript&logoColor=white) | Primary Language | 4.x+ |
| ![HTML5](https://img.shields.io/badge/-HTML5-E34F26?logo=html5&logoColor=white) | Markup | 5 |
| ![CSS3](https://img.shields.io/badge/-CSS3-1572B6?logo=css3&logoColor=white) | Styling | 3 |

---

## 🏗️ Architecture


```
┌─────────────────────────────────────────────────────────┐
│                    Angular Frontend                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │  Components  │  │   Services   │  │    Models    │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
                           │
                    HTTP REST API
                           │
┌─────────────────────────────────────────────────────────┐
│                  Spring Boot Backend                     │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ Controllers  │  │   Services   │  │    Models    │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│                          │                               │
│                  ┌──────────────┐                        │
│                  │ State Object │                        │
│                  └──────────────┘                        │
└─────────────────────────────────────────────────────────┘
```

### Design Patterns

- **MVC Pattern** - Separates concerns between UI, logic, and data
- **State Pattern** - Manages calculator states and transitions
- **Service Layer** - Encapsulates business logic
- **RESTful Architecture** - Stateless API design

---

## 🧩 Core Components

### Backend Core

#### 1️⃣ CalculatorState Class
The heart of the calculator - manages the entire state machine.

**Key Properties:**
```java
- String num1          // First operand (stored as string)
- String num2          // Second operand (stored as string)
- String operator      // Current operator (+, -, ×, ÷)
- boolean read1        // Flag: currently typing num1
- boolean pastIsOper   // Flag: last input was operator
- boolean pastIsEqual  // Flag: last input was equals
- boolean sfr1         // Flag: special function applied to num1
- boolean sfr2         // Flag: special function applied to num2
- String display1      // Expression display
- String display2      // Result/current number display
```

**State Transitions:**
- **Initial State** → `read1=true`, all flags false
- **Typing num1** → `read1=true`
- **Operator Pressed** → `read1=false, pastIsOper=true`
- **Typing num2** → `read1=false, pastIsOper=false`
- **Equals Pressed** → `pastIsEqual=true`
- **Special Function** → Sets `sfr1` or `sfr2` flag

#### 2️⃣ CalculatorService Class
Processes all calculator operations and manages state logic.

**Core Methods:**
```java
- processInput(String input, CalculatorState state)
  → Main entry point for all user inputs
  
- handleNumber(String input, CalculatorState state)
  → Processes digit input (0-9)
  
- handleOperator(String input, CalculatorState state)
  → Processes operators (+, -, ×, ÷)
  
- handleEquals(CalculatorState state)
  → Executes calculation and displays result
  
- handlePercent(CalculatorState state)
  → Context-aware percentage calculation
  
- handleSpecialFunction(String function, CalculatorState state)
  → Handles √, 1/x, x² operations
  
- handleBackspace(CalculatorState state)
  → Smart removal of last digit/operator
```

#### 3️⃣ CalculatorController Class
REST API endpoints for frontend communication.

**Endpoints:**
```java
POST   /api/calculator/input    // Process user input
POST   /api/calculator/clear    // Clear calculator
GET    /api/calculator/state    // Get current state
```

#### 4️⃣ CalculatorException Class
Custom exception for calculation errors with user-friendly messages.

**Error Types:**
- Division by zero
- Square root of negative numbers
- Invalid operations
- Overflow conditions

### Frontend Core

#### 1️⃣ Calculator Component
Main UI component handling user interactions.

**Responsibilities:**
- Renders calculator interface
- Captures button clicks
- Sends API requests
- Updates display based on state

#### 2️⃣ Calculator Service
Angular service for API communication.

**Methods:**
- `sendInput(input: string)` - Send button press to backend
- `clearCalculator()` - Reset calculator state
- `getState()` - Retrieve current state

#### 3️⃣ Calculator Model
TypeScript interface matching backend CalculatorState.

---

## 🎮 State Management


### State Flow Diagram

```
     [Initial State]
           │
           ├──[Digit]──→ num1 += digit
           │
           ├──[Operator]──→ pastIsOper = true
           │                  │
           │                  ├──[Digit]──→ num2 += digit
           │                  │
           │                  └──[=]──→ Calculate & Display
           │                              │
           │                              └──[Digit]──→ Reset & Start New
           │
           ├──[Special Function]──→ Apply & Set sfr flag
           │
           └──[C/CE]──→ [Initial State]
```

### Boolean Flags Logic

| Flag | True Meaning | False Meaning |
|------|--------------|---------------|
| `read1` | Typing first number | Typing second number or done |
| `pastIsOper` | Last input was operator | Last input was number/equals |
| `pastIsEqual` | Last input was equals | Normal operation mode |
| `sfr1` | Special function on num1 | No special function on num1 |
| `sfr2` | Special function on num2 | No special function on num2 |

---

## 📥 Installation

### Prerequisites

- ☕ **Java JDK 11+**
- 📦 **Node.js 14+** and npm
- 🅰️ **Angular CLI** (`npm install -g @angular/cli`)
- 🔨 **Maven 3.x**

### Backend Setup

```bash
# Clone the repository
git clone [https://github.com/MohamedMahmoudAli5125/windows-default-calculator.git]
cd windows-default-calculator/backend

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run

# Backend will start on http://localhost:8080
```

### Frontend Setup

```bash
# Navigate to frontend directory
cd windows-default-calculator/frontend

# Install dependencies
npm install

# Start development server
ng serve

# Frontend will start on http://localhost:4200
```

---

## 🚀 Usage

### 📺 Visual Examples

<div align="center">

#### Basic Calculation Flow
<img src="https://raw.githubusercontent.com/MohamedMahmoudAli5125/windows-default-calculator/main/screenshots/basic-calc-flow.gif" alt="Basic Calculation" width="500"/>

</div>

### Basic Calculation Example

```
User Input Sequence: 1 2 3 + 4 5 =

Step 1: Press '1' → Display: "1"
Step 2: Press '2' → Display: "12"
Step 3: Press '3' → Display: "123"
Step 4: Press '+' → Display: "123 +"
Step 5: Press '4' → Display: "123 + 4"
Step 6: Press '5' → Display: "123 + 45"
Step 7: Press '=' → Display: "123 + 45 = 168"
```

<div align="center">

#### Percentage Calculation Demo
<img src="https://raw.githubusercontent.com/MohamedMahmoudAli5125/windows-default-calculator/main/screenshots/percentage-calc.gif" alt="Percentage Calculation" width="500"/>

</div>

### Percentage Calculation Example

```
User Input: 2 0 0 + 1 0 % =

Calculation: 200 + (200 × 10 / 100) = 200 + 20 = 220
Result: 220
```

<div align="center">

#### Special Functions Demo
<img src="https://raw.githubusercontent.com/MohamedMahmoudAli5125/windows-default-calculator/main/screenshots/special-functions.gif" alt="Special Functions" width="500"/>

</div>

### Special Function Example

```
User Input: 1 6 √ =

Calculation: √16 = 4
Result: 4
```

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api/calculator
```

### Endpoints

#### Process Input
```http
POST /input
Content-Type: application/json

Request Body:
{
  "input": "5",
  "state": { ...currentState }
}

Response:
{
  "num1": "5",
  "num2": "0",
  "display1": "",
  "display2": "5",
  "read1": true,
  "pastIsOper": false,
  ...
}
```

#### Clear Calculator
```http
POST /clear

Response:
{
  "num1": "0",
  "num2": "0",
  "display1": "",
  "display2": "0",
  "read1": true,
  ...
}
```

#### Get Current State
```http
GET /state

Response:
{
  "num1": "123",
  "num2": "45",
  "operator": "+",
  "display1": "123 + 45",
  "display2": "45",
  ...
}
```

---

## 🎨 Display Formatting

### Number Formatting Rules

| Input | Display | Rule |
|-------|---------|------|
| 1234567 | 1,234,567 | Thousands separator |
| 1234.56 | 1,234.56 | Decimal precision |
| 5. | 5. | Active decimal typing |
| 0.00001234 | 0.00001234 | Up to 10 decimal places |
| 1234567890123456 | 16-digit max | Input limit |

---

## 🛡️ Error Handling

### Error States and Recovery

| Error Type | Trigger | Display | Recovery |
|------------|---------|---------|----------|
| **Division by Zero** | `n ÷ 0` | "Cannot divide by zero" | Press C/CE/digit |
| **Negative Square Root** | `√(-n)` | "Invalid input" | Press C/CE/digit |
| **Overflow** | Result > max | "Result too large" | Press C/CE/digit |

### Button States During Error

✅ **Enabled**: 0-9, C, CE, Backspace, =  
❌ **Disabled**: All operators, special functions

---

## 🧪 Testing

### Run Backend Tests
```bash
cd backend
mvn test
```

### Run Frontend Tests
```bash
cd frontend
ng test
```

---

## 📚 Project Structure

```
calculator-app/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/calculator3/
│   │   │   │       ├── controller/
│   │   │   │       │   └── CalculatorController.java
│   │   │   │       │
│   │   │   │       ├── service/
│   │   │   │       │   ├── CalculatorService.java
│   │   │   │       │   ├── BackspaceHandler.java
│   │   │   │       │   ├── DigitHandler.java
│   │   │   │       │   ├── DisplayManager.java
│   │   │   │       │   ├── EqualsHandler.java
│   │   │   │       │   ├── InputProcessor.java
│   │   │   │       │   ├── NumberFormatter.java
│   │   │   │       │   ├── OperationHandler.java
│   │   │   │       │   ├── OperatorHandler.java
│   │   │   │       │   ├── PercentageHandler.java
│   │   │   │       │   └── UnaryOperationHandler.java
│   │   │   │       │
│   │   │   │       ├── model/
│   │   │   │       │   ├── CalculatorState.java
│   │   │   │       │   ├── CalculatorRequest.java
│   │   │   │       │   └── CalculatorResponse.java
│   │   │   │       │
│   │   │   │       ├── Exception/
│   │   │   │       │   └── CalculatorException.java
│   │   │   │       │
│   │   │   │       └── Calculator3Application.java
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │       └── java/
│   │
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── calculator/
│   │   │   │   ├── calculator.ts
│   │   │   │   ├── calculator.html
│   │   │   │   ├── calculator.css
│   │   │   │   └── calculator.spec.ts
│   │   │   │
│   │   │   ├── app.ts
│   │   │   ├── app.html
│   │   │   ├── app.css
│   │   │   ├── app.spec.ts
│   │   │   ├── app.config.ts
│   │   │   └── app.routes.ts
│   │   │
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.css
│   │
│   └── package.json
│
└── README.md
```

---

## 👨‍💻 Developer Information

**Developer**: Mohammed Mahmoud Ali  
**Institution**: Alexandria University - Faculty of Engineering  
**Department**: Computer and Systems Engineering  
**Course**: CSE 223 - Programming 2

---

<img src="https://raw.githubusercontent.com/MohamedMahmoudAli5125/windows-default-calculator/main/screenshots/footer-animation.gif" alt="Thank You Animation" width="400"/>


</div>
