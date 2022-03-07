package dao;

import metier.entity.Categorie;

public class Test {
    public static void main(String[] args){
        GestionCatalogue gest = new GestionCatalogue();
        Categorie cat = new Categorie("Epices", "everything");
        
        gest.ajouterCategorie(cat);
        System.out.println(gest.listeNomCat());
        System.out.println("----------------------------------");

    }
}
