/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Test", group="GaryBot")
public class AutonomousBeacons extends LinearOpMode {
    /* Declare OpMode members. */
    GaryBot robot   = new GaryBot();   // Use GaryBot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    // constants
    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;
    static final int        FORWARD = 1;
    static final int        BACK = -1;
    static final int        RIGHT = 1;
    static final int        LEFT  = -1;
    // code runs ONCE when driver hits INIT
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Waiting for start");
        updateTelemetry(telemetry);
        waitForStart();
        move(FORWARD, 1.0);
        move(BACK, 1.0);
        turn(RIGHT, 1.0);
        turn(LEFT, 1.0);
    }

    // move(direction, power) - move forwards/backwards in the time specified
    // direction: +1 = FORWARD, -1 = BACKWARDS
    // time: positive decimal number (e.g. 1.1)
    public void move(int direction, double time) {
        long timeInMs = (long)(time*1000);
        robot.FrontLeftDrive.setPower(FORWARD_SPEED*direction);
        robot.FrontRightDrive.setPower(FORWARD_SPEED*direction);
        robot.RearLeftDrive.setPower(FORWARD_SPEED*direction);
        robot.RearRightDrive.setPower(FORWARD_SPEED*direction);
        sleep(timeInMs);
        robot.FrontLeftDrive.setPower(0.0);
        robot.FrontRightDrive.setPower(0.0);
        robot.RearLeftDrive.setPower(0.0);
        robot.RearRightDrive.setPower(0.0);
    }

    // turn(direction, power) - turn right/left in the time specified
    // direction: +1 = RIGHT, -1 = LEFT
    // time: positive decimal number (e.g. 1.1)
    public void turn(int direction, double time) {
        long timeInMs = (long)(time*1000);
        robot.FrontLeftDrive.setPower(TURN_SPEED*direction);
        robot.FrontRightDrive.setPower(-TURN_SPEED*direction);
        robot.RearLeftDrive.setPower(TURN_SPEED*direction);
        robot.RearRightDrive.setPower(-TURN_SPEED*direction);
        sleep(timeInMs);
        robot.FrontLeftDrive.setPower(0.0);
        robot.FrontRightDrive.setPower(0.0);
        robot.RearLeftDrive.setPower(0.0);
        robot.RearRightDrive.setPower(0.0);
    }
}