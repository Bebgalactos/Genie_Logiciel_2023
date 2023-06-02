package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Dijkstra;
import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraTest {

    @Test
    public void testDijkstra() {
        // Variables
        String line1 = "ligne1";
        String line2 = "ligne2";

        // Premier branchement
        Node node1 = new Node();
        node1.setId(Long.parseLong("1"));
        Node node2 = new Node();
        node2.setId(Long.parseLong("2"));
        Edge edge1 = new Edge();
        edge1.setSource(node1.getId());
        edge1.setTarget(node2.getId());
        edge1.setLine(line1);

        Node node3 = new Node();
        node3.setId(Long.parseLong("3"));
        Edge edge2 = new Edge();
        edge2.setSource(node2.getId());
        edge2.setTarget(node3.getId());
        edge2.setLine(line1);

        Node node4 = new Node();
        node4.setId(Long.parseLong("4"));
        Edge edge3 = new Edge();
        edge3.setSource(node3.getId());
        edge3.setTarget(node4.getId());
        edge3.setLine(line2);

        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2, edge3));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);
        /*
        On obtient le trajet :
            1 -------ligne1------- 2 -------ligne1------- 3 -------ligne2------- 4
        Soit Un passage dans 4 stations et 2 lignes différentes
         */
        Long startPoint = node1.getId();
        Long endPoint = node4.getId();
        List<Long> expected = new ArrayList<Long>();
        List<Long> actual = (new Dijkstra()).findShortestPath(startPoint, endPoint, metro);
    }
}
