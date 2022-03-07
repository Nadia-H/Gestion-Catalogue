package presentation;

import dao.GestionCatalogue;
import dao.IDaoCatalogue;
import metier.entity.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import static java.lang.Double.valueOf;
/*import java.util.ArrayList;
import java.util.List;*/

public class AjouterProduit extends JFrame {
    IDaoCatalogue gest_catalog = new GestionCatalogue();
    TableModele_Produit tableModele_produit = new TableModele_Produit();

    JPanel jpan = new JPanel(new GridLayout(2,0));
    JPanel jPanel_bout = new JPanel(new FlowLayout());
    JPanel jPanel_n = new JPanel(new FlowLayout());
    JPanel jPanel_p = new JPanel(new FlowLayout());
    JPanel jPanel_q = new JPanel(new FlowLayout());
    JPanel jPanel_c = new JPanel(new FlowLayout());

    JLabel nom = new JLabel("Nom: ");
    JTextField tnom = new JTextField(20);
    JLabel prix_u = new JLabel("Prix Unitaire(TND): ");
    JTextField tprix_u = new JTextField(20);
    JLabel qte_stock = new JLabel("Quantité en Stock: ");
    JTextField tqte_stock = new JTextField(20);
    JLabel cat = new JLabel("Sélectionner la catégorie: ");
    JComboBox<String> jComboBox_c = new JComboBox<>();

    JButton annuler = new JButton("Annuler");
    JButton confirm = new JButton("Confirmer");
    JButton liste_prod = new JButton("Liste des Produits");
    JButton quitter = new JButton("Quitter");

    public AjouterProduit() throws HeadlessException {
        super("Ajouter un produit");
        for (String s:
                gest_catalog.listeNomCat()) {
            jComboBox_c.addItem(s);
        }

        jPanel_n.add(nom);
        jPanel_n.add(tnom);
        jPanel_p.add(prix_u);
        jPanel_p.add(tprix_u);
        jPanel_q.add(qte_stock);
        jPanel_q.add(tqte_stock);
        jPanel_c.add(cat);
        jPanel_c.add(jComboBox_c);
        jpan.add(jPanel_n);
        jpan.add(jPanel_p);
        jpan.add(jPanel_q);
        jpan.add(jPanel_c);
        jpan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),
                "Informations du produit")));
        jPanel_bout.add(annuler);
        jPanel_bout.add(confirm);
        jPanel_bout.add(liste_prod);
        jPanel_bout.add(quitter);

        this.setLayout(new GridLayout(2,0,10,0));
        add(jpan);
        add(jPanel_bout);

        this.setVisible(true);
        this.setSize(800,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        annuler.addActionListener(e -> {
            tnom.setText("");
            tprix_u.setText("");
            tqte_stock.setText("");
            this.dispose();
            new AjouterProduit();
        });

        confirm.addActionListener(e -> {
            try{
                String nom = tnom.getText();
                NumberFormat nf = NumberFormat.getInstance();
                double prix_u = nf.parse(tprix_u.getText()).doubleValue();
                int qte_stock = Integer.parseInt(tqte_stock.getText());
                String categorie = (String) jComboBox_c.getSelectedItem();
                if(nom.equals("")|| prix_u == 0.0||qte_stock ==0)
                    JOptionPane.showMessageDialog(this, "Veuillez remplir les champs!");
                else
                {   int res = JOptionPane.showConfirmDialog(this,
                        "Produit: "+nom+"\nPrix Unit:"+prix_u+"\nQte en Stock: "+qte_stock+"\n\nVoulez vous ajouter ce produit?",
                        "Ajouter produit",
                        JOptionPane.YES_NO_OPTION);
                    if(res == 0) {
                            Produit p = new Produit(nom, prix_u, qte_stock, categorie);
                            gest_catalog.ajouterProduit(p);
                            new PresentationTableProduit();
                            this.dispose();
                    }
                }
            }catch (NumberFormatException et1){
                //JOptionPane.showMessageDialog(this, "Veuillez remplir les champs!");
                //et1.printStackTrace();
            }
            catch(ParseException et){
                JOptionPane.showMessageDialog(this, "Veuillez remplir les champs!");
                //et.printStackTrace();
            }
        });

        liste_prod.addActionListener(e -> {
            tableModele_produit.charger(gest_catalog.listeProd());
            new PresentationTableProduit();
            this.dispose();
        });

        quitter.addActionListener(e -> {
            this.dispose();
        });

        //Ce block ne fonctionne pas. Il est sensé empécher l'entrer des lettres du clavier
        tprix_u.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if (key.getKeyChar() >= '0' && key.getKeyChar() <= '9' || key.getKeyChar() == '.')  {
                    tprix_u.setEditable(true);
                } else {
                    tprix_u.setEditable(false);
                }
            }
        });
        //Ce block fonctionne. Il est sensé empécher l'entrer des lettres du clavier
        tqte_stock.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if (key.getKeyChar() >= '0' && key.getKeyChar() <= '9')  {
                    tqte_stock.setEditable(true);
                } else {
                    tqte_stock.setEditable(false);
                }
            }
        });

    }

    public static void main(String[] args){
        new AjouterProduit();
    }

}
