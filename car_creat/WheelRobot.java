package test.thread.car_creat;

public class WheelRobot extends Robot{
    public WheelRobot(RobotPool pool){
        super(pool);
    }
    @Override
    protected void perfomService() {
        System.out.println(this + " installing WheelRobot");
        assembler.getCar().addWheels();
    }
}
