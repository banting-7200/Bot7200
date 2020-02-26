package frc.robot.utils;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class I2CCOM {

  public int deviceAddress;

  public I2C wire;

  public I2CCOM(int deviceAddress) {
    // 1 = the arduino
    this.deviceAddress = deviceAddress;
    this.wire = new I2C(Port.kOnboard, this.deviceAddress);
  }

  public void getData(int address, int count, byte[] buffer) {
    this.wire.read(address, count, buffer);
    /*
    Address is where the roboRIO is expecting to read from. If it is 1, it is the arduino
    The count is how many bytes to read from the address
    The buffer is an array to store the data it receives
    */
  
  }
  public void sendData(int address, int data) {
    this.wire.write(address, data);
  }

public boolean read(int i, int j) {
	return false;
}

}

