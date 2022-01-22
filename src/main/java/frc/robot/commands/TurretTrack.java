package frc.robot.commands;

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
    }

    double error;
    double currentPOS;
    @Override
    public void execute() {
        error = m_limelight.getHorizontalOffset(true); //Gets the x angle from the limelight
        currentPOS = m_turret.getAdjustedEncoder(); //Gets the current position of the turret
        //if(isFinished()) { m_TurretSubsystem.PIDMove(error); }
        m_turret.PIDMove(error);
      }

    @Override
    public boolean isFinished() {
        return (error + 100 > currentPOS && currentPOS > error - 100) || error+currentPOS > 1450 || error+currentPOS < -1450;
    }

    @Override
    public void end(boolean interupted){
    } 

    

    
}