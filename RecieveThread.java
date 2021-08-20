import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class RecieveThread extends Thread {
	final static String BG_RED = "\033[41m";
	final static String BG_BLU = "\033[44m";
	final static String RED = "\033[31m";
	final static String BLU = "\033[34m";
	final static String WHT = "\033[97m";
	final static String CLR = "\033[0m";

	Socket target = null;
	Scanner in = new Scanner(System.in);
	Scanner tcp_in = null;

	public RecieveThread (Socket s) {
		this.target = s;
	}

	private void makeConnection() {
		try {
			this.tcp_in = new Scanner(
					target.getInputStream()
					);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run () {
		this.makeConnection();
		while (true) {
			String msg = tcp_in.nextLine();
			if(msg.contains("/end")) {
				System.out.println("Thank you for using TermChat");
				this.stop();
			}
			System.out.print(CLR+"\033[k\n");
			System.out.println(msg);
			System.out.print(BLU+" "+BG_BLU+WHT);
		}
	}
}
