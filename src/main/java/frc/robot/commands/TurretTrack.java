package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class TurretTrack extends CommandBase{
    private TurretSubsystem m_turret;
    private LimelightSubsystem m_limelight;

    public TurretTrack(TurretSubsystem subsystem, LimelightSubsystem ls) {
        m_turret= subsystem;
        m_limelight= ls;
        addRequirements(m_turret, m_limelight);
    }

    @Override
    public void initialize(){
        m_turret.configureTurret(1, 1); //Configures the turret to run at max velocity + acceleration
        m_turret.zeroEncoder();

    }

    @Override
    public void execute() {
        double error = m_limelight.getHorizontalOffset(false); //Gets the x angle from the limelight
        double currentPOS = m_turret.getAdjustedEncoder();
        double velocity = m_turret.getEncoderVelocity();
        //if(isFinished()) {- m_TurretSubsystem.PIDMove(error); }
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putNumber("CurrentPOS", currentPOS);
        SmartDashboard.putNumber("Velocity", velocity);
        SmartDashboard.putNumber("Debug Error", m_turret.getError());
        SmartDashboard.putBoolean("Target Detected", m_limelight.isTargetDetected());
        if(m_limelight.isTargetDetected()) {m_turret.PIDMove(error);}

      }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interupted){
    } 

    

    
}
