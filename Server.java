import java.net.ServerSocket;

public class Server {
	final static String BG_RED = "\033[41m";
	final static String BG_GRN = "\033[42m";
	final static String CLR = "\033[0m";

	public static void main (String [] args) {
		try {
			var listener = new ServerSocket(59059);
			System.out.println(BG_RED+" Waiting... "+CLR);
			var socket = listener.accept();
			System.out.println(BG_GRN+" Connected! "+CLR);

			var sender = new SendThread(socket);
			var reciever = new RecieveThread(socket);

			sender.start();
			reciever.start();

			listener.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
