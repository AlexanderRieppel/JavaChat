import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * This class is the Reader thread. It's for the listening on the multicastport
 * you gave him from the constructor.
 * 
 * @author AHMED ALY, Alexander Rieppel
 * @version 03-14-2013
 */
public class Reader extends Thread {
	private MulticastSocket socket = null;
	private String message;

	/**
	 * This constructor gets the Multicastsocket and initialize
	 * 
	 * @param socket
	 *            gets the Multicastsocket
	 */
	public Reader(MulticastSocket socket) {
		message = "";
		this.socket = socket;
	}

	/**
	 * This run function is for the Thread necessary to start. It's create a
	 * DatagramPacket read the byte code coming and put it in a String
	 */
	public void run() {
		byte[] buf = null;
		buf = new byte[1000];
		DatagramPacket p = null;

		while (true) {
			try {
				p = new DatagramPacket(buf, buf.length);
				socket.receive(p);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String line = new String(p.getData(), 0, p.getLength());
			if (!line.equals("")) {
				message = line + "\n";
			}
		}
	}

	/**
	 * get the message came in
	 * 
	 * @return message came in
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * set the message
	 * 
	 * @param message
	 *            message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}