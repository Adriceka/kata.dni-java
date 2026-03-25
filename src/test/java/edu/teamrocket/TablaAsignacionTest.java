package edu.teamrocket;

import edu.teamrocket.utilidades.TablaAsignacion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TablaAsignacionTest {

    private static TablaAsignacion tabla;
    private static final char[] letrasNoPermitidas = {'I', 'Ñ', 'O', 'U'};

    @BeforeAll
    static void crearTabla() {
        tabla = new TablaAsignacion();
    }

    @Test 
    void getLetraTablaAsignacion() {
        assertEquals('T', tabla.getLetra(0));
        assertEquals('E', tabla.getLetra(22));
    }

    @Test
    void getLetraFueraLimitesTablaAsignacion() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            tabla.getLetra(100);
        });
    }

    @Test
    void letraNoPermitida() {
        for (char letra : letrasNoPermitidas) {
            assertFalse(tabla.isLetraPermitida(letra));
        }
    }

    private static boolean testCalcularLetra(List<String> casosTest, TablaAsignacion tabla){
        for (String dni : casosTest) {
            String parteNumericaDni = dni.substring(0, dni.length() - 1);
            char letraDni = dni.charAt(dni.length() - 1);
            if (tabla.calcularLetra(parteNumericaDni) != letraDni) {
                return false;
            }
        }
        return true;
    }

    @Test
    void calcularLetraPermitida() {
        String[] casosTest = {
            "78484464T","72376173A","01817200Q","95882054E","63587725Q",
            "26861694V","21616083Q","26868974Y","40135330P","89044648X",
            "80117501Z","34168723S","76857238R","66714505S","66499420A"
        };
        assertTrue(testCalcularLetra(List.of(casosTest), tabla));
    }

    @Test
    void calcularLetraNoPermitida() {
        int numeroCasos = 15;
        StringBuilder caso;
        List<String> casosTestKO = new ArrayList<>();

        for(int i = 1; i <= numeroCasos; i++){
            caso = new StringBuilder("");
            for(int j = 1; j < 9; j++){
                int caracterAscii = ThreadLocalRandom.current().nextInt(48, 58);
                caso.append((char) caracterAscii);
            }
            caso.append(letrasNoPermitidas[ThreadLocalRandom.current().nextInt(0, 4)]);
            casosTestKO.add(caso.toString());
        }

        assertFalse(testCalcularLetra(casosTestKO, tabla));
    }
}