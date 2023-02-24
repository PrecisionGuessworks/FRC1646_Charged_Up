package frc.robot.Subsystems.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;

public class IntakeSubsystem extends SubsystemBase {
    private static IntakeSubsystem instance;
    private CANSparkMax leftIntake, rightIntake;

    private IntakeSubsystem() {
        leftIntake = new CANSparkMax(RobotMap.INTAKE_LEFT_ID, MotorType.kBrushless);
        rightIntake = new CANSparkMax(RobotMap.INTAKE_RIGHT_ID, MotorType.kBrushless);

        leftIntake.setInverted(false);
        rightIntake.setInverted(true);

        leftIntake.setIdleMode(IdleMode.kBrake);
        rightIntake.setIdleMode(IdleMode.kBrake);

        // Current Limiting???
        leftIntake.setSmartCurrentLimit(Constants.IntakeConstants.NEO550_CURRENT_LIMIT);
        rightIntake.setSmartCurrentLimit(Constants.IntakeConstants.NEO550_CURRENT_LIMIT);
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
