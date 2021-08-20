import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {
	final static String BG_RED = "\033[41m";
	final static String BG_BLU = "\033[44m";
	final static String RED = "\033[31m";
	final static String BLU = "\033[34m";
	final static String WHT = "\033[97m";
	final static String CLR = "\033[0m";

	Socket target = null;
	Scanner in = new Scanner(System.in);
	PrintWriter tcp_out = null;

	public SendThread (Socket s) {
		this.target = s;
	}

	private void makeConnection() {
		try {
			this.tcp_out = new PrintWriter(
					target.getOutputStream(),true
					);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String sendPad(String s) {
		return "\033[k\n"+RED+""+WHT+BG_RED+" "+s+" "+CLR+RED+""+CLR;
	}

	@Override
	public void run () {
		this.makeConnection();
		while (true) {
			System.out.print(BLU+" "+BG_BLU+WHT);
			String str = in.nextLine();
			System.out.print(CLR);
			if (str.contains("/close")){
				tcp_out.println(sendPad(str));
				System.out.println("Thank you for using TermChat");
				this.stop();
			}
			tcp_out.println(sendPad(str));
		}
	}
}
