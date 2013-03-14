
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;


public class ChatGUI {
	public static Shell chatShell;
	public Display display;
	
	private Text text;
	private Text text_1;
	
	private String name;
	private Color color;
	private String[] list;

	public ChatGUI(Display display, String name, Color color, String[] list, Shell oldShell) {
		this(display, name, color, list);
		oldShell.close();
		while (!chatShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	public ChatGUI(Display display, String name, Color color, Shell oldShell) {
		this(display, name, color);
		oldShell.close();
		while (!chatShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	public ChatGUI(Display display, String name, Color color, String[] list) {
		this(display);
		this.display = display;
		this.name = name;
		this.color = color;
		this.list = list;
	}
	
	public ChatGUI(Display display, String name, Color color) {
		this(display);
		this.display = display;
		this.name = name;
		this.color = color;
	}

	public ChatGUI(Display display){
		chatShell = new Shell(display);
		chatShell.setText("JavaChat");
		initGUI();
		chatShell.pack();
		chatShell.setLocation(300, 300);
		chatShell.setSize(800, 600);

		chatShell.open();
	}
	

	public void initGUI() {
		chatShell.setLayout(new BorderLayout(0, 0));

		List list = new List(chatShell, SWT.BORDER | SWT.V_SCROLL);
		list.setLayoutData(BorderLayout.WEST);

		text = new Text(chatShell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text.setLayoutData(BorderLayout.CENTER);

		Composite composite = new Composite(chatShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		text_1 = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setAlignment(SWT.RIGHT);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNewButton.setText("SEND");

	}
}
