package test.thread.car_creat;

import java.util.concurrent.TimeUnit;

//chassis 底盘
public class ChassisBuilder implements Runnable{
    private CarQueue cars;
    private int count = 0;
    public ChassisBuilder(CarQueue cars){
        this.cars = cars;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(500);
                //make cassis
                Car car = new Car(count++);
                System.out.println("ChassisBuilder create" + car);
                cars.add(car);
            }
        }catch (InterruptedException e){
            System.out.println("interrupt chassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}
