package fr.ul.miage.bipwac.gl.metro.graphe;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class MetroAccident {

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
        removeEdgeAccident(mp);
        return mp;
    }

    public MetroParisien removeEdgeAccident(MetroParisien entry){
        MetroParisien mp = new MetroParisien();
        mp.setEdges(entry.getEdges().stream().filter(e -> !e.isAccident()).collect(Collectors.toList()));
        return mp;
    }

    private MetroParisien parseJson(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        return gson.fromJson(reader, MetroParisien.class);
    }
}
