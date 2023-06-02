package fr.ul.miage.bipwac.gl.metro.graphe;

import java.util.Comparator;
import java.util.Optional;

public class MetroStopService {

    /**
     * Fonction permettant de trouver la station de metro la plus proche d'un utilisateur
     * @param user User qui cherche la station la plus proche
     * @param metroParisien Graphe de metro sur lequel on veut chercher la station la plus proche
     */
    public void nearestMetro(User user, MetroParisien metroParisien) {
        double radius = 1.0;

        Optional<Node> nearestMetroStop = Optional.empty();
        while (radius <= 5.0 && nearestMetroStop.isEmpty()) {
            nearestMetroStop = findNearestMetroStop(metroParisien, user.getUserLatitude(), user.getUserLongitude(), radius);
            radius += 1.0;
        }

        if (nearestMetroStop.isPresent()) {
            System.out.println("La station de métro la plus proche : " + nearestMetroStop.get().getText());
        } else {
            System.out.println("Aucune station trouvée dans les alentours.");
        }
    }

    /**
     * Fonction permettant de trouver la station de metro la plus proche d'un point donné
     * @param metroParisien Graphe de metro sur lequel on veut chercher la station la plus proche
     * @param latitude latitude du point de recherche
     * @param longitude longitude du point de recherche
     * @param radiusInKm radius limite pour la recherche autour du point
     * @return la station de metro la plus proche d'un point donné
     */
    private Optional<Node> findNearestMetroStop(MetroParisien metroParisien, double latitude, double longitude, double radiusInKm) {
        return metroParisien.getNodes().stream()
                .filter(node -> distanceBetweenPoints(latitude, longitude, node.getLatitude(), node.getLongitude()) <= radiusInKm)
                .min(Comparator.comparingDouble(node -> distanceBetweenPoints(latitude, longitude, node.getLatitude(), node.getLongitude())));
    }

    /**
     * Fonction permettant de calculer la distance entre deux points
     * @param lat1 latitude du premier point
     * @param lon1 longitude du premier point
     * @param lat2 latitude du second point
     * @param lon2 longitude du second point
     * @return un long représentant la distance
     */
    private double distanceBetweenPoints(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // radius de la terre en km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


}