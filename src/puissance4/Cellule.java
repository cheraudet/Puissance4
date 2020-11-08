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
public class Cellule {
    //creation des attributs de la classe cellule 
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule(){ //creation du constructeur qui initialise les attributs 
        jetonCourant = null;
        trouNoir =false;
        desintegrateur = false;
    }
    
    public boolean affecterJeton(Jeton unJeton){ //creation de la methode qui ajoute le jeton en paramètre à la cellule
        boolean a;
        if (jetonCourant != null){ //test si il y a deja un jeton dans la cellule 
            a=false; //retourne faux 
        }
        else{ //sinon ajoute le jeton et retourne vrai si il a bien été ajouté
            jetonCourant = unJeton;
            a = true;
        }
        return a;
    }
    
    public Jeton recupererJeton(){ //creation de la methode qui renvoie une refernce vers le jeton de la cellule
        return jetonCourant;
    }
    
    public boolean supprimerJeton(){ //creation de la methode qui supprime un jeton
        boolean b;
        if(jetonCourant != null){ //test si un jeton est bien present 
            jetonCourant = null; //si c'est le cas le jeton devient nul
            b =true; //retiurne vrai si la suppression a eu lieu
        }else { //sinon retourne faux 
            b=false;
        }
        return b;
    }
    
    public boolean placerTrouNoir(){ //creation de la methode qui permet d'ajouter un trou noir 
        boolean c;
        if(trouNoir == false){ //test si il y a un trou noir 
            trouNoir = true; //s'il n'y en a pas, on en ajoute un 
            c=true; //retourne vrai si l'ajout a bien eu lieu
        }else{ //sinon retourne faux 
            c = false;
        }
        return c;
    }
    
    public boolean placerDesintegrateur(){ //creation de la methode qui permet d'ajouter un désintegrateur 
       boolean d;
       if (desintegrateur == false){ //test si il y a un désintegrateur
           desintegrateur =true; //s'il n'y en a pas, on en ajoute un
           d = true; //retourne vrai si l'ajout a bien eu lieu
       }else{ //sinon retourne faux 
           d =false;
       }
       return d;
    }
    
    public boolean presenceTrouNoir() { //creation de la methode qui teste si il y a un trou noir 
        boolean trou;
        if (trouNoir==true){ //test si il y a un trou noir dans la cellule 
            trou = true; //si c'est le cas on returne vrai 
        }else{ //sinon on retourne faux 
            trou =false;
        }
        return trou;
    }
    
    public boolean presenceDesintegrateur(){ //creation de la methode qui teste si un désintegrateur est present
        boolean desint=true;
        if (desintegrateur == true){ //test si il y a un désintegrateur dans la cellule 
            desint = true; //si c'est le cas, retourne vrai 
        }
        return desint;
    }
    
    public String  lireCouleurDuJeton(){ //creation de la methode qui permet de lire la couleur d'un jeton 
        if(jetonCourant == null){ //test si un jeton est present dans la cellule 
            return "pas de jetons"; //si ce n'est pas le cas, retourne qu'il n'y a pas de jetons
        }
        return jetonCourant.Couleur; //sinon on retourne la couleur du jeton
    }
    
    public boolean recupererDesintegrateur(){ //creation de la methode qui permet de recuperer un desintegrateur 
        boolean recupDesint;
        if(desintegrateur==true){ //test la présence d'un désintegrateur 
            desintegrateur = false; //si il y en a un, on le supprime 
            recupDesint = true; //et on retourne vrai
        } else { //sinon on retourne faux 
            recupDesint = false;
        }
        return recupDesint;
    }
    
    public boolean activerTrouNoir(){ //creation de la methode qui permet d'activer un trou noir 
        boolean activTrou;
        if (trouNoir){ //test la presence du trou noir 
            jetonCourant = null; //si il est present, lejeton est engloutit et disparait  
            trouNoir = false; //on supprime le trou noir 
            activTrou = true; //et on retourne vrai 
        } else { //sinon on retourne faux 
            activTrou = false;
        }
        return activTrou;
    }
}
