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

        shooterTalon.configFactoryDefault();
    }
}
