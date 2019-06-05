package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Sprite;
import fr.unilim.iut.spaceinvaders.utils.Dimension;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.Position;

public class Vaisseau extends Sprite {

    public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
        super(dimension, positionOrigine, vitesse);
    }

    public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
        if (dimensionMissile.longueur() > this.dimension.longueur() || dimensionMissile.hauteur()> this.dimension.hauteur()){
            throw new MissileException("la taille du missile est superieure a celle du vaisseau");
        }
        Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
        return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
    }

    private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
        int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
        int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

        int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
        return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
    }
}
