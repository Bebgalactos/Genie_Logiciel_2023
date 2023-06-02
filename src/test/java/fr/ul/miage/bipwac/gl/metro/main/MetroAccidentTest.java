package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroAccident;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MetroAccidentTest {

    @Test
    public void testManuallyCreatedGraphRemoveNode() {

        Node node1 = new Node();
        node1.setId(Long.parseLong("1"));
        Node node2 = new Node();
        node2.setId(Long.parseLong("2"));
        Edge edge1 = new Edge();
        edge1.setSource(node1.getId());
        edge1.setTarget(node2.getId());

        Node node3 = new Node();
        node3.setId(Long.parseLong("3"));

        node2.setAccident(true);
        node3.setAccident(true);

        Edge edge2 = new Edge();
        edge2.setSource(node2.getId());
        edge2.setTarget(node3.getId());


        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);

        MetroParisien res = (new MetroAccident()).removeNodeAccident(metro);

        int expectedSize = 1;
        long expectedId = 1;


        assertAll(
                () -> assertEquals(expectedSize, res.getNodes().size()),
                () -> assertEquals(Long.valueOf(expectedId), res.getNodes().get(0).getId()),
                () -> assertFalse(res.getNodes().get(0).isAccident())
        );

    }

    @Test
    public void testManuallyCreatedGraphRemoveEdge() {

        Node node1 = new Node();
        node1.setId(Long.parseLong("1"));
        Node node2 = new Node();
        node2.setId(Long.parseLong("2"));
        Edge edge1 = new Edge();
        edge1.setSource(node1.getId());
        edge1.setTarget(node2.getId());
        edge1.setId("1 - 2");

        Node node3 = new Node();
        node3.setId(Long.parseLong("3"));

        Edge edge2 = new Edge();
        edge2.setSource(node2.getId());
        edge2.setTarget(node3.getId());
        edge2.setId("2 - 3");

        edge2.setAccident(true);

        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);

        MetroParisien res = (new MetroAccident()).removeEdgeAccident(metro);

        int expectedSize = 1;
        String expectedId = "1 - 2";

        assertAll(
                () -> assertEquals(expectedSize, res.getEdges().size()),
                () -> assertEquals(expectedId, res.getEdges().get(0).getId()),
                () -> assertFalse(res.getEdges().get(0).isAccident())
        );
    }

    @Test
    public void testManuallyCreatedGraphRemoveNodeEmpty() {

        Node node1 = new Node();
        node1.setId(Long.parseLong("1"));
        Node node2 = new Node();
        node2.setId(Long.parseLong("2"));
        Edge edge1 = new Edge();
        edge1.setSource(node1.getId());
        edge1.setTarget(node2.getId());

        Node node3 = new Node();
        node3.setId(Long.parseLong("3"));

        Edge edge2 = new Edge();
        edge2.setSource(node2.getId());
        edge2.setTarget(node3.getId());

        node1.setAccident(true);
        node2.setAccident(true);
        node3.setAccident(true);

        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);

        MetroParisien res = (new MetroAccident()).removeNodeAccident(metro);

        assertAll(
                () -> assertTrue(res.getNodes().isEmpty()),
                () -> assertTrue(res.getEdges().isEmpty())
        );

    }

    @Test
    public void testManuallyCreatedGraphRemoveNodeCircuitRond() {

        Node node1 = new Node();
        node1.setId(Long.parseLong("1"));
        Node node2 = new Node();
        node2.setId(Long.parseLong("2"));
        Node node3 = new Node();
        node3.setId(Long.parseLong("3"));
        Node node4 = new Node();
        node4.setId(Long.parseLong("4"));
        Node node5 = new Node();
        node5.setId(Long.parseLong("5"));

        node5.setAccident(true);

        Edge edge1 = new Edge();
        edge1.setSource(node1.getId());
        edge1.setTarget(node2.getId());
        Edge edge2 = new Edge();
        edge2.setSource(node2.getId());
        edge2.setTarget(node3.getId());
        Edge edge3 = new Edge();
        edge3.setSource(node3.getId());
        edge3.setTarget(node4.getId());
        Edge edge4 = new Edge();
        edge4.setSource(node4.getId());
        edge4.setTarget(node1.getId());

        Edge edge1c = new Edge();
        edge1c.setSource(node1.getId());
        edge1c.setTarget(node5.getId());
        Edge edge2c = new Edge();
        edge2c.setSource(node2.getId());
        edge2c.setTarget(node5.getId());
        Edge edge3c = new Edge();
        edge3c.setSource(node3.getId());
        edge3c.setTarget(node5.getId());
        Edge edge4c = new Edge();
        edge4c.setSource(node4.getId());
        edge4c.setTarget(node5.getId());


        List<Node> nodes = new ArrayList<>(Arrays.asList(node1, node2, node3, node4, node5));
        List<Edge> edges = new ArrayList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge1c, edge2c, edge3c, edge4c));
        MetroParisien metro = new MetroParisien();
        metro.setNodes(nodes);
        metro.setEdges(edges);

        MetroParisien res = (new MetroAccident()).removeNodeAccident(metro);

        int expectedSize = 4;
        int expectedNbEdges = 4;

        assertAll(
                () -> assertEquals(expectedSize, res.getNodes().size()),
                () -> assertEquals(expectedNbEdges, res.getEdges().size())
        );

    }

}
