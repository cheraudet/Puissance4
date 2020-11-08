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
public class Jeton { 
    //creation de l'attribut de la classe jeton
    String Couleur;
    
    public Jeton (String uneCouleur){ // creation du constructeur qui initialise la couleur du jeton
        Couleur = uneCouleur;
    }
    
    public String lireCouleur(){ //creation de la methode permettant de lire la couleur du jeton
        return Couleur; 
    }
   
}
