import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;

public class ClientGUI {
	public static Shell clientShell;
	public static Shell failShell;
	public Display display;

	public static Button nextButton = null;
	public static List chatList = null;
	public static Label chatLabel = null;

	private String name;
	private Color color;

	// Fail window
	public static Label fail = null;
	public static Button back = null;

	public ClientGUI(Display display, String name, Color color, Shell oldShell) {
		new ClientGUI(display, name, color);
		oldShell.close();
		while (!clientShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public ClientGUI(Display display, String name, Color color) {
		this.display = display;
		this.name = name;
		this.color = color;
		clientShell = new Shell(display);
		clientShell.setText("JavaChat");
		initGUI();
		clientShell.pack();
		clientShell.setLocation(300, 300);
		clientShell.setSize(300, 300);
		clientShell.open();
	}

	public void initGUI() {
		clientShell.setLayout(new BorderLayout(0, 0));

		chatLabel = new Label(clientShell, SWT.CENTER);
		chatLabel.setLayoutData(BorderLayout.NORTH);
		chatLabel.setText("Choose your Chat");

		chatList = new List(clientShell, SWT.BORDER | SWT.V_SCROLL);
		chatList.setLayoutData(BorderLayout.CENTER);

		nextButton = new Button(clientShell, SWT.CENTER);
		nextButton.setLayoutData(BorderLayout.SOUTH);
		nextButton.setText("Next");
		nextButton.addSelectionListener(listener);
	}

	private SelectionListener listener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			if (event.getSource() == nextButton) {
				if (chatList.getSelection() != null)
					new ChatGUI(display, name, color, chatList.getSelection(), clientShell);
				else {
					failShell = new Shell(display);
					failShell.setText("JavaChat");
					failGUI();
					failShell.pack();
					failShell.setSize(300, 100);
					clientShell.close();

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

		fail.setText("Please select a Chat");
		back = new Button(failShell, SWT.None);
		back.setLayoutData(BorderLayout.SOUTH);
		back.setText("back");
		back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				failShell.close();
				new ClientGUI(display, name, color);
			}
		});
	}
}
