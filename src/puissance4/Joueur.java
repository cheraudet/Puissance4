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
    String Nom;
    String Couleur;
    Jeton ListeJetons [] = new Jeton [21];
    int nombreDesintegrateurs;
    int nombreJetonsRestants;
    
    public Joueur(String unNom){  
        Nom = unNom;
        nombreDesintegrateurs = 0;
        nombreJetonsRestants = 21;
    }
    
    public void affecterCouleur(String uneCouleur){
        Couleur = uneCouleur;
    }
    
    public boolean  ajouterJeton(Jeton unJeton){
        boolean b = true;
        ListeJetons [nombreJetonsRestants++] = unJeton;
        return b;
    }
    
    
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs ++;
    }
    
    public boolean  utiliserDesintegrateur(){
        boolean a = true ;
        if (nombreDesintegrateurs==0){
            a = false;
        }
        nombreDesintegrateurs --;
        return a;
    }
}
