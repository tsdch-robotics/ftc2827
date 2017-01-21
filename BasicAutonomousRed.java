
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Red Ramp Autonomous", group="GaryBot")

public class BasicAutonomousRed extends LinearOpMode {
    /* Declare OpMode members. */
    GaryBot robot = new GaryBot();   // Use GaryBot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    // constants
    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    double curr_time = 0.0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Yo!");

        waitForStart();

        //Autonomous code here

        //load
        robot.Arm.setPower(-1.0);
        sleep(1000);

        robot.Arm.setPower(0.0);
        sleep(1500);

        robot.Conveyor.setPower(-1.0);
        sleep(1000);

        robot.Conveyor.setPower(0.0);
        sleep(1000);

        robot.Arm.setPower(-0.5);
        sleep(1000);

        robot.Arm.setPower(0.0);
        sleep(500);

        robot.Arm.setPower(-0.5);
        sleep(1000);

        robot.Arm.setPower(0.0);
        sleep(1000);


        //drive forward
        //robot.FrontLeftDrive.setPower(-1.0);

        robot.RearLeftDrive.setPower(-1.0);
        robot.RearRightDrive.setPower(-1.0);
        sleep(3000);
        robot.RearLeftDrive.setPower(0.0);
        robot.RearRightDrive.setPower(0.0);
        sleep(1000);



            //turn left
            //robot.FrontLeftDrive.setPower(1.0);
            robot.RearLeftDrive.setPower(1.0);

            //robot.FrontRightDrive.setPower(-1.0);
            robot.RearRightDrive.setPower(-1.0);
            sleep(800);
            robot.RearLeftDrive.setPower(0.0);
            robot.RearRightDrive.setPower(0.0);
            sleep(1000);


            //robot.FrontLeftDrive.setPower(-1.0);
            //robot.FrontLeftDrive.setPower(-1.0);
            //drive forward
            robot.RearLeftDrive.setPower(-1.0);

            //robot.FrontRightDrive.setPower(-1.0);
            robot.RearRightDrive.setPower(-1.0);
            sleep(1000);
            robot.RearRightDrive.setPower(0.0);
            robot.RearLeftDrive.setPower(0.0);
            sleep(1000);


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
}