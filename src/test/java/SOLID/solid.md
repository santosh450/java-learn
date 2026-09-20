## S — Single Responsibility Principle
A class should have only one reason to change — i.e., it should do one job.
Bad
```java
public class OrderService {

    public void createOrder(String product, int qty) {
        System.out.println("Order created for " + qty + " x " + product);
    }

    public void processPayment(double amount) {
        // payment gateway logic mixed in
        System.out.println("Payment of $" + amount + " processed via gateway");
    }

    public void sendEmail(String customerEmail) {
        // email sending logic mixed in
        System.out.println("Email sent to " + customerEmail + ": Your order is confirmed.");
    }

    public void generateInvoice(String product, int qty, double amount) {
        // invoice/PDF logic mixed in
        System.out.println("Invoice generated: " + qty + " x " + product + " = $" + amount);
    }
}
```
Good Code (SRP applied)

```java
// Handles order creation only
public class OrderService {
    public void createOrder(String product, int qty) {
        System.out.println("Order created for " + qty + " x " + product);
    }
    public void cancelOrder(String product, int qty) {
        System.out.println("Order cancelled for " + qty + " x " + product);
    }
}

// Handles payment only
public class PaymentService {
    public void processPayment(double amount) {
        System.out.println("Payment of $" + amount + " processed via gateway");
    }
    public void processRefund(double amount) {
        System.out.println("Payment of $" + amount + " processed via gateway");
    }
}

// Handles email only
public class EmailService {
    public void sendEmail(String customerEmail) {
        System.out.println("Email sent to " + customerEmail + ": Your order is confirmed.");
    }
    public void receiveEmail(String customerEmail) {
        System.out.println("Email received fromm " + customerEmail + ": Your order is confirmed.");
    }
}

// Handles invoice generation only
public class InvoiceService {
    public void generateInvoice(String product, int qty, double amount) {
        System.out.println("Invoice generated: " + qty + " x " + product + " = $" + amount);
    }
}
```

## O — Open/Closed Principle
Classes should be open for extension but closed for modification.

Bad:
adding new payment makes existing code to be tested. should make existing method to be modified
```java
public class PaymentService {

    public void makePayment(String paymentType){
        if(paymentType.equals("UPI")){
            System.out.println("UPI payment made.");
        } else if (paymentType.equals("CC")) {
            System.out.println("CC Payment made.");
        }
    }
}
```
Good Code (OCP applied)
```java
public interface PaymentService {
    void makePayment(double amount);
}

public class UpiPayment implements PaymentService {
    @Override
    public void makePayment(double amount) {
        System.out.println("UPI payment of $" + amount + " made.");
    }
}

public class CreditCardPayment implements PaymentService {
    @Override
    public void makePayment(double amount) {
        System.out.println("Credit Card payment of $" + amount + " made.");
    }
}

public class DebitCardPayment implements PaymentService {
    @Override
    public void makePayment(double amount) {
        System.out.println("Debit Card payment of $" + amount + " made.");
    }
}
```

## 3. L - Liskov Substitution Principle (LSP).

An object of a subclass should be usable wherever an object of its parent class is expected, without breaking the program's behavior.

```java
public interface Payment {

    void pay();
    void refund();
}

public class PaymentClient {
    public void processTransaction(Payment payment){
        payment.pay();
        payment.refund();
    }
}

public class UPI implements Payment{
    @Override
    public void pay() {
        System.out.println("UPI - Pay");
    }

    @Override
    public void refund() {
        System.out.println("UPI - refund");
    }

    public void checkBalance(){
        System.out.println("UPI - balanceCheck");
    }
}

public class CreditCard implements Payment{
    @Override
    public void pay() {
        System.out.println("CreditCard - pay");
    }

    @Override
    public void refund() {
        System.out.println("CreditCard - refund");
    }
}

public class TestMain {
    public static void main(String[] args) {

        PaymentClient paymentClient = new PaymentClient();
        List<Payment> payments = List.of(new UPI(), new CreditCard());
        payments.forEach(paymentClient::processTransaction);
    }
}

```
O/P:
UPI - Pay
UPI - refund
CreditCard - pay
CreditCard - refund

Problem: Now i am adding new service crypto doesn't support refunds

