import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static final int PORT = 49822;

	private static final String SERVER_ADDRESS = "localhost";

	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private String username;
	private Chat chatWindow;
	private boolean connected = false;

	public Client(){}

	public void setChatWindow(Chat chat){
		this.chatWindow = chat;
	}

	public void connect(String name){
		this.username = name;

		try{
			socket = new Socket(SERVER_ADDRESS, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			connected = true;

			System.out.println("Connected to server as " + username);
			out.println(username + " has joined the chat");

				Thread receiveThread = new Thread(()-> {
					try {

						String message;

						while((message = in.readLine()) != null){
							if (chatWindow != null){

								int colonInd = message.indexOf(":");


								if (colonIndex > 0){
									String sender = message.substring(0, colonIndex);
									String msg = message.substring( colonIndex + 1).trim();
									chatWindow.addIncomingMessage(sender, msg);
								}

								else {
									chatWindow.addIncomingMessage("SERVER", message);
								}

							} else {
								System.out.println(message);
							}
						}
				}

				catch(IOException e){
					System.out.println("Disconnected from server");
				}

			});

			receiveThread.start();

		}  catch(IOException e) {
			System.out.println("Could not connect to server at " + SERVER_ADDRESS + ":" + PORT);
		}

	}


	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your name: ");
		String name = scanner.nextLine();

		System.out.println("Connecting to " + SERVER_ADDRESS + " on port " + PORT + "...");

		try(Socket socket = new Socket(SERVER_ADDRESS, PORT)){

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println("Connected to server!");

			Thread receiveThread = new Thread(()-> {
				try{
					String message;
					while ((message = in.readLine()) != null){
						System.out.println(message);
					}
				}catch(IOException e){
					System.out.println("Disconnected from server");
				}
			});

			receiveThread.start();

			System.out.println("Type messages ( type quit to exit):");
			String input;
			while(!(input = scanner.nextLine()).equalsIgnoreCase("quit")){
				out.println(name + ": " + input);
			}

			System.out.println("Disconnecting....");

		}catch(IOException e) {

			System.out.println("Could not connect to server at" + SERVER_ADDRESS + ":" + PORT);
		}

		scanner.close();
	}

}
