// Created and Coded with love by Danny
// Please be sure to refine the code, this is rudimentary and there should be mistakes here and there.
// God bless Banting 7200, we can do it!

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;



public class PixyCommand extends Command {
    public PixyCommand() {
        requires(Robot.m_pixysubsystem);
    }

    public I2CCOM arduinoI2C;
    public int signature;

    public void trackBalls(){
        arduinoI2C.sendData(1, 1);
    }

    public void trackTape(){
        arduinoI2C.sendData(1, 2);
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}