```java

public class Crypto implements Payment{
    @Override
    public void pay() {
        System.out.println("Crypo - pay");
    }

    @Override
    public void refund() {
        throw new UnsupportedOperationException("Refund NOT supported");
    }
}

public class TestMain {
    public static void main(String[] args) {

        PaymentClient paymentClient = new PaymentClient();
        List<Payment> payments = List.of(new UPI(), new CreditCard(), new Crypto());
        payments.forEach(paymentClient::processTransaction);
    }
}
```
UPI - Pay
UPI - refund
CreditCard - pay
CreditCard - refund
Crypo - pay
Exception in thread "main" java.lang.UnsupportedOperationException: Refund NOT supported
at SOLID.LSP.Crypto.refund(Crypto.java:11)
at SOLID.LSP.PaymentClient.processTransaction(PaymentClient.java:6)
at java.base/java.lang.Iterable.forEach(Iterable.java:75)
at SOLID.LSP.TestMain.main(TestMain.java:10)

Process finished with exit code 1

Solution
```java
public interface NonRefundablePayments {
    void pay();
}

public interface Payment extends NonRefundablePayments{
    void refund();
}

public class Crypto implements NonRefundablePayments{
    @Override
    public void pay() {
        System.out.println("Crypo - pay");
    }
}

public class PaymentClient {
    public void processTransaction(Payment payment){
        payment.pay();
        payment.refund();
    }

    public void processTransaction(NonRefundablePayments nonRefundablePayments){
        nonRefundablePayments.pay();
    }
}

public class TestMain {
    public static void main(String[] args) {

        PaymentClient paymentClient = new PaymentClient();
        List<Payment> payments = List.of(new UPI(), new CreditCard());
        payments.forEach(paymentClient::processTransaction);
        List<NonRefundablePayments> nonRefundablePayments = List.of(new Crypto());
        nonRefundablePayments.forEach(paymentClient::processTransaction);
    }
}
```
No changes in existing child classes UPI, creditCard

## 4. I - Interface Segregation Principle
A class should not be forced to implement methods which it does not use
Instead of creating one large interface, we should create smaller and more specific interfaces.

Bad Code
```java

public interface Employee {

    void writeCode();
    void testCode();
    void deployApp();
}


public class QAEngineer implements Employee{
    @Override
    public void writeCode() {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void testCode() {
        System.out.println("QA can test code.");
    }

    @Override
    public void deployApp() {
        throw  new UnsupportedOperationException();
    }
}


public class DevOpsEngineer implements Employee{
    @Override
    public void writeCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deployApp() {
        System.out.println("DevOps can deploy app.");
    }
}

public class BackendDeveloper implements Employee{
    @Override
    public void writeCode() {
        System.out.println("Backend engineer can write code");
    }

    @Override
    public void testCode() {
        System.out.println("Backend engineer can test code");
    }

    @Override
    public void deployApp() {
        throw new UnsupportedOperationException();
    }
}


```
Solution
```java
public interface Coder {
    void writeCode();
}
public interface Tester {
    void testCode();
}
public interface Deployer {
    void deployApp();
}

public class BackendDeveloper implements Coder, Tester{
    @Override
    public void writeCode() {
        System.out.println("Backend engineer can write code");
    }

    @Override
    public void testCode() {
        System.out.println("Backend engineer can test code");
    }

}
public class DevOpsEngineer implements Deployer{
    @Override
    public void deployApp() {
        System.out.println("DevOps can deploy app.");
    }
}
public class QAEngineer implements Tester{

    @Override
    public void testCode() {
        System.out.println("QA can test code.");
    }
}
```

## 5. D - Dependency Inversion Principle 

High-level modules should not depend on low-level modules. Both should depend on abstraction.

Instead of one class directly depending on another concrete class, we should depend on interfaces.

Loosely coupled code is easier to maintain, easier to scale and easier to modify in the future

```java
public class MySQLDatabase {
    public void saveData(String data){
        System.out.println("Mysql save data: "+ data);
    }
}
public class MongoDBDatabase {
    public void storeDocument(String data){
        System.out.println("Mango store data: "+ data);
    }
}
public class UserService {

    private MySQLDatabase mySQLDatabase = new MySQLDatabase();
    public MongoDBDatabase mongoDBDatabase = new MongoDBDatabase();

    public void saveUser(String user){
        mySQLDatabase.saveData(user);
    }

    public void saveDocMongo(String doc){
        mongoDBDatabase.storeDocument(doc);
    }
}
```
Solution: Loosely coupled
```java

public interface Database {
    void save(String user);
}

public class MongoDBDatabase implements Database{

    @Override
    public void save(String user) {
        System.out.println("Mango store data: "+ user);
    }
}

public class MySQLDatabase implements Database{

    @Override
    public void save(String user) {
        System.out.println("Mysql save data: "+ user);
    }
}

public class UserService {

    private Database database;

    public UserService(Database database){
        this.database = database;
    }

    public void saveUser(String user){
        database.save(user);
    }
}

```


