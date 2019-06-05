package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurJeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.*;

import static fr.unilim.iut.spaceinvaders.model.Constante.*;

public class SpaceInvaders implements Jeu {


    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Invader invader;
    boolean droite = true;
    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public int getLongueur() {
        return this.longueur;
    }
    public int getHauteur(){
        return this.hauteur;
    }
    public void initialiserJeu() {
        Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
        Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
        Position positionInvader = new Position(this.longueur/2, this.hauteur/3);
        Dimension dimensionInvader = new Dimension(Constante.INVADER_LONGUEUR, INVADER_HAUTEUR );
        int vitesseVaisseau = 10;
        int vitesseInvader = 5;
        positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau,vitesseVaisseau);
        positionnerUnNouvelInvader(dimensionInvader,positionInvader, vitesseInvader);

    }

    public String recupererEspaceJeuDansChaineASCII() {
        StringBuilder espaceDeJeu = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < longueur; x++) {
                espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
            espaceDeJeu.append(MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }
    @Override
    public String toString(){
        return this.recupererEspaceJeuDansChaineASCII();
    }
    private char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_VAISSEAU;
        else if (this.aUnMissileQuiOccupeLaPosition(x, y))
            marque = Constante.MARQUE_MISSILE;
        else if (this.aUnInvaderQuiOccupeLaPosition(x,y))
            marque = Constante.MARQUE_INVADER;
        else marque = Constante.MARQUE_VIDE;
        return marque;
    }

    private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
    }
    private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
        return this.aUnMissile() && missile.occupeLaPosition(x, y);
    }
    private boolean aUnInvaderQuiOccupeLaPosition(int x, int y){
        return this.aUnInvader() && invader.occupeLaPosition(x, y);
    }

    public boolean aUnVaisseau() {
        return vaisseau != null;
    }
    public boolean aUnMissile() {
        return missile != null;
    }
    public boolean aUnInvader(){
        return invader != null;
    }
    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position,int vitesse) {

        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

        int longueurVaisseau = dimension.longueur();
        int hauteurVaisseau = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
            throw new DebordementEspaceJeuException(
                    "Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
            throw new DebordementEspaceJeuException(
                    "Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

        vaisseau = new Vaisseau(dimension,position,vitesse);
    }
    public void positionnerUnNouvelInvader(Dimension dimension, Position position,int vitesse) {

        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position de l'invader est en dehors de l'espace jeu");

        int longueurInvader = dimension.longueur();
        int hauteurInvader = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurInvader - 1, y))
            throw new DebordementEspaceJeuException(
                    "L'invader déborde de l'espace jeu vers la droite à cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurInvader + 1))
            throw new DebordementEspaceJeuException(
                    "L'invader déborde de l'espace jeu vers le bas à cause de sa hauteur");

        invader = new Invader(dimension,position,vitesse);
    }

    private boolean estDansEspaceJeu(int x, int y) {
        return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
    }

    public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
            vaisseau.deplacerHorizontalementVers(Direction.DROITE);
            if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
                vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
            }
        }
    }

    public void deplacerVaisseauVersLaGauche() {
        if (0 < vaisseau.abscisseLaPlusAGauche())
            vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
        if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
            vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
        }
    }
    public void deplacerInvaderVersLaDroite(){
        if (invader.abscisseLaPlusADroite() < (longueur - 1)) {
            invader.deplacerHorizontalementVers(Direction.DROITE);
            if (!estDansEspaceJeu(invader.abscisseLaPlusADroite(), invader.ordonneeLaPlusHaute())) {
                invader.positionner(longueur - invader.longueur(), invader.ordonneeLaPlusHaute());
            }
        }
    }
    public void deplacerInvaderVersLaGauche() {
        if (0 < invader.abscisseLaPlusAGauche())
            invader.deplacerHorizontalementVers(Direction.GAUCHE);
        if (!estDansEspaceJeu(invader.abscisseLaPlusAGauche(), invader.ordonneeLaPlusHaute())) {
            invader.positionner(0, invader.ordonneeLaPlusHaute());
        }
    }


    public Vaisseau recupererVaisseau() {
        return this.vaisseau;
    }
    public Missile recupererMissile() {
        return this.missile;
    }
    public Invader recupererInvader(){
        return this.invader;
    }
    @Override
    public void evoluer(Commande commandeUser) {

        if (commandeUser.gauche) {
            deplacerVaisseauVersLaGauche();
        }

        if (commandeUser.droite) {
            deplacerVaisseauVersLaDroite();
        }

        if (commandeUser.tir && !this.aUnMissile())
            tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
                    Constante.MISSILE_VITESSE);
        if (this.aUnMissile()){
            deplacerMissile();
        }
        if (this.aUnInvader()){
            deplacerInvader();
        }

    }

    private void deplacerInvader() {
        if (droite) {
            deplacerInvaderVersLaDroite();
        } else {
            deplacerInvaderVersLaGauche();
        }
        if (recupererInvader().abscisseLaPlusADroite() >= getLongueur() - 1) {
            droite = false;
        }
        if (recupererInvader().abscisseLaPlusAGauche() <= 0) {
            droite = true;
        }
    }


    @Override
    public boolean etreFini() {
        return false;

    }

    public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

        if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

        this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
    }
    public void deplacerMissile() {


        if (missile.ordonneeLaPlusBasse() <= 0) {
            missile = null;
        } else {
            this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
        }
    }
}
