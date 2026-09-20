## How Classes and objects are organized and related each other

### 1. Adapter Design Pattern

- Adapter allows two incompatible interfaces/classes to work together by converting one interface into the interface the client expects.
- Allows two incompatible systems or interfaces work together


```java

// Target
public interface PaymentProcessor {
    String pay(double amount);
}

// Service
public class CreditCardPayment implements PaymentProcessor{
    @Override
    public String pay(double amount) {
        System.out.println("Credit card payment: " + amount);
        return "SUCCESS";
    }
}

//Client
public class PaymentClient {

    //    CreditCardPayment payment = new CreditCardPayment();
    //or
    PaymentProcessor payment = new CreditCardPayment();

    public void makePayment(){
        String result = payment.pay(1000);
        if(result.equals("SUCCESS")){
            System.out.println("Payment done successfully");
        }else {
            System.out.println("Payment failed");
        }
    }
}
```

Now third party payment library (Adaptee)
```java
public class PayPalGateway {

    public int makePayment(String currency, double value) {

        System.out.println("PayPal payment: " + value + " " + currency);
        return 1;
    }
}

//Now not campatable
PaymentProcessor payment = new PayPalGateway(); // ❌
```

Create adopter
```java

public class PayPalAdapter implements PaymentProcessor {

    private final PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public String pay(double amount) {

        int res = payPalGateway.makePayment("INR", amount);
        if(res == 1){
            return "SUCESS";
        }
        return "FAIL";
    }
}

public class PaymentClient {

    //    CreditCardPayment payment = new CreditCardPayment();
    //or
//    PaymentProcessor payment = new CreditCardPayment();
    //or
    PaymentProcessor payment = new PayPalAdapter(new PayPalGateway());

    public void makePayment(){
        String result = payment.pay(1000);
        if(result.equals("SUCCESS")){
            System.out.println("Payment done successfully");
        }else {
            System.out.println("Payment failed");
        }
    }
}

```

Similarly for Rozopay
```java
// Third party payment
public class RazoPayGateway {

    public boolean makePayment(String currency, int value) {
        System.out.println("Razo payment: " + value + " " + currency);
        return true;
    }
}

//Adaptor
public class RazoPayAdapter implements PaymentProcessor {

    private final RazoPayGateway razoPayGateway;

    public RazoPayAdapter(RazoPayGateway razoPayGateway) {
        this.razoPayGateway = razoPayGateway;
    }

    @Override
    public String pay(double amount) {

        boolean res = razoPayGateway.makePayment("INR", (int)amount);
        if(res){
            return "SUCESS";
        }
        return "FAIL";
    }
}

//Client
public class PaymentClient {

    //    CreditCardPayment payment = new CreditCardPayment();
    //or
//    PaymentProcessor payment = new CreditCardPayment();
    //or
//    PaymentProcessor payment = new PayPalAdapter(new PayPalGateway());
    //or
    PaymentProcessor payment = new RazoPayAdapter(new RazoPayGateway());

    public void makePayment(){
        String result = payment.pay(1000);
        if(result.equals("SUCCESS")){
            System.out.println("Payment done successfully");
        }else {
            System.out.println("Payment failed");
        }
    }
}


```
#### in Automation
```java

public interface TestReporter {
    void logPass(String message);
    void logFail(String message);
}

// 1st Third Party
public class ExtentReportAdapter implements TestReporter {

    private final ExtentTest extentTest;

    public ExtentReportAdapter(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    @Override
    public void logPass(String message) {
        extentTest.pass(message);
    }

    @Override
    public void logFail(String message) {
        extentTest.fail(message);
    }
}

//2nd rd party
public class AllureReportAdapter implements TestReporter {

    @Override
    public void logPass(String message) {
        // translate to Allure API
    }

    @Override
    public void logFail(String message) {
        // translate to Allure API
    }
}

// Client (test methods ) always
public test1(){
    //logic
    reporter.logPass("Login successful");
}

public test2(){
    //logic
    reporter.logFail("Login fail");
}


// Logic/Properties decide which Report to use

TestReporter reporter = new ExtentReportAdapter(extentTest);
//Or
TestReporter reporter = new AllureReportAdapter();

```

---

### 2. Proxy Design Pattern

- provides a placeholder (representative object) for another object
- The Proxy Pattern is a structural design pattern where one object acts as a substitute/intermediary for another object.

The client thinks it is talking directly to the real object, but the proxy can perform extra work before or after forwarding the request.

#### 1. Virtual proxy design
- it allows to delay the creation of a heavy object until it is actually needed

