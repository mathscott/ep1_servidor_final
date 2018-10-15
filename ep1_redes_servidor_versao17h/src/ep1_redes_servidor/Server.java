package ep1_redes_servidor;

import java.io.*;
import java.net.*;

class Server {

	public static void main(String argv[]) throws Exception {

		System.out.println(" Server is Running  ");
		ServerSocket mysocket = new ServerSocket(5555);
		Server server = new Server();
		while (true) {
			Socket s = null;
			try {
				s = mysocket.accept();
				System.out.println("Novo Cliente Conectado" + s);
				
				DataInputStream din = new DataInputStream(s.getInputStream());
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				String msgin = "", msgout = "";
				
				Thread t = new ClientHandler(s, din, dout);
				t.start();
			} catch (Exception e) {
				
			}
			

			
		}
	}

}
