package com.planttech.controller.rest;

import java.util.List;

import com.planttech.domain.Dht11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planttech.service.DHT11Service;

@RestController
@RequestMapping("/DHT11")
public class DHT11Controller {
	
	@Autowired
	private DHT11Service dht11Service;

	@GetMapping
	public List<Dht11> getDht11List() {
		System.out.println("::: GET -  getDht11List :::");
		System.out.println("::: GET -  getDht11List :::");
		System.out.println("::: GET -  getDht11List :::");
		
		return dht11Service.getDHT11List();
	}
	
	@PostMapping
	public int addDht11(@RequestBody Dht11 dht11) {
		
		System.out.println("::: POST -  addDht11 :::");
		System.out.println("::: POST -  addDht11 :::");
		System.out.println("::: POST -  addDht11 :::");
		
		System.out.println(dht11.toString());
		return dht11Service.addDHT11(dht11);
	}
	
}
