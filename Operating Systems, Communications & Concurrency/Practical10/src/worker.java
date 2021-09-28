import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class worker extends Thread{
		private Socket client;
		private int count;
	

public worker(Socket client, int count) {
		super();
		this.client = client;
		this.count = count;
	}

	@Override
	public void run() {
		try {
			PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
			pout.println(new java.util.Date().toString()+" "+count);	
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
	
	
	

}
