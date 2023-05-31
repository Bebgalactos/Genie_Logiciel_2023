package fr.ul.miage.bipwac.gl.metro.graphe;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class MetroAccident {

    public static void main(String[] args) throws FileNotFoundException {

        MetroParisien metroParisien = parseJson("src/main/resources/graph.json");
        List<Node> nodes = metroParisien.getNodes().subList(1,51);

        MetroParisien mp = new MetroParisien();

        mp.setNodes(nodes);
        mp.setEdges(metroParisien.getEdges());

        int counterNode = 0;
        Random r = new Random();
        for (Node n : nodes) {
            float rng = r.nextFloat();
            if (rng >= 0.5)  { // plus c'est proche de 1 = plus il y aura d'accidents
                n.setAccident(true);
                counterNode++;
            }
        }

        MetroParisien res = removeNodeAccident(mp);
        System.out.println("--------------------------------\nBase: ");
        System.out.println("Nombre total de nodes: " + nodes.size());
        System.out.println("--------------------------------\nApres remove: ");
        System.out.println("Nombre total de nodes: " + res.getNodes().size());
        System.out.println("--------------------------------\nDifference: ");
        System.out.println("Nombre de nodes avec accidents: " + (nodes.size() - res.getNodes().size()));
        System.out.println("Nombre d'accidents: " + counterNode);

        Set<Long> ids = new HashSet<>();
        Set<Long> src = new HashSet<>();
        Set<Long> dst = new HashSet<>();
        System.out.println();
        for (Node n : res.getNodes()) {
            ids.add(n.getId());
        }

        System.out.println();
        for (Edge e : res.getEdges()) {
            src.add(e.getSource());
            dst.add(e.getTarget());
        }

        System.out.println(ids);
        System.out.println(src);
        System.out.println(dst);

    }

    public static MetroParisien removeNodeAccident(MetroParisien entry){
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
        removeEdgeAccident(mp);
        return mp;
    }

    public static MetroParisien removeEdgeAccident(MetroParisien entry){
        MetroParisien mp = new MetroParisien();
        mp.setEdges(entry.getEdges().stream().filter(e -> !e.isAccident()).collect(Collectors.toList()));
        return mp;
    }

    private static MetroParisien parseJson(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, MetroParisien.class);
    }
}
