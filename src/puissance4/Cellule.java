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
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule(){
        jetonCourant = null;
        trouNoir =false;
        desintegrateur = false;
    }
    
    public boolean affecterJeton(Jeton nouveauJeton){
        boolean a;
        if (jetonCourant != null){
            a=false;
        }
        else{
            jetonCourant = nouveauJeton;
            a = true;
        }
        return a;
    }
    
    public Jeton recupererJeton(){
        return jetonCourant;
    }
    
    public boolean supprimerJeton(){
        boolean b;
        if(jetonCourant != null){
            jetonCourant = null;
            b =true;
        }else {
            b=false;
        }
        return b;
    }
    
    public boolean placerTrouNoir(){
        boolean c;
        if(trouNoir == false){
            trouNoir = true;
            c=true;
        }else{
            c = false;
        }
        return c;
    }
    
    public boolean placerDesintegrateur(){
       boolean d;
       if (desintegrateur == false){
           desintegrateur =true;
           d = true;
       }else{
           d =false;
       }
       return d;
    }
    
    public boolean presenceTrouNoir() {
        boolean trou;
        if (trouNoir==true){
            trou = true;
        }else{
            trou =false;
        }
        return trou;
    }
    
    public boolean presenceDesintegrateur(){
        boolean desint=true;
        if (desintegrateur == true){
            desint = true;
        }
        return desint;
    }
    
    public String  lireCouleurDuJeton(){
        if(jetonCourant == null){
            return "pas de jetons";
        }
        return jetonCourant.Couleur;
    }
    
    public boolean recupererDesintegrateur(){
        boolean recupDesint;
        if(desintegrateur==true){
            desintegrateur = false;
            recupDesint = true;
        } else {
            recupDesint = false;
        }
        return recupDesint;
    }
    
    public boolean activerTrouNoir(){
        boolean activTrou;
        if (trouNoir == true){
            jetonCourant = null;
            trouNoir = false;
            activTrou = true;
        } else {
            activTrou = false;
        }
        return activTrou;
    }
}
