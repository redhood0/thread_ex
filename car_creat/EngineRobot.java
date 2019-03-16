package test.thread.car_creat;

public class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool){
        super(pool);
    }
    @Override
    protected void perfomService() {
        System.out.println(this + " installing engine");
        assembler.getCar().addEngine();
    }


}
