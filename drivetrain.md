# Programming The Drive Train

- You will need to know a few things before programming a drive train.  
- Find the iD numbers for the talons connected to the drive train.

1. In `Constants.java` create a class for the drive subsystem
    ```
    public static final DriveConstants {

    }
    ```

2. Create talon constants within the DriveSubsystem class
    ```
    public static final class DriveConstants {
        // Example:
        public static final int RIGHT_FRONT_TALON = 0;
    }
    ```
3. Create a new file called `DriveSubsystem.java`. General Template:
    ```
    package frc.robot.subsystems;

    import edu.wpi.first.wpilibj2.command.SubsystemBase;

    import frc.robot.Constants.DriveConstants;

    public class DriveSubsystem extends SubsystemBase {
        
    }

    ```
4. Assuming the talons are the same as the ones used in 2022, use the [Phoenix API](https://docs.ctre-phoenix.com/en/stable/index.html)

    ```
    import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
    ```
5. Creating a talon instance: