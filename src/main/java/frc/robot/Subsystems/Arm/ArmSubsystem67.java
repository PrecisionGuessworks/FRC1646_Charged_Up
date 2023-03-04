// package frc.robot.Subsystems.Arm;

// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// import edu.wpi.first.math.controller.ProfiledPIDController;
// import edu.wpi.first.math.trajectory.TrapezoidProfile;
// import edu.wpi.first.wpilibj.AnalogPotentiometer;
// import edu.wpi.first.wpilibj.DutyCycleEncoder;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.constants.Constants;
// import frc.robot.constants.RobotMap;
// import frc.robot.constants.Constants.ArmConstants;

// public class ArmSubsystem67 extends SubsystemBase{
//     private static ArmSubsystem67 instance;

//     private TalonFX shoulderLeftMotor = new WPI_TalonFX(RobotMap.SHOULDER_LEFT_ID);

//     private TalonFX shoulderRightMotor = new WPI_TalonFX(RobotMap.SHOULDER_RIGHT_ID);
//     private TalonFX elbowLeftMotor = new WPI_TalonFX(RobotMap.ELBOW_LEFT_ID);
//     private TalonFX elbowRightMotor = new WPI_TalonFX(RobotMap.ELBOW_RIGHT_ID);

//     private AnalogPotentiometer shoulderPotentiometer = new AnalogPotentiometer(RobotMap.SHOULDER_POT_LEFT_ID, 100, Constants.ArmConstants.SHOULDER_POT_OFFSET);

//     private TrapezoidProfile.Constraints shoulderConstraints = new TrapezoidProfile.Constraints(ArmConstants.SHOULDER_CRUISE, ArmConstants.SHOULDER_ACCELERAION);
//     private TrapezoidProfile.Constraints elbowConstraints = new TrapezoidProfile.Constraints(ArmConstants.ELBOW_CRUISE, ArmConstants.ELBOW_ACCELERAION);

//     private ProfiledPIDController m_controllerShoulder = new ProfiledPIDController(ArmConstants.GAINS_SHOULDER.kP, ArmConstants.GAINS_SHOULDER.kI, ArmConstants.GAINS_SHOULDER.kD, shoulderConstraints);
//     private ProfiledPIDController m_controllerElbow = new ProfiledPIDController(ArmConstants.GAINS_ELBOW.kP, ArmConstants.GAINS_ELBOW.kI, ArmConstants.GAINS_ELBOW.kD, elbowConstraints);

//     private JointConfig shoulderJoint = new JointConfig(ArmConstants.STAGE1_MASS, ArmConstants.STAGE1_LENGTH, ArmConstants.STAGE1_MOI, ArmConstants.STAGE1_CGRADIUS, ArmConstants.STAGE1_MOTOR);
//     private JointConfig elbowJoint = new JointConfig(ArmConstants.STAGE2_MASS, ArmConstants.STAGE2_LENGTH, ArmConstants.STAGE2_MOI, ArmConstants.STAGE2_CGRADIUS, ArmConstants.STAGE2_MOTOR);

//     private DJArmFeedforward doubleJointedFeedForwards = new DJArmFeedforward(shoulderJoint, elbowJoint);

//     private Setpoint setpoint;
//     private double shoulderSetpoint;
//     private double elbowSetpoint;
    

//     private ArmSubsystem67(){
//         // Set Neutral Mode to Brake
//         shoulderLeftMotor.setNeutralMode(NeutralMode.Brake);
//         shoulderRightMotor.setNeutralMode(NeutralMode.Brake);
//         elbowLeftMotor.setNeutralMode(NeutralMode.Brake);
//         elbowRightMotor.setNeutralMode(NeutralMode.Brake);

//         shoulderLeftMotor.configNeutralDeadband(ArmConstants.NEUTRAL_DEADBAND);
//         shoulderRightMotor.configNeutralDeadband(ArmConstants.NEUTRAL_DEADBAND);
//         elbowLeftMotor.configNeutralDeadband(ArmConstants.NEUTRAL_DEADBAND);
//         elbowRightMotor.configNeutralDeadband(ArmConstants.NEUTRAL_DEADBAND);

//         SupplyCurrentLimitConfiguration armCurrentLimit = new SupplyCurrentLimitConfiguration(true, 20, 30, 0.2);
//         shoulderLeftMotor.configSupplyCurrentLimit(armCurrentLimit);
//         shoulderRightMotor.configSupplyCurrentLimit(armCurrentLimit);
//         elbowLeftMotor.configSupplyCurrentLimit(armCurrentLimit);
//         elbowRightMotor.configSupplyCurrentLimit(armCurrentLimit);

//         // TODO: Do these work????
//         shoulderLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
//         shoulderRightMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog);

//         shoulderLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
//         shoulderRightMotor.setInverted(TalonFXInvertType.Clockwise);
//         elbowLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
//         elbowRightMotor.setInverted(TalonFXInvertType.Clockwise);

//         shoulderLeftMotor.configFeedbackNotContinuous(true, ArmConstants.TIMEOUT);
//         shoulderRightMotor.configFeedbackNotContinuous(true, ArmConstants.TIMEOUT);
//     }

//     public static ArmSubsystem67 getInstance(){
//         if (instance == null){
//           instance = new ArmSubsystem67();
//         }
//         return instance;
//       }
//     @Override
//     public void periodic() {
//         // This method will be called once per scheduler run
//         shoulderLeftMotor.setSelectedSensorPosition(0,0,0);
//         shoulderRightMotor.setSelectedSensorPosition();

//     }
// }
