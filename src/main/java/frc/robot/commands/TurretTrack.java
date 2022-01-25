package frc.robot.commands;

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
        m_turret.configureTurret(); //Configures the turret to run at max velocity + acceleration
    }

    double error;
    double currentPOS;
    @Override
    public void execute() {
        error = m_limelight.getHorizontalOffset(true); //Gets the x angle from the limelight
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putNumber("Current POS", m_turret.getPOS());
        SmartDashboard.putNumber("Velocitt" , m_turret.getVelocity());
        SmartDashboard.putNumber("Debug Error", m_turret.getDebugError());
        SmartDashboard.putNumber("Motor Power", m_turret.getMotorPower());
        SmartDashboard.putBoolean("Targeted", m_limelight.isTargetDetected());

        m_turret.PIDmove(error + m_turret.getPOS());
      }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interupted){
    } 

    

    
}
