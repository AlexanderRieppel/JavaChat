package chat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Reader thread.
 */
public class Reader extends Thread {
	private MulticastSocket socket = null;
	private String message;
	private String lastMessage;

	public Reader(MulticastSocket socket) {
		message = "";
		lastMessage = "";
		this.socket = socket;
	}

	public void run() {
		byte[] buf = new byte[512];
		DatagramPacket p = null;
		// Just loop forever, as the main thread will take care of the
		// exit conditions
		while (true) {
			try {
				p = new DatagramPacket(buf, buf.length);
				socket.receive(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// We got a packet, let's extract the message and display it
			String line = new String(p.getData(), 0, p.getLength());
			lastMessage = line;
			message = line + "\n";
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

}