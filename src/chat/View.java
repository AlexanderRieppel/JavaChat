package chat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public class View {
	private static Shell mainShell;
	private Display display;
	private Text chatText;
	private Text chatMessage;
	private List list;
	private Reader reader;

	public View(final Display display, Reader reader) {
		this.display = display;
		this.reader = reader;
		mainShell = new Shell(display);
		mainShell.setText("JavaChat");
		initGUI();

		mainShell.pack();
		mainShell.setLocation(200, 200);
		mainShell.setSize(800, 600);
		System.out.println(reader.getMessage());
		new LongRunningOperation(display, chatText, list).start();

	}

	public void open() {
		mainShell.open();
		while (!mainShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// chatText.append(controller.getReader().getMessage());
				display.sleep();
			}
		}
	}

	public void initGUI() {
		mainShell.setLayout(new BorderLayout(0, 0));

		chatText = new Text(mainShell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		chatText.setLayoutData(BorderLayout.CENTER);
		chatText.setText("");

		list = new List(mainShell, SWT.BORDER);
		list.setLayoutData(BorderLayout.WEST);

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

		Button button = new Button(inside, SWT.NONE);
		button.setText("SEND");
		button.setAlignment(SWT.RIGHT);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));
		lblNewLabel.setText("GO to Settings and change your name in!");

		Menu menu = new Menu(mainShell, SWT.BAR);
		mainShell.setMenuBar(menu);

		MenuItem settings = new MenuItem(menu, SWT.CASCADE);
		settings.setText("Settings");

		Menu menu_1 = new Menu(settings);
		settings.setMenu(menu_1);

		MenuItem name = new MenuItem(menu_1, SWT.NONE);
		name.setText("Change Name");

		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.setText("Abort");
	}

	public Text getChatText() {
		return chatText;
	}

	public void setChatText(Text chatText) {
		this.chatText = chatText;
	}

	public Shell getMainShell() {
		return mainShell;
	}

	public void setMainShell(Shell mainShell) {
		this.mainShell = mainShell;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Text getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(Text chatMessage) {
		this.chatMessage = chatMessage;
	}

	class LongRunningOperation extends Thread {
		private Display display;
		private Text progressBar;
		private List list;
		private String s;
		private String[] neu;
		private int anzahl;

		/**
		 * Alles übergeben was aus diesem Thread erreichbar sein soll
		 */
		public LongRunningOperation(Display display, Text progressBar, List list) {
			this.display = display;
			this.progressBar = progressBar;
			this.list = list;
			s = "";
			anzahl = 0;
		}

		/**
		 * Länger laufende Methode um eine Verarbeitung zu simulieren
		 */
		public void run() {
			while (!Thread.interrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				// progressBar.setText(reader.getMessage());
				// ProgressBar kann nur via asyncExec aktualisiert werden!
				s = " ";
				if (!progressBar.isDisposed()) {
					display.asyncExec(new Runnable() {
						public void run() {

							if (progressBar.isDisposed())
								return;
							if (!s.equals(progressBar.getText())) {
								neu = reader.getLastMessage().split(": ");
								if (neu.length == 2)
									if (neu[1].equals("I connected")) {
										list.add(neu[0]);
										neu = null;
										reader.setLastMessage("");
									}
								if (!reader.getMessage().equals("")) {
									progressBar.append(reader.getMessage());
									reader.setMessage("");
									s = progressBar.getText();
								}
							}
						}
					});
				}
				// display.asyncExec(new Runnable() {
				// private List list2 = null;
				//
				// public void run() {
				//
				// if (list.isDisposed())
				// return;
				// if (anzahl != list.getItemCount()) {
				// try {
				// list2 = list;
				// list.removeAll();
				// for (int i = 1; i <= list2.getItemCount(); i++) {
				// controller.send(list2.getItem(i)
				// + "I connected");
				// neu = reader.getLastMessage().split(": ");
				// if (neu.length == 2)
				// if (neu[1].equals("I connected")) {
				// list.add(neu[0]);
				// neu = null;
				// reader.setLastMessage("");
				// }
				// }
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				//
				// }
				// }
				// });
			}
		}
	}
}
