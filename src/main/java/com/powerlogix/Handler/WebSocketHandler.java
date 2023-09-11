package com.powerlogix.Handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import com.powerlogix.models.Charger;
import com.powerlogix.repo.ChargerRepository;
import com.powerlogix.repo.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends BinaryWebSocketHandler 
{
	@Autowired
    private  UserRepository userRepository;
	
	@Autowired
	private ChargerRepository chargerRepository;
	 
	
	private static List<WebSocketSession> sessions = new ArrayList<>();
	private List<String> receivedMessages = new ArrayList<>();

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS'Z'");

	private ObjectMapper objectMapper = new ObjectMapper();
	public String remoteStartReqUniqueId;
	public String RemoteStopTransaction;
	public String getConfiguration;
	public String triggerMessage;
	public String UpdateFirmware;
	public String changeConfiguration;
	public String reset;
	public String changeAvailability;
	public String unlockConnector;
	public String getDiagnostics;
	public String sendLocalList;
	public String getLocalListVersion;
	private WebSocketSession session;

	  @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception 
	    {
	        System.out.println("WebSocket Connection Established!");	    
	        sessions.add(session);
	        this.session=session;	
//	        sendConnectionMessage(session);
	        
	     }
	    
//	    private void sendConnectionMessage(WebSocketSession session) throws IOException 
//	    {
//	        String message = "WebSocket Connection Established!";
//			LocalDateTime now = LocalDateTime.now();
//			String timeAndDate = now.format(formatter);
//			String messageToSend = String.format("{\"Warning\":\"%s\",\"DateAndTime\":\"%s\"}",message,timeAndDate);
//			sendMessageToSession(session, messageToSend);
//	    }
	      
//	    private void sendMessageToSession(WebSocketSession session, String message) 
//	    {
//	        if (session.isOpen()) {
//	            try {
//	                session.sendMessage(new TextMessage(message));
//	            } catch (IOException e) {
//	                System.err.println("Error while sending message: " + e.getMessage());
//	            }
//	        }
	        
//	        for (WebSocketSession s : sessions) 
//			{
//		        if (!s.equals(session)) 
//		        {
//		            try {
//		                s.sendMessage(new TextMessage(message));
//		            } catch (IOException e) {
//		                e.printStackTrace();
//		            }
//		        }
//		    }
//	    }

	
	public void handleTextMessage(WebSocketSession session, TextMessage msg) 
	{
	    try 
	    {
	        String receivedMessage = msg.getPayload();
	        System.out.println("Received message: " + receivedMessage);
	        	        
	        if (isValidJson(receivedMessage)) 
	        {
	            processValidJsonMessage(session, receivedMessage);
	        } 
	        else {
	            handleInvalidJsonFormat(session);
	        }
	    } catch (JSONException e) {
	        System.out.println("JSON Invalid Format : " + e.getMessage());
	    }
	}

	private void processValidJsonMessage(WebSocketSession session, String receivedMessage) 
	{
	    LocalDateTime now = LocalDateTime.now();
	    String timeAndDate = now.format(formatter);

	    JSONArray jsonArray = new JSONArray(receivedMessage);
	    int messageId = jsonArray.getInt(0);
	    String uniqueId = jsonArray.getString(1);

	    System.out.println("messageId : " + messageId);
	    System.out.println("uniqueId : " + uniqueId);
	    
	    if (messageId == 2) 
	    {
		    String action = jsonArray.getString(2);
		    System.out.println("action : " + action);
	    
	        try 
	        {
	          	if(!action.equals("RemoteStartTransaction") && !action.equals("RemoteStopTransaction")
	          			&& !action.equals("GetConfiguration") && !action.equals("ChangeConfiguration") 
	          			&& !action.equals("TriggerMessage") && !action.equals("UpdateFirmware") 
	          			&& !action.equals("Reset") && !action.equals("ChangeAvailability") && !action.equals("UnlockConnector")
	          			&& !action.equals("GetDiagnostics") && !action.equals("GetLocalListVersion") && !action.equals("SendLocalList")) 
	        	{
		    	    
	        		for (WebSocketSession s : sessions) {
		    	        if (!s.equals(session)) 
		    	        {
		    	            try {
		    	                s.sendMessage(new TextMessage(receivedMessage));
		    	            } catch (IOException e) {
		    	                e.printStackTrace();
		    	            }
		    	        }
		    	    }
		    	}
	        	
	            handleAction2(jsonArray, uniqueId, timeAndDate, receivedMessage);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else if (messageId == 3) {
	        handleAction3(jsonArray, uniqueId,receivedMessage);
	    }
	}

	private void handleInvalidJsonFormat(WebSocketSession session) 
	{
	    System.out.println("JSON Invalid Format!!!");
	    for (WebSocketSession s : sessions) 
	    {
	        if (!s.equals(session)) 
	        {
	            try {
	                s.sendMessage(new TextMessage("JSON Invalid Format!!!"));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	private boolean isValidJson(String jsonStr) {
	    try {
	        Object obj = new JSONTokener(jsonStr).nextValue();
	        return obj instanceof JSONArray;
	    } catch (JSONException e) {
	        return false;
	    }
	}
	
	
	private void handleAction2(JSONArray jsonArray, String uniqueId, String timeAndDate,String receivedMessage) throws IOException {
		String action = jsonArray.getString(2);
		System.out.println("action :" + action);
		String temp = null;
		
		if (action.equals("BootNotification")) 
		{
			// ... handle BootNotification logic ...
			String chargePointSerialNumber = jsonArray.getJSONObject(3).getString("chargePointSerialNumber");
			String chargePointVendor = jsonArray.getJSONObject(3).getString("chargePointVendor");
			String chargePointModel = jsonArray.getJSONObject(3).getString("chargePointModel");
			String firmwareVersion = jsonArray.getJSONObject(3).getString("firmwareVersion");

			System.out.println("chargePointSerialNumber : " + chargePointSerialNumber);
			System.out.println("chargePointVendor : " + chargePointVendor);
			System.out.println("chargePointModel : " + chargePointModel);
			System.out.println("firmwareVersion : " + firmwareVersion);

			// Perform the necessary logic to determine the boot notification status
			temp = String.format("[3,\"%s\",{\"status\" : \"Accepted\", \"currentTime\":\"%s\",\"interval\":30}]",
					uniqueId, timeAndDate);

		} 
		else if (action.equals("Authorize")) 
		{
			String idTag = jsonArray.getJSONObject(3).getString("idTag"); 
			temp = handleAuthorizeAction(uniqueId, timeAndDate,idTag);

		}
		else if (action.equals("DataTransfer")) 
		{
			temp = String.format("[3,\"%s\",{\"status\" : \"Accepted\", \"data\":\"string\"}]", uniqueId);
		} 
		else if (action.equals("DiagnosticsStatusNotification")) 
		{
			temp = String.format("[3,\"%s\",{}]", uniqueId);
		} 
		else if (action.equals("FirmwareStatusNotification")) 
		{
			temp = String.format("[3,\"%s\",{}]", uniqueId);
		} 
		else if (action.equals("Heartbeat")) 
		{
         	temp = String.format("[3,\"%s\",{\"currentTime\":\"%s\"}]", uniqueId, timeAndDate);

		}
		else if (action.equals("MeterValues")) 
		{
			temp = String.format("[3,\"%s\",{}]", uniqueId);
		} 
		else if (action.equals("StartTransaction")) 
		{
			temp = String.format("[3,\"%s\",{\"idTagInfo\" : {\"status\" : \"Accepted\"},\"transactionId\":12345}]",
					uniqueId);
		} 
		else if (action.equals("StatusNotification")) 
		{
			temp = String.format("[3,\"%s\",{}]", uniqueId);
		} 
		else if (action.equals("StopTransaction")) 
		{
			temp = String.format(
					"[3,\"%s\",{\"idTagInfo\" : {\"expiryDate\" : \"%s\", \"parentIdTag\":\"20\",\"status\" : \"Accepted\"}}]",
					uniqueId, timeAndDate);
		} 
		else if (action.equals("RemoteStartTransaction")) 
		{
			temp = receivedMessage;
			remoteStartReqUniqueId = uniqueId;
			System.out.println("remoteStartReqUniqueId inside RemoteStartTransaction:" + remoteStartReqUniqueId);
		} 
		else if (action.equals("RemoteStopTransaction")) 
		{
			temp = receivedMessage;
			RemoteStopTransaction = uniqueId;
			System.out.println("remoteStartReqUniqueId inside RemoteStopTransaction:" + RemoteStopTransaction);
		} 
		else if (action.equals("GetConfiguration")) 
		{
			temp = receivedMessage;
			getConfiguration = uniqueId;
			System.out.println("getConfiguration inside getConfiguration:" + getConfiguration);
		}
		else if(action.equals("TriggerMessage"))
		{
			temp = receivedMessage;	
			triggerMessage=uniqueId;
			System.out.println("TriggerMessage :"+triggerMessage);
		}	
		else if(action.equals("UpdateFirmware"))
		{
			temp = receivedMessage;	
			UpdateFirmware=uniqueId;
			System.out.println("UpdateFirmware :"+UpdateFirmware);
		}
		else if(action.equals("ChangeConfiguration"))
		{
			temp = receivedMessage;	
			changeConfiguration=uniqueId;
			System.out.println("ChangeConfiguration :"+changeConfiguration);
		}		
		else if(action.equals("Reset"))
		{
			temp = receivedMessage;	
			reset=uniqueId;
			System.out.println("reset :" +reset);
		}
		else if(action.equals("ChangeAvailability"))
		{
			temp = receivedMessage;	
			changeAvailability=uniqueId;
			System.out.println("changeAvailability :" +changeAvailability);
		}	
       
		else if(action.equals("UnlockConnector"))
		{
			temp = receivedMessage;	
			unlockConnector=uniqueId;
			System.out.println("unlockConnector :" +unlockConnector);
		}
		else if(action.equals("GetDiagnostics"))
		{
			temp = receivedMessage;	
			getDiagnostics=uniqueId;
			System.out.println("getDiagnostics :" +getDiagnostics);
		}
		else if(action.equals("SendLocalList"))
		{
			temp = receivedMessage;	
			sendLocalList=uniqueId;
			System.out.println("sendLocalList :" +sendLocalList);
		}
		else if(action.equals("GetLocalListVersion"))
		{
			temp = receivedMessage;	
			getLocalListVersion=uniqueId;
			System.out.println("getLocalListVersion :" +getLocalListVersion);
		}

		// ... handle other actions ...

		sendResponseToAllSessions(temp);
	}

	private String handleAuthorizeAction(String uniqueId, String timeAndDate, String idTag) 
	{
	    Charger charger = chargerRepository.getChargerByIdTag(idTag);
	    String temp;

	    if (charger != null) {
	        if (charger.getStatus().equals("Accepted")) {
	            temp = String.format("[3,\"%s\",{\"idTagInfo\" : {\"expiryDate\":\"%s\",\"status\" : \"Accepted\"}}]",
	                    uniqueId, timeAndDate);
	        } else if (charger.getStatus().equals("Rejected")) {
	            temp = String.format("[3,\"%s\",{\"idTagInfo\" : {\"expiryDate\":\"%s\",\"status\" : \"Rejected\"}}]",
	                    uniqueId, timeAndDate);
	        } else {
	            temp = String.format("[3,\"%s\",{\"idTagInfo\" : {\"expiryDate\":\"%s\",\"status\" : \"%s\"}}]",
	                    uniqueId, timeAndDate, charger.getStatus());
	        }
	    } else {
	        temp = String.format(
	                "[3,\"%s\",{\"idTagInfo\" : {\"expiryDate\":\"%s\",\"status\" : \"Rejected\"}}]",
	                uniqueId, timeAndDate);
	    }

	    return temp;
	}

	private void handleAction3(JSONArray jsonArray, String uniqueId,String receivedMessage) 
	{
		System.out.println("Verifying Unique id...!");

		if (uniqueId.equals(remoteStartReqUniqueId)) 
		{
			System.out.println("RemoteStartTrans UniqueId Matched!");
			String status = jsonArray.getJSONObject(2).getString("status");
			System.out.println("status : " + status);
		}
		else if(uniqueId.equals(getConfiguration))
	      {
	    	  System.out.println("getConfiguration UniqueId Matched!");
	      }
		else if(uniqueId.equals(changeConfiguration))
	      {
	    	  System.out.println("changeConfiguration UniqueId Matched!");
	      }
		else if(uniqueId.equals(triggerMessage))
	      {
	    	  System.out.println("TriggerMessage UniqueId Matched!");
	      }
		else if(uniqueId.equals(UpdateFirmware))
	      {
			  System.out.println("UpdateFirmware UniqueId Matched!");
	      }
		else if(uniqueId.equals("reset"))
	      {
			  System.out.println("reset UniqueId Matched!");
	      }
		else if(uniqueId.equals("changeAvailability"))
	      {
			  System.out.println("changeAvailability UniqueId Matched!");
	      }
		else if(uniqueId.equals("unlockConnector"))
	      {
			  System.out.println("unlockConnector UniqueId Matched!");
	      }
		else if(uniqueId.equals("getDiagnostics"))
	      {
			  System.out.println("getDiagnostics UniqueId Matched!");
	      }
		else if(uniqueId.equals("sendLocalList"))
	      {
			  System.out.println("sendLocalList UniqueId Matched!");
	      }
		else if(uniqueId.equals("getLocalListVersion"))
	      {
			  System.out.println("getLocalListVersion UniqueId Matched!");
	      }
		else
		{
			System.out.println("UniqueId Mis Matched!");
		}
		
		for (WebSocketSession s : sessions) 
		{
	        if (!s.equals(session)) 
	        {
	            try {
	                s.sendMessage(new TextMessage(receivedMessage));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	private void sendResponseToAllSessions(String response) 
	{
		for (WebSocketSession webSocketSession : sessions) 
		{
			try {
				webSocketSession.sendMessage(new TextMessage(response));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	  public void disconnectSession(WebSocketSession session) 
//	    {
//	        try 
//	        {
//	            session.close(CloseStatus.NO_CLOSE_FRAME);
//	            sessions.remove(session);
//	            System.out.println("Disconnected WebSocket session");
//	        }
//	        catch (IOException e) {
//	            handleException("Error disconnecting session", e);
//	        }
//	    }
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception 
		{
		    System.out.println("WebSocket connection closed");
		    sessions.remove(session);
//	        session.close(CloseStatus.NORMAL);
		
		
//		    String message = "WebSocket Connection Closed !";
//		    LocalDateTime now = LocalDateTime.now();
//		    String timeAndDate = now.format(formatter);
//		    String messageToSend = String.format("{\"Warning\":\"%s\",\"DateAndTime\":\"%s\"}",message,timeAndDate);
//
//		    List<WebSocketSession> sessionsToSend = new ArrayList<>();
//
//		    for (WebSocketSession s : sessions) 
//		    {
//		        if (!s.equals(session)) {
//		                try {
//		                    s.sendMessage(new TextMessage(messageToSend));
//		                    sessionsToSend.add(s);
//		                } catch (IOException e) {
//		                    handleException("Error sending message", e);
//		                }
//		        }
//		    }
//
//		    sessions.removeAll(sessionsToSend);
//		}
//
//		private void handleException(String message, Exception e) {
//		    System.err.println(message + ": " + e.getMessage());
//		    e.printStackTrace();
//		}
	}	
}
