/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

/**
 *
 * @author rfougero
 */
public class Partie {
    Joueur ListeJoueurs [] = new Joueur [2];
    Grille grilleJeu = new Grille();
    Joueur joueurCourant = new Joueur();
    
    public void initialiserPartie(){
      grilleJeu.viderGrille();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("JPseudo joueur1 : ");
      Joueur joueur1 = new Joueur(sc.nextLine());
      System.out.println("Pseudo joueur2 : ");
      Joueur joueur2 = new Joueur(sc.nextLine());
      ListeJoueurs[0] = joueur1;
      ListeJoueurs[1] = joueur2;
      
      attribuerCouleursAuxJoueurs();
      System.out.println(joueur1.Nom + " a la couleur "+joueur1.Couleur);
      System.out.println(joueur2.Nom + " a la couleur "+joueur2.Couleur);
    }
    
    public void debuterPartie(){
        
    }
    
    public void attribuerCouleursAuxJoueurs(){
        //Random r = new Random();
        //boolean couleur;
        //couleur = r.nextBoolean();
        //if(couleur){
            ListeJoueurs[0].Couleur = "Rouge";
            ListeJoueurs[1].Couleur = "Jaune";
        //} else {
            //ListeJoueurs[0].Couleur = "Jaune";
            //ListeJoueurs[1].Couleur = "Rouge";
        //}
    }
}
