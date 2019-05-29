package fr.unilim.iut.spaceinvaders.utils;

public class Dimension {
    int x;
    int y;

    public Dimension(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int longueur() {
        return this.x;
    }

    public int hauteur() {
        return this.y;
    }

}
