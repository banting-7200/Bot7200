package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controllers.Controller;

import frc.robot.utils.Config;



public class LiftCommand extends Command {
    public LiftCommand() {
        requires(Robot.m_liftsubsystem);
    }

    

    @Override
    protected void execute() {
      Robot.m_LimitSubsystem.getlimit(0);
      //Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);

      Controller controller = Config.getController("controls.main");

      boolean liftUp = controller.getButton(Config.getInt("controls.liftup"));
      boolean liftDown = controller.getButton(Config.getInt("controls.liftdown"));
      int CanID = (Config.getInt("lift.can.id"));
      int Rotation = (Config.getInt("number.of.rotations"));
      int FastSpeedUp = (Config.getInt("lift.up.fast.pos"));
      int SlowSpeedUp = (Config.getInt("lift.up.slow.pos"));
      int FastSpeedDown = (Config.getInt("lift.down.fast.pos"));
      int SlowSpeedDown = (Config.getInt("lift.down.slow.pos"));
      int SwitchPin = (Config.getInt("lift.switch.pin"));

        if (Robot.m_LimitSubsystem.getlimit(SwitchPin)){
          Robot.m_liftsubsystem.Lift.encoderReset();
        }

        if (liftUp){
            Robot.m_liftsubsystem.Lift.encoderup(CanID, Rotation, SlowSpeedUp, FastSpeedUp);
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);//turns the lift lock off
          }else{
            Robot.m_liftsubsystem.Lift.stop();
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);//urns the lift lock on
          }
          if (liftDown){
            Robot.m_liftsubsystem.Lift.encoderdown(CanID, Rotation, SlowSpeedDown, FastSpeedDown);
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(true);
      
          }else{
            Robot.m_liftsubsystem.Lift.stop();
          //  Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
          }
      }
  


    @Override
    protected boolean isFinished() {
      Robot.m_liftsubsystem.Lift.stop();
      //Robot.m_liftsubsystem.LiftlockPiston.ToggleSolenoid(false);
      return false;
    }
}