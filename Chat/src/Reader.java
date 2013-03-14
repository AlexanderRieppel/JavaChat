
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;

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
		byte[] buf = null;
		try {
			buf = new byte[socket.getReceiveBufferSize()];
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			String line = new String(p.getData());
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