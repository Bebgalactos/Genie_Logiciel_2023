package metro.main;

import metro.graphe.MetroRequest;

import java.util.logging.Logger;

public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		//Set up des variables
		String jsonFileName = "graph.json";
		MetroRequest metro = new MetroRequest(jsonFileName);
		System.out.println(metro);
	}
	
}
