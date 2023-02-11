package frc.robot.lib;

public class PIDConfig {
    public double kP, kI, kD, kF;

    public PIDConfig(double kP, double kI, double kD, double kF){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }
}