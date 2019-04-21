package lab10;

import java.net.*;
import java.io.*;

public class Client
{
	private  static final int    serverPort = 6666;
	private  static final String localhost  = "127.0.0.1";
	public static boolean prov1(String str)
	{
		int k = 0;
		for(int i = 0; i < str.length(); ++i)
		{ 	
			if(str.charAt(i)==' ') 
				k++;
			if(str.charAt(i)>='0'&&str.charAt(i)<='9')
				k++;
		}
		if(k==str.length())
			return true;
		return false;
		
	}
	public static void main(String[] ar)
	{
		Socket socket = null;
        try{
	        try {
	        	System.out.println("Welcome to Client side\n" +
	       		                   "Connecting to the server\n\t" +
	       				           "(IP address " + localhost + 
	       				           ", port " + serverPort + ")");
	       		InetAddress ipAddress = InetAddress.getByName(localhost);
				socket = new Socket(ipAddress, serverPort);
	       		System.out.println("The connection is established.");

	       		System.out.println("\tLocalPort = " + socket.getLocalPort() + 
	       				"\n\tInetAddress.HostAddress = " + socket.getInetAddress().getHostAddress() +
	       				"\n\tReceiveBufferSize (SO_RCVBUF) = " + socket.getReceiveBufferSize());
	
	       		// �������� ������� � �������� ������ ������ ��� ������ ����������� � �������� 
	       		InputStream  sin  = socket.getInputStream();
	       		OutputStream sout = socket.getOutputStream();
	
	       		DataInputStream  in  = new DataInputStream (sin );
	       		DataOutputStream out = new DataOutputStream(sout);
	
	       		// ������� ����� ��� ������ � ����������.
	       		InputStreamReader isr = new InputStreamReader(System.in);
	       		BufferedReader keyboard = new BufferedReader(isr);
	       		String line = null;
	       		String line2  = null;
	       		
	       		while (true) {
	       			line=in.readUTF();
	       			System.out.println(line);
	       		                  // ������������ ������ ������ ������ � ������ Enter
	       			System.out.println("������� ����� �������������� ����� (��� quit ��� ������) � ������� ����:");
		       		System.out.println();
	       			line = keyboard.readLine();
	       			if(!line.endsWith("quit"))
	       			while (!prov1(line))
	       			{
	       				System.out.println("�� ��������! ������� ��������!");
	       				line = keyboard.readLine();
	       			}
	       			out.writeUTF(line);         // �������� ��������� ������ �������
	       			if (line.endsWith("quit")){
	       				out.flush();                // ��������� �����
	       				line = in.readUTF(); 
	       				System.out.println(line);
	       				break;
	       			}
	       			else {
	       				System.out.println("����� �������� :\n\t");
	       				System.out.println();
	       				line2 = keyboard.readLine();
	       				System.out.println(line2);
	       				out.writeUTF(line2);   
	       				out.flush();                // ��������� �����
		       			line = in.readUTF();        // ����
		       			System.out.println(line);
	       			}
	       		}
	       		
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}