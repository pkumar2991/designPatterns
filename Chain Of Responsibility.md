#DesingPattern #BehavioralDP 
## GOF Definition
>Gives more than one object an opportunity to handle request by linking receiving objects together.

Decouple the client who sends the request to the object that handles it. The solution is a list of handler objects, also known as responding objects each capable to deal with a specific nature of request. If one handler object can’t handle a request, it passes it to the next object in the chain. At the end of the chain, there will be one or more generic handler objects implementing default behavior for the request.

## Components
- **Handler** (**AbstractSupportHandler**) Abstract base class acting as the interface to handle request. Optionally, but most often the **Handler** implements the chain links.
- **ConcreteHandler** (**TechnicalSupportHandler**, **BillingSupportHandler**, and **GeneralSupportHandler**.) Handles request, else passes it to the next successor of the handler chain
- **Client**(**RequestorClient**): Initiates a request that one of the chain of handlers ( a **ConcreteHandler**) handles

### Handler
```java
public abstract class AbstractSupportHandler {  
  
    public static int DESK=10;  
    public static int SUPERVISOR=20;  
    public static int MANAGER=30;  
    protected int level;  
  
    protected AbstractSupportHandler nextHandler;  
    public void setNextHandler(AbstractSupportHandler nextHandler){  
        this.nextHandler=nextHandler;  
    }  
  
    public void receiveRequest(int level, String message){  
        if(this.level <= level){  
            handleRequest(message);  
        }  
        if(nextHandler !=null){  
            nextHandler.receiveRequest(level, message);  
        }  
    }  
  
    abstract protected void handleRequest(String message);  
}
```

### Concrete Handler
```java
class DESK extends AbstractSupportHandler{  
  
    public DESK(int level){  
        this.level = level;  
    }  
  
    @Override  
    protected void handleRequest(String message) {  
        System.out.println("Desk Support handling the request:" + message);  
    }  
}  
class Supervisor extends AbstractSupportHandler{  
  
    public Supervisor(int level){  
        this.level = level;  
    }  
  
    @Override  
    protected void handleRequest(String message) {  
        System.out.println("Supervisor handling the request:" + message);  
    }  
}  
  
class Manager extends AbstractSupportHandler{  
  
    public Manager(int level){  
        this.level = level;  
    }  
  
    @Override  
    protected void handleRequest(String message) {  
        System.out.println("Manager handling the request:" + message);  
    }  
}
```

### Client
```java
public class RequestClient {  
  
    public static AbstractSupportHandler getHandlerChain(){  
        AbstractSupportHandler deskHandler = new DESK(AbstractSupportHandler.DESK);  
        AbstractSupportHandler supervisorHandler = new Supervisor(AbstractSupportHandler.SUPERVISOR);  
        AbstractSupportHandler managerHandler = new Manager(AbstractSupportHandler.MANAGER);  
  
        deskHandler.setNextHandler(supervisorHandler);  
        supervisorHandler.setNextHandler(managerHandler);  
        return deskHandler;  
    }  
  
}
```

```java
public static void main(String[] args) {  
        AbstractSupportHandler handler = RequestClient.getHandlerChain();  
        handler.receiveRequest(AbstractSupportHandler.MANAGER,"Update Address");  
}
```

`Output:`
Desk Support handling the request:Update Address
Supervisor handling the request:Update Address
Manager handling the request:Update Address

### **Reference implementations in JDK**

- [javax.servlet.Filter#doFilter()](https://docs.oracle.com/javaee/6/api/javax/servlet/Filter.html#doFilter%28javax.servlet.ServletRequest,%20javax.servlet.ServletResponse,%20javax.servlet.FilterChain%29 "dofilter chain")

The `doFilter` method of the Filter is called by the container each time a request/response pair is passed through the chain due to a client request for a resource at the end of the chain. The FilterChain passed in to this method allows the Filter to pass on the request and response to the next entity in the chain.

- [java.util.logging.Logger#log](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html#log%28java.util.logging.Level,%20java.lang.String%29 "logger.log")

If the logger is currently enabled for the given message level then the given message is forwarded to all the registered output Handler objects