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
public class Grille { 
    //creation des attributs de la classe grille 
    Cellule cellules [][] = new Cellule [6][7];
    
    public boolean ajouterJetonDansColonne(Joueur joueurCourant, int indColonne){ //creation de la methode qui ajoute un jeton dans la colonne voulue 
      if (colonneRemplie(indColonne)){ //si la colonne est pleine, on ne peut pas ajouter de jeton
        return false; //donc on retourne faux 
      }
      else{ //si ce n'est pas le cas :
        //Jeton unJeton = joueurCourant.ListeJetons; //je voulais definir le jeton unJeton comme etant un jeton de la liste des jetons du joueur mais je n'ai pas réussi
        Jeton unJeton = new Jeton(joueurCourant.Couleur); //on definit et initialise la variable jeton
        int i=5;
        while(cellules[i][indColonne].jetonCourant != null){ //on teste tant que la cellule n'est pas vide 
            i--; //on passe à la cellule du dessus 
        }
        if(cellules[i][indColonne].jetonCourant == null){ //test si la cellule est vide  
            joueurCourant.enleverJeton(unJeton); //si c'est le cas, on enleve un jeton au joueur
            cellules[i][indColonne].jetonCourant = unJeton; //on ajoute le jeton enlevé dans la cellule 
            joueurCourant.nombreJetonsRestants --; //et on decremente le nb de jetons du joueur
        }
        if(cellules[i][indColonne].presenceTrouNoir()){ //test si un trou noir est présent 
            cellules[i][indColonne].activerTrouNoir(); //si c'est le cas, on l'active
            joueurCourant.nombreJetonsRestants --; //et on decremente le nb de jetons du joueur
        }
        if(cellules[i][indColonne].presenceDesintegrateur()){ //test si un desintegrateur est present 
            cellules[i][indColonne].recupererDesintegrateur(); //si c'est le cas, on le recupere 
            joueurCourant.nombreDesintegrateurs ++; //on incrémente le nb de desintegrateurs du joueur 
            joueurCourant.nombreJetonsRestants --; //et on decremente le nb de jetons du joueur
        }
        return true; //on retourne vrai
      }
    } 
    
    
    public boolean etreRemplie(){ //creation de la methode qui teste si la grille est pleine
        boolean remp=true;
        for(int i=0; i<7; i++){ //creation d'un curseur pour parcourir les colonnes 
            if(cellules[0][i].jetonCourant != null){ //si la derniere ligne de chaque colonne de la grille a un jeton 
                remp = true; //alors on retourne vrai 
            }
        }
        return remp;
    }
    
    public void viderGrille(){ //creation de la methode qui permet de vider la grille 
        for (int i=0; i<6; i++){ //boucle pour parcourir les lignes 
            for(int j=0; j<7; j++){ //boucle pour parcourir les colonnes 
                if (cellules[i][j] != null){ //test si une cellule est pleine 
                    cellules [i][j] = null; //si c'est le cas alors on la vide 
                }
            }
        }
        
    }
    
    public void afficherGrilleSurConsole(){ //creation de la methode qui affiche la grille sur la console 
        for(int i=0; i<6; i++){ //boucle pour parcourir les lignes
            for(int j=0; j<7; j++){ //boucle pour parcourir les colonnes
               if(cellules[i][j] == null){ //si la cellule est vide 
                   System.out.print("V"); //alors on affiche V pour vide 
               }
               else if(cellules[i][j].jetonCourant != null){ // sinon si la cellule possede un jeton 
                   if(cellules[i][j].jetonCourant.Couleur == "Rouge"){ //si le jeton est rouge 
                       System.out.print("R"); //on afiche R pour rouge 
                   }
                   else if(cellules[i][j].jetonCourant.Couleur == "Jaune"){ //sinon si le jeton est jaune 
                       System.out.print("J"); //on affiche J pour jaune 
                   }
                }
               else if(cellules[i][j].placerTrouNoir() && cellules[i][j].presenceDesintegrateur()){ //sinon si la cellule possede un desintegrateur et un trou noir 
                   System.out.print("T"); //on affiche T pour trou noir, les desintegrateurs sont donc invisibles 
               }
               else if(cellules[i][j].presenceDesintegrateur() && cellules[i][j].placerTrouNoir()==false){ //sinon si la cellule possede un desintegrateur 
                   System.out.print("D"); //on affiche D pour desintegrateur 
               }
               else if(cellules[i][j].presenceTrouNoir() && cellules[i][j].presenceDesintegrateur()==false){ //sinon si la cellule possede un trou noir 
                   System.out.print("T"); //on affiche T pour trou noir 
               }
               System.out.print(" ");//affiche un espace entre chaque colonne
            }
            System.out.println(" ");//affiche un espace entre chaque ligne 
        }
    }
    
    public boolean celluleOccupee(int indLigne, int indColonne){ //creation de la methode qui permet de savoir si la cellule est occupée 
        boolean occup = true;
        if (cellules[indLigne][indColonne].jetonCourant != null){ //test si la cellule possede un jeton 
            occup = true; //on retourne vrai 
        } 
        return occup;
    }
    
    public String lireCouleurDuJeton(int indLigne, int indColonne){ //creation de la methode qui permet de lre la couleur du jeton
        return cellules[indLigne][indColonne].jetonCourant.Couleur; //retourne la couleur du jeton courant 
    }
    
