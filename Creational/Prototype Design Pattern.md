### Definition
Prototype Design a creational design pattern. 

### When to use Prototype Design Pattern?
The prototype Design Pattern is beneficial in the following scenarios:

1. Expensive Object Creation
2. Complex Object Initialization
3. Immutable Instances

Let's suppose, there is need to create 1000 objects and creating an object cost 1$ . This would cost 1000$ which would be inefficient approach. Likewise, high CPU utilization in creating these objects would impact the application.

### Real world Example
A Weather application pulls weather data for the from third party which charges 1$ per api call. If 10000 users active from the same geo location, there would be 10000 times api call to get the data which would be very costly for website owner. 

Prototype Design Pattern provides solution to this problem and could help the website owner to avoid the loss. We could create make a call to third party when first user need weather data for its geo location. Later, any request for weather data from the same geo location, the cloned data would be returned.

### Without Prototype Design Pattern
*loadWeatherData()* - Fetches weather data from third party applications which cost 1$ per API call.

```java
class Weather{  
    private double temp;  
    private double tempMin;  
    private double tempMax;  
    private double pressure;  
    private int humidity;  
  
    // Each call cost 1$  
    public void loadWeatherData(long pincode){  
        // REST API call to get data from third party  
        this.temp = 275.83;  
        this.tempMin = 275.83;  
        this.tempMax = 277.894;  
        this.pressure = 994.71;  
        this.humidity = 96;  
    }  
  
    @Override  
    public String toString() {  
        return "Weather{" +  
                "temp=" + temp +  
                ", tempMin=" + tempMin +  
                ", tempMax=" + tempMax +  
                ", pressure=" + pressure +  
                ", humidity=" + humidity +  
                '}';  
    }  
}
```

**Client calls the API to get data for 10000 users resides in same area PIN CODE.**
```java
public static void main(String[] args) {  
    // for 10000 users, there would be 10000 api call  
    IntStream.rangeClosed(1,10000).forEach(data -> {  
        Weather weather = new Weather();  
        weather.loadWeatherData(560037);  
        System.out.println(weather);  
    });  
}
```

This solution is financially inefficient. Let's mitigate the problem using Prototype Design Pattern.

### With Prototype Design Pattern
