import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public class View {
	// Various GUI components and info
	private Shell mainShell;
	private Shell hostShell;
	private Shell clientShell;
	private Shell failShell;
	private Shell chatShell;
	private Display display = null;

	// First window
	private Label welcome = null;
	private Label fName = null;
	private Text userName = null;// TextField
	private Combo userColor = null;
	private Label hostLabel = null;// RadioButton Label
	private Label clientLabel = null;// RadioButton Label
	private Button hostOption = null;// RadioButton
	private Button clientOption = null;// RadioButton
	private Button nextButtonFirst = null;

	// Host window
	private Button nextButtonHost = null;
	private Label chatChoose = null;
	private Combo userNumber = null;
	private Label lchatName = null;
	private Text chatName = null;
	private Label lchatPassword = null;
	private Text chatPassword = null;

	// Client Wondow
	private Button nextButtonClient = null;
	private List chatList = null;
	private Label chatLabel = null;

	// Chat Window
	private List userList = null;
	private Text chatText = null;
	private Text chatMessage = null;
	private Button sendButton = null;

	// Fail window
	private Label fail = null;
	private Button back = null;

	public View(Display display) {
		this.display=display;
		mainShell = new Shell(display);
		mainShell.setText("JavaChat");
		mainGUI();
		mainShell.pack();
		mainShell.setLocation(300, 300);
		mainShell.setSize(300, 300);

	}

	public void mainGUI() {
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

		nextButtonFirst = new Button(mainShell, SWT.NONE);
		nextButtonFirst.setLayoutData(BorderLayout.SOUTH);
		nextButtonFirst.setText("Next");
		// nextButton.addSelectionListener(listener);

	}

	public void hostGUI() {
		hostShell = new Shell(display);
		hostShell.setText("JavaChat");

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

		nextButtonHost = new Button(hostShell, SWT.NONE);
		nextButtonHost.setLayoutData(BorderLayout.SOUTH);
		nextButtonHost.setText("Next");
		// nextButtonHost.addSelectionListener(listener);
		hostShell.pack();
		hostShell.setLocation(300, 300);
		hostShell.setSize(300, 300);
		hostShell.open();

		
	}

	public void clientGUI() {
		clientShell = new Shell(display);
		clientShell.setText("JavaChat");
		clientShell.setLayout(new BorderLayout(0, 0));

		chatLabel = new Label(clientShell, SWT.CENTER);
		chatLabel.setLayoutData(BorderLayout.NORTH);
		chatLabel.setText("Choose your Chat");

		chatList = new List(clientShell, SWT.BORDER | SWT.V_SCROLL);
		chatList.setLayoutData(BorderLayout.CENTER);

		nextButtonClient = new Button(clientShell, SWT.CENTER);
		nextButtonClient.setLayoutData(BorderLayout.SOUTH);
		nextButtonClient.setText("Next");
		clientShell.pack();
		clientShell.setLocation(300, 300);
		clientShell.setSize(300, 300);
		clientShell.open();
		// nextButtonClient.addSelectionListener(listener);
	}

	public void chatGUI() {
		chatShell = new Shell(display);
		chatShell.setText("JavaChat");

		chatShell.setLayout(new BorderLayout(0, 0));

		userList = new List(chatShell, SWT.BORDER | SWT.V_SCROLL);
		userList.setLayoutData(BorderLayout.WEST);

		chatText = new Text(chatShell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		chatText.setLayoutData(BorderLayout.CENTER);

		Composite composite = new Composite(chatShell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		chatMessage = new Text(composite, SWT.BORDER | SWT.MULTI);
		chatMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		sendButton = new Button(composite, SWT.NONE);
		sendButton.setAlignment(SWT.RIGHT);
//		sendButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//			}
//		});
		sendButton.setText("SEND");
		chatShell.pack();
		chatShell.setLocation(300, 300);
		chatShell.setSize(300, 300);
		chatShell.open();

	}

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
		fail.setText("dghsrtju");
		back = new Button(failShell, SWT.None);
		back.setLayoutData(BorderLayout.SOUTH);
		back.setText("back");
	}

	public Shell getMainShell() {
		return mainShell;
	}

	public void setMainShell(Shell mainShell) {
		this.mainShell = mainShell;
	}

	public Shell getHostShell() {
		return hostShell;
	}

	public void setHostShell(Shell hostShell) {
		this.hostShell = hostShell;
	}

	public Shell getClientShell() {
		return clientShell;
	}

	public void setClientShell(Shell clientShell) {
		this.clientShell = clientShell;
	}

	public Shell getFailShell() {
		return failShell;
	}

	public void setFailShell(Shell failShell) {
		this.failShell = failShell;
	}

	public Shell getChatShell() {
		return chatShell;
	}

	public void setChatShell(Shell chatShell) {
		this.chatShell = chatShell;
	}

	public Label getWelcome() {
		return welcome;
	}

	public void setWelcome(Label welcome) {
		this.welcome = welcome;
	}

	public Text getUserName() {
		return userName;
	}

	public void setUserName(Text userName) {
		this.userName = userName;
	}

	public Combo getUserColor() {
		return userColor;
	}

	public void setUserColor(Combo userColor) {
		this.userColor = userColor;
	}

	public Button getHostOption() {
		return hostOption;
	}

	public void setHostOption(Button hostOption) {
		this.hostOption = hostOption;
	}

	public Button getClientOption() {
		return clientOption;
	}

	public void setClientOption(Button clientOption) {
		this.clientOption = clientOption;
	}

	public Button getNextButtonFirst() {
		return nextButtonFirst;
	}

	public void setNextButtonFirst(Button nextButtonFirst) {
		this.nextButtonFirst = nextButtonFirst;
	}

	public Button getNextButtonHost() {
		return nextButtonHost;
	}

	public void setNextButtonHost(Button nextButtonHost) {
		this.nextButtonHost = nextButtonHost;
	}

	public Combo getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Combo userNumber) {
		this.userNumber = userNumber;
	}

	public Text getChatName() {
		return chatName;
	}

	public void setChatName(Text chatName) {
		this.chatName = chatName;
	}

	public Text getChatPassword() {
		return chatPassword;
	}

	public void setChatPassword(Text chatPassword) {
		this.chatPassword = chatPassword;
	}

	public Button getNextButtonClient() {
		return nextButtonClient;
	}

	public void setNextButtonClient(Button nextButtonClient) {
		this.nextButtonClient = nextButtonClient;
	}

	public List getChatList() {
		return chatList;
	}

	public void setChatList(List chatList) {
		this.chatList = chatList;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public Text getChatText() {
		return chatText;
	}

	public void setChatText(Text chatText) {
		this.chatText = chatText;
	}

	public Text getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(Text chatMessage) {
		this.chatMessage = chatMessage;
	}

	public Button getSendButton() {
		return sendButton;
	}

	public void setSendButton(Button sendButton) {
		this.sendButton = sendButton;
	}

	public Label getFail() {
		return fail;
	}

	public void setFail(Label fail) {
		this.fail = fail;
	}

	public Button getBack() {
		return back;
	}

	public void setBack(Button back) {
		this.back = back;
	}

}
