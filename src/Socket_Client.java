import java.io.IOException;
import java.net.MalformedURLException;

import com.posco.swp.es.ecmsocket.client.ECMSocketClient;

public class Socket_Client 
{
	private static String target_ip = "127.0.0.1";
	private static int connection_timeout = 5000000, port = 9000;
	
	//public static void main(String[] args) throws MalformedURLException, ServiceException, IOException
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
	 {
		// 소켓 연결 생성
		ECMSocketClient ECMClient = new ECMSocketClient(target_ip, port, connection_timeout);
		try
		{

			String Message = "company_code;30:law;0x00";
			ECMClient.sendMsgDocID(Message, true);

			while (!ECMClient.receiveQ.isEmpty()) 
			{
				String message = ECMClient.receiveQ.poll().toString();
				System.out.println("Server ACK >> " +  message);
				
				// Success 여부
				if(message.contains("S;")) {
					System.out.println("SUCCESS");
				}
						
			}
		}
		catch(Exception ex)
		{
			System.err.println("[Main Statics] : " + ex.getMessage());
		}
		finally
		{
			ECMClient.releaseSocket();
		}
	}
}




