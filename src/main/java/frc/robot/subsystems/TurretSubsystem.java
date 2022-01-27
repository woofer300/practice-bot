package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
    private final WPI_TalonSRX m_turretMotor = new WPI_TalonSRX(TurretConstants.TURRET_TALON);

    public TurretSubsystem() {
    }

    public void configureTurret() {
        
    /* Factory default hardware to prevent unexpected behavior */
    m_turretMotor.configFactoryDefault();

    /* Configure Sensor Source for Pirmary PID */
    m_turretMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, TurretConstants.kPIDLoopIdx, TurretConstants.kTimeoutMs);

    /* set deadband to super small 0.001 (0.1 %).
    The default deadband is 0.04 (4 %) */
    m_turretMotor.configNeutralDeadband(0.001, TurretConstants.kTimeoutMs);

    /**
     * Configure Talon SRX Output and Sensor direction accordingly Invert Motor to
     * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
     * sensor to have positive increment when driving Talon Forward (Green LED)
     */
    m_turretMotor.setSensorPhase(true);
    m_turretMotor.setInverted(false);

    /* Set relevant frame periods to be at least as fast as periodic rate */
    m_turretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TurretConstants.kTimeoutMs);
    m_turretMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TurretConstants.kTimeoutMs);

    /* Set the peak and nominal outputs */
    m_turretMotor.configNominalOutputForward(0, TurretConstants.kTimeoutMs);
    m_turretMotor.configNominalOutputReverse(0, TurretConstants.kTimeoutMs);
    m_turretMotor.configPeakOutputForward(1, TurretConstants.kTimeoutMs);
    m_turretMotor.configPeakOutputReverse(-1, TurretConstants.kTimeoutMs);

    /* Set Motion Magic gains in slot0 - see documentation */
    m_turretMotor.selectProfileSlot(TurretConstants.kSlotIdx, TurretConstants.kPIDLoopIdx);
    m_turretMotor.config_kF(TurretConstants.kSlotIdx, TurretConstants.kF, TurretConstants.kTimeoutMs);
    m_turretMotor.config_kP(TurretConstants.kSlotIdx, TurretConstants.kP, TurretConstants.kTimeoutMs);
    m_turretMotor.config_kI(TurretConstants.kSlotIdx, TurretConstants.kI, TurretConstants.kTimeoutMs);
    m_turretMotor.config_kD(TurretConstants.kSlotIdx, TurretConstants.kD, TurretConstants.kTimeoutMs);

    /* Set acceleration and vcruise velocity - see documentation */
    m_turretMotor.configMotionCruiseVelocity(3000, TurretConstants.kTimeoutMs);
    m_turretMotor.configMotionAcceleration(3000, TurretConstants.kTimeoutMs);

    /* Zero the sensor once on robot boot up */
    m_turretMotor.setSelectedSensorPosition(0, TurretConstants.kPIDLoopIdx, TurretConstants.kTimeoutMs);

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