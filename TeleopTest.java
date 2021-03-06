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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Tele test", group="GaryBot")
public class TeleopTest extends OpMode {
    /* Declare OpMode members. */
    GaryBot robot   = new GaryBot();   // Use GaryBot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    // constants
    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    // code runs ONCE when driver hits INIT
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        updateTelemetry(telemetry);
    }

    // code runs REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() { }

    // code runs ONCE when the driver hits PLAY
    @Override
    public void start() { }

    // code runs REPEATEDLY when driver hits play
    // put driver controls (drive motors, servos, etc.) HERE
    @Override
    public void loop() {
        double ThrottleLeft =  gamepad1.left_stick_y;
        double ThrottleRight = gamepad1.right_stick_y;

        if(gamepad2.right_bumper) {
            robot.Arm.setPower(-1.0);
        }
        else {
            robot.Arm.setPower(0.0);
        }

        if(gamepad2.left_bumper) {
            robot.Conveyor.setPower(-1.0);
        }
        else {
            robot.Conveyor.setPower(0.0);
        }

        // right beacon trigger
        if (gamepad2.y) {
            robot.ServoRight.setPosition(Servo.MAX_POSITION);
        }
        if (gamepad2.a) {
            robot.ServoRight.setPosition(Servo.MIN_POSITION);
        }

        // left beacon trigger
        if (gamepad2.dpad_up) {
            robot.ServoMiddle.setPosition(Servo.MAX_POSITION);
        }
        if (gamepad2.dpad_down) {
            robot.ServoMiddle.setPosition(Servo.MIN_POSITION);
        }


        // strafing controls
        //if(gamepad1.dpad_left) {
            //robot.FrontLeftDrive.setPower(-1.0);
            //robot.RearLeftDrive.setPower(1.0);
            //robot.FrontRightDrive.setPower(1.0);
            //robot.RearRightDrive.setPower(-1.0);
        //}

        //else if(gamepad1.dpad_right) {
            //robot.FrontLeftDrive.setPower(1.0);
            //robot.RearLeftDrive.setPower(-1.0);
            //robot.FrontRightDrive.setPower(-1.0);
            //robot.RearRightDrive.setPower(-1.0);
        //robot.FrontLeftDrive.setPower(ThrottleLeft);
        robot.RearLeftDrive.setPower(ThrottleLeft);

        //robot.FrontRightDrive.setPower(ThrottleRight);
        robot.RearRightDrive.setPower(ThrottleRight);


        // telemetry
        telemetry.addData("left", "%.2f", ThrottleLeft);
        telemetry.addData("right", "%.2f", ThrottleRight);
        //updateTelemetry(telemetry);
    }
                }
