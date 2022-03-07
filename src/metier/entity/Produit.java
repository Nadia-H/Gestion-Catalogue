package metier.entity;

import java.util.Objects;

public class Produit {
    private int id;
    private String nom;
    private double prix_unit;
    private int quantite_stck;
    private String categorie;
    private static int nb;

    public Produit(int id, String nom, double prix_unit, int quantite_stck, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prix_unit = prix_unit;
        this.quantite_stck = quantite_stck;
        this.categorie = categorie;
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(String nom, double prix_unit, int quantite_stck, String categorie) {
        this.id = ++nb;
        this.nom = nom;
        this.prix_unit = prix_unit;
        this.quantite_stck = quantite_stck;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix_unit() {
        return prix_unit;
    }

    public void setPrix_unit(double prix_unit) {
        this.prix_unit = prix_unit;
    }

    public int getQuantite_stck() {
        return quantite_stck;
    }

    public void setQuantite_stck(int quantite_stck) {
        this.quantite_stck = quantite_stck;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return getId() == produit.getId() && Double.compare(produit.getPrix_unit(), getPrix_unit()) == 0 && getQuantite_stck() == produit.getQuantite_stck() && Objects.equals(getNom(), produit.getNom()) && Objects.equals(getCategorie(), produit.getCategorie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrix_unit(), getQuantite_stck(), getCategorie());
    }

}
