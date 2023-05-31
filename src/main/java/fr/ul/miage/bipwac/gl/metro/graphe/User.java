package fr.ul.miage.bipwac.gl.metro.graphe;

import java.util.Random;

public class User {

    private double userLatitude;
    private double userLongitude;

    /*
    Latitude minimum : 48.815573
    Latitude maximum : 48.902145
    Longitude minimum : 2.225828
    Longitude maximum : 2.469920
     */

    public User(){
        getLatitudeRandom();
        getLongitudeRandom();
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void getLongitudeRandom(){
        Random r = new Random();
        this.userLongitude = 2.225828 + (2.469920 - 2.225828) * r.nextDouble();
        System.out.println(this.userLongitude);
    }

    public void getLatitudeRandom(){
        Random r = new Random();
        this.userLatitude = 48.815573 + (48.902145 - 48.815573) * r.nextDouble();
        System.out.println(this.userLatitude);
    }

}
