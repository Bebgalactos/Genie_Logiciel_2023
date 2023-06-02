package fr.ul.miage.bipwac.gl.metro.graphe;

import java.util.*;
import java.util.stream.Collectors;

public class MetroAccident {

    /**
     * Fonction permettant de retirer les nœuds accidentés d'un graphe (retire également les edges qui sont reliés aux nœuds supprimés)
     * @param entry Graphe dont on veut retirer les nœuds accidentés
     * @return Un nouveau graphe sans les nœuds accidentés et sans les edges qui y étaient reliés
     */
    public MetroParisien removeNodeAccident(MetroParisien entry){
        MetroParisien mp = new MetroParisien();
        List<Node> noAccidents = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for(Node n : entry.getNodes()) {
            if (!n.isAccident()) {
                noAccidents.add(n);
            } else {
                ids.add(n.getId());
            }
        }

        for(Edge e : entry.getEdges()) {
            if (ids.contains(e.getSource()) || ids.contains(e.getTarget())) {
                e.setAccident(true);
            }
        }

        mp.setNodes(noAccidents);
        mp.setEdges(entry.getEdges());
        mp = (new MetroAccident()).removeEdgeAccident(mp);
        return mp;
    }

    /**
     * Fonction permettant de retirer les edges accidentés d'un graphe
     * @param entry Graphe dont on veut retirer les edges accidentés
     * @return Un nouveau graphe sans les edges accidentés
     */
    public MetroParisien removeEdgeAccident(MetroParisien entry){
        MetroParisien mp = new MetroParisien();
        mp.setNodes(entry.getNodes());
        mp.setEdges(entry.getEdges().stream().filter(e -> !e.isAccident()).collect(Collectors.toList()));
        return mp;
    }
}
