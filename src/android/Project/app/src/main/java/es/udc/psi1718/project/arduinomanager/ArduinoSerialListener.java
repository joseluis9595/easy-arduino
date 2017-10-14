package es.udc.psi1718.project.arduinomanager;


public interface ArduinoSerialListener {

	/**
	 * Called when new data is received from Arduino
	 *
	 * @param data string received from arduino
	 */
	public void receivedData(String data);

	/**
	 * Called when the connection is opened
	 */
	public void connectionOpened();

	/**
	 * Called when the connection is closed
	 */
	public void connectionClosed();

	/**
	 * Called when connection can´t start
	 */
	public void connectionFailed(ArduinoResponseCodes arduinoResponseCode);
}