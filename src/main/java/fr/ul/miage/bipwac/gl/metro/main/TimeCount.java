package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Node;

import java.util.*;

public class TimeCount {

    // Variables
    public static final Double averageTravelTime = 1.5; // Temps moyen entre stations
    public static final Double averageTransitionEntryTime = 5.0; // Temps moyen de changements et de première entrée

    // Fonctions

    /**
     * Fonction retournant le temps relatif aux
     * @param metro
     * @return
     */
    public Double getTimeInMinutes(MetroParisien metro) {
        // Variables
        double time = 0.0;

        // Vérification de la cohérence du chemin en entrée
        if(!verifyCoherence(metro)){
            return null;
        }

        // Pour chaque station
        for(Edge ignored : metro.getEdges())  {
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
        time += numberTransitions * averageTransitionEntryTime;

        return time;
    }

    private boolean verifyCoherence(MetroParisien metro) {
        List<Node> nodes = metro.getNodes();
        for(Node node : nodes) {
            for(Node node2 : nodes) {
                if(!String.valueOf(node.getId()).equals(String.valueOf(node2.getId()))) {
                    System.out.println(node.getId()  + " vers " + node2.getId() + " : " + isReachable(metro, node.getId(), node2.getId()));
                    if(!isReachable(metro, node.getId(), node2.getId())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Algorithme de recherche en largeur d'abord simplifié étant donné la structure des données (une ligne droite du départ à l'arrivée)
    public boolean isReachable(MetroParisien metro, Long startNodeId, Long targetNodeId) {
        // Vérification des nœuds de départ et d'arrivée
        Node startNode = getNodeById(metro, startNodeId, startNodeId).get(0);
        Node targetNode = getNodeById(metro, targetNodeId, targetNodeId).get(0);
        if (startNode == null || targetNode == null) {
            return false; // L'un des nœuds n'existe pas dans le graphe
        }

        // Utilisation d'une pile pour la recherche en profondeur
        Deque<Node> stack = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();

        // Ajouter le nœud de départ à la pile
        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode.getId() == targetNodeId) {
                return true; // Le nœud cible a été trouvé
            }

            if (!visited.contains(currentNode.getId())) {
                visited.add(currentNode.getId());

                // Explorer les nœuds voisins non visités (pas besoin de se souvenir des edges visités puisque les points ne sont reliés que par un edge)
                List<Edge> edges = getAdjacentEdges(metro, currentNode.getId());
                for (Edge edge : edges) {
                    List<Node> neighbors = getNodeById(metro, edge.getTarget(), edge.getSource());
                    for(Node neighbor : neighbors) {
                        if (!visited.contains(neighbor.getId())) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }

        return false; // Le nœud cible n'a pas été trouvé
    }

    private List<Node> getNodeById(MetroParisien metro, long nodeTargetId, long nodeSourceId) {
        List<Node> nodes = new ArrayList<>();
        for (Node node : metro.getNodes()) {
            if (node.getId() == nodeTargetId || node.getId() == nodeSourceId) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    private List<Edge> getAdjacentEdges(MetroParisien metro, long nodeId) {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : metro.getEdges()) {
            if (edge.getSource() == nodeId || edge.getTarget() == nodeId) {
                edges.add(edge);
            }
        }
        return edges;
    }
}
