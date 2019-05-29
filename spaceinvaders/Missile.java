package fr.unilim.iut.spaceinvaders.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class Missile extends Sprite {

    public Missile(Dimension dimension, Position positionOrigine, int vitesse) {
        super(dimension,positionOrigine,vitesse);
    }
    /*public void deplacer(Commande c ){
        if (c.tir){
            this.deplacerVerticalementVers(Direction.HAUT_ECRAN);
        }
    }

     */

}
