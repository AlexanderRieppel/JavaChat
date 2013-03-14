

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public class StartGUI {

	// Various GUI components and info
	public static Shell mainShell;
	public static Shell failShell;
	public static Display display = null;

	// First window
	public static Label welcome = null;
	public static Label fName = null;
	public static Text userName = null;// TextField
	public static Combo userColor = null;
	public static Label hostLabel = null;// RadioButton Label
	public static Label clientLabel = null;// RadioButton Label
	public static Button hostOption = null;// RadioButton
	public static Button clientOption = null;// RadioButton
	public static Button nextButton = null;

	// Fail window
	public static Label fail = null;
	public static Button back = null;

	public StartGUI(Display display) {
		mainShell = new Shell(display);
		mainShell.setText("JavaChat");
		initGUI();
		mainShell.pack();
		mainShell.setLocation(300, 300);
		mainShell.setSize(300, 300);

		mainShell.open();
		while (!mainShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public void initGUI() {
		mainShell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(mainShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));
		GridLayout gl_second = new GridLayout();
		gl_second.horizontalSpacing = 20;
		gl_second.numColumns = 2;
		gl_second.makeColumnsEqualWidth = true;

		Composite first = new Composite(composite, SWT.CENTER);
		first.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1,
				1));
		first.setLayout(new GridLayout(2, false));
		Composite name = new Composite(first, SWT.CENTER);
		name.setLayout(new GridLayout(1, false));
		Composite second = new Composite(composite, SWT.CENTER);
		second.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1,
				1));
		second.setLayout(gl_second);

		welcome = new Label(mainShell, SWT.CENTER);
		welcome.setLayoutData(BorderLayout.NORTH);
		welcome.setText("Welcome to JavaChat 0.1");

		fName = new Label(name, SWT.CENTER);
		fName.setText("Name");
		fName.setLocation(20, 45);

		userName = new Text(name, SWT.CENTER);
		GridData gd_userName = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 1);
		gd_userName.widthHint = 100;
		userName.setLayoutData(gd_userName);

		userColor = new Combo(first, SWT.NONE);
		userColor.setBackground(new Color(null, 50, 50, 50));

		hostLabel = new Label(second, SWT.NONE);
		hostLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));
		hostLabel.setText("Host");

		clientLabel = new Label(second, SWT.NONE);
		clientLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));
		clientLabel.setText("Client");

		hostOption = new Button(second, SWT.RADIO);
		hostOption.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));

		clientOption = new Button(second, SWT.RADIO);
		clientOption.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));

		nextButton = new Button(mainShell, SWT.NONE);
		nextButton.setLayoutData(BorderLayout.SOUTH);
		nextButton.setText("Next");
		nextButton.addSelectionListener(listener);

	}

	public static void main(String args[]) {
		display = new Display();
		new StartGUI(display);
		display.dispose();
	}

	private SelectionListener listener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			if (event.getSource() == nextButton) {
				if (hostOption.getSelection() && !userName.getText().equals(""))
					new HostGUI(display, userName.getText(),
							userColor.getBackground(), mainShell);
				else if (clientOption.getSelection()
						&& !userName.getText().equals(""))
					new ClientGUI(display, userName.getText(),
							userColor.getBackground(), mainShell);
				else
					failGUI();
			}
		}
	};

	public void failGUI() {
		failShell = new Shell(display);
		failShell.setText("JavaChat");
		failShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(failShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		fail = new Label(composite, SWT.NONE);
		fail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1,
				1));
		fail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1,
				1));
		if (!hostOption.getSelection() || !clientOption.getSelection())
			fail.setText("Please select host or client");
		if (userName.getText().equals(""))
			fail.setText("Please type your Name");
		back = new Button(failShell, SWT.None);
		back.setLayoutData(BorderLayout.SOUTH);
		back.setText("back");
		back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				failShell.close();
				new StartGUI(display);
				setClientOption(clientOption);
				setHostOption(hostOption);
				setUserName(userName);
			}
		});
		failShell.pack();
		failShell.setSize(300, 100);
		mainShell.close();

		failShell.open();
		while (!failShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public static Text getUserName() {
		return userName;
	}

	public static void setUserName(Text userName) {
		StartGUI.userName = userName;
	}

	public static Button getHostOption() {
		return hostOption;
	}

	public static void setHostOption(Button hostOption) {
		StartGUI.hostOption = hostOption;
	}

	public static Button getClientOption() {
		return clientOption;
	}

	public static void setClientOption(Button clientOption) {
		StartGUI.clientOption = clientOption;
	}

}
