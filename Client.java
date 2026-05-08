import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static final int PORT = 49822;

	private static final String SERVER_ADDRESS = "localhost";

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
