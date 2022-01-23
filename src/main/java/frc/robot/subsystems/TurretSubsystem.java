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
    private final double kTicksPerDegree = 4096/360; //This makes the encoder 1:1 w/ rotation of the 24 tooth pulley
    private final double GearRatio = 24/204; //This compensates for the gear ration between

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
        m_TurretMotor.configFactoryDefault();
        m_QuadEncoder.configFactoryDefault();
        m_TurretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);
        m_TurretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, TurretConstants.kPIDLoopIdx,
				TurretConstants.kTimeoutMs);
        m_TurretMotor.configNeutralDeadband(0.001, 0);
        m_TurretMotor.configMotionCruiseVelocity(TurretConstants.MAX_VELOCITY * velocity);
        m_TurretMotor.configMotionAcceleration(TurretConstants.MAX_ACCELERATION * acceleration);
        m_TurretMotor.setSensorPhase(true);
        m_TurretMotor.setInverted(true);
        m_TurretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TurretConstants.kTimeoutMs);
		m_TurretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TurretConstants.kTimeoutMs);
        m_TurretMotor.configNominalOutputForward(0, TurretConstants.kTimeoutMs);
		m_TurretMotor.configNominalOutputReverse(0, TurretConstants.kTimeoutMs);
		m_TurretMotor.configPeakOutputForward(.9, TurretConstants.kTimeoutMs);
		m_TurretMotor.configPeakOutputReverse(-.9, TurretConstants.kTimeoutMs);
        m_TurretMotor.configPeakCurrentLimit(20);
        m_TurretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);
		m_TurretMotor.config_kF(TurretConstants.kSlotIdx, TurretConstants.kF, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kP(TurretConstants.kSlotIdx, TurretConstants.kP, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kI(TurretConstants.kSlotIdx, TurretConstants.kI, TurretConstants.kTimeoutMs);
		m_TurretMotor.config_kD(TurretConstants.kSlotIdx, TurretConstants.kD, TurretConstants.kTimeoutMs);
        m_TurretMotor.configMotionSCurveStrength(TurretConstants.Smoothing);
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
