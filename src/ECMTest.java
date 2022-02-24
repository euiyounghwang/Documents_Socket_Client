import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.google.gdata.util.ServiceException;
import com.posco.swp.es.ecmindexing.utiles.ECMDelegateDocuments;
import com.posco.swp.es.ecmindexing.utiles.Util;
import com.posco.swp.es.ecmsocket.client.ECMSocketClient;
import com.posco.swp.es.slack.ChannelNotifySlack;
import com.posco.swp.es.socketclient.ecmdelegate.util.EcmSocketDelegateIOUtil;

import SSH.ShellFTP.SSHShellClient;



public class ECMTest 
{
	private static String target_ip = "127.0.0.1", user_id="account", pass_wd="passwd";
    private static String src = "/TOM/ECM_Delegate/dec_files/";
	private static String des = "C:\\";
	private static int connection_timeout = 5000000, port = 8191;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ServiceException 
	 * @throws MalformedURLException 
	 */
	
	
	private static Logger logger = Logger.getLogger("ECMTest".getClass().getName());// 로그
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ServiceException 
	 * @throws MalformedURLException 
	 * @throws InterruptedException 
	 */
	
	//public static void main(String[] args) throws MalformedURLException, ServiceException, IOException
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException
	 {
		// TODO Auto-generated method stub

		// 소켓 연결 생성
		ECMSocketClient ECMClient = new ECMSocketClient(target_ip, port, connection_timeout);
//		ECMSocketClient ECMClient = new ECMSocketClient(target_ip, port, connection_timeout, user_id, pass_wd);
//		ECMSocketClient ECMClient = new ECMSocketClient(target_ip, port, connection_timeout, user_id, pass_wd, src, des);
		
		
		// ECM DocID인 경우 해당 서버로부터 파일 다운
		ECMDelegateDocuments getDocs = new ECMDelegateDocuments();
		
		String remotefilename = null;
		// 스토리지(구 KM DRM / 원문 체크)
		
		try
		{
			String[] Messages = {"DOC;0900bf4b9f4c7fc1"};
//			String[] Messages = {"LOCAL_DRM;ALL"};
					
//			ECMClient.sendMsgDocID("THUMBNAIL;"+getFiles);
			
			// 사용자 su devuser
//			/storage/thumb_files <-저장할것
//			##########################################################################
//			##########################################################################
//			##########################################################################
			
			
//			##########################################################################
//			##########################################################################
//			##########################################################################
			
//			ECMClient.sendMsgDocID(Message);
							
			
//			ECMClient.sendMsgDocID("DOC;" + "doc0900bf4b98f792cb");
//			ECMClient.sendMsgDocID("EXPT;" + V_PATH);
			
			/*
			while(ECMClient.isDeque(Message))
			{
				if(ECMClient.isDeque(Message)) {
					remotefilename = ECMClient.getResponsefileName();
					if(remotefilename!=null)
						break;
					
					logger.info("remotefilename : " + remotefilename);
					
					// ThumbNamil Action
					// 썸네일일 경우 서버에서 해당 파일을 자동으로 삭제함
					// 텍스트 추출 목적은 텍스트를 모두 추출한 이후 삭제명령어를 던져줘야함 DEL;S;tmp....xxx (LastIndexOf 로 삭제)
					
//					if(remotefilename != null) {
//						// 원격지 파일 삭제
//						ECMClient.sendMsgDocID("DEL;"+remotefilename);
//						
//						//로컬 파일 삭제
//						// /ES/Feed/Download/filename
////						EcmDelegateIOUtil.deleteFile(DownloadPath + downloadRemoteFileNm);
//					}
				}
				
				Thread.sleep(1000);
			}
			*/
			
			for(int i=0; i <= Messages.length; i++)
			{
				ECMClient.sendMsgDocID(Messages[i]);
				
				while(ECMClient.isDeque(Messages[i]))
				{
					if(ECMClient.isDeque(Messages[i])) {
						remotefilename = ECMClient.getResponsefileName();
						
						if(remotefilename!=null)
							break;
						
						logger.info("remotefilename : " + remotefilename);
					}
					
					Thread.sleep(1000);
				}
			}
		}
		catch(Exception ex)
		{
			System.err.println("[Main Statics] : " + ex.getMessage());
		}
		finally
		{
			// socket -> downlaod -> thumbnail -> filena (dleelte) -> 
			
//			ECMClient.sendMsgDocID("DEL;"+remotefilename.split(";")[1]);
			
//			ChannelNotifySlack slack = new ChannelNotifySlack();
//			String Web_hook_URL = "https://hooks.slack.com/services/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";
//			try {
//				slack.postNotify(Web_hook_URL, "test_java", "monitoring_bot");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			// Loop 끝나기직전 (1번 콜)
			ECMClient.sendMsgDocID("F");
			ECMClient.releaseSocket();
		}
	}
	
		
	
}



