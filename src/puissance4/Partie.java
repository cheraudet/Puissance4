/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import java.util.Random;
import java.util.Scanner;

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
      
      String couleurJeton1 = joueur1.Couleur;
      Jeton jetonJ1 = new Jeton(couleurJeton1);
      joueur1.ajouterJeton(jetonJ1);
      
      String couleurJeton2 = joueur2.Couleur;
      Jeton jetonJ2 = new Jeton(couleurJeton2);
      joueur1.ajouterJeton(jetonJ2);
      
      Random r = new Random();
      boolean premierJoueur = r.nextBoolean();
      if (premierJoueur==true){
          joueurCourant = ListeJoueurs[0];
      }else{
          joueurCourant = ListeJoueurs[1];
      }
      //positionnement des 5 trous noirs aléatoires
      Random rt = new Random();
      int nb1 = rt.nextInt(6);
      int nb2 = rt.nextInt(7);
      int i=0;
      while(i<=5){
          grilleJeu.placerTrouNoir(nb1, nb2);
          i++;
      }
      
      //positionnement des 3 désintégrateurs seuls
      Random rd = new Random();
      int nb3 = rd.nextInt(6);
      int nb4 = rd.nextInt(7);
      int j=0;
      while(j<=3){
          grilleJeu.placerDesintegrateur(nb3, nb4);
          j++;
      }
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
            if(choixJoueur == 2 && joueurCourant.nombreDesintegrateurs != 0){
                System.out.println("Dans quelle colonne voulez vous placer votre désintégrateur?");
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous placer votre désintégrateur?");
                indLigne = sc.nextInt();
                grilleJeu.placerDesintegrateur(indLigne, indColonne);
                joueurCourant.utiliserDesintegrateur();
                joueurCourant.nombreDesintegrateurs --;
            }
            if(choixJoueur == 3){
                System.out.println("Dans quelle colonne voulez vous récupérer votre jeton?");
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous récupérer votre jeton?");
                indLigne = sc.nextInt();
                grilleJeu.recupererJeton(indLigne, indColonne);
                grilleJeu.tasserGrille(indLigne, indColonne);
                joueurCourant.nombreJetonsRestants++;
            }
            if(choixJoueur == 2 && joueurCourant.nombreDesintegrateurs == 0){
                System.out.println("Attention, vous n'avez pas de désintégrateur !");
            }
            tourJoueur();
        }
        if (grilleJeu.etreGagnantePourJoueur(joueurCourant)){
            if (joueurCourant == ListeJoueurs[0]){
                System.out.println(ListeJoueurs[0]+" est le gagnant ! Bravo!");
            }
            if (joueurCourant == ListeJoueurs[1]){
                System.out.println(ListeJoueurs[1]+" est le gagnant ! Bravo!");
            }}
        if (grilleJeu.etreRemplie()){
            System.out.println("Match nul! Recommencez une partie!");
        }
    }
    
    public void attribuerCouleursAuxJoueurs(){
        Random r = new Random();
        boolean couleurJoueurs = r.nextBoolean();
        if(couleurJoueurs){
            ListeJoueurs[0].Couleur = "Rouge";
            ListeJoueurs[1].Couleur = "Jaune";
        }else{
            ListeJoueurs[1].Couleur = "Rouge";
            ListeJoueurs[0].Couleur = "Jaune";
        }
    }
    
    public void tourJoueur(){
        if(ListeJoueurs[0]==joueurCourant){
            joueurCourant = ListeJoueurs[1];
            System.out.println("A vous de jouer! "+"Il vous reste: "+ ListeJoueurs[1].nombreJetonsRestants+" jetons, et "+ListeJoueurs[1].nombreDesintegrateurs+" désintégrateurs");
        } else {
            joueurCourant = ListeJoueurs[0];
            System.out.println("A vous de jouer! "+"Il vous reste: "+ ListeJoueurs[0].nombreJetonsRestants+" jetons, et "+ListeJoueurs[0].nombreDesintegrateurs+" désintégrateurs");
        }
    }
}
