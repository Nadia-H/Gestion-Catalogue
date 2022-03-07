package presentation;

import dao.GestionCatalogue;
import dao.IDaoCatalogue;
import metier.entity.Categorie;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AjouterCategorie extends JFrame{
    IDaoCatalogue gest_catalog = new GestionCatalogue();
    TableModele_Categorie tableModele_categorie = new TableModele_Categorie();

    JPanel jpan = new JPanel(new GridLayout(2,0));
    JPanel jPanel = new JPanel(new FlowLayout());
    JPanel jPanel1 = new JPanel(new FlowLayout());
    JPanel jPanel2 = new JPanel(new FlowLayout());


    JLabel nom = new JLabel("Nom: ");
    JTextField tnom = new JTextField(20);
    JLabel desc = new JLabel("Description: ");
    JTextField tdesc = new JTextField(20);

    JButton annuler = new JButton("Annuler");
    JButton confirm = new JButton("Confirmer");
    JButton liste_cat = new JButton("Liste des Catégories");
    JButton quitter = new JButton("Quitter");

    public AjouterCategorie() throws HeadlessException {
        super("Ajouter une catégorie");
        jPanel1.add(nom);
        jPanel1.add(tnom);
        jPanel2.add(desc);
        jPanel2.add(tdesc);
        jpan.add(jPanel1);
        jpan.add(jPanel2);
        jpan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),
                "Informations de la Catégorie")));
        jPanel.add(annuler);
        jPanel.add(confirm);
        jPanel.add(liste_cat);
        jPanel.add(quitter);

        this.setLayout(new GridLayout(2,0,5,0));
        add(jpan);
        add(jPanel);


        this.setVisible(true);
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        annuler.addActionListener(e -> {
            tnom.setText("");
            tdesc.setText("");
        });

        confirm.addActionListener(e -> {
            String nom = tnom.getText();
            String desc = tdesc.getText();
            if(nom.equals("")||tdesc.equals(""))
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs!");
            else
            {   int res = JOptionPane.showConfirmDialog(this,
                    "Catégorie: "+nom+"\nDescription: "+desc+"\n\nVoulez vous ajouter cette catégorie?",
                    "Ajouter catégorie",
                    JOptionPane.YES_NO_OPTION);
                if(res == 0) {
                    Categorie categorie = new Categorie(nom, desc);
                    gest_catalog.ajouterCategorie(categorie);
                    //System.out.println(categorie.getNom()+"\n"+categorie.getDescription());
                    new PresentationTableCategorie();
                    this.dispose();
                }
            }
        });

        liste_cat.addActionListener(e -> {
            new PresentationTableCategorie();
            this.dispose();
        });

        quitter.addActionListener(e -> {
            this.dispose();
        });

        tnom.addKeyListener(new EcouteurClavierAdapter());
        tdesc.addKeyListener(new EcouteurClavierAdapter());

    }

    public static void main(String[] args){
        new AjouterCategorie();
    }

    class EcouteurClavier implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }
    class EcouteurClavierAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            int key = e.getKeyChar();
            if((key >= 'a' && key <= 'z') || (key >= 'A'&& key <= 'Z') || key ==' ' || key ==','|| key == '\''|| key =='é'|| key =='è');
            else
                e.consume();
        }
    }
}
