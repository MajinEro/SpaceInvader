package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class Missile extends Sprite {

    public Missile(Dimension dimension, Position positionOrigine, int vitesse) {
        super(dimension,positionOrigine,vitesse);
    }
    public void deplacerVerticalementVers(Direction direction) {
        this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur() * vitesse);
    }
}
