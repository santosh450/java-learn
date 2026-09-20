## Object Creation mechanism

### 1. Factory Design Pattern

Create objects without exposing the object-creation logic to the client.

Instead of doing this everywhere:

Payment payment = new CreditCardPayment();

we delegate object creation to a factory:

Payment payment = PaymentFactory.createPayment("CREDIT_CARD");

without Factory:
PaymentService (business logic and object-creation logic are mixed together) -> payment

With factory:

PaymentService (business logic) -> Factory (object-creation logic) -> payment

```java
public interface Payment {
    void pay(double amount);
}

public class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }
}

public class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}

public class PayPalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}

public class PaymentService {

    public void processPayment(String type, double amount) {

        Payment payment;

        if (type.equals("UPI")) {
            payment = new UpiPayment();
        } else if (type.equals("CREDIT_CARD")) {
            payment = new CreditCardPayment();
        } else if (type.equals("PAYPAL")) {
            payment = new PayPalPayment();
        } else {
            throw new IllegalArgumentException("Invalid payment type");
        }

        payment.pay(amount);
    }
}
```
#### Solution

```java
public class PaymentFactory {

    public static Payment createPayment(String type) {

        if (type.equals("UPI")) {
            return new UpiPayment();
        } else if (type.equals("CREDIT_CARD")) {
            return new CreditCardPayment();
        } else if (type.equals("PAYPAL")) {
            return new PayPalPayment();
        }

        throw new IllegalArgumentException("Invalid payment type");
    }
}
public class PaymentService {

    public void processPayment(String type, double amount) {
        Payment payment = PaymentFactory.createPayment(type);
        payment.pay(amount);
    }
}
```
Now The key point is that PaymentService depends mainly on the abstraction

#### even better with enums
```java
public enum PaymentType {
    UPI,
    CREDIT_CARD,
    PAYPAL
}

public class PaymentFactory {
    public static Payment createPayment(PaymentType type) {
        return switch (type) {
            case UPI -> new UpiPayment();
            case CREDIT_CARD -> new CreditCardPayment();
            case PAYPAL -> new PayPalPayment();
        };
    }
}

```

### Notes

Simple Factory – Selenium WebDriver Notes

Without Factory

- BaseTest contains object-creation logic using if/switch and directly creates new ChromeDriver(), new FirefoxDriver(), etc.
- This tightly couples BaseTest with concrete driver classes and mixes test setup + object creation.

With Simple Factory

- Move WebDriver creation into a separate DriverFactory; BaseTest only requests a WebDriver.
- This centralizes object creation, reduces coupling, and improves maintainability.


#### Factory Method

Simple Factory - The problem is that every time we add a new payment type, we modify PaymentFactory.
Factory Method - Define a method for creating an object, but let subclasses decide which concrete object gets created.

```java
public interface Payment {
    void pay(double amount);
}

public class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }
}

public class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}

public class PayPalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}

// Now instead of one factory with if/switch, create an abstract factory/creator:
public abstract class PaymentProcessor {

    // Factory Method
    protected abstract Payment createPayment();

    public void processPayment(double amount) {
        Payment payment = createPayment();
        payment.pay(amount);
    }
}

// Create concrete factories

public class UpiPaymentProcessor extends PaymentProcessor {

    @Override
    protected Payment createPayment() {
        return new UpiPayment();
    }
}

public class CreditCardPaymentProcessor extends PaymentProcessor {

    @Override
    protected Payment createPayment() {
        return new CreditCardPayment();
    }
}


public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new UpiPaymentProcessor();
        processor.processPayment(5000);
    }
}

```

#### 1. Factory Method is not automatically better than Simple Factory. Factory Method is most useful when subclass selection is naturally known by the application's architecture. If you're simply converting "UPI" / "CARD" / "PAYPAL" into objects, a Simple Factory may actually be cleaner

---

### 2. Abstract Design Pattern

create families of related objects (using interface) without the client knowing their concrete classes.

