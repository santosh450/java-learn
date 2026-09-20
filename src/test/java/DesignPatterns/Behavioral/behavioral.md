## Communication between objects

### 1. Observer Design Pattern
 - one object automatically notifies multiple other objects whenever any change or any event occurs.
 - When the state of one object changes, all objects that depend on it are automatically notified.

```java
public interface Subscriber {

    void update(String videoTitle);
}

//Observer
public class YouTubeSubscriber implements Subscriber {

    private String name;

    public YouTubeSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " received notification: " + videoTitle);
    }
}

//Subject

public class YouTubeChannel {

    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String title) {
        System.out.println("Uploaded: " + title);
        notifySubscribers(title);
    }

    private void notifySubscribers(String title) {

        for (Subscriber subscriber : subscribers) {
            subscriber.update(title);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        Subscriber santosh = new YouTubeSubscriber("Santosh");

        Subscriber ram = new YouTubeSubscriber("Ram");

        Subscriber john = new YouTubeSubscriber("John");

        channel.subscribe(santosh);
        channel.subscribe(ram);
        channel.subscribe(john);

        channel.uploadVideo("Observer Design Pattern");
    }
}
```

so The client DOESN't manually do:

santosh.update(...);
ram.update(...);
john.update(...);

Instead, The Subject automatically notifies registered observers.

---

### 2. Strategy design pattern

- It defines a family of algorithms, puts each algorithm in a separate class, and makes them interchangeable at runtime.

Problem:

Interface Notification - send(), encrypt(), Compress()

Class Email     - send(email),  encrypt(AES),   Compress(ZIP)
Class SMS       - send(SMS),    encrypt(AES),   Compress(GZIP)
Class Push      - send(Push),   encrypt(RSA),   Compress(ZIP)

due to duplicate code
encrypt(AES) - email, SMS
Compress(ZIP) - email, Push

Temporary Solution:
Interfaces: AESNotification, RSANotification, ZIPNotification, GZIPNotification

Too complex if methods are increasing, interfaces are increasing

Before:

```java

public interface Notification {
    void send();
    void encrypt();
    void compress();
}

public class Email implements Notification{
    @Override
    public void send() {
        System.out.println("sending email");
    }

    @Override
    public void encrypt() {
        System.out.println("AES encryption");
    }

    @Override
    public void compress() {
        System.out.println("ZIP compression");
    }
}

public class Sms implements Notification{
    @Override
    public void send() {
        System.out.println("Notification sms");
    }

    @Override
    public void encrypt() {
        System.out.println("AES encryption");
    }

    @Override
    public void compress() {
        System.out.println("GZIP compression");
    }
}

public class Push implements Notification{
    @Override
    public void send() {
        System.out.println("Push Notification");
    }

    @Override
    public void encrypt() {
        System.out.println("RSA encryption");
    }

    @Override
    public void compress() {
        System.out.println("ZIP compression");
    }
}

```

Solution:
```java

// Lets Create strategy for Encryption, compression (these having duplicate logic)

public interface EncryptionStrategy {
    void encrypt();
}

public class AESEncryption implements EncryptionStrategy{
    @Override
    public void encrypt() {
        System.out.println("AES encryption");
    }
}

public class RSAEncryption implements EncryptionStrategy{
    @Override
    public void encrypt() {
        System.out.println("RAS encryption");
    }
}


public interface CompressionStrategy {
    void compress();
}

public class ZIPCompression implements CompressionStrategy{
    @Override
    public void compress() {
        System.out.println("ZIP Compression");
    }
}

public class GZIPCompression implements CompressionStrategy{
    @Override
    public void compress() {
        System.out.println("GZIP Compression");
    }
}

// Update Notification interface to Abstract and implement methods which are duplicate earlier (encrypt, compress)

public abstract class Notification {

    EncryptionStrategy encryptionStrategy;
    CompressionStrategy compressionStrategy;

    public Notification(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
        this.compressionStrategy = compressionStrategy;
    }

    abstract void send();

    void encrypt(){
        encryptionStrategy.encrypt();
    }

    void compress(){
        compressionStrategy.compress();
    }
}

// Now remove duplicate methods (encrypt, compress) and keep methods which have different logic (send)

public class Email extends Notification{

    public Email(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("sending email");
    }

////    @Override
////    public void encrypt() {
////        System.out.println("AES encryption");
////    }
////
////    @Override
////    public void compress() {
////        System.out.println("ZIP compression");
//    }
}

package DesignPatterns.Behavioral.Strategy;

public class Sms extends Notification{

    public Sms(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("Notification sms");
    }

//    @Override
//    public void encrypt() {
//        System.out.println("AES encryption");
//    }
//
//    @Override
//    public void compress() {
//        System.out.println("GZIP compression");
//    }
}

public class Push extends Notification{

    public Push(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("Push Notification");
    }

//    @Override
//    public void encrypt() {
//        System.out.println("RSA encryption");
//    }
//
//    @Override
//    public void compress() {
//        System.out.println("ZIP compression");
//    }
}


// Client
public class Main {
    public static void main(String[] args) {
        Notification email = new Email(new AESEncryption(), new ZIPCompression());
        email.encrypt();
        email.compress();

        Notification sms = new Sms(new AESEncryption(), new GZIPCompression());
        sms.encrypt();
        sms.compress();
    }
}


```

