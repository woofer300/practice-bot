package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    // private final WPI_TalonSRX intakeTalon = new WPI_TalonSRX(IntakeConstants.INTAKE_TALON); // Talon 5
    private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    public IntakeSubsystem() {

    }

    @Override
    public void periodic() {
        
    }
    
    public void intake() {
        // moves the motors
        // intakeTalon.set(-1);

    }

    public void extend() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retract() {
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

    