package Server;

import java.net.*;
import java.io.*;

public class ConnectionReader implements Runnable{
	private ServerSocket server = null;
	private Thread       thread = null;
	//Controller
	public ConnectionReader(int port){
		try{
			System.out.println("Binding port...");
			server = new ServerSocket(port);
			System.out.println("Server started");
			connecting();
		}catch(IOException ioe){  
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}
	public void connecting() throws IOException {
		if(thread==null){
			this.thread=new Thread(this);
			this.thread.start();
		}
	}
	
	
	
	
	
	@Override
	public void run() {
		while(thread!=null){
			System.out.println("Waiting for Clients to connect");
			//controller.addClients(Socket (server.accept())
		}
	}
}
