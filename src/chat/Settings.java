package chat;

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

public class Settings {
	private Shell settingsShell;
	private Display display;
	private Text userName;
	private Button save;
	private Label lblName;
	private Label errorMessage;

	public Settings(Display display) {
		this.display = display;
		settingsShell = new Shell(display);
		settingsShell.setLocation(100, 100);
		settingsShell.setSize(306, 144);
		
	}

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

	public void initGuiVersion() {
		settingsShell.setText("Abort");
		settingsShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		lblName = new Label(composite, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true,
				1, 1));
		lblName.setText("Name: \"Type your Name in damit\" ");

		Label lblVersion = new Label(composite, SWT.NONE);
		lblVersion.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1));
		lblVersion.setText("Version: 1.0");

		Label lblMulticastIp = new Label(composite, SWT.NONE);
		lblMulticastIp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				true, 1, 1));
		lblMulticastIp.setText("Multicast IP: 239.1.2.3");

		Label lblPort = new Label(composite, SWT.NONE);
		lblPort.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true,
				1, 1));
		lblPort.setText("Port: 1234");

		Label lblProductMadeBy = new Label(composite, SWT.NONE);
		lblProductMadeBy.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,
				true, true, 1, 1));
		lblProductMadeBy
				.setText("Product made by AHMED ALY and Alexander Rieppel");
		settingsShell.open();
	}

	public void fehler() {
		settingsShell.setText("A clue for you");
		settingsShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));
		
		errorMessage = new Label(composite, SWT.NONE);
		errorMessage.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		errorMessage.setText("Enter your name");
		
		Label lblAdviseGoTo = new Label(composite, SWT.NONE);
		lblAdviseGoTo.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 1, 1));
		lblAdviseGoTo.setBounds(0, 0, 55, 15);
		lblAdviseGoTo.setText("Advise: Go to settings and enter your name");
		settingsShell.open();
	}

	public void open() {
		settingsShell.open();
		while (!settingsShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public Text getUserName() {
		return userName;
	}

	public void setUserName(Text userName) {
		this.userName = userName;
	}

	public Shell getSettingsShell() {
		return settingsShell;
	}

	public void setSettingsShell(Shell settingsShell) {
		this.settingsShell = settingsShell;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}

	public Label getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Label errorMessage) {
		this.errorMessage = errorMessage;
	}

}
