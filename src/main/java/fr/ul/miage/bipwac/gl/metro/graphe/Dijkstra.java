package fr.ul.miage.bipwac.gl.metro.graphe;
import java.util.*;

public class Dijkstra {

    public List<Long> findShortestPath(Long source, Long destination, MetroParisien metro) {

        // Construis le graphe pour Dijkstra
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

        Map<Long, Long> distances = new HashMap<>();
        Map<Long, Long> previous = new HashMap<>();
        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.comparingLong(distances::get));

        // Initialize distances and previous nodes
        for (Long node : graph.keySet()) {
            distances.put(node, Long.MAX_VALUE);
            previous.put(node, null);
        }

        distances.put(source, Long.parseLong(String.valueOf(0)));
        queue.offer(source);

        while (!queue.isEmpty()) {
            Long current = queue.poll();

            if (current.equals(destination)) {
                break; // Reached the destination, stop the search
            }

            if (distances.get(current) == Long.MAX_VALUE) {
                // Current node is not reachable, skip it
                continue;
            }

            List<Edge> neighbors = graph.get(current);
            if (neighbors == null) neighbors = new ArrayList<Edge>();

            for (Edge neighbor : neighbors) {
                Long nextNode;
                if(neighbor.getSource() == current) {
                    nextNode = neighbor.getTarget();
                } else {
                    nextNode = neighbor.getSource();
                }

                System.out.println(current + " " + nextNode);
                Long distanceToNextNode = distances.get(current) + 1; // Assuming edge weight is 1

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
}
