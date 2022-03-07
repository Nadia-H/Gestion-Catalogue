package presentation;

import metier.entity.Categorie;
import metier.entity.Produit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModele_Categorie extends AbstractTableModel {

    private List<Categorie> list ;
    private String titres[] = {"Id","Nom", "Description"};

    public TableModele_Categorie () {
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
                return list.get(l).getDescription();
        }
        return null;

    }
    public String getColumnName(int column) {
        return titres[column];
    }

    public void charger(List<Categorie> liste) {
        this.list = liste;
        fireTableDataChanged();
    }
}
