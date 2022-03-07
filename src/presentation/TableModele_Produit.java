package presentation;

import metier.entity.Produit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModele_Produit extends AbstractTableModel {

    private List<Produit> list ;
    private String[] titres = {"Id","Nom","Prix Unitaire(TND)","Quantité en Stock","Catégorie"};

    public TableModele_Produit () {
        list = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }
    @Override
    public int getColumnCount() {
        return titres.length;
    }
    @Override
    public Object getValueAt(int l, int c) {
        switch(c) {
            case 0:
                return list.get(l).getId();
            case 1:
                return list.get(l).getNom();
            case 2:
                return list.get(l).getPrix_unit();
            case 3:
                return list.get(l).getQuantite_stck();
            case 4:
                return list.get(l).getCategorie();
        }
        return null;

    }
    public String getColumnName(int column) {
        return titres[column];
    }

    public void charger(List<Produit> liste) {
        this.list = liste;
        fireTableDataChanged();
    }
}
