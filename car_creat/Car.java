package test.thread.car_creat;

public class Car {
    private final int id;
    //driveTrain动力传动系统
    private boolean engine = false,driveTrain = false, wheels = false;

    public Car(int id) {
        this.id = id;
    }
    //Empty car
    public Car(){
        id = -1;
    }
    public synchronized int getId(){
        return id;
    }
    public synchronized void addEngine(){
        engine = true;
    }
    public synchronized void addDriveTrain(){
        driveTrain = true;
    }
    public synchronized void addWheels(){
        wheels = true;
    }
    @Override
    public String toString(){
        return "Car" + id + "[ engine: " + engine + "driveTrain  :" + driveTrain + "wheels :" + wheels + "]";
    }


}
