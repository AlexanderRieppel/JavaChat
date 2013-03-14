import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.UIManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

/**
 * This is the Controller Class where the Listener, the Main Function and the
 * connection with the MulticastSocket is.
 * 
 * @author AHMED ALY, Alexander Rieppel
 * @version 03-14-2013
 * 
 */
public class Controller {

	private String name;
	private Reader reader;
	private MulticastSocket socket = null;
	private View view;
	private static Display display = null;
	private int port;
	private InetAddress group;

	/**
	 * This is the main constructor. Starts the Reader class set the listener
	 * for the View GUI and start it of corse. We also join here the
	 * MulticastSocket Group.
	 */
	public Controller(Display display) {
		this.display = display;
		name = "";

		try {
			port = 6789;
			socket = new MulticastSocket(port);
			// socket.setInterface(InetAddress.getLocalHost());
			group = InetAddress.getByName("226.1.3.5");

			socket.joinGroup(group);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());

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

		view.open();

	}

	/**
	 * This function is for put the message in a packet and send it to the
	 * socket which send it to the group.
	 * 
	 * @param msg
	 *            your message you want to send
	 * @throws IOException
	 */
	public void send(String msg) throws IOException {
		if (!name.equals("")) {
			String buf = name + ": " + msg;
			byte[] b = buf.getBytes();
			DatagramPacket packet = new DatagramPacket(b, 0, b.length, group,
					port);
			socket.send(packet);
		} else if (name.equals(""))
			forgotName();
	}

	/**
	 * If you forgot to set the Name this function open a GUI which tell you
	 * that you forgot message.
	 */
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

	/**
	 * This Listener is for the textfield. "if enter send the message"
	 */
	KeyAdapter key = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.character == SWT.CR) {
				try {
					if (!view.getChatMessage().getText().equals("")) {
						send(view.getChatMessage().getText());
						view.getChatMessage().setText("");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	};
	/**
	 * This Listener is for the menu buttons and for the send button.
	 */
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
				s.getUserName().addKeyListener(new KeyAdapter() {

					@Override
					public void keyReleased(KeyEvent e) {
						if (e.character == SWT.CR) {
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
					if (!view.getChatMessage().getText().equals("")) {
						send(view.getChatMessage().getText());
						view.getChatMessage().setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	};
	/**
	 * If you close the GUI window this listener close the programm.
	 */
	DisposeListener disoseListener = new DisposeListener() {
		@Override
		public void widgetDisposed(DisposeEvent e) {
			System.exit(0);
		}
	};

	/**
	 * 
	 * @return
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * 
	 * @param reader
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * The main Methode
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		display = new Display();
		new Controller(display);
		display.dispose();
	}
}
