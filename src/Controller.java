import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

public class Controller {
	//private final Model model = new Model();
	private View view;
	private Display display;
	private int go = 1;

	Controller(Display display) {
		this.display = display;
		view = new View(display);
		SelectionHandler listener = new SelectionHandler();
		view.getNextButtonFirst().addSelectionListener(listener);

		view.getMainShell().open();
		while (!view.getMainShell().isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	} 

	public void hostRun() {
		view.hostGUI();
		view.getNextButtonHost().addSelectionListener(new SelectionHandler());
		while (!view.getHostShell().isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public void clientRun() {
		view.clientGUI();
		view.getNextButtonClient().addSelectionListener(new SelectionHandler());
		while (!view.getClientShell().isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	public void chatRun() {
		view.chatGUI();
		view.getSendButton().addSelectionListener(new SelectionHandler());
		while (!view.getChatShell().isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}

	private class SelectionHandler extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			boolean bool = false;
			if (event.getSource() == view.getNextButtonFirst()) {
				if (view.getHostOption().getSelection()
						&& !view.getUserName().getText().equals("")) {
					view.getMainShell().close();
					go = 2;
					hostRun();
				} else if (view.getClientOption().getSelection()
						&& !view.getUserName().getText().equals("")) {
					view.getMainShell().close();
					go = 3;
					clientRun();
				} else {
					if (view.getUserName().getText().equals(""))
						bool = false;
					else if (!view.getHostOption().getSelection()
							&& !view.getClientOption().getSelection())
						bool = true;

					view.getMainShell().close();
					view.failGUI();
					if (bool)
						view.getFail().setText("Please select host or client");
					else
						view.getFail().setText("Please type your Name");
					view.getFailShell().pack();
					view.getFailShell().setSize(300, 100);
					view.getBack().addSelectionListener(this);
					view.getFailShell().open();
					while (!view.getFailShell().isDisposed())
						if (!display.readAndDispatch())
							display.sleep();
				}
			} else if (event.getSource() == view.getNextButtonHost()) {
				if (!view.getChatName().getText().equals("")) {
					view.getHostShell().close();
					go = 4;
					chatRun();
				} else {
					view.getHostShell().close();
					view.failGUI();
					view.getFail().setText("Please enter a Chat Name");
					view.getFailShell().pack();
					view.getFailShell().setSize(300, 100);
					view.getBack().addSelectionListener(this);
					view.getFailShell().open();
					while (!view.getFailShell().isDisposed())
						if (!display.readAndDispatch())
							display.sleep();
				}
			} else if (event.getSource() == view.getNextButtonClient()) {
				if (!view.getChatServerAddress().getText().equals("")&&!view.getChatPort().getText().equals("")) {
					view.getClientShell().close();
					go = 4;
					chatRun();
				} else {
					view.getClientShell().close();
					view.failGUI();
					view.getFail().setText("Enter a Server Address and the Port");
					view.getFailShell().pack();
					view.getFailShell().setSize(300, 100);
					view.getBack().addSelectionListener(this);
					view.getFailShell().open();
					while (!view.getFailShell().isDisposed())
						if (!display.readAndDispatch())
							display.sleep();
				}
			} else if (event.getSource() == view.getBack()) {
				view.getFailShell().close();
				if (go == 1)
					new Controller(display);
				else if (go == 2)
					hostRun();
				else if (go == 3)
					clientRun();
				else if (go == 4)
					chatRun();
			}

		}
	}

	public static void main(String args[]) {
		Display display = new Display();
		new Controller(display);
		display.dispose();
	}

}