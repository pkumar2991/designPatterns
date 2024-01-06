#DesingPattern #BehavioralDP 

# Definition

**_The Template Design Pattern is a behavioral design pattern that defines the basic structure of an algorithm in a superclass, while allowing subclasses to provide specific implementations of certain steps of the algorithm without modifying its overall structure. It promotes code reuse and enforces a common algorithm structure across multiple subclasses._**

### Create Template Abstract Class
```java
abstract class  BuildSoftwareTemplate{  
  
    public final void buildSoftware(){  
        gatherRequirements();  
        createSystemDesing();  
        developSoftware();  
        doTesting();  
        deliverProduct();  
    }  
  
    abstract void createSystemDesing();  
    abstract void gatherRequirements();  
    abstract void developSoftware();  
    abstract void doTesting();  
  
    public void deliverProduct(){  
        System.out.println("Software is delivered to the customer through cloud services.");  
    }  
}
```

### Create Implementation Classes
```java
class IBM extends BuildSoftwareTemplate{  
  
    @Override  
    void createSystemDesing() {  
        System.out.println("IBM : Using PAINT to create system design");  
    }  
  
    @Override  
    void gatherRequirements() {  
        System.out.println("IBM: Using JIRA to gather requirements");  
    }  
  
    @Override  
    void developSoftware() {  
        System.out.println("IBM: Developing software using JAVA");  
    }  
  
    @Override  
    void doTesting() {  
        System.out.println("IBM: Using Manual Testing for the validation of the application");  
    }  
}
```

```java
class INFOSYS extends BuildSoftwareTemplate{  
  
    @Override  
    void createSystemDesing() {  
        System.out.println("INFOSYS : Using WebSketch to create system design");  
    }  
  
    @Override  
    void gatherRequirements() {  
        System.out.println("INFOSYS: Using Bamboo to gather requirements");  
    }  
  
    @Override  
    void developSoftware() {  
        System.out.println("INFOSYS: Developing software using .NET");  
    }  
  
    @Override  
    void doTesting() {  
        System.out.println("INFOSYS: Using Automation Testing for the validation of the application");  
    }  
}
```

### Client Class
```java
public static void main(String[] args) {  
    BuildSoftwareTemplate bs = new IBM();  
    bs.buildSoftware();  
  
    BuildSoftwareTemplate bs1 = new INFOSYS();  
    bs1.buildSoftware();  
}
```

`Output:`
IBM: Using JIRA to gather requirements
IBM : Using PAINT to create system design
IBM: Developing software using JAVA
IBM: Using Manual Testing for the validation of the application
Software is delivered to the customer through cloud services.
INFOSYS: Using Bamboo to gather requirements
INFOSYS : Using WebSketch to create system design
INFOSYS: Developing software using .NET
INFOSYS: Using Automation Testing for the validation of the application
Software is delivered to the customer through cloud services.
