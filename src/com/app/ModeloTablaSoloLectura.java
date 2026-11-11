package com.app;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaSoloLectura extends DefaultTableModel {
    public ModeloTablaSoloLectura(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
