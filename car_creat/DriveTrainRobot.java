package test.thread.car_creat;

public class DriveTrainRobot extends Robot{
    public DriveTrainRobot(RobotPool pool){
        super(pool);
    }
    @Override
    protected void perfomService() {
        System.out.println(this + " installing DriveTrainRobot");
        assembler.getCar().addDriveTrain();
    }
}
