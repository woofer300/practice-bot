package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase{
    private final WPI_TalonSRX m_TurretMotor = new WPI_TalonSRX(TurretConstants.TURRET_TALON);
    private final CANifier m_QuadEncoder = new CANifier(TurretConstants.TURRET_TALON);

    public TurretSubsystem() {
        m_TurretMotor.configMotionProfileTrajectoryInterpolationEnable(true);
        m_TurretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    }
    /*
    public void PIDControl(double error) {
        double totalPOS = error + m_AdjustedEncoer;
        if(totalPOS < 130 && totalPOS > -130)
        {
            m_TurretMotor.set(ControlMode.MotionMagic, (int)((error/360 * 12) * GearRatio), DemandType.Neutral, 1);
        }
    }
    */

    public void configureTurret(double velocity, double acceleration) {
        //Configures the Talon and Encoder to default to avoid unexpected behavior
        m_TurretMotor.configFactoryDefault();
        m_QuadEncoder.configFactoryDefault();
        
        //Selects what PID slot to use on the controller
        m_TurretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);

        //Selects which sensor to use for PID control
        m_TurretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, TurretConstants.kPIDLoopIdx,
				TurretConstants.kTimeoutMs);
        
        //Honestly no clue
        m_TurretMotor.configNeutralDeadband(0.001, 0);

        //Configures the max velocity the system shourd run at
        m_TurretMotor.configMotionCruiseVelocity(TurretConstants.MAX_VELOCITY * velocity);

        //Configure max acceleration the system should run at
        m_TurretMotor.configMotionAcceleration(TurretConstants.MAX_ACCELERATION * acceleration);

        //I forgot
        m_TurretMotor.setSensorPhase(true);

        //Inverts the mootor + talon so that limelight reported offset is in-line with turret offset
        m_TurretMotor.setInverted(true);

        //Sets update frequency to be higher then default
        m_TurretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TurretConstants.kTimeoutMs);
		m_TurretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TurretConstants.kTimeoutMs);

        //Configures the forward and backwards min/max to set the motors to (-1 .. 1)
        m_TurretMotor.configNominalOutputForward(0, TurretConstants.kTimeoutMs);
		m_TurretMotor.configNominalOutputReverse(0, TurretConstants.kTimeoutMs);
		m_TurretMotor.configPeakOutputForward(.9, TurretConstants.kTimeoutMs);
		m_TurretMotor.configPeakOutputReverse(-.9, TurretConstants.kTimeoutMs);

        //Configure max allowable current through motor
        m_TurretMotor.configPeakCurrentLimit(20);

        //Sets the PID profile to use (ability to use slots 1-4)
        m_TurretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);

        //Sets PID constants
		m_TurretMotor.config_kF(TurretConstants.kSlotIdx, TurretConstants.kF, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kP(TurretConstants.kSlotIdx, TurretConstants.kP, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kI(TurretConstants.kSlotIdx, TurretConstants.kI, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kD(TurretConstants.kSlotIdx, TurretConstants.kD, TurretConstants.kTimeoutMs);

        //Sets the smoothing of the generated motion profile, using a smoothing coefficient
        m_TurretMotor.configMotionSCurveStrength(TurretConstants.Smoothing);

        //Resets the position of the encoder
        m_TurretMotor.setSelectedSensorPosition(0, TurretConstants.kPIDLoopIdx, TurretConstants.kTimeoutMs);
    }

    public void zeroEncoder()
    {
        m_TurretMotor.setSelectedSensorPosition(0);
        //m_TurretMotor.configClearPositionOnLimitR(true, timeoutMs);
        //m_TurretMotor.configClearPositionOnQuadIdx(true, timeoutMs);
    }

    public double getAdjustedEncoder() {
        return m_TurretMotor.getSelectedSensorPosition(0);
    }

    public double getEncoderVelocity() {
        return m_TurretMotor.getSelectedSensorVelocity(0);
    }

    /** Moves the turret with PID control. Error is how far away */
    public void PIDMove(double error) {
        m_TurretMotor.set(ControlMode.MotionMagic, error);
    }

    public void move() {
        m_TurretMotor.set(1);
    }
    
    public double getError() {
        return m_TurretMotor.getClosedLoopError(TurretConstants.kPIDLoopIdx);
    }
}
