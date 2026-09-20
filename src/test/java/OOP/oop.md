In Java, the four main OOP (Object-Oriented Programming) concepts are:

OOP Concept	Memory word	Meaning
Encapsulation   -   HIDE    -   Hide internal data and control access
[Encapsulation](https://github.com/ashishps1/awesome-low-level-design/tree/main/oop/java/encapsulation)

```java
class BankAccount {

    private double balance;

    public void deposit(double amount) {  //Controlled
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

Inheritance     -   REUSE   -   Child class inherits properties/behavior from parent
[Inheritance](https://github.com/ashishps1/awesome-low-level-design/tree/main/oop/java/inheritance)

Polymorphism    -   MANY FORMS  -   Same interface/method can have different implementations
[Polymorphism](https://github.com/ashishps1/awesome-low-level-design/tree/main/oop/java/polymorphism)

Abstraction -   HIDE    -   COMPLEXITY	Expose what to do, hide how it is done
[Abstraction](https://github.com/ashishps1/awesome-low-level-design/tree/main/oop/java/abstraction1fgyuiooc)

    Association

The most general relationship: one class uses or knows about another. Neither owns the other, and both have independent lifecycles. It can be one-to-one, one-to-many or many-to-many.

```java
class Doctor {
void treat(Patient p) { /* uses Patient */ }
}
class Patient { }
```

A doctor treats many patients, and a patient can see many doctors. 
Neither's existence depends on the other.

---

    Aggregation

A "has-a" relationship where the whole holds parts, but the parts can exist independently. It is a weak form of ownership. The part is usually created outside and passed in via a constructor or setter.

```java
class Employee { String name; }

class Department {
private List<Employee> employees;

    Department(List<Employee> employees) {   // created outside, injected
        this.employees = employees;
    }
}
```

If the Department is deleted, the Employee objects still exist and can move to another department.

---
    Composition

A "has-a" relationship with strong ownership. The part's lifecycle is tied to the whole: it is created by the whole, and it has no meaning without it.

```java
class Room { }

class House {
private final Room room = new Room();   // created and owned internally
}
```

When the House is gone, its Room is gone too. Because the JVM uses garbage collection, this is enforced by design discipline: create the part inside the whole and don't leak its reference outside.

---
| Relationship | Meaning      | Ownership    |
| ------------ | ------------ | ------------ |
| Association  | USES/KNOWS-A | None/general |
| Aggregation  | HAS-A        | Weak         |
| Composition  | HAS-A        | Strong       |


---
IS-A vs HAS-A

IS-A is inheritance (extends for classes, implements for interfaces). The subclass is a specialized type of the parent and can be used wherever the parent is expected.

```java
class Animal { void eat() {} }
class Dog extends Animal { void bark() {} }   // Dog IS-A Animal
```

HAS-A is a field reference to another object, which covers aggregation and composition.
```java
class Car {
private Engine engine;   // Car HAS-A Engine
}
```

---

4 OOP PILLARS

Encapsulation
→ HIDE DATA

Inheritance
→ REUSE / IS-A

Polymorphism
→ MANY FORMS

Abstraction
→ HIDE COMPLEXITY


OBJECT RELATIONSHIPS

Association
→ USES / KNOWS-A

Aggregation
→ HAS-A, weak ownership
→ Child can live independently

Composition
→ HAS-A, strong ownership
→ Owner controls lifecycle


IS-A
→ Inheritance

HAS-A
→ Composition / Aggregation


POLYMORPHISM

Overloading
→ Different parameters
→ Compile time

Overriding
→ Same signature, different implementation
→ Runtime


ABSTRACTION

Interface
→ CONTRACT / CAPABILITY
→ Multiple interfaces possible

Abstract Class
→ SHARED STATE + BEHAVIOR
→ Single class inheritance