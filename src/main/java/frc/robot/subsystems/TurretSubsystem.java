package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase{
    private final WPI_TalonSRX m_TurretMotor = new WPI_TalonSRX(TurretConstants.TURRET_TALON);
    private final double kTicksPerDegree = 4096/360; //This makes the encoder 1:1 w/ rotation of the 24 tooth pulley
    private final double GearRatio = 24/204; //This compensates for the gear ration between

    public TurretSubsystem() {
        m_TurretMotor.configMotionProfileTrajectoryInterpolationEnable(true);
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
        m_TurretMotor.configMotionCruiseVelocity(TurretConstants.MAX_VELOCITY);
        m_TurretMotor.configMotionAcceleration(TurretConstants.MAX_ACCELERATION);
    }

    public void zeroEncoder()
    {
        m_TurretMotor.configClearPositionOnLimitF(true, 2);
        //m_TurretMotor.configClearPositionOnLimitR(true, timeoutMs);
        //m_TurretMotor.configClearPositionOnQuadIdx(true, timeoutMs);
    }

    public double getAdjustedEncoder() {
        return m_TurretMotor.getSelectedSensorPosition() * kTicksPerDegree * GearRatio;
    }

    /** Moves the turret with PID control. Error is how far away */
    public void PIDMove(double error) {
        m_TurretMotor.set(ControlMode.MotionMagic, error, DemandType.Neutral, 1);
    }



    
}
