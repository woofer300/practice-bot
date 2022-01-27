package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
    private final WPI_TalonSRX m_turretMotor = new WPI_TalonSRX(TurretConstants.TURRET_TALON);

    public TurretSubsystem() {
    }

    public void configureTurret() {
        
        //Factory Default to ensure that talon behaves the same all the time
        m_turretMotor.configFactoryDefault();

        //Zero the encoder
		m_turretMotor.setSelectedSensorPosition(0, TurretConstants.kPIDLoopIdx, TurretConstants.kTimeoutMs);

        //Configure sensor source for primary PID
        m_turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, TurretConstants.kPIDLoopIdx, TurretConstants.kTimeoutMs);

        //Set the deadband to a super small value
        m_turretMotor.configNeutralDeadband(0.001, TurretConstants.kTimeoutMs);

        /**
		 * Configure Talon SRX Output and Sensor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
        m_turretMotor.setSensorPhase(true);
        m_turretMotor.setInverted(false);
        
        //Set relative Frame periods to be at least as fast as periodic rate
        m_turretMotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 10, TurretConstants.kTimeoutMs);
        m_turretMotor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, 10, TurretConstants.kTimeoutMs);

        //Set the peak and nominal outputs
        m_turretMotor.configNominalOutputForward(0, TurretConstants.kTimeoutMs);
		m_turretMotor.configNominalOutputReverse(0, TurretConstants.kTimeoutMs);
		m_turretMotor.configPeakOutputForward(1, TurretConstants.kTimeoutMs);
		m_turretMotor.configPeakOutputReverse(-1, TurretConstants.kTimeoutMs);

        //Set the motion magic in slot 0
        m_turretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);
		m_turretMotor.config_kF(TurretConstants.kSlotIdx, TurretConstants.kF, TurretConstants.kTimeoutMs);
		m_turretMotor.config_kP(TurretConstants.kSlotIdx, TurretConstants.kP, TurretConstants.kTimeoutMs);
		m_turretMotor.config_kI(TurretConstants.kSlotIdx, TurretConstants.kI, TurretConstants.kTimeoutMs);
		m_turretMotor.config_kD(TurretConstants.kSlotIdx, TurretConstants.kD, TurretConstants.kTimeoutMs);

        //Set the max cruise velocity and acceleration
        m_turretMotor.configMotionCruiseVelocity(TurretConstants.MAX_VELOCITY, TurretConstants.kTimeoutMs);
        m_turretMotor.configMotionCruiseVelocity(TurretConstants.MAX_ACCELERATION, TurretConstants.kTimeoutMs);

        //Sets peak current
        m_turretMotor.configPeakCurrentLimit(20);

        //The allowable error 
        m_turretMotor.configAllowableClosedloopError(TurretConstants.kSlotIdx, 10, TurretConstants.kTimeoutMs);

    }

    public void PIDmove(double error) {
        m_turretMotor.set(TalonSRXControlMode.MotionMagic, error);
    }

    public double getPOS() {
        return m_turretMotor.getSelectedSensorPosition();
    }

    public double getVelocity() {
        return m_turretMotor.getSelectedSensorVelocity();
    }

    public double getMotorPower() {
        return m_turretMotor.getBusVoltage();
    }

    public double getDebugError() {
        return m_turretMotor.getClosedLoopError();
    }

    public void moveA() {
        m_turretMotor.set(.1);
    }

    public void moveB() {
        m_turretMotor.set(-.1);
    }
}