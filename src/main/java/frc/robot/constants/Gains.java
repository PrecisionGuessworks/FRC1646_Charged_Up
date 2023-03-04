package frc.robot.constants;

public class Gains {
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    public final double kIzone;

    public Gains(double _kP, double _kI, double _kD, double _kF, double _kIzone){
        kP = _kP;
        kI = _kI;
        kD = _kD;
        kF = _kF;
        kIzone = _kIzone;
    }
}
