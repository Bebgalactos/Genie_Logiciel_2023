package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.Edge;
import fr.ul.miage.bipwac.gl.metro.graphe.MetroParisien;
import fr.ul.miage.bipwac.gl.metro.graphe.Node;
import fr.ul.miage.bipwac.gl.metro.graphe.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.ul.miage.bipwac.gl.metro.graphe.MetroAccident.removeEdgeAccident;
import static fr.ul.miage.bipwac.gl.metro.graphe.MetroAccident.removeNodeAccident;
import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void rangeLatitude(){
        User u = new User();
        assertTrue(u.getUserLatitude()>=48.815573 && u.getUserLatitude()<=48.902145);
    }

    @Test
    public void rangeLongitude(){
        User u = new User();
        assertTrue(u.getUserLongitude()>=2.225828 && u.getUserLongitude()<=2.469920);
    }




}
