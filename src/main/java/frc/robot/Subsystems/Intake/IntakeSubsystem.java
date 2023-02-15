package frc.robot.Subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class IntakeSubsystem extends SubsystemBase {
    private static IntakeSubsystem instance;
    private CANSparkMax leftIntake, rightIntake;

    private IntakeSubsystem() {
        leftIntake = new CANSparkMax(RobotMap.INTAKE_LEFT_ID, MotorType.kBrushless);
        rightIntake = new CANSparkMax(RobotMap.INTAKE_RIGHT_ID, MotorType.kBrushless);

        rightIntake.setInverted(true);
    }

    public static IntakeSubsystem getInstance() {
        if (instance == null){
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public void setPower(double power) {
        leftIntake.set(power);
        rightIntake.set(power);
    }

    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
