package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class GaryBot {
    // define all hardware on robot
    public DcMotorController Front;
    public DcMotorController Back;

    public DcMotor FrontMotorLeft;
    public DcMotor FrontMotorRight;
    public DcMotor BackMotorLeft;
    public DcMotor BackMotorRight;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public GaryBot(){ }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap; // reference to hardware map

        // initialize controllers - motor and servo
        Front = hwMap.dcMotorController.get("Motor 2");
        Back = hwMap.dcMotorController.get("Motor 1");

        // initialize motors
        FrontMotorLeft = hwMap.dcMotor.get("FrontMotorLeft");
        FrontMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        FrontMotorRight = hwMap.dcMotor.get("FrontMotorRight");
        BackMotorLeft = hwMap.dcMotor.get("BackMotorLeft");
        BackMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        BackMotorRight = hwMap.dcMotor.get("BackMotorRight");

        // Set all motors to zero power
        FrontMotorLeft.setPower(0.0);
        FrontMotorRight.setPower(0.0);
        BackMotorLeft.setPower(0.0);
        BackMotorRight.setPower(0.0);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

