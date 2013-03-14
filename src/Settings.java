import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

/**
 * This GUI-Class is for the settings in the menu of the GUI-Class View.
 * 
 * @author AHMED ALY, Alexander Rieppel
 * @version 03-14-2013
 */
public class Settings {
	private Shell settingsShell;
	private Display display;
	private Text userName;
	private Button save;
	private Label errorMessage;

	/**
	 * Main Constructor initialize the Shell and set the Location and the size.
	 * 
	 * @param display
	 */
	public Settings(Display display) {
		this.display = display;
		settingsShell = new Shell(display);
		settingsShell.setLocation(100, 100);
		settingsShell.setSize(306, 144);

	}

	/**
	 * This function is for the menuitem change name. It's create a GUI with a
	 * textfield to type in the name and a button save to save the new name.
	 */
	public void initGUI() {
		settingsShell.setText("Type in your Name");
		settingsShell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1,
				1));
		label.setText("Name :");

		userName = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		gd_text.widthHint = 100;
		userName.setLayoutData(gd_text);
		save = new Button(settingsShell, SWT.NONE);
		save.setLayoutData(BorderLayout.SOUTH);
		save.setText("Save");
		settingsShell.open();
	}

	/**
	 * This function is for the menuitem abort. It's create a GUI with a labels
	 * with the programm information.
	 */
	public void initGuiVersion() {
		settingsShell.setText("Abort");
		settingsShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		Label lblVersion = new Label(composite, SWT.NONE);
		lblVersion.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1));
		lblVersion.setText("Version: 1.0");

		Label lblMulticastIp = new Label(composite, SWT.NONE);
		lblMulticastIp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1));
		lblMulticastIp.setText("Multicast IP: 226.1.3.5");

		Label lblPort = new Label(composite, SWT.NONE);
		lblPort.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true,
				1, 1));
		lblPort.setText("Port: 6789");

		Label lblProductMadeBy = new Label(composite, SWT.NONE);
		lblProductMadeBy.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,
				true, true, 1, 1));
		lblProductMadeBy
				.setText("Product made by AHMED ALY and Alexander Rieppel");
		settingsShell.open();
	}

	/**
	 * This function is for the view gui if you try to type a message without
	 * set a name. It's create a GUI with a label with the write message.
	 */
	public void fehler() {
		settingsShell.setText("A clue for you");
		settingsShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		errorMessage = new Label(composite, SWT.NONE);
		errorMessage.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1));
		errorMessage.setText("Enter your name");

		Label lblAdviseGoTo = new Label(composite, SWT.NONE);
		lblAdviseGoTo.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true,
				false, 1, 1));
		lblAdviseGoTo.setBounds(0, 0, 55, 15);
		lblAdviseGoTo.setText("Advise: Go to settings and enter your name");
		settingsShell.open();
	}

	/**
	 * This function is for opening the swt gui and for makeing sure it does not
	 * close after open it emedeatly. This function let the gui open until you
	 * close it. This function is nessesary because swt is a thred.
	 */
	public void open() {
		settingsShell.open();
		while (!settingsShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * get the usernametextfield
	 * 
	 * @return usernametextfield
	 */
	public Text getUserName() {
		return userName;
	}

	/**
	 * set the usernametextfield
	 * 
	 * @param userName
	 *            usernametextfield
	 */
	public void setUserName(Text userName) {
		this.userName = userName;
	}

	/**
	 * get the shell
	 * 
	 * @return the shell
	 */
	public Shell getSettingsShell() {
		return settingsShell;
	}

	/**
	 * set the shell
	 * 
	 * @param settingsShell
	 *            shell
	 */
	public void setSettingsShell(Shell settingsShell) {
		this.settingsShell = settingsShell;
	}

	/**
	 * get the savebutton
	 * 
	 * @return savebutton
	 */
	public Button getSave() {
		return save;
	}

	/**
	 * set savebutton
	 * 
	 * @param save
	 *            savebutton
	 */
	public void setSave(Button save) {
		this.save = save;
	}

	/**
	 * get the errormessagelabel
	 * 
	 * @return errormessagelabel
	 */
	public Label getErrorMessage() {
		return errorMessage;
	}

	/**
	 * set the errormessagelabel
	 * 
	 * @param errorMessage
	 *            errormessagelabel
	 */
	public void setErrorMessage(Label errorMessage) {
		this.errorMessage = errorMessage;
	}

}
