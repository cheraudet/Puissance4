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
    Joueur joueurCourant;
    
    public void initialiserPartie(){
      grilleJeu.viderGrille();
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Pseudo joueur1 : ");
      Joueur joueur1 = new Joueur(sc.nextLine());
      System.out.println("Pseudo joueur2 : ");
      Joueur joueur2 = new Joueur(sc.nextLine());
      ListeJoueurs[0] = joueur1;
      ListeJoueurs[1] = joueur2;
      
      attribuerCouleursAuxJoueurs();
      System.out.println(joueur1.Nom + " a la couleur "+joueur1.Couleur);
      System.out.println(joueur2.Nom + " a la couleur "+joueur2.Couleur);
      
      grilleJeu.afficherGrilleSurConsole();
    }
    
    public void debuterPartie(){
        Scanner sc = new Scanner(System.in);
        initialiserPartie();
        int choixJoueur;
        int indColonne;
        int indLigne;
        while(grilleJeu.etreGagnantePourJoueur(ListeJoueurs[0])==false && grilleJeu.etreGagnantePourJoueur(ListeJoueurs[1])==false && grilleJeu.etreRemplie()==false){
            System.out.println("Quelle action voulez vous réaliser ?");
            System.out.println("1) Ajouter un jeton");
            System.out.println("2) Utiliser un désintégrateur");
            System.out.println("3) Récupérer un jeton");
            choixJoueur = sc.nextInt();
            
            if(choixJoueur == 1){
                System.out.println("Dans quelle colonne voulez vous ajouter votre jeton?");
                indColonne = sc.nextInt();
                grilleJeu.ajouterJetonDansColonne(joueurCourant, indColonne);
            }
            if(choixJoueur == 2){
                System.out.println("Dans quelle colonne voulez vous placer votre désintégrateur?");
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous placer votre désintégrateur?");
                indLigne = sc.nextInt();
                grilleJeu.placerDesintegrateur(indLigne, indColonne);
                joueurCourant.nombreDesintegrateurs --;
            }
            if(choixJoueur == 3){
                System.out.println("Dans quelle colonne voulez vous récupérer votre jeton?");
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous récupérer votre jeton?");
                indLigne = sc.nextInt();
                grilleJeu.recupererJeton(indLigne, indColonne);
                joueurCourant.nombreJetonsRestants++;
            }
        }
    }
    
    public void attribuerCouleursAuxJoueurs(){
            ListeJoueurs[0].Couleur = "Rouge";
            ListeJoueurs[1].Couleur = "Jaune";
    }
}
