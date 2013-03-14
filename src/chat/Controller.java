package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;

import javax.swing.UIManager;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

public class Controller {

	private String name;
	// private SocketAddress dest = null;
	private Reader reader;
	private MulticastSocket socket = null;
	private View view;
	private static Display display = null;
	private int port;
	private InetAddress group;

	/**
	 * Main constructor. Takes a String containing the name (or id) of the user.
	 */
	public Controller(Display display) {
		this.display = display;
		name = "";

		// This is the multicast group we'll be using.
		// dest = new InetSocketAddress("239.1.2.3", 1234);

		try {
			// The socket is created and bound to the proper port.
			socket = new MulticastSocket(1234);
			socket.setInterface(InetAddress.getLocalHost());
			group = InetAddress.getByName("239.1.2.3");
			port = 1234;
			// Set the loopback mode to false if you don't want to see your
			// own messages
			// socket.setLoopbackMode(false);

			// get the default Network Interface
			// NetworkInterface netif = NetworkInterface
			// .getByInetAddress(InetAddress.getLocalHost());
			// Join the multicast group
			socket.joinGroup(group);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// From here onwards, the GUI is created

		// Force SwingSet to come up in the Cross Platform L&F
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
			// If you want the System L&F instead, comment out the above line
			// and
			// uncomment the following:
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exc) {
			System.err.println("Error loading L&F: " + exc);
		}
		reader = new Reader(socket);
		reader.start();
		view = new View(display, reader);
		view.getChatMessage().addKeyListener(key);
		view.getMainShell().addDisposeListener(disoseListener);
		view.getAbort().addSelectionListener(selectListener);
		view.getName().addSelectionListener(selectListener);
		view.getSend().addSelectionListener(selectListener);
		try {
			send("I connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.open();

	}

	public void send(String msg) throws IOException {
		if (!name.equals("")) {
			String buf = name + ": " + msg;
			byte[] b = buf.getBytes();
			DatagramPacket packet = new DatagramPacket(b, 0, b.length, group,
					port);
			socket.send(packet);
		} else
			forgotName();
	}

	private void forgotName() {
		view.getMainShell().setEnabled(false);
		final Settings s = new Settings(display);
		s.fehler();

		s.getSettingsShell().addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				s.getSettingsShell().dispose();
				view.getMainShell().setEnabled(true);
			}
		});
		s.open();
	}

	KeyAdapter key = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == 13) {
				try {
					send(view.getChatMessage().getText());
					view.getChatMessage().setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	};
	SelectionAdapter selectListener = new SelectionAdapter() {

		@Override
		public void widgetSelected(SelectionEvent event) {
			if (event.getSource() == view.getName()) {
				view.getMainShell().setEnabled(false);
				final Settings s = new Settings(display);
				s.initGUI();
				s.getSave().addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent event) {
						if (event.getSource() == s.getSave()) {
							name = s.getUserName().getText();
							s.getSettingsShell().dispose();
						}
					}
				});
				s.getSettingsShell().addDisposeListener(new DisposeListener() {
					@Override
					public void widgetDisposed(DisposeEvent e) {
						s.getSettingsShell().dispose();
						view.getMainShell().setEnabled(true);
					}
				});
				s.open();
			} else if (event.getSource() == view.getAbort()) {
				view.getMainShell().setEnabled(false);
				final Settings s = new Settings(display);
				s.initGuiVersion();
				if (!name.equals(""))
					s.getLblName().setText("Name: " + name);
				s.getSettingsShell().addDisposeListener(new DisposeListener() {
					@Override
					public void widgetDisposed(DisposeEvent e) {
						s.getSettingsShell().dispose();
						view.getMainShell().setEnabled(true);
					}
				});
				s.open();
			} else if (event.getSource() == view.getSend()) {
				try {
					send(view.getChatMessage().getText());
					view.getChatMessage().setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	};

	DisposeListener disoseListener = new DisposeListener() {
		@Override
		public void widgetDisposed(DisposeEvent e) {
			System.exit(0);
		}
	};

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public static void main(String args[]) {
		display = new Display();
		new Controller(display);
		display.dispose();
	}
}
