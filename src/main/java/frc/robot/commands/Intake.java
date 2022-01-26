package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {
    
    private IntakeSubsystem m_intake;

    public Intake(IntakeSubsystem subsystem) {
        m_intake = subsystem;
        addRequirements(m_intake);
    }
    
    @Override
    public void initialize() {
        m_intake.deploy();
    } 

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Compressor Status", m_intake.getCompressorStatus());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
