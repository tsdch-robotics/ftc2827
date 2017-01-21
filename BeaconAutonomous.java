
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="BeaconBlue", group="Gary Bot")
public class BeaconAutonomous extends LinearOpMode {
    /* Declare OpMode members. */
    GaryBot robot = new GaryBot();   // Use GaryBot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    // constants
    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    double curr_time = 0.0;
    int threshold = 10;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Yo!");

        waitForStart();
        while (true) {
            telemetry.addData("cr", robot.lineFollowr.alpha());
            telemetry.addData("cl", robot.lineFollowl.alpha());
            telemetry.update();

            while (robot.lineFollowl.alpha() < threshold) {
                robot.RearLeftDrive.setPower(-0.5);
                robot.RearRightDrive.setPower(0.0);
            }

            while (robot.lineFollowr.alpha() < threshold) {
                robot.RearLeftDrive.setPower(0.0);
                robot.RearRightDrive.setPower(-0.5);
            }




        }
    }
}


