package fr.ul.miage.bipwac.gl.metro.graphe;

import java.util.Comparator;
import java.util.Optional;

public class MetroStopService {

    /**
     * Recherche la station de métro la plus proche dans un radius de 1 km et augmente jusqu'à 5km si aucune trouvée
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

    private Optional<Node> findNearestMetroStop(MetroParisien metroParisien, double latitude, double longitude, double radiusInKm) {
        return metroParisien.getNodes().stream()
                .filter(node -> distanceBetweenPoints(latitude, longitude, node.getLatitude(), node.getLongitude()) <= radiusInKm)
                .min(Comparator.comparingDouble(node -> distanceBetweenPoints(latitude, longitude, node.getLatitude(), node.getLongitude())));
    }

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