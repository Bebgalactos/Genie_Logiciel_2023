package fr.ul.miage.bipwac.gl.metro.graphe;

import java.util.Random;

/**
 * Class gérant l'utilisateur
 */
public class User {

    private double userLatitude;
    private double userLongitude;

    /*
    Latitude minimum : 48.815573
    Latitude maximum : 48.902145
    Longitude minimum : 2.225828
    Longitude maximum : 2.469920
     */

    /**
     * constructeur
     */
    public User(){
        getLatitudeRandom();
        getLongitudeRandom();
    }

    /**
     * Getter userLongitude
     * @return double
     */
    public double getUserLongitude() {
        return userLongitude;
    }

    /**
     * Getter userLatitude
     * @return double
     */
    public double getUserLatitude() {
        return userLatitude;
    }

    /**
     * Génère un random longitude entre les limites
     */
    public void getLongitudeRandom(){
        Random r = new Random();
        this.userLongitude = 2.225828 + (2.469920 - 2.225828) * r.nextDouble();
    }

    /**
     * Génère un random latitude entre les limites
     */
    public void getLatitudeRandom(){
        Random r = new Random();
        this.userLatitude = 48.815573 + (48.902145 - 48.815573) * r.nextDouble();
    }

}
