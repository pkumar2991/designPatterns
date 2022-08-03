# Design Patterns
## Builder Design Pattern (Creational Design Pattern)
### When to use Builder design pattern?
Builder design pattern can be used in following cases:
* when creation an object of a class is a complex process
* Suppose are number of fields that need to be provided while creating an object even though only few of them are required. This can be done by telescoping constructor or using getter/setters but it leads to runtime error. While calling constructor, if the parameters are passed in incorrect sequence, there would be no compile error but at runtime reproduce expected behavior. So to avoid these error, *Builder design Pattern* would be right choice.


#### Secenario - 1
**Example -** Car company decided to launch two modal of Car -*Sedan* and *SUV*. The process of manufacturning these cars is very complex. An individual has to follow the process and if anything missed there will be defect in car introduced. So company has released a manual which have all the process and precautions that need to take care while manufacturing the car.

Let's creat a car class.

```java

class Car {
    private String name;
    private String color;
    private int seat;

    private String engin;

    private String wheels;

    private String steering;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngin() {
        return engin;
    }

    public void setEngin(String engin) {
        this.engin = engin;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

    public String getSteering() {
        return steering;
    }

    public void setSteering(String steering) {
        this.steering = steering;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", seat=" + seat +
                ", engin='" + engin + '\'' +
                ", wheels='" + wheels + '\'' +
                ", steering='" + steering + '\'' +
                '}';
    }
}
```
Now, we have created the Car class. Now, lets gather all the steps that need to be taken while creating car. *CarBuilder* interface has methods which will be executed while manufacturing car.

```java
interface CarBuilder {

    void addName();

    void addColor();

    void addSteering();

    void addWheels();

    void addEngine();

    void buildSeat();

    Car getCar();
}
```
*SedanCarBuilder* and *SUVCarBuilder* are two differnt modals of Car.

```java

class SedanCarBuilder implements CarBuilder {
    Car car = new Car();

    @Override
    public void addName() {
        car.setName("Sedan");
    }

    @Override
    public void addColor() {
        car.setColor("White");
    }

    @Override
    public void addSteering() {
        car.setSteering("Normal Steering");
    }

    @Override
    public void addWheels() {
        car.setWheels("Apolo Tyres");
    }

    @Override
    public void addEngine() {
        car.setEngin("1000 bhp power");
    }

    @Override
    public void buildSeat() {
        car.setSeat(4);
    }

    @Override
    public Car getCar() {
        return car;
    }
}

class SUVCarBuilder implements CarBuilder {

    Car car = new Car();

    @Override
    public void addName() {
        car.setName("Fortuner");
    }

    @Override
    public void addColor() {
        car.setColor("Blue");
    }

    @Override
    public void addSteering() {
        car.setSteering("Power Steering");
    }

    @Override
    public void addWheels() {
        car.setWheels("CEAT Tyres");
    }

    @Override
    public void addEngine() {
        car.setEngin("1500 bhp power");
    }

    @Override
    public void buildSeat() {
        car.setSeat(8);
    }

    @Override
    public Car getCar() {
        return car;
    }
}
```
So far, we are have Car and two different modals of Car. We can create car now using these car builders but we must need to know the sequence of steps. This creates dependecy on client that client must aware about the process and if any change happens in the car manufacuring process, car company must let client know about the updated process. We can get rid off this dependency and follow the manual provided by the company. If any change happens in future in the process, manual would be updated and client can refer that and update at only one place. 

Here, we have CarDirector which follows the Car Manual and follow the process.
```java
class CarDirector {
    CarBuilder carBuilder;

    public CarDirector(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public void build() {
//  Car Decorator knows the process of building specific car
        carBuilder.addName();
        carBuilder.addColor();
        carBuilder.addWheels();
        carBuilder.addEngine();
        carBuilder.addSteering();
        carBuilder.buildSeat();
    }
}

class BuilderDesignPattern {
    public static void main(String[] args) {
        CarBuilder carBuilder = new SUVCarBuilder();
        CarDirector carDirector = new CarDirector(carBuilder);
        carDirector.build();
        Car car = carBuilder.getCar();
        System.out.println(car);
    }
}
```
Using CarDecorator to get the manufactured car turns the complex process into simple process.

#### Secenario - 2
```java
class Form{
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String billingAddress;
    private String shippingAddress;

    public static class FormBuilder{
        private String fName;
        private String lName;
        private String email;
        private String phone;
        private String city;
        private String state;
        private String country;
        private String pin;
        private String billingAddress;
        private String shippingAddress;

        public FormBuilder(String fName, String lName, String phone) {
            this.fName = fName;
            this.lName = lName;
            this.phone = phone;
        }
        public FormBuilder addEmail(String email){
            this.email = email;
            return this;
        }
        public FormBuilder addCity(String city){
            this.city = city;
            return this;
        }
        public FormBuilder addState (String state){
            this.state = state;
            return this;
        }
        public FormBuilder addCountry(String country){
            this.country = country;
            return this;
        }
        public FormBuilder addPin(String pin){
            this.pin = pin;
            return this;
        }

        public Form build(){
            return new Form(this);
        }
    }

    private Form(FormBuilder formBuilder){
        this.fName = formBuilder.fName;
        this.lName = formBuilder.lName;
        this.email = formBuilder.email;
        this.phone = formBuilder.phone;
        this.city = formBuilder.city;
        this.state = formBuilder.state;
        this.country = formBuilder.country;
        this.pin  = formBuilder.pin;
        this.billingAddress = formBuilder.billingAddress;
        this.shippingAddress = formBuilder.shippingAddress;
    }

    @Override
    public String toString() {
        return "Form{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pin='" + pin + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                '}';
    }
}
```
