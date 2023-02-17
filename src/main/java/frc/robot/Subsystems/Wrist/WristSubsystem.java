package frc.robot.Subsystems.Wrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.lib.TalonFXFactory;

public class WristSubsystem extends SubsystemBase{
    private static WristSubsystem instance;
    private TalonFX supinationMotor, flexionMotor;

    private WristSubsystem() {
        supinationMotor = TalonFXFactory.makeTalonFX(RobotMap.SUPINATION_ID); 
        flexionMotor = TalonFXFactory.makeTalonFX(RobotMap.FLEXION_ID); 

        supinationMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
        flexionMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

        supinationMotor.setInverted(TalonFXInvertType.CounterClockwise);
        flexionMotor.setInverted(TalonFXInvertType.Clockwise);

        supinationMotor.setNeutralMode(NeutralMode.Brake);
        flexionMotor.setNeutralMode(NeutralMode.Brake);
    }

    public static WristSubsystem getInstance() {
        if (instance == null){
            instance = new WristSubsystem();
        }
        return instance;
    }

    public void setSupinationPower(double power){
        supinationMotor.set(ControlMode.PercentOutput, power);
    }

    private boolean isWristTooFarLeft(double power) {
        return power < 0.0 && getSupinationPosition() < Constants.WristConstants.LEFT_SUPINATION_LIMIT;
    }
    private boolean isWristTooFarRight(double power) {
        return  power > 0.0 && getSupinationPosition() > Constants.WristConstants.RIGHT_SUPINATION_LIMIT;
    }

    public void setSupinationPowerWithSafeties(double power){
        if (isWristTooFarLeft(power)) {
            setSupinationPower(0);
        } else if (isWristTooFarRight(power)) {
            setSupinationPower(0);
        } else {
            setSupinationPower(power);
        }
    }

    public void setFlexionPower(double power){
        flexionMotor.set(ControlMode.PercentOutput, power);
    }

    private boolean isWristTooHigh(double power){
        return power > 0.0 && getFlexionPosition() > Constants.WristConstants.UPPER_FLEXION_LIMIT;
    }
    private boolean isWristTooLow(double power){
        return power < 0.0 && getFlexionPosition() < Constants.WristConstants.LOWER_FLEXION_LIMIT;
    }
    public void setFlexionPowerWithSafeties(double power){
        if (isWristTooHigh(power)) {
            setFlexionPower(0);
        } else if (isWristTooLow(power)) {
            setFlexionPower(0);
        } else {
            setFlexionPower(power);
        }
    }

    public double getSupinationPosition(){
        return supinationMotor.getSelectedSensorPosition();
    }

    public double getFlexionPosition(){
        return flexionMotor.getSelectedSensorPosition();
    }

    public void displaySupinationPosition(){
        SmartDashboard.putString("Supination", getSupinationPosition() + "");
      }

    public void displayFlexionPosition(){
        SmartDashboard.putString("Flexion", getFlexionPosition() + "");
    }

    public void displayWristPositions(){
        displayFlexionPosition();
        displaySupinationPosition();
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
