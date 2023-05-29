package fr.ul.miage.bipwac.gl.metro.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Dijkstra;
import fr.ul.miage.bipwac.gl.metro.graphe.Edge;

public class Main {

    public static void main(String[] args) {
        

        // Obtiens le graphe du métro
        MetroParisien metroParisien = metro.getMetroParisien();
        List<Edge> edges = metroParisien.getEdges();

        // Construis le graphe pour Dijkstra
        Map<Long, List<Edge>> graph = new HashMap<>();
        for (Edge edge : edges) {
            Long source = edge.getSource();
            if (!graph.containsKey(source)) {
                graph.put(source, new List<>());
            }
            graph.get(source).add(edge);
        }

        // Utilise Dijkstra pour trouver le chemin optimal
        Dijkstra dijkstra = new Dijkstra(graph);
        Long sourceNodeId = 1L; // ID du nœud source
        Long destinationNodeId = 5L; // ID du nœud destination
        List<Long> shortestPath = dijkstra.findShortestPath(sourceNodeId, destinationNodeId);

        // Affiche le chemin optimal
        System.out.println("Chemin optimal :");
        for (Long nodeId : shortestPath) {
            System.out.println(nodeId);
        }
    }

}
