package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.controllers.Controller;
import frc.robot.utils.Config;
import com.revrobotics.CANEncoder;


import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
 
public class CSMSubsystem extends Subsystem {

    public CANSparkMax NEWCSM;
    private CANEncoder m_encoder;

    

    public CSMSubsystem(int canadress) {
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);// new brushless motor object for elevator
        this.NEWCSM.set(0);// initially sets the motor to stop
    }


    public void start(int speed){
        this.NEWCSM.set(speed);
    }

    public void stop(){
        this.NEWCSM.set(0);
    }

    public void encoderup(int canadress, int rotation){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        double liftspeed;
        double liftpos = (m_encoder.getPosition());
        if (liftpos < rotation - 1){ //if we pass hight limit we go down
            liftspeed = 0.2;
        }
        if (liftpos < rotation + 80 && liftpos > rotation + 10){ // change the speed as we get close to the top
            liftspeed = -0.3;
        }if (liftpos < rotation + 10){
            liftspeed = -0.1;
        } if (liftpos < rotation){ // this stops the motor
            liftspeed = 0;
        }else{
        liftspeed = -1; // negative lift speed equals up
        }
        
        this.NEWCSM.set(liftspeed);// run the motor
    }

    public void encoderdown(int canadress, int rotation){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        double liftspeed = 0;
        double liftpos = (m_encoder.getPosition());

        if (liftpos < -50){
            liftspeed = 1;
        }if (liftpos > -49){
            liftspeed = 0.2;
        }if (liftpos > -1){
            liftspeed = 0;
        }
        System.out.print("rotation  :");
        System.out.println(rotation);

        System.out.print("     liftpos  :");
        System.out.println(liftpos);

    
		this.NEWCSM.set(liftspeed);
       
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        // fucking usless garbage 

    }
}