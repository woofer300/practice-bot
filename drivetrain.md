# Programming The Drive Train

- You will need to know a few things before programming a drive train.  
- Find the ID numbers for the talons connected to the drive train.
    - use Phoenix Tuner (application) on the driver station (the computer) to find the ID or ask someone

1. In `Constants.java` create a class for the drive subsystem
    ```
    public static final class DriveConstants {
    
    }
    ```

2. Create talon constants within the `DriveConstants` class
    ```
    public static final class DriveConstants {
        // example:
        public static final int RIGHT_FRONT_TALON = 0; // Talon ID: 0
    }
    ```
- the naming convention for constants is an all-CAPS format.
    >   "nothing is more constant than the variable yelling at me"
    
3. Create a new file called `DriveSubsystem.java`. (The following steps should be completed within this file) General Template:
    ```
    package frc.robot.subsystems; 
    // allows the file to be recognize as a subsystem

    import edu.wpi.first.wpilibj2.command.SubsystemBase;
    // required for creating a subsystem class

    import frc.robot.Constants.DriveConstants;

    public class DriveSubsystem extends SubsystemBase {
        
    }
    ```
    
- `public class DriveSubsystem extends SubsystemBase` this line creates a class that is called `DriveSubsystem` which inherits `SubsystemBase`

4. Assuming the talons are the same as the ones used in 2022, use the [Phoenix API](https://docs.ctre-phoenix.com/en/stable/index.html)

    ```
    import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
    ```
5. Creating a talon instance:

    ```
    private final WPI_TalonSRX m_rightFrontTalon = new WPI_TalongSRX(DriveConsteants.RIGHT_FRONT_TALON);
    ```

6. To group talons together, use [MotorControllerGroup](https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/motorcontrol/MotorControllerGroup.html)
    ```
    // import MotorControllerGroup
    import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
    ```
    ```
    // creating a group
    private final MotorControllerGroup m_rightTalons = new MotorControllerGroup(m_rightFrontTalon, m_rightBackTalon);
    ```
    
7. After talons are created and group, the class used to drive the robot is [DifferentialDrive](https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html)
    
    ```
    // import DifferentialDrive
    import edu.wpi.first.wpilibj.drive.DifferentialDrive;
    ```
    ```
    // initalizing
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftTalons, m_rightTalons);
    ```
    
8. Create a function for driving the drive train
    ```
    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed, true);
    }
    ```
- The `tankDrive()` takes two arguments: `leftSpeed` and `rightSpeed`.
- If one side of the drive train is going the opposite direction as intended, make the the speed negative: e.g. `-leftSpeed`. 
- The boolean argument in `tankDrive()` if `true` will square the values of `leftSpeed` and `rightSpeed`. 
    