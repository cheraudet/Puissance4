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
    //creation attributs de la classe partie 
    Joueur ListeJoueurs [] = new Joueur [2];
    Grille grilleJeu = new Grille();
    Joueur joueurCourant;
    
    public void initialiserPartie(){ //creation de la methode qui initialise chaque partie 
      grilleJeu.viderGrille(); //on commence par vider la grille 
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Pseudo joueur1 : "); //on demande au premier joueur de rentrer son pseudo
      Joueur joueur1 = new Joueur(sc.nextLine());
      System.out.println("Pseudo joueur2 : "); //de meme pour le deuxieme joueur 
      Joueur joueur2 = new Joueur(sc.nextLine());
      ListeJoueurs[0] = joueur1; 
      ListeJoueurs[1] = joueur2;
      
      attribuerCouleursAuxJoueurs(); //on appelle la methode qui attribue une couleru a chaque joueur
      System.out.println(joueur1.Nom + " a la couleur "+joueur1.Couleur); //on annonce la couleur du joueur 1
      System.out.println(joueur2.Nom + " a la couleur "+joueur2.Couleur); //on annonce la couleur du joueur 2 
      
      //on attribue la couleur des jetons en fonction de celle du joueur 
      String couleurJeton1 = joueur1.Couleur; 
      Jeton jetonJ1 = new Jeton(couleurJeton1);
      joueur1.ajouterJeton(jetonJ1);
      //de meme pour le joueur 2
      String couleurJeton2 = joueur2.Couleur;
      Jeton jetonJ2 = new Jeton(couleurJeton2);
      joueur1.ajouterJeton(jetonJ2);
      
      //tirage au sort du joueur qui commencera la partie 
      Random r = new Random();
      boolean premierJoueur = r.nextBoolean();
      if (premierJoueur==true){
          joueurCourant = ListeJoueurs[0];
          System.out.println(joueur1.Nom+" vous commencez la partie !");
      }else{
          joueurCourant = ListeJoueurs[1];
          System.out.println(joueur2.Nom+" vous commencez la partie !");
      }
      
      //positionnement aléatoire des 5 trous noirs aléatoires
      Random rt = new Random();
      int nb1 = rt.nextInt(6);
      int nb2 = rt.nextInt(7);
      int i=0;
      int k=0;
      while(i<=5){
          grilleJeu.placerTrouNoir(nb1, nb2);
          i++;
          while(k<=2){ //positionnement aleatoire des 2 désintégrateurs derrières trous noirs
              grilleJeu.placerDesintegrateur(nb1, nb2);
              k++;
          }
      }
      
      //positionnement aleatoire des 3 désintégrateurs seuls
      Random rd = new Random();
      int nb3 = rd.nextInt(6);
      int nb4 = rd.nextInt(7);
      int j=0;
      while(j<=3){
          grilleJeu.placerDesintegrateur(nb3, nb4);
          j++;
      }
      grilleJeu.afficherGrilleSurConsole(); //affichage de la grille
    }
    
    public void debuterPartie(){ //creation de la methode qui lance la partie 
        Scanner sc = new Scanner(System.in);
        initialiserPartie();
        int choixJoueur;
        int indColonne;
        int indLigne;
        //tant qu'aucun joueur n'a gagné et que la grille n'est pas pleine :
        while(grilleJeu.etreGagnantePourJoueur(ListeJoueurs[0])==false && grilleJeu.etreGagnantePourJoueur(ListeJoueurs[1])==false && grilleJeu.etreRemplie()==false){
            //affichage du menu du jeu 
            System.out.println("Quelle action voulez vous réaliser ?");
            System.out.println("1) Ajouter un jeton");
            System.out.println("2) Utiliser un désintégrateur");
            System.out.println("3) Récupérer un jeton");
            choixJoueur = sc.nextInt();
            
            if(choixJoueur == 1){ //si le joueur veut ajouter un jeton 
                System.out.println("Dans quelle colonne voulez vous ajouter votre jeton?"); //on lui demande la position qu'il souhaite pour son jeton 
                indColonne = sc.nextInt();
                grilleJeu.ajouterJetonDansColonne(joueurCourant, indColonne); //on ajoute le jeton dans la colonne souhaité 
                grilleJeu.etreGagnantePourJoueur(joueurCourant); //on teste si 4 jetons sont alignés 
            }
            if(choixJoueur == 2 && joueurCourant.nombreDesintegrateurs != 0){ //si le joueur veut utiliser un desitegrateur 
                //on lui demande la position qu'il souhaite pour placer son desintegrateur 
                System.out.println("Dans quelle colonne voulez vous placer votre désintégrateur?"); 
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous placer votre désintégrateur?");
                indLigne = sc.nextInt();
                grilleJeu.placerDesintegrateur(indLigne, indColonne); //placement du desintegrateur a la position souaitée
                joueurCourant.utiliserDesintegrateur(); 
                joueurCourant.nombreDesintegrateurs --; //décrémentation du nombre de désintegrateur du joueur
                grilleJeu.etreGagnantePourJoueur(joueurCourant); //on teste si 4 jetons sont alignés
            }
            if(choixJoueur == 3){ //si le joueur veut recuperer un jeton 
                //on lui demande la position du jeton 
                System.out.println("Dans quelle colonne voulez vous récupérer votre jeton?");
                indColonne = sc.nextInt();
                System.out.println("Dans quelle ligne voulez vous récupérer votre jeton?");
                indLigne = sc.nextInt();
                grilleJeu.recupererJeton(indLigne, indColonne); //recuperation du jeton souhaité 
                grilleJeu.tasserGrille(indColonne); //tassement de la grille
                joueurCourant.nombreJetonsRestants++; //incrémentation du nombre de jetons du joueur 
                grilleJeu.etreGagnantePourJoueur(joueurCourant); //on teste si 4 jetons sont alignés
            }
            if(choixJoueur == 2 && joueurCourant.nombreDesintegrateurs == 0){ //si le joueur veut utiliser un desintegrateur mais qu'il n'en possede pas 
                System.out.println("Attention, vous n'avez pas de désintégrateur !"); //affichage d'un message d'erreur 
            }
            tourJoueur();
        }
        if (grilleJeu.etreGagnantePourJoueur(joueurCourant)){ //si un des deux joueurs a gagné :
            if (joueurCourant == ListeJoueurs[0]){ //si c'est le joueur 1, affichage d'un message de félicitations 
                System.out.println(ListeJoueurs[0]+" est le gagnant ! Bravo!");
            }
            if (joueurCourant == ListeJoueurs[1]){ //de meme pour le joueur 2 
                System.out.println(ListeJoueurs[1]+" est le gagnant ! Bravo!");
            }}
        if (grilleJeu.etreRemplie()){ //si la grille est pleine 
            System.out.println("Match nul! Recommencez une partie!"); //affichage d'un message informatif au joueurs 
            grilleJeu.viderGrille(); //vidage de la grille
        }
    }
    
    public void attribuerCouleursAuxJoueurs(){ //creation de la methode qui attribue une couleur aléatoire aux joueurs
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
    
    public void tourJoueur(){ //creation de la methode qui permet de savoir le tour des joueurs 
        if(ListeJoueurs[0]==joueurCourant){
            joueurCourant = ListeJoueurs[1];
            System.out.println("A vous de jouer! "+"Il vous reste: "+ ListeJoueurs[1].nombreJetonsRestants+" jetons, et "+ListeJoueurs[1].nombreDesintegrateurs+" désintégrateurs");
        } else {
            joueurCourant = ListeJoueurs[0];
            System.out.println("A vous de jouer! "+"Il vous reste: "+ ListeJoueurs[0].nombreJetonsRestants+" jetons, et "+ListeJoueurs[0].nombreDesintegrateurs+" désintégrateurs");
        }
    }
}
