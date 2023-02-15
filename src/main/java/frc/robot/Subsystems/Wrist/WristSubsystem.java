package frc.robot.Subsystems.Wrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.lib.TalonFXFactory;

public class WristSubsystem {
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
        if (power > 0.0 && getSupinationPosition() < Constants.WristConstants.LEFT_LIMIT) {
            supinationMotor.set(ControlMode.PercentOutput, 0.0);
        } else if (power > 0.0 && getSupinationPosition() > Constants.ArmConstants.UPPER_LIMIT) {
            supinationMotor.set(ControlMode.PercentOutput, 0.0);
        } else {
            supinationMotor.set(ControlMode.PercentOutput, power);
        }
    }

    public double getSupinationPosition(){
        return supinationMotor.getSelectedSensorPosition();
    }

    public double getFlexionPosition(){
        return supinationMotor.getSelectedSensorPosition();
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
