package fr.unilim.iut.spaceinvaders.SpaceInvaders;

import fr.unilim.iut.spaceinvaders.SpaceInvaders.Vaisseau;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders {

    private static final char FIN_DE_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';
	int longueur;
    int hauteur;
    Vaisseau vaisseau;
    
    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
   }
    @Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
	}
	private String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int x = 0; x < hauteur; x++) {
			for (int y = 0; y < longueur; y++) {
				espaceDeJeu.append(recupererMarqueDeLaposition(x, y));
			}
			espaceDeJeu.append(FIN_DE_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaposition(int x, int y) {
		char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
           marque=MARQUE_VAISSEAU;
        else
           marque=MARQUE_VIDE;
        return marque;
	}
	private boolean aUnVaisseauQuiOccupeLaPosition(int y, int x) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}
	public void positionnerUnNouveauVaisseau(int x, int y) {
		
		if (x >= longueur)
			throw new HorsEspaceJeuException("Vous êtes en dehors de l'espace jeu");
		
		vaisseau = new Vaisseau(x, y);

	}
    
}
