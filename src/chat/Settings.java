package chat;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class Settings {
	public static Shell settingsShell;
	public Display display;
	private Text userName;

	public Settings(Display display) {
		this.display = display;
		settingsShell = new Shell(display);

		initGUI();
		settingsShell.setLocation(100, 100);
		settingsShell.setSize(306, 144);

	}

	public void initGUI() {
		settingsShell.setText("Type in your Name");
		settingsShell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(settingsShell, SWT.NONE);

		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1,
				1));
		label.setText("Name :");

		userName = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		gd_text.widthHint = 100;
		userName.setLayoutData(gd_text);
		settingsShell.open();
	}

	public void initGuiVersion() {
		settingsShell.setText("Version");
		settingsShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(settingsShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		Label lblName = new Label(composite, SWT.NONE);
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

}
