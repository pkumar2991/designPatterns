#BehavioralDP 
# Strategy Desgin Pattern (Behavioral Pattern)
### Definition
>The Strategy Design Pattern defines a family of algorithms, encapsulating each one, and making them interchangeable. Strategy lets the algorithm vary independently from the clients that use it.

### Problem Statement
In the  current era, everything is data driven  which results huge volume of data getting generated every second. These data must be stored somewhere. So, storing data is a major problem for giant companies. So, they compress the file before storing them to the storage and uncompress them on demand. They use different file compression technique for this purpose.

There could be many compression techniques. Let's build a design for a client to use these techniques.

```java
class CompressFile {  
    private int algoType;  
  
    public CompressFile(int algoType) {  
        this.algoType = algoType;  
    }  
  
    void compress() {  
        switch (algoType) {  
            case 1:  
                System.out.println("Compressed file size is 10% lesser than actual size.");  
                break;  
            case 2:  
                System.out.println("Compressed file size is 30% lesser than actual size.");  
                break;  
            case 3:  
                System.out.println("Compressed file size is 60% lesser than actual size.");  
                break;  
            default:  
                System.out.println("File is NOT compressed.");  
        }  
    }  
}
```

Client Program :

```java
public static void main(String[] args) {  
    CompressFile file = new CompressFile(1);  
    file.compress();  
}
```

This solution looks fine if we have fixed number of ways to compress a file. In future, may be an expert come up with a more effective compression technique. So , we have to add another case to append the new technique which forced us to test the entire functionallity.

We can use **Strategy Design Pattern** to avoid altering the existing code.

### Solution
We segregated the compress file techniques to a separate class for each technique. An interface is created to apply the same contract for all the classes.

```java
interface ICompressFile {  
    void doCompress();  
}
```

All concrete classes implements the interface and provides the definition of the method.

*Algorithm - 1*

```java
class CompressFileAlgo1 implements ICompressFile {  
    @Override  
    public void doCompress() {  
        System.out.println("Compressed file size is 10% lesser than actual size.");  
    }  
}
```

*Algorithm - 2*

```java
class CompressFileAlgo2 implements ICompressFile {  
    @Override  
    public void doCompress() {  
        System.out.println("Compressed file size is 30% lesser than actual size.");  
    }  
}
```

*Algorithm - 3*

```java
class CompressFileAlgo3 implements ICompressFile {  
    @Override  
    public void doCompress() {  
        System.out.println("Compressed file size is 40% lesser than actual size.");  
    }  
}
```

*Client Code :*

```java
public static void main(String[] args) {  
    ICompressFile algo = new CompressFileAlgo1();  
    CompressFileContext file = new CompressFileContext(algo);  
    file.doCompress();  
  
    algo = new CompressFileAlgo2();  
    file = new CompressFileContext(algo);  
    file.doCompress();  
}
```

Now, if new alogrithm is developed by an expert (reduce file size by 80%) then to append that logic into this code would be easy. 
- Create a **CompressFileAlgo4** and implements **ICompressFile** interface
- Write the new alogrithm definition in the doCompress() method
- Let client use this new algorithm for compressing their file

```java
class CompressFileAlgo4 implements ICompressFile {  
    @Override  
    public void doCompress() {  
        System.out.println("Compressed file size is 80% lesser than actual size.");  
    }  
}
```

*Client Code :*

```java
public static void main(String[] args) {  
    ICompressFile algo = new CompressFileAlgo4();  
    CompressFileContext file = new CompressFileContext(algo);  
    file.doCompress();  
}
```

References:

- https://www.javacodegeeks.com/2015/09/strategy-design-pattern.html
- https://www.oodesign.com/strategy-pattern
- https://refactoring.guru/design-patterns/strategy

***
Another analogy which explains the Strategy design pattern using class instead of interface.

### Problem Statement
Laptop manufacturer needs an application which helps to implement different strategies of processing instructions of a user by laptop. In future, there could be new strategies discovered, so application should be flexible to accommodate this change.

### Naive Approach
Laptop class having default processing Instructions and authentication mechanism.
```java
class Laptop{
	void processInstructions() {
		System.out.println("Default processing mechanism.");
	}
	void authentication() {
		System.out.println("Defalut authentication mechanism.");
	}
}
```

Professional Laptop and GamingLaptop using same processing mechanism. 

```java
class ProfessionalLaptop extends Laptop{
	
	void processInstructions() {
		System.out.println("Processing using custom mechanism- PROC1.");
	}
	
}

class GamingLaptop extends Laptop{
	
	void processInstructions() {
		System.out.println("Processing using custom mechanism- PROC1.");
	}
	
}
```

**What's the problem in this code?**  
We are writing redundant code as the Professional Laptop and Gaming Laptop both using same mechanism. In future, if the mechanism is being updated, these classes need to be updated. This incurs development as well as testing effort. We need to mitigate this problem and cut down this additional effort.

Let's fix this.

### Better Approach
Strategy Design Pattern enable us to write loose coupled code with low maintenance effort for the aforementioned scenario.

Define the strategy
```Java
interface ProcessingStrategy {
	void processInstructions();
}

class ProfessionalProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using custom mechanism- PROC1.");
	}

}

class NormalProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using normal mechanism.");
	}

}

class SuperProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using super fast mechanism.");
	}

}```

Here, there are three strategies defined for processing instructions. May be in future there could be more mechanism invented.

Now, design Laptop class and its subclasses.

```java
class Laptop {

	ProcessingStrategy strategy;

	public Laptop(ProcessingStrategy strategy) {
		this.strategy = strategy;
	}

	void processInstructions() {
		strategy.processInstructions();
	}
	
	void authentication() {
		System.out.println(this.getClass().getSimpleName() + "> Defalut authentication mechanism.");
	}
}

class SimpleLaptop extends Laptop {

	public SimpleLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}
}

class ProfessionalLaptop extends Laptop {

	public ProfessionalLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}

}

class GamingLaptop extends Laptop {

	public GamingLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}
}
```

Client class which would pass the processing mechanism strategy at runtime.

```java
public class StrategyDesignPattern {

	public static void main(String[] args) {
		Laptop laptop = new SimpleLaptop(new NormalProcessingStrategy());
		laptop.processInstructions();
		laptop.authentication();
		
		Laptop profLaptop = new ProfessionalLaptop(new ProfessionalProcessingStrategy());
		profLaptop.processInstructions();
		
		Laptop simpleLaptop = new SimpleLaptop(new SuperProcessingStrategy());
		simpleLaptop.processInstructions();
		
	}
}
```

For each type of laptop, processing mechanism can be decided at runtime. If there is new mechanism invented, there is no need to modify the existing code which follows the Open for extension and closed for modification principle.

### Summary
The Strategy design pattern is like having different tools in a toolbox for a specific job. You can swap out tools (strategies) depending on what you need to do without changing how you use them. It keeps your code flexible and easy to maintain by separating the implementation details of different algorithms from the code that uses them.
