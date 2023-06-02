package fr.ul.miage.bipwac.gl.metro.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.ul.miage.bipwac.gl.metro.graphe.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class MetroStopServiceTest {
    /**
     * Mock
     * On créé des Mocks pour être independant du graph.json en le simulant
     * Et on récupère l'information avec outcontent
     */
    @Mock
    private MetroParisien metroParisien;

    @Mock
    private User user;

    @InjectMocks
    private MetroStopService metroStopService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    /**
     * Test afin de vérifier que l'on récupère la gare la plus proche
     * @param Mock
     */
    public void testNearestMetro() {
        Node mockNode = mock(Node.class);
        when(mockNode.getLatitude()).thenReturn(48.8566);
        when(mockNode.getLongitude()).thenReturn(2.3522);
        when(mockNode.getText()).thenReturn("Gare du Nord (métro)");
        when(metroParisien.getNodes()).thenReturn(Arrays.asList(mockNode));
        when(user.getUserLatitude()).thenReturn(48.857);
        when(user.getUserLongitude()).thenReturn(2.351);
        metroStopService.nearestMetro(user, metroParisien);
        verify(metroParisien, times(1)).getNodes();
        verify(user, times(1)).getUserLatitude();
        verify(user, times(1)).getUserLongitude();
        assertEquals("La station de métro la plus proche : Gare du Nord (métro)", outContent.toString().trim());
    }

    @Test
    /**
     * Test afin de vérifier que l'on essaye 5 fois avant d'échouer
     * @param Mock
     */
    public void testNearestMetro_NoMetroWithinRadius() {
        when(metroParisien.getNodes()).thenReturn(Arrays.asList());
        when(user.getUserLatitude()).thenReturn(48.857);
        when(user.getUserLongitude()).thenReturn(2.351);
        metroStopService.nearestMetro(user, metroParisien);
        verify(metroParisien, times(5)).getNodes();
        verify(user, times(5)).getUserLatitude();
        verify(user, times(5)).getUserLongitude();
        assertEquals("Aucune station trouvée dans les alentours.", outContent.toString().trim());
    }
}