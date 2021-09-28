import java.net.*;
import java.io.*;

public class DateServer {
	public static void main(String[] args) throws IOException {
		int count1 = 1;
		int count2 = 2;
		int count3 = 3;
		try {
			// This creates a listener socket
			ServerSocket sock = new ServerSocket(6013);
			while (true) {
				Socket client = sock.accept();
				// pout is the output stream to the client
//				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
//				pout.println(new java.util.Date().toString());
//				client.close();
//				count++;
				worker work1 = new worker(client,count1);
				worker work2 = new worker(client,count2);
				worker work3 = new worker(client,count3);
				work1.start();
				work2.start();
				work3.start();
							
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}