Without
```java
public interface Payment {
    void pay(double amount);
}

public class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Processing UPI payment: ₹" + amount);
    }
}

public class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card payment: ₹" + amount);
    }
}

public interface PaymentValidator {
    boolean validate();
}

public class UpiPaymentValidator implements PaymentValidator {

    @Override
    public boolean validate() {
        System.out.println("Validating UPI ID");
        return true;
    }
}

public class CreditCardValidator implements PaymentValidator {

    @Override
    public boolean validate() {
        System.out.println("Validating card number and CVV");
        return true;
    }
}

```

Solution:
```java
public interface PaymentFactory {
    Payment createPayment();
    PaymentValidator createValidator();
}

public class UpiPaymentFactory implements PaymentFactory {

    @Override
    public Payment createPayment() {
        return new UpiPayment();
    }

    @Override
    public PaymentValidator createValidator() {
        return new UpiPaymentValidator();
    }
}

public class CreditCardPaymentFactory implements PaymentFactory {

    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }

    @Override
    public PaymentValidator createValidator() {
        return new CreditCardValidator();
    }
}

public class PaymentService {

    private final Payment payment;
    private final PaymentValidator validator;

    public PaymentService(PaymentFactory factory) {
        this.payment = factory.createPayment();
        this.validator = factory.createValidator();
    }

    public void processPayment(double amount) {
        if (validator.validate()) {
            payment.pay(amount);
        }
    }
}
```

Abstract Factory is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes.

---

### 3. Builder Design Pattern

- Create complex objects easily, by keeping the object creation process flexible, readable and maintainable
- Construct complex objects step by step, especially when a class has many optional parameters.

Without builder

```java
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int age;
    private String city;

    public User(
            String firstName,
            String lastName,
            String email,
            String phone,
            int age,
            String city) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.city = city;
    }
}

public class Main {
    public static void main(String[] args) {
        User user1 = new User(
                "Santosh",
                "Maharana",
                "santosh@test.com",
                "9999999999",
                35,
                "Hyderabad"
        );
        User user2 = new User(
                "Ramesh",
                null,
                null,
                "777",
                35,
                "Hyderabad"
        );
    }
}
```

Now we have several problems if there are 15 fields:

- Hard to understand which value belongs to which field.
- Many optional fields lead to null arguments.
- Constructor becomes huge.
- Easy to accidentally swap parameters of the same type.

```java
public class User {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final int age;
    private final String city;

    private User(Builder builder) {

        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.age = builder.age;
        this.city = builder.city;
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private int age;
        private String city;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}


User user = new User.Builder()
        .firstName("Santosh")
        .lastName("Maharana")
        .email("santosh@test.com")
        .age(35)
        .city("Hyderabad")
        .build();

```
#### User constructor private
- we don't want someone doing: new User(...);
- We want object creation to happen through: 
  new User.Builder()
  ...
  .build();

#### Required and Optional Fields
- if someone needs 
    Required: firstName, email
    Optional: lastName, phone, age, city
```java
public static class Builder {

    private String firstName, email, lastName, phone, age, city;

    public Builder(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    // other builder methods...
}

User user = new User.Builder( "Santosh", "santosh@test.com")
        .age(35)
        .city("Hyderabad")
        .build();

```

- Validation inside build()
```java
public User build() {

    if (firstName == null || firstName.isBlank()) {
        throw new IllegalArgumentException(
                "First name is required"
        );
    }

    if (email == null || email.isBlank()) {
        throw new IllegalArgumentException(
                "Email is required"
        );
    }

    if (age < 0) {
        throw new IllegalArgumentException(
                "Age cannot be negative"
        );
    }

    return new User(this);
}
```

- Builder is useful for immutable objects
    Fields of User class is private and final.

---

### 4. Singleton Design Pattern

Singleton is a creational design pattern that ensures:

 - Only one instance of a class exists.
- Provides a global access point to that instance.

#### Basic implementation

