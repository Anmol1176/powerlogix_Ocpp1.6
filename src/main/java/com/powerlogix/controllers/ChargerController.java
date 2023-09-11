package com.powerlogix.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.powerlogix.models.ReturnMessage;
import com.powerlogix.models.Charger;
import com.powerlogix.repo.ChargerRepository;

@RestController
public class ChargerController 
{
	
	private final ChargerRepository chargerRepository;
	private Set<String> idtags = new HashSet<>();

	@Autowired
	public ChargerController(ChargerRepository chargerRepository) {
		this.chargerRepository = chargerRepository;
	}

	   @MessageMapping("/message")
	    @SendTo("/topic/return-to")
	    public ReturnMessage  saveUserData(@RequestBody Charger websocket) 
	    {
	    	ReturnMessage returnMessage = new ReturnMessage();
	        String idTag = websocket.getIdTag();

	        if (chargerRepository.existsByIdTag(idTag)) 
	        {
	            // ID Tag already exists in the database, add a custom error message
	            returnMessage.setError("ID Tag already exists !");
	            return returnMessage;
	        }else {
		        chargerRepository.save(websocket); // Save the user if the ID Tag does not exist
		        // Return a success message
		        returnMessage.setMessage("User data saved successfully.");
	
	        }
	        return returnMessage;
	    }

	@MessageMapping("/getData")
	@SendTo("/topic/userData")
	public List<Charger> getData() {
		return chargerRepository.findAll();
	}

	@MessageMapping("/deleteUser")
	@SendTo("/topic/userData")
	public List<Charger> deleteUser(Long userId) {
		// Delete the user data from the database
		chargerRepository.deleteById(userId);
		// Return the updated user data list
		return chargerRepository.findAll();
	}

	@MessageMapping("/getUserById/{userId}")
	@SendTo("/topic/userData")
	public void getUserById(@PathVariable("userId") Long userId) {
		// Find the user data by ID from the database
		Charger userData = chargerRepository.findById(userId).orElse(null);
//		messagingTemplate.convertAndSend("/topic/singleUserData", userData);

		System.out.println(userId);
//		return userRepository.findUserById(userId);
	}

	@MessageMapping("/update/{userId}")
	@SendTo("/topic/updatedUserData")
	public void updateUser(@DestinationVariable  Long userId, Charger websocket) {
		// Find the existing user data from the database
		Charger UserData = chargerRepository.findById(userId).orElseThrow();

		// Update the user data fields
		UserData.setUsername(websocket.getUsername());
		UserData.setIdTag(websocket.getIdTag());
		UserData.setExpiryDate(websocket.getExpiryDate());
		UserData.setParentIdTag(websocket.getParentIdTag());
		UserData.setStatus(websocket.getStatus());
		// Save the updated user data in the database
		Charger updated = chargerRepository.save(UserData);
		System.out.println(updated);
		
	}
		
}
