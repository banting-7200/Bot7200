// Created and Coded with love by Danny.
// Please be sure to refine the code, I lay out the foundation so I may make a mistake here and there.
// God Bless Canada

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.PixyCommand;

public class PixySubsystem extends Subsystem {

    public PixySubsystem Pixy;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new PixyCommand());
    }
}