```java
public class DatabaseConnection {

    private static DatabaseConnection instance = new DatabaseConnection();

    private DatabaseConnection() {
        System.out.println("Object created");
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }
}
```
- Private constructor - Outside classes cannot call the constructor. So object creation is controlled by the DatabaseConnection class itself.
- Static instance: initially we don't have a DatabaseConnection object. We therefore need to access the instance through the class

#### Problem
- Eager Initialization

The instance is created as soon as the class loads, whether or not anyone ever calls getInstance(). If DatabaseConnection is expensive to create (opening actual DB connections, pooling, etc.) and it turns out this class gets loaded but never used, you've wasted resources for nothing.

#### Lazy implementation
```java
public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Object created");
    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
```
- Multithreading: violates Singleton.

#### Thread-safe Singleton using synchronized
```java
public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Object created");
    }

    public static synchronized DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
```

- because of synchronized, only one thread can execute it at a time.
- synchronization happens every time getInstance() is called, even after the object already exists.
- other threads wait even objects gets created.

#### Double-Checked Locking

```java
public class DatabaseConnection {

    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}
```

 - volatile:

Object creation conceptually involves multiple operations:

1. Allocate memory
2. Initialize object
3. Assign reference to instance

Without the appropriate memory-ordering guarantees, another thread could theoretically observe the reference before construction is safely visible.

volatile provides the required visibility and ordering guarantees for double-checked locking.

- if 1st time 10 threads started at same time, 2nd time 10 threds stared at sametime  
- then 1st 10 threads use locks( 1st thread gets created, from 2-10, will check 2nd if and skip)
- from 11-20, 1st if skips (no locks)


#### Enum Singleton
Because enum constants have special semantics in Java. For a given enum class initialization, 
Java creates the declared enum constants once.

```java
public enum DatabaseConnection {

    INSTANCE;

    DatabaseConnection() {
        System.out.println("Constructor called");
    }
}

public class Main {

    public static void main(String[] args) {

        DatabaseConnection db1 = DatabaseConnection.INSTANCE;
        DatabaseConnection db2 = DatabaseConnection.INSTANCE;
        DatabaseConnection db3 = DatabaseConnection.INSTANCE;

        System.out.println(db1 == db2);
        System.out.println(db2 == db3);
    }
}

/*
Constructor called
true
true
 */
```

### 5. Prototype Design Pattern

Instead of creating a new object from scratch using new and configuring everything again, 
create a new object by copying an existing object.

```java
public class Employee {

    private String name, department, company, location;

    public Employee(String name, String department, String company, String location) {

        this.name = name;
        this.department = department;
        this.company = company;
        this.location = location;
    }
}


Employee e1 = new Employee("Santosh", "Engineering", "ABC Tech", "Hyderabad");
Employee e2 = new Employee("Rahul", "Engineering", "ABC Tech", "Hyderabad");
Employee e3 = new Employee("Ravi", "Engineering", "ABC Tech", "Hyderabad");

```

Imagine most employees have: department, company,location are same
Only the employee name changes

Solution: Copy of existing object

```java
public class Employee {

    private String name, department, company, location;

    public Employee(String name, String department, String company, String location) {

        this.name = name;
        this.department = department;
        this.company = company;
        this.location = location;
    }

    public Employee copy() {

        return new Employee(
                this.name,
                this.department,
                this.company,
                this.location
        );
    }

    public void setName(String name) {
        this.name = name;
    }
}


Employee prototype = new Employee(
        "Default",
        "Engineering",
        "ABC Technologies",
        "Hyderabad"
);

Employee employee1 = prototype.copy();
employee1.setName("Santosh");

Employee employee2 = prototype.copy();
employee2.setName("Rahul");
```

Shallow Copy: 

Now suppose Employee contains another object:

```java
class Address {

    String city;

    Address(String city) {
        this.city = city;
    }
}

public class Employee implements Cloneable {

    String name;
    Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public Employee clone() {

        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

Address address = new Address("Hyderabad");
Employee e1 = new Employee("Santosh", address);
Employee e2 = e1.clone();

Both employees share the same Address. - shallow copy

Deep Copy:
```java
class Address {

