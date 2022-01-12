package it.univpm.ProvaSantarelliRecinelli.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class CityTest {
	
	City city = new City("Ancona", "IT");
	Weather weat;
	
	
	
	void setUp() {
		
	}

	@Test
	void getNameTest() {
		City city = new City("Ancona", "IT");
		String name = city.getName();
		assertEquals("Ancona", name);
		
	}

}
