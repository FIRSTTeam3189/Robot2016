package org.usfirst.frc.team3189.robot.utils;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.hal.DIOJNI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class ReusableDigitalInput extends DigitalSource implements LiveWindowSendable {
	
	private int channel;
	  public ReusableDigitalInput(int channel) {
		  this.channel = channel;
		  unfree();
	  }
	  
	  public void unfree(){
		  initDigitalPort(channel, true);
		
		  LiveWindow.addSensor("DigitalInput", channel, this);
		  UsageReporting.report(tResourceType.kResourceType_DigitalInput, channel);
	  }

	  /**
	   * Get the value from a digital input channel. Retrieve the value of a single
	   * digital input channel from the FPGA.
	   *
	   * @return the status of the digital input
	   */
	  public boolean get() {
	    return DIOJNI.getDIO(m_port);
	  }

	  /**
	   * Get the channel of the digital input
	   *
	   * @return The GPIO channel number that this object represents.
	   */
	  public int getChannel() {
	    return m_channel;
	  }

	  @Override
	  public boolean getAnalogTriggerForRouting() {
	    return false;
	  }

	  /*
	   * Live Window code, only does anything if live window is activated.
	   */
	  @Override
	  public String getSmartDashboardType() {
	    return "Digital Input";
	  }

	  private ITable m_table;

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void initTable(ITable subtable) {
	    m_table = subtable;
	    updateTable();
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void updateTable() {
	    if (m_table != null) {
	      m_table.putBoolean("Value", get());
	    }
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public ITable getTable() {
	    return m_table;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void startLiveWindowMode() {}

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void stopLiveWindowMode() {}

}
