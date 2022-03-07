package presentation;

import dao.GestionCatalogue;
import dao.IDaoCatalogue;
import metier.entity.Categorie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PresentationTableCategorie extends JFrame{
    IDaoCatalogue gest_catalog = new GestionCatalogue();
    TableModele_Categorie tableModele_categorie = new TableModele_Categorie();
    JTable table = new JTable(tableModele_categorie);
    JScrollPane jsp = new JScrollPane(table);

    JPanel jPanel2 = new JPanel(new GridLayout(1,1));
    JPanel jPanel3 = new JPanel(new FlowLayout());
    JPanel jPanel31 = new JPanel(new FlowLayout());

    JButton mod_cat = new JButton("Modifier la Catégorie");
    JButton sup_cat = new JButton("Supprimer");
    JButton ajout_cat = new JButton("Ajouter nouvelle catégorie");
    JButton liste_prod = new JButton("Voir tout les Produits");
    JButton quitter = new JButton("Quitter");

    int numrow = -1;

    public PresentationTableCategorie() throws HeadlessException {
        super("Liste des Categories");
        jPanel2.add(jsp);
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        jPanel3.add(mod_cat);
        jPanel3.add(sup_cat);
        jPanel3.add(ajout_cat);
        jPanel3.add(liste_prod);
        jPanel3.add(quitter);
        jPanel31.add(jPanel3);

        this.setLayout(new GridLayout(2,0));
        add(jPanel2);
        add(jPanel31);

        this.setVisible(true);
        this.setSize(700,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        table.addMouseListener(new EcouteurSourisAdapter());

        sup_cat.addActionListener(e -> {
            System.out.println(numrow);
            if(numrow == -1)
                JOptionPane.showMessageDialog(this, "selectionnez la catégorie à supprimer");
            else{
                int res = JOptionPane.showConfirmDialog(this, "voulez vous supprimez cette catégorie ?");
                if(res == 0) {
                    int id = (int) tableModele_categorie.getValueAt(numrow, 0);
                    gest_catalog.supprimerCat(id);
                    tableModele_categorie.charger(gest_catalog.listeCat());
                    numrow = -1;
                }
            }
        });
        mod_cat.addActionListener(e -> {//ce action listener ne fonctionne pas c
            System.out.println(numrow);
            if(numrow == -1)
                JOptionPane.showMessageDialog(this, "selectionnez la catégorie à modifier");
            else{
                int id = (int) tableModele_categorie.getValueAt(numrow, 0);
                System.out.println(id);
                new ModifierCategorie();
                this.dispose();
            }

        });

        ajout_cat.addActionListener(e -> {
            new AjouterCategorie();
            this.dispose();
        });

        liste_prod.addActionListener(e -> {
            new PresentationTableProduit();
            this.dispose();
        });

        quitter.addActionListener(e -> {
            this.dispose();
        });

        tableModele_categorie.charger(gest_catalog.listeCat());
    }


    public static void main(String[] args){
        new PresentationTableCategorie();
    }


    class EcouteurSouris implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

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

