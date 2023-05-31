package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;

import java.util.ArrayList;
import java.util.List;

public class TimeCount {

    public Double getTimeInMinutes(MetroParisien metro) {
        // Variables
        Double time = 0.0;
        Double averageTravelTime = 1.5; // Temps moyen entre stations
        Double averageTransitionTime = 5.0; // Temps moyen de changements et de première entrée

        // Pour chaque station
        for(Edge edge : metro.getEdges())  {
            time += averageTravelTime;
        }

        // Calcul du nombre de changements de ligne
        List<String> listDifferentLines = new ArrayList<String>();
        for(Edge edge : metro.getEdges()) {
            if(!listDifferentLines.contains(edge.getLine())) {
                listDifferentLines.add(edge.getLine());
            }
        }
        int numberTransitions = listDifferentLines.size();

        // Ajout des temps de transitions de ligne
        time += numberTransitions * averageTransitionTime;

        return time;
    }
}