```java

public interface Video {
    void play();
}

public class Movies implements Video{

    private String fileName;

    public Movies(String fileName) {
        this.fileName = fileName;
        loadVideo();
    }

    private void loadVideo() {
        System.out.println("loading video from server");
    }


    @Override
    public void play() {
        System.out.println("Video is playing: "+fileName);
    }
}

public class Main {
    public static void main(String[] args) {
        Video video1 = new Movies("Bhaubali.mp4");
        Video video2 = new Movies("Phuspa.mp4");
        Video video3 = new Movies("OG.p4");
    }
}


```
Problem: during object creation (no play method is called), still it takes time (loadVideo())

Solution:

```java
public class MoviesProxy implements Video{

    private String fileName;
    private Video video;

    public MoviesProxy(String fileName) {
        this.fileName = fileName;
        loadVideo();
    }

    private void loadVideo() {
        System.out.println("loading video from server");
    }


    // Lazy intialization
    @Override
    public void play() {
        System.out.println("Video is playing: "+fileName);
        if(video == null) {
            video = new Movies(fileName);
        }
        video.play();
    }
}

public class Main {
    public static void main(String[] args) {
        Video video1 = new MoviesProxy("Bhaubali.mp4");
        Video video2 = new MoviesProxy("Phuspa.mp4");
        Video video3 = new MoviesProxy("OG.p4");

        video1.play();
    }
}


```
Now during object creation, no loadVideo() is called.
with Lazy initialization, loadVideo(), play() runs

---

#### 2. Protection proxy
- it controls access to the real object by performing authorization or permission checks before allowing operations.

```java
public interface Database {
    void delete();
}

public class MySqlDatabase implements Database{
    @Override
    public void delete() {
        System.out.println("User deleted");
    }
}

public class Main {
    public static void main(String[] args) {
        Database db = new MySqlDatabase();
        db.delete();
    }
}

```
Problem:
Solution:
```java

public class DatabaseProxy implements Database{
    private Database db;
    private String role;

    public DatabaseProxy(String role){
        this.db = new MySqlDatabase();
        this.role = role;
    }
    @Override
    public void delete() {
        if(role.equals("Admin"))
            System.out.println("User deleted");
        else 
            System.out.println("Access denied");
    }
}
public class Main {
    public static void main(String[] args) {
        Database db = new DatabaseProxy("Admin");
        db.delete();
    }
}

```

#### 3. Remote Proxy
- it provides a local representative for a class that exists on a remote server thereby handling communication between the client and the remote class

---

### 3. Decorative
- allows us to add new behaviour or feature to an object dynamically

```java
public interface Pizza {
    String description();
    int getCost();
}

public class BasicPizza implements Pizza{
    @Override
    public String description() {
        return "Basic Pizza";
    }

    @Override
    public int getCost() {
        return 100;
    }
}
```
Problem: Now lets add cheeze, onion, mushroom
now instead of creation multiple classes for each combinations
Class 1: Basic + cheeze
Class 2: Basic + onion
Class 3: Basic + mushroom
Class 4: Basic + cheeze + onion
Class 5: Basic + cheeze + mushroom
Class 6: Basic + cheeze + onion + mushroom

solution: create decorators which are minimal 
```java
public abstract class PizzaDecorator implements Pizza{
    Pizza pizza;  //Important

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

public class CheezeDecorator extends PizzaDecorator {
    public CheezeDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description()+ "Cheese Added, ";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 20;
    }
}

public class MushroomDecorator extends PizzaDecorator {
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description()+ "Mushroom Added, ";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 30;
    }
}

public class Main {

    public static void main(String[] args) {

        Pizza pizza = new BasicPizza();
        System.out.println(pizza.description()+" "+pizza.getCost());

        pizza = new CheezeDecorator(pizza);
        System.out.println(pizza.description()+" "+pizza.getCost());

        pizza = new MushroomDecorator(pizza);
        System.out.println(pizza.description()+" "+pizza.getCost());

    }
}

/*
Basic Pizza,  100
Basic Pizza, Cheese Added,  120
Basic Pizza, Cheese Added, Mushroom Added,  150
 */
```

Adapter can appear in automation

1. Selenium/Playwright abstraction
2. Reporting-tool integration
   ExtentReports → Framework Reporter interface

3. Logging-tool integration
   Log4j → Framework Logger interface

4. Test-data sources
   JSON / Excel / Database → common TestData interface

5. Third-party API clients
   External client → framework's API interface

---

Adapter: I can use adapters to hide third-party implementations such as reporting, test-data providers, or browser libraries behind framework-specific interfaces, making them easier to replace.

Proxy: Selenium PageFactory demonstrates proxy-style lazy element handling, and proxies can also intercept operations for logging or access control.

Decorator: I can wrap element or API-client behavior with reusable capabilities such as logging, waits, screenshots, retries, or reporting without modifying the underlying implementation.

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



