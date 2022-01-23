package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    private final WPI_TalonSRX intakeTalon = new WPI_TalonSRX(IntakeConstants.INTAKE_TALON); // Talon 5

    public void intake() {
        // moves the motors
        intakeTalon.set(-1);

    }

    public void deploy() {
        // deploys pneumatics
    }

    public IntakeSubsystem() {

    }
}