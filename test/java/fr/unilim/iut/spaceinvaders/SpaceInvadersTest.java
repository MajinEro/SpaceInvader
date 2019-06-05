package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.*;
import org.junit.Before;
import org.junit.Test;

public class SpaceInvadersTest {
	private boolean droite;

	private SpaceInvaders spaceinvaders;
	@Before
	public void initialisation(){
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	 @Test

	   public void test_AuDebut_JeuSpaceInvaderEstVide() {
			String chaine = spaceinvaders.toString();
		    assertEquals("" + 
		    "...............\n" + 
		    "...............\n" +
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" + 
		    "...............\n" , chaine);
	        }



	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9),1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......V.......\n" , spaceinvaders.toString());
	}
	@Test(expected = HorsEspaceJeuException.class)
	public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropADroite_UneExceptionEstLevee() throws Exception {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9),1);
	}
    @Test(expected = HorsEspaceJeuException.class)
    public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnBas_UneExceptionEstLevee() throws Exception {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10),1);
    }
    @Test(expected = HorsEspaceJeuException.class)
    public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropAGauche_UneExceptionEstLevee() throws Exception {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,10),1);
    }
    @Test(expected = HorsEspaceJeuException.class)
    public void test_unNouveauVaisseauEstPositionneHorsEspaceJeuTropEnHaut_UneExceptionEstLevee() throws Exception {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1),1);
    }




	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),1);
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".......VVV.....\n" +
				".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9),1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1),1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9),3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"............VVV\n" +
				"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"....VVV........\n" +
				"....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9),3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"VVV............\n" +
				"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite_AvecVitesse() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"..........VVV..\n" +
				"..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"............VVV\n" +
				"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"VVV............\n" +
				"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".......MMM.....\n" +
				".......MMM.....\n" +
				".....VVVVVVV...\n" +
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
	}
	@Test
	public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		spaceinvaders.deplacerMissile();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".......MMM.....\n" +
				".......MMM.....\n" +
				"...............\n" +
				"...............\n" +
				".....VVVVVVV...\n" +
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
		for (int i = 1; i <=6 ; i++) {
			spaceinvaders.deplacerMissile();
		}

		spaceinvaders.deplacerMissile();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".....VVVVVVV...\n" +
				".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_InvaderSeDeplaceCorrectementDansEspaceDeJeu(){

		spaceinvaders.positionnerUnNouvelInvader(new Dimension(2,2), new Position(7,3), 1);
		spaceinvaders.deplacerInvaderVersLaDroite();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				".........OO....\n" +
				".........OO....\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_InvaderSeDeplaceEtRevientCorrectementDansEspaceDeJeu(){
		boolean droite;
		droite = true;
		spaceinvaders.positionnerUnNouvelInvader(new Dimension(2,2), new Position(7,3), 1);
		for (int i = 0; i <= 13; i++) {
			if (droite) {
				spaceinvaders.deplacerInvaderVersLaDroite();
			} else {
				spaceinvaders.deplacerInvaderVersLaGauche();
			}
			if (spaceinvaders.recupererInvader().abscisseLaPlusADroite() >= spaceinvaders.getLongueur() - 1) {
				droite = false;
			}
			if (spaceinvaders.recupererInvader().abscisseLaPlusAGauche() <= 0) {
				droite = true;
			}
		}

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				".....OO........\n" +
				".....OO........\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
}
