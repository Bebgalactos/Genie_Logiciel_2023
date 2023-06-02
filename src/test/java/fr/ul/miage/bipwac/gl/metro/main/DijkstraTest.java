package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Dijkstra;
import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Node;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {


    @Test
    public void testDijkstraChangeStructure() {
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
        Map<Long, List<Edge>> expected = new HashMap<>(){{
            put(Long.parseLong("1"), (new ArrayList<>(List.of(edge1))));
            put(Long.parseLong("2"), new ArrayList<>(Arrays.asList(edge1, edge2)));
            put(Long.parseLong("3"), new ArrayList<>(Arrays.asList(edge2, edge3)));
            put(Long.parseLong("4"), new ArrayList<>(List.of(edge3)));
        }};
        Map<Long, List<Edge>> actual = (new Dijkstra()).changeStructure(metro);
        assertAll(
                () -> assertArrayEquals(
                        expected.get(Long.parseLong("1")).toArray(),
                        actual.get(Long.parseLong("1")).toArray()),
                () -> assertArrayEquals(
                        expected.get(Long.parseLong("2")).toArray(),
                        actual.get(Long.parseLong("2")).toArray()),
                () -> assertArrayEquals(
                        expected.get(Long.parseLong("3")).toArray(),
                        actual.get(Long.parseLong("3")).toArray()),
                () -> assertArrayEquals(
                        expected.get(Long.parseLong("4")).toArray(),
                        actual.get(Long.parseLong("4")).toArray())
        );
    }

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
        List<Long> expected = new ArrayList<Long>(
            Arrays.asList(
                    Long.parseLong("1"),
                    Long.parseLong("2"),
                    Long.parseLong("3"),
                    Long.parseLong("4")
            )
        );
        List<Long> actual = (new Dijkstra()).findShortestPath(startPoint, endPoint, metro);
        assertArrayEquals(
                expected.toArray(),
                actual.toArray()
        );
    }

    @Test
    public void testDijkstra2() {
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

        Node node5 = new Node();
        node5.setId(Long.parseLong("5"));
        Edge edge4 = new Edge();
        edge4.setSource(node1.getId());
        edge4.setTarget(node5.getId());
        Edge edge5 = new Edge();
        edge5.setSource(node5.getId());
        edge5.setTarget(node4.getId());

        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4, node5));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);
        /*
        On obtient le trajet :
            5-------------------------------ligne5--------------------------------
            |                                                                    |
            |                                                                    |
          ligne5                                                                 |
            |                                                                    |
            |                                                                    |
            1 -------ligne1------- 2 -------ligne1------- 3 -------ligne2------- 4
        Soit Un passage dans 4 stations et 2 lignes différentes
         */
        Long startPoint = node1.getId();
        Long endPoint = node4.getId();
        List<Long> expected = new ArrayList<Long>(
                Arrays.asList(
                        Long.parseLong("1"),
                        Long.parseLong("5"),
                        Long.parseLong("4")
                )
        );
        List<Long> actual = (new Dijkstra()).findShortestPath(startPoint, endPoint, metro);
        System.out.println(actual);
        assertArrayEquals(
                expected.toArray(),
                actual.toArray()
        );
    }


    @Test
    public void testDijkstra3() {
        // Variables
        String line1 = "ligne1";
        String line2 = "ligne2";
        String line5 = "ligne5";

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

        Node node5 = new Node();
        node5.setId(Long.parseLong("5"));
        Edge edge4 = new Edge();
        edge4.setSource(node1.getId());
        edge4.setTarget(node5.getId());
        edge4.setLine(line5);

        Node node6 = new Node();
        node6.setId(Long.parseLong("6"));
        Edge edge5 = new Edge();
        edge5.setSource(node6.getId());
        edge5.setTarget(node5.getId());
        edge5.setLine(line5);

        Node node7 = new Node();
        node7.setId(Long.parseLong("7"));
        Edge edge6 = new Edge();
        edge6.setSource(node7.getId());
        edge6.setTarget(node6.getId());
        edge6.setLine(line5);

        Node node8 = new Node();
        node8.setId(Long.parseLong("8"));
        Edge edge7 = new Edge();
        edge7.setSource(node7.getId());
        edge7.setTarget(node8.getId());
        edge7.setLine(line5);
        Edge edge8 = new Edge();
        edge8.setSource(node8.getId());
        edge8.setTarget(node4.getId());
        edge8.setLine(line5);

        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);
        /*
        On obtient le trajet :
            5--------ligne5--------6--------ligne5--------7--------ligne5--------8
            |                                                                    |
            |                                                                    |
         ligne5                                                               ligne5
            |                                                                    |
            |                                                                    |
            1 -------ligne1------- 2 -------ligne1------- 3 -------ligne2------- 4
        Soit Un passage dans 4 stations et 2 lignes différentes
         */
        Long startPoint = node1.getId();
        Long endPoint = node4.getId();
        List<Long> expected = new ArrayList<Long>(
                Arrays.asList(
                        Long.parseLong("1"),
                        Long.parseLong("2"),
                        Long.parseLong("3"),
                        Long.parseLong("4")
                )
        );
        List<Long> actual = (new Dijkstra()).findShortestPath(startPoint, endPoint, metro);
        assertArrayEquals(
                expected.toArray(),
                actual.toArray()
        );
    }
}
