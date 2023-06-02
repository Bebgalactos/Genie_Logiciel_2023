package fr.ul.miage.bipwac.gl.metro.graphe;
import java.util.*;

public class Dijkstra {

    /**
     * Fonction qui calcule le chemin optimal entre les deux points dans un graphe
     * @param source le point du graphe de départ
     * @param destination le point du graphe à atteindre
     * @param metro le graphe en entrée
     * @return la liste des id des Nodes du graphe à parcourir en sortie (on ne précise pas les Edges car pas nécessaire)
     */
    public List<Long> findShortestPath(Long source, Long destination, MetroParisien metro) {

        // Construis le graphe pour Dijkstra
        Map<Long, List<Edge>> graph = this.changeStructure(metro);

        // Initialisations des variables de parcours
        Map<Long, Double> distances = distanceInitialize(graph.keySet());
        Map<Long, Long> previous = previousInitialize(graph.keySet());
        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        distances.put(source, Double.parseDouble(String.valueOf(0)));
        queue.offer(source);

        while (!queue.isEmpty()) {
            Long current = queue.poll();

            if (distances.get(current) == Long.MAX_VALUE) {
                // Current node is not reachable, skip it
                continue;
            }

            List<Edge> neighbors = graph.get(current);
            if (neighbors == null) neighbors = new ArrayList<>();

            for (Edge neighbor : neighbors) {
                Long nextNode;
                if(Objects.equals(neighbor.getSource(), current)) {
                    nextNode = neighbor.getTarget();
                } else {
                    nextNode = neighbor.getSource();
                }

                Double distanceToNextNode = distances.get(current) + 1.5;

                if (distanceToNextNode < distances.get(nextNode)) {
                    distances.put(nextNode, distanceToNextNode);
                    previous.put(nextNode, current);
                    queue.offer(nextNode);
                }
            }
        }

        // Reconstruct the shortest path
        List<Long> shortestPath = new ArrayList<>();
        Long current = destination;

        while (current != null) {
            shortestPath.add(current);
            current = previous.get(current);
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

    /**
     * Fonction qui transforme la structure du graphe
     * @param metro graphe en entrée
     * @return graphe transformé pour le bon fonctionnement de l'algorithme
     */
    public Map<Long, List<Edge>> changeStructure(MetroParisien metro){
        Map<Long, List<Edge>> graph = new HashMap<>();
        for (Node node : metro.getNodes()) {
            List<Edge> edgesConcerned = new ArrayList<>();
            for(Edge edge : metro.getEdges()) {
                if(edge.getSource().equals(node.getId()) || edge.getTarget().equals(node.getId())) {
                    edgesConcerned.add(edge);
                }
            }
            graph.put(node.getId(), edgesConcerned);
        }
        return graph;
    }

    /**
     * Fonction initialisant les distances au point de départ à MAX_VALUE
     * @param graphKeySet clés du graphe transformé en entrée
     * @return la liste des distances au point départ
     */
    public Map<Long, Double> distanceInitialize(Set<Long> graphKeySet) {
        Map<Long, Double> distances = new HashMap<>();

        // Initialize distances and previous nodes
        for (Long node : graphKeySet) {
            distances.put(node, Double.MAX_VALUE);
        }
        return distances;
    }

    /**
     * Fonction initialisant les points précédents du chemin optimal à partir des points
     * @param graphKeySet clés du graphe transformé en entrée
     * @return la liste des id des points précédents pour chaque point
     */
    public Map<Long, Long> previousInitialize(Set<Long> graphKeySet) {
        Map<Long, Long> previous = new HashMap<>();

        // Initialize distances and previous nodes
        for (Long node : graphKeySet) {
            previous.put(node, null);
        }
        return previous;
    }
}
