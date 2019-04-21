package lab10;



import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Server extends Thread
{
	private static final int port  = 6666; // ˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜ ˜˜˜˜˜˜˜
	private String TEMPL_MSG  = "˜˜˜˜˜˜ '%d' ˜˜˜˜˜˜˜ ˜˜˜˜˜˜ ˜˜˜˜: \n\t";
    private String TEMPL_MSG2  = "˜˜˜˜˜˜ '%d' ˜˜˜˜˜˜˜ ˜˜˜˜˜ ˜˜˜˜˜˜˜˜: \n\t";
	private String TEMPL_CONN = "˜˜˜˜˜˜ '%d' ˜˜˜˜˜˜˜˜˜˜";
	public String [] st = { "1. ˜˜˜˜˜˜˜, ˜˜˜˜˜˜˜˜˜˜ ˜ ˜˜˜˜˜˜˜\t˜˜˜˜: 3.76 ˜˜˜.", "2. ˜˜˜˜˜˜˜ ˜˜˜˜˜˜.\t˜˜˜˜: 2.24 ˜˜˜.",
			"3. ˜˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜.\t˜˜˜˜: 0.87 ˜˜˜.", "4. ˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜.\t˜˜˜˜: 0.79 ˜˜˜.", 
			"5. ˜˜˜˜ ˜˜˜˜˜˜˜˜˜.\t˜˜˜˜: 1.01 ˜˜˜.", "6. ˜˜˜ ˜˜˜˜˜˜˜˜˜˜˜˜.\t˜˜˜˜: 0.95 ˜˜˜."};
	private  Socket socket;
	private  int    num;
	
	public Server() {}
	public void setSocket(int num, Socket socket)
	{
        // ˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜
        this.num    = num;
        this.socket = socket;

        // ˜˜˜˜˜˜˜˜˜ daemon-˜˜˜˜˜˜
        setDaemon(true);
        /*
         * ˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜
         * int java.lang.Thread.NORM_PRIORITY = 5 - the default 
         *               priority that is assigned to a thread.
         */
        setPriority(NORM_PRIORITY);
        // ˜˜˜˜˜ ˜˜˜˜˜˜
        start();
	}
	public void run()
	{
		try {
			// ˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜ ˜ ˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜ ˜˜˜˜˜˜
			// ˜˜˜ ˜˜˜˜˜˜ ˜˜˜˜˜˜˜ ˜ ˜˜˜˜˜˜˜˜ 
			InputStream  sin  = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			DataInputStream  dis = new DataInputStream (sin );
			DataOutputStream dos = new DataOutputStream(sout);
			
			String line = null;
			String line2 = null;
			while(true) {
				dos.writeUTF(st[0]+'\n'+st[1]+'\n'+st[2]+'\n'+st[3]+'\n'+st[4]+'\n' +st[5]);
				line = dis.readUTF();
				System.out.println(String.format(TEMPL_MSG, num) + line + "\n");
				System.out.println();
				if (line.equalsIgnoreCase("quit")) {
					// ˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜
					dos.writeUTF("˜˜ ˜˜˜˜˜");					
					dos.flush();
					socket.close();
					System.out.println(String.format(TEMPL_CONN, num));
					break;
				}
				else
				{
					StringTokenizer st = new StringTokenizer(line, " \n");
					int c =0; boolean flag=true;
					while(st.hasMoreTokens())
					{
						String w = st.nextToken();
						int []k=new int[10];
						k[c++] = Integer.parseInt(w);
						if(k[c-1]<1||k[c-1]>6){
							dos.writeUTF("˜˜˜˜˜ ˜˜˜˜˜˜˜ " +k[c-1]+ " ˜˜˜˜˜˜˜˜˜˜˜");
							flag = false;
						}
					}
					
					line2 = dis.readUTF();
					System.out.println(String.format(TEMPL_MSG2, num) + line2 + "\n");
					System.out.println();
					if(flag){	
						dos.writeUTF("˜˜˜ ˜˜˜˜˜ ˜˜˜˜˜˜.");
					}
					else
						dos.writeUTF("˜˜˜˜˜ ˜˜ ˜˜˜˜˜˜.");
					dos.flush();
				}
			}
		} catch(Exception e) {
			System.out.println("Exception : " + e);
	    }
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static void main(String[] ar)
	{
		ServerSocket srvSocket = null;
		try {
			try {
				int i = 1; // ˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜˜
				// ˜˜˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜ ˜ localhost
				InetAddress ia = InetAddress.getByName("localhost");
				srvSocket = new ServerSocket(port, 0, ia);
	
				System.out.println("˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜\n\n");
	
				while(true) {
					// ˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜˜˜
					Socket socket = srvSocket.accept();
					System.err.println("˜˜˜˜˜˜ ˜˜˜˜˜˜");
					// ˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜˜ ˜ ˜˜˜˜˜˜˜˜˜ ˜˜˜˜˜˜
					new Server().setSocket(i++, socket);
				}
			} catch(Exception e) {
				System.out.println("Exception : " + e);
			}
		} finally {
			try {
				if (srvSocket != null)
					srvSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
