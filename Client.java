import java.net.Socket;
import java.net.InetAddress;

public class Client {
	final static String BG_RED = "\033[41m";
	final static String BG_GRN = "\033[42m";
	final static String CLR = "\033[0m";

	public static void main (String [] args) {
		try {
			var socket = new Socket(
					InetAddress.getLocalHost(), 59059
					);
			System.out.println(BG_GRN+" Connected! "+CLR);

			var sender = new SendThread(socket);
			var reciever = new RecieveThread(socket);

			reciever.start();
			sender.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
