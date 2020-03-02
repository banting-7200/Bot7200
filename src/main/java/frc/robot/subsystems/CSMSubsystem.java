package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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

    public void encoderReset(){
        double reset = 0;
        this.m_encoder = new CANEncoder(NEWCSM);
        this.m_encoder.setPosition(reset);
        
    }
    public void encoderup(int canadress, int rotation, int slowposition, int fastposition){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        double liftspeed = 0;
        double liftpos = (m_encoder.getPosition());
        if (liftpos < fastposition){// should be -179
            liftspeed = -1;
        }if (liftpos > slowposition){ // should be in this case -180
            liftspeed = -0.2;//slows the motor down when getting close to the top
        }if (liftpos < rotation){ // this stops the motor
            liftspeed = 0;
        }
        
        this.NEWCSM.set(liftspeed);// run the motor
    }

    public void encoderdown(int canadress, int rotation, int slowposition, int fastposition){
        this.m_encoder = new CANEncoder(NEWCSM);
        this.NEWCSM = new CANSparkMax(canadress, MotorType.kBrushless);
        double liftspeed = 0;
        double liftpos = (m_encoder.getPosition());

        if (liftpos < fastposition){ //should be set to -50
            liftspeed = 1;
        }if (liftpos > slowposition){ //should be -49 for this case
            liftspeed = 0.2;
        }if (liftpos > -1){//stops the motor
            liftspeed = 0;
        }

		this.NEWCSM.set(liftspeed);
       
    }

    @Override
    protected void initDefaultCommand() {
        // fucking usless garbage 
    }
}