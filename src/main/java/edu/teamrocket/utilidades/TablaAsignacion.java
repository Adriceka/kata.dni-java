package edu.teamrocket.utilidades;

public class TablaAsignacion {

    private final char[] tabla = {
        'T','R','W','A','G','M',
        'Y','F','P','D','X','B',
        'N','J','Z','S','Q','V',
        'H','L','C','K','E'
    };

    public TablaAsignacion() {}

    public char[] getTabla() {
        return this.tabla;
    }

    public char getLetra(int clave) {
        if(clave < 0 || clave >= tabla.length){
            throw new ArrayIndexOutOfBoundsException("La letra no es válida.");
        }
        return tabla[clave];
    }

    public int getModulo() {
        return tabla.length;
    }

    public Boolean isLetraPermitida(char letra) {
        for (char l : tabla) {
            if (l == letra) return true;
        }
        return false;
    }

    public char calcularLetra(String numeroDni) {
        int posicion = Integer.parseInt(numeroDni) % getModulo();
        return getLetra(posicion);
    }

    @Override
    public String toString() {
        return new String(tabla);
    }
}