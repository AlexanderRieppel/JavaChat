import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

/**
 * This is the Main GUI Class its here for the chat gui.
 * 
 * @author AHMED ALY, Alexander Rieppel
 * @version 03-14-2013
 * 
 */
public class View {
	private Shell mainShell;
	private Display display;
	private Text chatText;
	private Text chatMessage;

	private Reader reader;
	private MenuItem abort;
	private MenuItem name;
	private Button send;

	/**
	 * 
	 * Main Constructor initialize the Shell and set the Location and the size.
	 * 
	 * @param display
	 * @param reader
	 */
	public View(final Display display, Reader reader) {
		this.display = display;
		this.reader = reader;
		mainShell = new Shell(display);
		mainShell.setText("JavaChat");
		initGUI();

		mainShell.pack();
		mainShell.setLocation(200, 200);
		mainShell.setSize(800, 600);
		new LongRunningOperation(display, chatText).start();

	}

	/**
	 * This function is for opening the swt gui and for makeing sure it does not
	 * close after open it emedeatly. This function let the gui open until you
	 * close it. This function is nessesary because swt is a thred.
	 */
	public void open() {
		mainShell.open();
		while (!mainShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * This function is for the main gui. It's create the GUI.
	 */
	public void initGUI() {
		mainShell.setLayout(new BorderLayout(0, 0));

		chatText = new Text(mainShell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		chatText.setLayoutData(BorderLayout.CENTER);
		chatText.setEditable(false);
		chatText.setText("");

		Composite composite = new Composite(mainShell, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(BorderLayout.SOUTH);

		Composite inside = new Composite(composite, SWT.NONE);
		inside.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		GridLayout gl_inside = new GridLayout(2, false);
		gl_inside.verticalSpacing = 0;
		gl_inside.marginWidth = 0;
		gl_inside.marginHeight = 0;
		gl_inside.horizontalSpacing = 0;
		inside.setLayout(gl_inside);

		chatMessage = new Text(inside, SWT.BORDER | SWT.MULTI);
		chatMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		send = new Button(inside, SWT.NONE);
		send.setText("SEND");
		send.setAlignment(SWT.RIGHT);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));
		lblNewLabel.setText("Go to Settings and change your name!");

		Menu menu = new Menu(mainShell, SWT.BAR);
		mainShell.setMenuBar(menu);

		MenuItem settings = new MenuItem(menu, SWT.CASCADE);
		settings.setText("Settings");

		Menu menu_1 = new Menu(settings);
		settings.setMenu(menu_1);

		name = new MenuItem(menu_1, SWT.NONE);
		name.setText("Change Name");

		abort = new MenuItem(menu_1, SWT.NONE);
		abort.setText("Abort");
	}

	/**
	 * 
	 * @return
	 */
	public Text getChatText() {
		return chatText;
	}

	/**
	 * 
	 * @param chatText
	 */
	public void setChatText(Text chatText) {
		this.chatText = chatText;
	}

	/**
	 * 
	 * @return
	 */
	public Shell getMainShell() {
		return mainShell;
	}

	/**
	 * 
	 * @param mainShell
	 */
	public void setMainShell(Shell mainShell) {
		this.mainShell = mainShell;
	}

	/**
	 * 
	 * @return
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * 
	 * @param display
	 */
	public void setDisplay(Display display) {
		this.display = display;
	}

	/**
	 * 
	 * @return
	 */
	public Text getChatMessage() {
		return chatMessage;
	}

	/**
	 * 
	 * @param chatMessage
	 */
	public void setChatMessage(Text chatMessage) {
		this.chatMessage = chatMessage;
	}

	/**
	 * 
	 * @return
	 */
	public MenuItem getAbort() {
		return abort;
	}

	/**
	 * 
	 * @param abort
	 */
	public void setAbort(MenuItem abort) {
		this.abort = abort;
	}

	/**
	 * 
	 * @return
	 */
	public MenuItem getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(MenuItem name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public Button getSend() {
		return send;
	}

	/**
	 * 
	 * @param send
	 */
	public void setSend(Button send) {
		this.send = send;
	}

	/**
	 * This private class is for the changing the data of the chatText. This
	 * class will run a thred. You have to do this this way because there are a
	 * problem if you want to change something in a Thread from another.
	 * 
	 * @author AHMED ALY, Alexander Rieppel
	 * @version 03-14-2013
	 * 
	 */
	private class LongRunningOperation extends Thread {
		private Display display;
		private Text txtField;

		/**
		 * In this constructor you give what you want in the thread to change
		 */
		public LongRunningOperation(Display display, Text txtField) {
			this.display = display;
			this.txtField = txtField;
		}

		/**
		 * This is a Long Running function to simulate the changing
		 */
		public void run() {
			while (!Thread.interrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				if (!txtField.isDisposed()) {
					display.asyncExec(new Runnable() {
						public void run() {
							if (txtField.isDisposed())
								return;
							if (!reader.getMessage().equals("")) {
								txtField.append(reader.getMessage());
								reader.setMessage("");
							}
						}
					});
				}
			}
		}
	}
}