    String city;

    Address(String city) {
        this.city = city;
    }

    public Address copy() {
        return new Address(this.city);
    }
}

public class Employee {

    String name;
    Address address;

    public Employee( String name, Address address) {

        this.name = name;
        this.address = address;
    }

    public Employee copy() {
        return new Employee( this.name, this.address.copy()
        );
    }
}
```

Employee e1 = new Employee( "Santosh", new Address("Hyderabad"));
Employee e2 = e1.copy();

---

Factory Pattern - WebDriver creation

Factory in Automation: Use when the framework must choose one implementation dynamically. Common examples: browser/driver creation, reporters, data readers, API clients, wait strategies

DriverFactory
├── ChromeDriver
├── FirefoxDriver
└── EdgeDriver

WaitFactory
├── ExplicitWait
└── FluentWait

ReporterFactory
├── ExtentReporter
├── AllureReporter
└── ConsoleReporter

TestDataFactory
├── JsonDataProvider
├── ExcelDataProvider
└── DatabaseDataProvider

ApiClientFactory
├── DevApiClient
├── QAApiClient
└── ProdApiClient

---

Abstract Factory Pattern -

Create a family of related objects that should work together.

This becomes useful when your framework supports multiple environments/platforms with several related components.
1)
WEB
├── WebDriver
└── WebElementHandler

MOBILE
├── AppiumDriver
└── MobileElementHandler

2)
QA Factory
├── QA API client
├── QA database
└── QA configuration

STAGING Factory
├── Staging API client
├── Staging database
└── Staging configuration

Abstract Factory in Automation: Use when several related framework objects must be created as a compatible family. Examples: Web/Mobile components, QA/Staging environments, Local/Grid/Cloud execution components.

---

Builder Pattern - 

This is extremely useful in automation, especially API automation.

Purpose

Create complex objects with many optional parameters in a readable way.

Usage 1: API request payloads
Usage 2: Test data creation
Usage 3: Complex API request specification

Builder in Automation: Best for complex test objects/API payloads having many optional fields. Improves readability, avoids huge constructors and makes test-data setup fluent.

---

Singleton Pattern - 

Usage 1: Configuration Manager - eg. .properties
Usage 2: Report Manager

Singleton in Automation: Useful for truly shared framework services such as configuration or reporting coordination. Avoid a single global WebDriver for parallel tests; use per-test/per-thread lifecycle management instead.

---
Prototype Pattern - 

Purpose
Create new test objects by copying an already-configured object and changing only the required fields.

This is especially useful for test data.

Usage 1: Test data templates
Usage 2: API payload variations

Prototype in Automation: Best for reusable test-data templates. Start from a valid/default object, copy it, and modify only fields required by the scenario. Particularly useful for positive/negative API payload variations.

---

FACTORY
"Give me the correct implementation"
↓
Chrome / Firefox / Edge


ABSTRACT FACTORY
"Give me a complete compatible family"
↓
Web    → WebDriver + Web components
Mobile → AppiumDriver + Mobile components


BUILDER
"Let me configure this object step by step"
↓
User.builder()
.name(...)
.email(...)
.role(...)
.build()


SINGLETON
"Everyone should access the same instance"
↓
ConfigManager.INSTANCE


PROTOTYPE
"Give me a new object based on this existing one"
↓
ValidUser
├── copy → MissingEmailUser
├── copy → InvalidPhoneUser
└── copy → InactiveUser

---
---

    CREATIONAL

Factory
→ CREATE the right object

Abstract Factory
→ FAMILY of related objects

Builder
→ BUILD step by step

Singleton
→ ONE instance

Prototype
→ CLONE existing object

    STRUCTURAL

Adapter
→ CONVERT interface

Proxy
→ CONTROL access

Decorator
→ ENHANCE object


    BEHAVIORAL

Observer
→ NOTIFY on event

Strategy
→ CHOOSE behavior

Chain of Responsibility
→ PASS through handlers
