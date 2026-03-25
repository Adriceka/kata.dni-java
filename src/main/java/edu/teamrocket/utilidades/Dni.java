package edu.teamrocket.utilidades;

public class Dni {
    private final String dni;
    private Boolean numeroSano = false;
    private Boolean letraSana = false;
    private final TablaAsignacion tabla = new TablaAsignacion();
    private final byte LONGITUD_DNI = 9;
    public Dni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return this.dni;
    }

    public Boolean getLetraSana() {
    return this.letraSana;
    }

    public Boolean getNumeroSano() {
        return this.numeroSano;
    }


    public Boolean checkDni() {
        setNumeroSano(checkNumero());
        return getNumeroSano() && checkLetra();
    }

    public Boolean checkNumero() {
        boolean correcto = longitudCorrecta() && soloNumeros();
        setNumeroSano(correcto);
        return getNumeroSano();
    }

    public Boolean checkLetra() {
        if (!getNumeroSano()) {
            setLetraSana(false);
            return false;
        }
        char letraReal = tabla.calcularLetra(getParteNumericaDni());
        char letraDni = getParteAlfabeticaDni();
        boolean esValida = letraReal == letraDni;
        setLetraSana(esValida);
        return esValida;
    }

    public Character obtenerLetra() {
        if (getNumeroSano()) {
            return tabla.calcularLetra(getParteNumericaDni());
        }
        return Character.MIN_VALUE;
    }

    @Override
    public String toString() {
        return getDni();
    }

    private void setNumeroSano(Boolean valor) {
        this.numeroSano = valor;
    }

    private void setLetraSana(Boolean valor) {
        this.letraSana = valor;
    }

    private boolean longitudCorrecta() {
        return this.dni.length() == LONGITUD_DNI;
    }

    private char getParteAlfabeticaDni() {
        return dni.charAt(dni.length() - 1);
    }

    private String getParteNumericaDni() {
        if (getNumeroSano()) {
            return dni.substring(0, dni.length() - 1);
        } else {
            return null;
        }
    }

        private boolean soloNumeros() {
        String parteNumerica = dni.substring(0, dni.length() - 1);
        for (int i = 0; i < parteNumerica.length(); i++) {
            if (!Character.isDigit(parteNumerica.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
