import java.io.*;
import java.net.*;
import java.util.*;

public class Server{ 
	private static final int PORT = 49822;
	private static Set<Printwriter> clientWriters = new Hashset<>();

	public static void main(String[] args){

		System.out.println("===GamblingHell Server Started on port" + PORT +" ===");

		try(ServerSocket serverSocket = new ServerSocket(PORT)){
			while(true){

				Socket clientSocket = serverSocket.accept();

				System.out.println("New client connected from: " + clientSocket.getInetAddress());
				ClientHandler handler = new ClientHandler(clientSocket); new Thread(handler).start();
			}

		} catch (IOException e ){
			System.out.println("Server error: " + e.getMessage());
			System.out.println(" Try changint the PORT to another number between 49152 and 65535");
			}
	}

	static class ClientHandler implements Runnable{
		private Socket sockt;
		private PrintWriter out;
		private BufferedReader in;
		private String clientAddress;

		public ClientHandler (Socket socket){
			this.socket = socket;
			this.clientAddress = socket.getInetAddress().toString();
		}
		public void run(){
			try{
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				synchronized(clientWriters){
					clientWriters.add(out);
				}
				System.out.println("Client " + clientAddress + " is now ready to chat");
				Strinf message;
				while((message - in.readLine()) != null) {
					System.out.println("[" + clientAddress + "] " + message);
					synchronized(clientWriters){
						for(PrintWriter writer  : clientWriters){
							writer.println(message);
						}
					}
					}
			} catch (IOException e)[
			System.out.println("Client " + clientAddress + " disconnected");
		} finally {
			synchronized (clientWriters0{
				clientWriters.remove(out0;
			}
			try [socket.close(); } catch(IOException e) {}
			System.out.println("Cleaned up client " + clientAddress);
	}
}
