package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurJeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class DessinSpaceInvaders implements DessinJeu {
    private SpaceInvaders jeu;

    public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
        this.jeu = spaceInvaders;
    }

    @Override
    public void dessiner(BufferedImage im) {
        if (this.jeu.aUnVaisseau()) {
            Vaisseau vaisseau = this.jeu.recupererVaisseau();
            this.dessinerUnVaisseau(vaisseau, im);
        }
        if (this.jeu.aUnMissile()){
            Missile missile = this.jeu.recupererMissile();
            this.dessinerUnMissile(missile, im);
        }
        if (this.jeu.aUnInvader()){
            Invader invader = this.jeu.recupererInvader();
            this.dessinerInvader(invader, im);
        }
    }

    private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();

        crayon.setColor(Color.gray);
        crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());

    }
    private void dessinerUnMissile(Missile missile, BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.blue);
        crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());
    }
    private void dessinerInvader(Invader invader, BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.MAGENTA);
        crayon.fillRect(invader.abscisseLaPlusAGauche(),invader.ordonneeLaPlusBasse(), invader.longueur(),invader.hauteur());
    }

}
