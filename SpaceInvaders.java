package fr.unilim.iut.spaceinvaders;

public class SpaceInvaders {

    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
   }
    @Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}
    private char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
           marque='V';
        else
           marque='.';
        return marque;
	}
	private boolean aUnVaisseauQuiOccupeLaPosition(int y, int x) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}
    public void positionnerUnNouveauVaisseau(int x, int y) {
        Vaisseau vaisseau = new Vaisseau (x,y);
	}
    
}
