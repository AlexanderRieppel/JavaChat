import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import swing2swt.layout.BorderLayout;

public class HostGUI {
	public static Shell hostShell;
	public static Shell failShell;
	public Display display;

	public static Button nextButton = null;
	public static Label chatChoose = null;
	public static Combo userNumber = null;
	public static Label lchatName = null;
	public static Text chatName = null;
	public static Label lchatPassword = null;
	public static Text chatPassword = null;
	
	private String name;
	private Color color;

	// Fail window
	public static Label fail = null;
	public static Button back = null;

	public HostGUI(Display display, String name, Color color, Shell oldShell) {
		this(display, name, color);
		oldShell.close();
		while (!hostShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public HostGUI(Display display, String name, Color color) {
		this.display = display;
		this.name = name;
		this.color = color;
		hostShell = new Shell(display);
		hostShell.setText("JavaChat");
		initGUI();
		hostShell.pack();
		hostShell.setLocation(300, 300);
		hostShell.setSize(300, 300);
		hostShell.open();
	}

	public void initGUI() {
		hostShell.setLayout(new BorderLayout(0, 0));

		chatChoose = new Label(hostShell, SWT.CENTER);
		chatChoose.setLayoutData(BorderLayout.NORTH);
		chatChoose.setText("Choose your Chat");

		Composite composite = new Composite(hostShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				true, 1, 1));

		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);

		lchatName = new Label(composite_1, SWT.NONE);
		lchatName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1));
		lchatName.setText("Chat Name");

		chatName = new Text(composite_1, SWT.BORDER);
		GridData gd_chatname = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_chatname.widthHint = 100;
		chatName.setLayoutData(gd_chatname);

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				true, 1, 1));

		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.marginWidth = 0;
		composite_2.setLayout(gl_composite_2);

		lchatPassword = new Label(composite_2, SWT.NONE);
		lchatPassword.setText("Chat Password");

		chatPassword = new Text(composite_2, SWT.BORDER | SWT.PASSWORD);
		GridData gd_chatpassword = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_chatpassword.widthHint = 100;
		chatPassword.setLayoutData(gd_chatpassword);

		userNumber = new Combo(composite, SWT.NONE);
		userNumber.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				true, 1, 1));

		nextButton = new Button(hostShell, SWT.NONE);
		nextButton.setLayoutData(BorderLayout.SOUTH);
		nextButton.setText("Next");
		nextButton.addSelectionListener(listener);
	}

	private SelectionListener listener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			if (event.getSource() == nextButton) {
				if (!chatName.getText().equals(""))
					new ChatGUI(display, name, color, hostShell);
				else {
					failShell = new Shell(display);
					failShell.setText("JavaChat");
					failGUI();
					failShell.pack();
					failShell.setSize(300, 100);
					hostShell.close();

					failShell.open();
					while (!failShell.isDisposed()) {
						if (!display.readAndDispatch())
							display.sleep();
					}
				}
			}
		}
	};

	public void failGUI() {
		failShell.setLayout(new BorderLayout(0, 0));
		Composite composite = new Composite(failShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new GridLayout(1, false));

		fail = new Label(composite, SWT.NONE);
		fail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1,
				1));
		fail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1,
				1));

		fail.setText("Please enter a Chat Name");
		back = new Button(failShell, SWT.None);
		back.setLayoutData(BorderLayout.SOUTH);
		back.setText("back");
		back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				failShell.close();
				new HostGUI(display, name, color);
			}
		});
	}
}
