// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LimelightSubsystem extends SubsystemBase {
  private final NetworkTable m_limelightTable;
    
  /** Creates a new LimeLight. */
  public LimelightSubsystem() {
     m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run 
  }

  public double getHorizontalOffset() {
    return m_limelightTable.getEntry("tx").getDouble(0);
  }

  public double getVerticalOffset() {
    return m_limelightTable.getEntry("ty").getDouble(0);
  }

  public boolean isTargetDetected() {
    return m_limelightTable.getEntry("tv").getDouble(0) == 1;
  }
}
