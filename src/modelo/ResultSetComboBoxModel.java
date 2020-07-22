package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ResultSetComboBoxModel implements ComboBoxModel {

    private Vector<ResultSetComboBoxModelObject> data = new Vector<ResultSetComboBoxModelObject>();
    private Vector<ListDataListener> list = new Vector<ListDataListener>();
    private ResultSetComboBoxModelObject selectedItem;

  
    
    
    public ResultSetComboBoxModel(ResultSet r, String codigo, String descri) throws SQLException {
        while (r.next()) {
            data.add(new ResultSetComboBoxModelObject(r.getInt(codigo), r.getString(descri)));
        }
    }

    public ResultSetComboBoxModelObject searchSelectedItem(Integer i) {
        for (ResultSetComboBoxModelObject o : data) {
            if (i.equals(o.getCodigo())) {
                return o;
            }
        }
        return null;
    }

    public ResultSetComboBoxModelObject searchSelectedItem(String s) {
        for (ResultSetComboBoxModelObject o : data) {
            if (s.equals(o.getDescri())) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem instanceof ResultSetComboBoxModelObject ? (ResultSetComboBoxModelObject) anItem : null;
        list.forEach((l) -> {
            l.contentsChanged(new ListDataEvent(this, javax.swing.event.ListDataEvent.CONTENTS_CHANGED, 0, 0));
        });
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Object getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        list.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        list.remove(l);
    }

    public Integer getSelectedCodigo() {
        return selectedItem == null ? null : selectedItem.getCodigo();
    }

    public String getSelectedDescri() {
        return selectedItem == null ? null : selectedItem.getDescri();
    }
}