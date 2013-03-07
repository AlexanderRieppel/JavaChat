package Server;

import java.io.*;
import java.net.*;

public class Connection extends Thread{
	private Socket socket=null;
	private int id = -1;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Connection(Socket s){
		super();
		System.out.println("Connecting...");
		socket=s;
		id = socket.getPort();
		try {
			connecting();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void connecting() throws IOException {
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(socket.getOutputStream(),true);
		this.start();
	}
	
	public void run(){
		while(!this.interrupted()){
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				
			}
			try{
				if(br.ready()){
					System.out.println(br.readLine());
				}
			}catch(IOException ioe){
				System.out.println(" ERROR reading: " + ioe.getMessage());
				//controller remov
	            interrupt();
			}
		}
	}
	
	public void sendMessage(String text){
		try{
			pw.print(text+"\n");
			pw.flush();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
