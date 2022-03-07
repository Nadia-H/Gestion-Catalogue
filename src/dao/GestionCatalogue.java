package dao;

import metier.entity.Categorie;
import metier.entity.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionCatalogue implements IDaoCatalogue{
    Connection connection = SingletonConnection.getCnx();

    @Override
    public Produit ajouterProduit(Produit p) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into produit (nom, prix_unit, quantite_stck, categorie) values(?, ?, ?, ?)");
            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrix_unit());
            ps.setInt(3, p.getQuantite_stck());
            ps.setString(4, p.getCategorie());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Categorie ajouterCategorie(Categorie c) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into categorie (nom, description) values(?, ?)");
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
//cette méthode génère une erreur
    @Override
    public Produit modifierProduit(Produit p) {
        try (PreparedStatement verif = connection.prepareStatement("Select nom, prix_unit, quantite_stck, categorie from produit where id = ?")) {
            verif.setInt(1, p.getId());
            System.out.println("verif okk");
            try{
                System.out.println("verif okk");
                PreparedStatement ps = connection.prepareStatement("Update produit set nom = ?, prix_unit = ? quantite_stck = ?, categorie = ? where id = ?");
                ps.setString(1, p.getNom());
                ps.setDouble(2, p.getPrix_unit());
                ps.setInt(3, p.getQuantite_stck());
                ps.setString(4, p.getCategorie());
                ps.setInt(5, p.getId());
                ps.executeUpdate();
                System.out.println("verif okk");
                } catch (SQLException sql) {
                    sql.printStackTrace();
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
//Cette méthode génère une erreur: to be continued
    @Override
    public Categorie modifierCategorie(Categorie c) {
        try (PreparedStatement verif = connection.prepareStatement("Select nom, description from categorie where id = ?")) {
            verif.setInt(1, c.getId());
            try (ResultSet rs = verif.executeQuery()) {
                /*String nom = rs.getString("nom");
                String cat = rs.getString("description");*/
                try {
                    PreparedStatement ps = connection.prepareStatement("Update categorie set nom = ?, description = ? where id = ?");
                    ps.setString(1, c.getNom());
                    ps.setString(2, c.getDescription());
                    ps.setInt(3, c.getId());
                    ps.executeUpdate();
                } catch (SQLException sql) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (SQLException ex){
        }
        return null;
    }

    @Override
    public List<Produit> listeProd() {
        List<Produit> produitList = new ArrayList<Produit>();
        try {
            PreparedStatement ps2 = connection.prepareStatement("UPDATE produit SET categorie = \"null\" where categorie_id is null");
            ps2.executeUpdate();
            PreparedStatement ps = connection.prepareStatement("Select * from produit");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Produit p = new Produit(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5) );
                produitList.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return produitList;
    }

    @Override
    public List<Categorie> listeCat() {
        List<Categorie> categorieList = new ArrayList<Categorie>();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from categorie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Categorie c = new Categorie(rs.getInt(1),
                        rs.getString(2),rs.getString(3));
                categorieList.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return categorieList;
    }

    public List<String> listeNomCat() {
        List<String> list = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from categorie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Categorie c = new Categorie(rs.getInt(1),
                        rs.getString(2), rs.getString(3));
                list.add(c.getNom());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Produit> afficherProdSelonCat(String c) {
        List<Produit> produitList = new ArrayList<Produit>();
        try {
            if (c.equals("") || c.equals("All")){
                PreparedStatement ps = connection.prepareStatement("Select * from produit");
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Produit p = new Produit(rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getString(5) );
                    produitList.add(p);
                }
            }
            else{
                PreparedStatement ps = connection.prepareStatement("Select * from produit where categorie = ?");
                ps.setString(1,c);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Produit p = new Produit(rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4),
                            rs.getString(5) );
                    produitList.add(p);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return produitList;
    }

    @Override
    public void supprimerProd(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from produit where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCat(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from categorie where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