### 3. Chain of Responsibility Design Pattern

 - A request is passed through a chain of handlers. Each handler decides whether to handle the request, reject it, or pass it to the next handler.

Solution:
```java

// Issue Pojo

package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class Issue {
    private String priority;
    private String status;

    public Issue(String priority, String status) {
        this.priority = priority;
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public Issue setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Issue setStatus(String status) {
        this.status = status;
        return this;
    }
}

// Handler
public interface SupportHandler {
    void handleRequest(Issue issue);
}

public class ChatBot implements SupportHandler {

    private SupportHandler handler;

    public ChatBot(){
        handler = new CustomerExecutive();      // Next level
    }
    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 1")){
            //chatbot logic
            System.out.println("chatbot issue resolved");
            issue.setStatus("SUCCESS");
        }else{
            System.out.println("chatbot couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue);
        }

    }
}

public class CustomerExecutive implements SupportHandler {

    private SupportHandler handler;

    public CustomerExecutive(){
        handler = new TechTeam();       // Next level
    }

    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 2")){
            //executive logic
            System.out.println("executive issue resolved");
            issue.setStatus("SUCCESS");
        }else {
            System.out.println("Customer couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue );
        }
    }
}


public class TechTeam implements SupportHandler {
    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 3")){
            //tech team logic
            System.out.println("tech team issue resolved");
            issue.setStatus("SUCCESS");
        }else {
            System.out.println("Issue couldn't be resolved.");
            issue.setStatus("Failed");
        }
    }
}

//Client
public class Client {

    private SupportHandler handler;

    public Client(){
        handler = new ChatBot();        // first handler
    }

    public void raiseIssue(Issue issue){
        handler.handleRequest(issue);
    }
}

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.raiseIssue(new Issue("Level3", "New"));
    }

}

```

improvement (avoiding tightly coupling)
update constructors
```java
public class Client {

    private SupportHandler handler;

    public Client(SupportHandler handler) {
        this.handler = handler;
    }

    public void raiseIssue(Issue issue){
        handler.handleRequest(issue);
    }
}

public class ChatBot implements SupportHandler {

    private SupportHandler handler;

    public ChatBot(SupportHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 1")){
            //chatbot logic
            System.out.println("chatbot issue resolved");
            issue.setStatus("SUCCESS");
        }else{
            System.out.println("chatbot couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue);
        }

    }
}

public class CustomerExecutive implements SupportHandler {

    private SupportHandler handler;

    public CustomerExecutive(SupportHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 2")){
            //executive logic
            System.out.println("executive issue resolved");
            issue.setStatus("SUCCESS");
        }else {
            System.out.println("Customer couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue );
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Client client01 = new Client(new ChatBot(new CustomerExecutive(new TechTeam())));
        client01.raiseIssue(new Issue("Level3", "New"));

        Client client02 = new Client(new CustomerExecutive(new TechTeam()));
    }

}


```

Observer Pattern in Test Automation

                 Test Execution
                    Subject
                       |
                 Test Failed
                       |
          ---------------------------
          |            |            |
          ↓            ↓            ↓
     Screenshot      Report        Logging
      Observer       Observer      Observer

---
Chain of Responsibility in Test Automation

Request
↓
Add Authentication
↓
Add Common Headers
↓
Add Correlation ID
↓
Logging
↓
Send Request

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