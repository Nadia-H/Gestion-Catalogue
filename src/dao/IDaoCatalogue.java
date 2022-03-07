package dao;

import metier.entity.Categorie;
import metier.entity.Produit;

import java.util.List;

public interface IDaoCatalogue {
    List<String> listeNomCat();
    Produit ajouterProduit(Produit p);
    Categorie ajouterCategorie(Categorie c);
    Produit modifierProduit(Produit produit);
    Categorie modifierCategorie(Categorie categorie);
    List<Produit> listeProd();
    List<Categorie> listeCat();
    List<Produit> afficherProdSelonCat(String c);
    void supprimerProd(int id);
    void supprimerCat(int id);
}
