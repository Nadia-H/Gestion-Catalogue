package presentation;

import dao.GestionCatalogue;
import dao.IDaoCatalogue;
import metier.entity.Categorie;
import metier.entity.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class PresentationTableProduit extends JFrame {
    IDaoCatalogue gest_catalog = new GestionCatalogue();
    TableModele_Produit tableModele_produit = new TableModele_Produit();
    JTable table = new JTable(tableModele_produit);
    JScrollPane jsp = new JScrollPane(table);

    JPanel jPanel1 = new JPanel(new FlowLayout());
    JPanel jPanel11 = new JPanel(new FlowLayout());
    JPanel jPanel2 = new JPanel(new GridLayout(1,1));
    JPanel jPanel3 = new JPanel(new FlowLayout());
    JPanel jPanel31 = new JPanel(new FlowLayout());
    JPanel jPanel32 = new JPanel(new FlowLayout());

    JLabel jLabel = new JLabel("Sélectionner la catégorie: ");
    JComboBox jComboBox = new JComboBox();

    JButton ajout_cat = new JButton("Ajouter une Catégorie");
    JButton mod_prod = new JButton("Modifier ce produit");
    JButton sup_prod = new JButton("Supprimer");
    JButton ajout_prod = new JButton("Ajouter nouveau produit");
    JButton liste_cat = new JButton("Liste des Catégories");
    JButton quitter = new JButton("Quitter");


    int numrow = -1;

    public PresentationTableProduit() throws HeadlessException {
        super("Rechercher des Produits");

        List<String> categories = new ArrayList<>();
        String ss = "All";
        categories.add(ss);
        for (String s:
                gest_catalog.listeNomCat()) {
            categories.add(s);
        }
        for (String s:
                categories) {
                jComboBox.addItem(s);
        }
        jPanel1.add(jLabel);
        jPanel1.add(jComboBox);
        jPanel11.add(jPanel1);
        jPanel2.add(jsp);
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        jPanel3.add(mod_prod);
        jPanel3.add(sup_prod);
        jPanel3.add(ajout_prod);
        jPanel3.add(liste_cat);
        jPanel3.add(ajout_cat);
        jPanel32.add(quitter);
        jPanel31.add(jPanel3);
        jPanel31.add(jPanel32);

        this.setLayout(new GridLayout(3,1));
        add(jPanel11);
        add(jPanel2);
        add(jPanel31);

        this.setVisible(true);
        this.setSize(750,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        table.addMouseListener(new EcouteurSourisAdapter());
        sup_prod.addActionListener(e -> {
            System.out.println(numrow);
            if(numrow == -1)
                JOptionPane.showMessageDialog(this, "selectionnez le produit à supprimer");
            else{
                int res = JOptionPane.showConfirmDialog(this, "voulez vous supprimez ce produit ?");
                if(res == 0){
                    int id = (int) tableModele_produit.getValueAt(numrow, 0);
                    gest_catalog.supprimerProd(id);
                    tableModele_produit.charger(gest_catalog.afficherProdSelonCat("All"));
                    numrow = -1;
                }
            }
        });
        mod_prod.addActionListener(e -> {
            System.out.println(numrow);
            if(numrow == -1)
                JOptionPane.showMessageDialog(this, "selectionnez le produit à modifier");
            else{
                int id = (int) tableModele_produit.getValueAt(numrow, 0);
                String nom_p = (String) tableModele_produit.getValueAt(numrow, 1);
                double prix = (double) tableModele_produit.getValueAt(numrow, 2);
                int qte = (int) tableModele_produit.getValueAt(numrow, 3);
                String cat = (String) tableModele_produit.getValueAt(numrow, 4);
                System.out.println(id);
                new ModifierProduit(id);
                this.dispose();
            }

        });

        ajout_prod.addActionListener(e -> {
            new AjouterProduit();
            this.dispose();
        });

        liste_cat.addActionListener(e -> {
            new PresentationTableCategorie();
            this.dispose();
        });

        quitter.addActionListener(e -> {
            this.dispose();
        });

        ajout_cat.addActionListener(e -> {
            new AjouterCategorie();
            this.dispose();
        });
        jComboBox.addActionListener(e -> {
            String cat = (String) jComboBox.getSelectedItem();
            tableModele_produit.charger(gest_catalog.afficherProdSelonCat(cat));
        });

        tableModele_produit.charger(gest_catalog.listeProd());
    }

    public static void main(String[] args){
        new PresentationTableProduit();
    }


    class EcouteurSouris implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }
    }

    class EcouteurSourisAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            numrow = table.getSelectedRow();
            System.out.println(numrow);
        }

    }
}
