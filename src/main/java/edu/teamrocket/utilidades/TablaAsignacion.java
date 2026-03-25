package edu.teamrocket.utilidades;

class TablaAsignacion {

    private final char[] tabla = {
        'T','R','W','A','G','M',
        'Y','F','P','D','X','B',
        'N','J','Z','S','Q','V',
        'H','L','C','K','E'
    };

    TablaAsignacion() {}

    char[] getTabla() {
        return this.tabla;
    }

    char getLetra(int clave) {
        try {
            return this.tabla[clave];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(
                "La letra no es válida."
            );
        }
    }

    int getModulo() {
        return this.tabla.length;
    }

    Boolean isLetraPermitida(char letra) {
        for (char l : tabla) {
            if (l == letra) {
                return true;
            }
        }
        return false;
    }

    char calcularLetra(String numeroDni) {
        int posicion = Integer.parseInt(numeroDni) % getModulo();
        return getLetra(posicion);
    }

    @Override
    public String toString() {
        return String.valueOf(tabla);
    }
}