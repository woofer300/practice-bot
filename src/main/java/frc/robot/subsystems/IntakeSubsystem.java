package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    // private final WPI_TalonSRX intakeTalon = new WPI_TalonSRX(IntakeConstants.INTAKE_TALON); // Talon 5
    private final DoubleSolenoid doubleSolenoid;

    public IntakeSubsystem() {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
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

    