package fr.ul.miage.bipwac.gl.metro.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ul.miage.bipwac.gl.metro.graphe.*;

public class Main {

    public static void main(String[] args) {

        // Initialize
        MetroRequest metro = new MetroRequest("graph.json");

        // Obtiens le graphe du métro
        MetroParisien metroParisien = metro.getMetroParisien();


        // Utilise Dijkstra pour trouver le chemin optimal
        Long sourceNodeId = Long.parseLong(String.valueOf(264495553)); // ID du nœud source
        Long destinationNodeId = Long.parseLong(String.valueOf(244494486)); // ID du nœud destination
        List<Long> shortestPath = (new Dijkstra()).findShortestPath(sourceNodeId, destinationNodeId, metroParisien);

        // Affiche le chemin optimal
        System.out.println("Chemin optimal :");
        for (Long nodeId : shortestPath) {
            System.out.println(nodeId);
        }
    }

}
