package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {
    
    private IntakeSubsystem m_intake;
    
    @Override
    public void initialize() {
        m_intake.deploy();
    } 

    @Override
    public void execute() {
        m_intake.intake();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
