package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.controllers.Controller;
import frc.robot.controllers.LogitechJoystick;
import frc.robot.controllers.PingController;
import frc.robot.subsystems.CSMSubsystem;
//import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.SparkDriveTrain;
import frc.robot.subsystems.SparkSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Config;
import frc.robot.utils.I2CCOM;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem m_drivetrainsubsystem = new SparkDriveTrain(); // CAN Spark MAX motor
  public static ColorSensorSubsystem m_colorsensorsubsystem = new ColorSensorSubsystem();
  public static DigitalInput m_liftLimit = new DigitalInput(8);
  public static LiftSubsystem m_liftsubsystem = new LiftSubsystem();
  public static SparkSubsystem intakeSpark = new SparkSubsystem(6);
  public static SparkSubsystem shiftSpark = new SparkSubsystem(7);

  //public static LogitechJoystick m_joystick = Config.getController("controls.main");
  //a fun test
  I2CCOM arduinoI2C;

  public PingController pingController;
  public CSMSubsystem Lift;//creats a vareable for a CSM (CSMSubsystem)
  //public PneumaticsSubsystem colorwheelpiston;
  //public PneumaticsSubsystem LiftlockPiston;

  public ColorSensorSubsystem findColor;

  Command driveCommand = new DriveCommand();
  Command m_autonomousCommand;
  Command liftCommand = new LiftCommand();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    this.Lift = new CSMSubsystem(4); //create a CSM using CSMSubsystem
   // this.colorwheelspinner = new LimitSubsystem(1);//limit switch for the color spinner
   // this.colorwheelpiston = new PneumaticsSubsystem(1, 1);//setting the can Adress of the PCM and the port on PCM
   // this.LiftlockPiston = new PneumaticsSubsystem(2, 2);
    
  }

  @Override
  public void robotPeriodic() {
  //this.findColor.ColorSencorSubsystem();
  //System.out.println(findColor);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    CameraServer.getInstance().startAutomaticCapture();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveCommand.start();
    liftCommand.start();
   // this.LiftlockPiston.ToggleSolenoid(false);
    Controller controller = Config.getController("controls.main");
  }
  @Override
  public void testPeriodic() {
  }
}