package fr.ul.miage.bipwac.gl.metro.main;

import java.util.logging.Logger;

import fr.ul.miage.bipwac.gl.metro.graphe.MetroRequest;

public class Main {

	private static final java.util.logging.Logger LOG = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		//Set up des variables
		String jsonFileName = "graph.json";
		MetroRequest metro = new MetroRequest(jsonFileName);
		System.out.println(metro.getMetroParisien().getNodes());
	}
	
}
