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
public class Joueur {
    //création des attributs de la classe Joueur
    String Nom;
    String Couleur;
    Jeton ListeJetons [] = new Jeton [21];
    int nombreDesintegrateurs;
    int nombreJetonsRestants;
    
    public Joueur(String unNom){ //creation du constructeur qui initialise le nom du joueur
        Nom = unNom;
        nombreDesintegrateurs = 0;
        nombreJetonsRestants = 0;
    }
    
    public void affecterCouleur(String uneCouleur){ //creation de la méthode pour affecter une couleur a un joueur
        Couleur = uneCouleur;
    }
    
    public boolean  ajouterJeton(Jeton unJeton){ //creation de la methode qui ajoute le jeton en parametre a la liste des jetons
        ListeJetons [nombreJetonsRestants++] = unJeton;
        return true; //retourne vrai quand le jeton est ajouté
    }
    
    public boolean enleverJeton(Jeton unJeton){
        ListeJetons [nombreJetonsRestants--] = unJeton; //creation de la methode qui enleve le jeton en parametre a la liste des jetons
        return true; //retourne vrai quand le jeton est enlevé
    }
    
    public void obtenirDesintegrateur(){ //creation de la methode qui incrémente le nb de désintegrateurs du joueur
        nombreDesintegrateurs ++;
    }
    
    public boolean  utiliserDesintegrateur(){ //creation de la methode pour que le joueur puisse utiliser ses désintégrateurs 
        boolean a = true ;
        if (nombreDesintegrateurs==0){ //test si le joueur n'a pas de désintégrateurs
            a = false; //methode retourne faux 
        }
        nombreDesintegrateurs --; //sinon le nombre de desintegrateurs du joueurs est décrémenté
        return a; //et la méthode retourne vrai, ce qui confirme l'utilisation du désintégrateur
    }
}
