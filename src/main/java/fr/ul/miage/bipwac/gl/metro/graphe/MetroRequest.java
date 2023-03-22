package fr.ul.miage.bipwac.gl.metro.graphe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MetroRequest {

	private static final java.util.logging.Logger LOG = Logger.getLogger(MetroRequest.class.getName());

	private MetroParisien metro = null;
	private String fileName = null;

	// Constructor
	public MetroRequest(String fileName) {
		setFileName(fileName);
		setMetroParisien(getMetroByJSON(fileName));
	}

	// Functionnalities
	public MetroParisien getMetroByJSON(String fileName) {
		// Lecture de la ressource
		String result = null;
		URL filePath = MetroRequest.class.getResource("/" + fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(filePath.openConnection().getInputStream()));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result += line + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);

		// Set up de l'objet
		MetroParisien res = null;
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.create();
		res = gson.fromJson(result, MetroParisien.class);
		LOG.info("Récupération des points et arrêtes avec succès.");

		return res;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Node.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("fileName");
		sb.append('=');
		sb.append(((this.fileName == null) ? "null" : this.fileName));
		sb.append(',');
		sb.append("metro");
		sb.append('=');
		sb.append(((this.metro == null) ? "null" : this.metro.toString()));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

	// Getters
	public MetroParisien getMetroParisien() {
		return metro;
	}

	public String getFileName() {
		return fileName;
	}

	// Setters
	public void setMetroParisien(MetroParisien metro) {
		this.metro = metro;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
