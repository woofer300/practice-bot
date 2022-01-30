package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    // talon 6
    private static final WPI_TalonFX shooterTalon = new WPI_TalonFX(ShooterConstants.SHOOTER_TALON);

    public ShooterSubsystem() {
        // configuration go in constructor
        configTalon(shooterTalon);
    }

    private void configTalon(WPI_TalonFX motorController) {
        /* Factory Default all hardware to prevent unexpected behaviour */
        motorController.configFactoryDefault();

        /* Config neutral deadband to be the smallest possible */
        motorController.configNeutralDeadband(0.001);

        /* Config sensor used for Primary PID [Velocity] */
        motorController.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, ShooterConstants.kPIDLoopIdx, ShooterConstants.TIMEOUT_MS);


        /* Config the peak and nominal outputs */
        motorController.configNominalOutputForward(0, ShooterConstants.TIMEOUT_MS);
        motorController.configNominalOutputReverse(0, ShooterConstants.TIMEOUT_MS);
        motorController.configPeakOutputForward(1, ShooterConstants.TIMEOUT_MS);
        motorController.configPeakOutputReverse(-1, ShooterConstants.TIMEOUT_MS);

        /* Config the Velocity closed loop gains in slot0 */
        motorController.config_kF(ShooterConstants.kPIDLoopIdx, ShooterConstants.kF, ShooterConstants.TIMEOUT_MS);
        motorController.config_kP(ShooterConstants.kPIDLoopIdx, ShooterConstants.kP, ShooterConstants.TIMEOUT_MS);
        motorController.config_kI(ShooterConstants.kPIDLoopIdx, ShooterConstants.kI, ShooterConstants.TIMEOUT_MS);
        motorController.config_kD(ShooterConstants.kPIDLoopIdx, ShooterConstants.kD, ShooterConstants.TIMEOUT_MS);
        /*
         * Talon FX does not need sensor phase set for its integrated sensor
         * This is because it will always be correct if the selected feedback device is integrated sensor (default value)
         * and the user calls getSelectedSensor* to get the sensor's position/velocity.
         * 
         * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#sensor-phase
         */
        // _talon.setSensorPhase(true);
    }

    public double getVelocity() {
        return shooterTalon.getSelectedSensorVelocity();
    }
}
