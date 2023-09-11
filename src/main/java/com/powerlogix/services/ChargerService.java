package com.powerlogix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerlogix.models.Charger;
import com.powerlogix.repo.ChargerRepository;

@Service
public class ChargerService 
{
	
	@Autowired
	public ChargerRepository chargerRepository;

	public List<Charger> getAllUser() {
		List<Charger> allUser = this.chargerRepository.findAll();

		return allUser;
	}

	  public Charger getChargerByIdTag(String idtag) 
	  {
	        return chargerRepository.getChargerByIdTag(idtag);
	    }
}