    public boolean etreGagnantePourJoueur(Joueur joueurCourant){ //creation de la methode qui teste si un joueur gagne
        boolean result=true;
        for(int i=0; i<3; i++){ //boucle pour parcourir les lignes sans sortir de la grille
            for(int j=0; j<4; j++){ //boucle pour parcourir les colonnes sans sortir de la grille
                //test si 4 jetons sont alignés en diagonale montante
                if (lireCouleurDuJeton(i,j)==joueurCourant.Couleur && lireCouleurDuJeton(i+1,j+1)==joueurCourant.Couleur && lireCouleurDuJeton(i+2,j+2)==joueurCourant.Couleur && lireCouleurDuJeton(i+3,j+3)==joueurCourant.Couleur){
                    result = true; //retourne vrai si c'est le cas 
                }}}
        for(int i=0; i<3; i++){ //boucle pour parcourir les lignes sans sortir de la grille
            for(int j=0; j<7; j++){ //boucle pour parcourir les colonnes sans sortir de la grille
                ////test si 4 jetons sont alignés en ligne
                if (lireCouleurDuJeton(i,j)==joueurCourant.Couleur && lireCouleurDuJeton(i+1,j)==joueurCourant.Couleur && lireCouleurDuJeton(i+2,j)==joueurCourant.Couleur && lireCouleurDuJeton(i+3,j)==joueurCourant.Couleur){
                    result = true;
                }}}
        for(int i=0; i<7; i++){ //boucle pour parcourir les lignes sans sortir de la grille
            for(int j=0; j<4; j++){ //boucle pour parcourir les colonnes sans sortir de la grille
                //test si 4 jetons sont alignés en colonne 
                if(lireCouleurDuJeton(i,j)==joueurCourant.Couleur && lireCouleurDuJeton(i,j+1)==joueurCourant.Couleur && lireCouleurDuJeton(i,j+2)==joueurCourant.Couleur && lireCouleurDuJeton(i,j+3)==joueurCourant.Couleur){
                    result = true;
                }}}
        for(int i=0; i<3; i++){ //boucle pour parcourir les lignes sans sortir de la grille 
            for(int j=0; j<4; j++){//boucle pour parcourir les colonnes sans sortir de la grille
                //test si 4 jetons sont alignés en diagonale descendante
                if (lireCouleurDuJeton(i,j)==joueurCourant.Couleur && lireCouleurDuJeton(i-1,j-1)==joueurCourant.Couleur && lireCouleurDuJeton(i-2,j-2)==joueurCourant.Couleur && lireCouleurDuJeton(i-3,j-3)==joueurCourant.Couleur){
                    result = true;
                }}}
        return result;
    }
    
    public void  tasserGrille(int indColonne){ //creation de la methode qui permet de tasser la grille 
        for(int i=5; i>0; i--){ //boucle qui parcourt les lignes  
            if (cellules[i][indColonne].jetonCourant == null){ //test la presence d'un jeton dans la cellule
                cellules[i][indColonne] = cellules[i--][indColonne]; //si il n'y a plus de jeons, on tase la grille 
            }
        } 
    }
    
    public boolean colonneRemplie(int indColonne){ //creation de la methode qui teste si une colonne est pleine 
        boolean rempli=true;
        if (cellules[0][indColonne].jetonCourant != null){ //test si la cellule tout en haut de la colonne a un jeton 
            rempli = true; //si c'est le cas, retourne vrai 
        } 
        return rempli;
    }
    
    public boolean placerDesintegrateur(int indLigne, int indColonne){ //creation de la methode qui permet d'ajouter un desintegrateur 
        boolean desint;
        if(cellules[indLigne][indColonne].presenceDesintegrateur()==false){ //test la presence d'un desintegrateur 
            cellules[indLigne][indColonne].desintegrateur = true; //si il n'y en a pas, on en ajoute un
            desint = true; //renvoie vrai si desintegrateur bien ajouté
        } else{ desint = false; } //sinon renvoie faux 
        return desint;
    }
    
    public boolean placerTrouNoir(int indLigne, int indColonne){ //creation de la methode qui permet d'ajouter un trou noir 
        boolean tn;
        if(cellules[indLigne][indColonne].presenceTrouNoir()==false){ //test la presence d'un trou noir 
            cellules[indLigne][indColonne].trouNoir = true; //si il n'y en a pas, on en ajoute un
            tn = true; //renvoie vrai si trou noir bien ajouté
        } else{ tn = false; } //sinon renvoie faux 
        return tn;
    }
    
    public boolean supprimerJeton(int indLigne, int indColonne){ //creation de la methode qui permet de supprimer un jeton
        boolean supp;
        if(cellules[indLigne][indColonne].jetonCourant != null){ //test la presence du jeton
            cellules[indLigne][indColonne].jetonCourant = null; //si u jeton est présent, on le supprime 
            supp=true; //renvoie vrai si jeton bien supprimé 
        } else{ supp =false; } //sinon renvoie faux 
      return supp;
    }
    
    
    public Jeton recupererJeton(int indLigne, int indColonne){ //creation de la methode qui permet de recuperer un jeton
        if(cellules[indLigne][indColonne].jetonCourant != null){ //test la presence du jeton dans la cellule 
            cellules[indLigne][indColonne].jetonCourant = null; //si jeton present, on le supprime 
        }
        return cellules[indLigne][indColonne].jetonCourant; //renvoie reference vers jeton 
    }
}

