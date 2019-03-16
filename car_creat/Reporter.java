package test.thread.car_creat;

public class Reporter implements Runnable{
    private CarQueue cars;
    public Reporter(CarQueue cars){
        this.cars = cars;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println(cars.take());
            }
        }catch (InterruptedException e){
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("reprot off");
    }
}
