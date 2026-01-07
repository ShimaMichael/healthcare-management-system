package controllers;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ListTableModel<T> extends AbstractTableModel {

    private final String[] columnNames;
    private final List<T> rows;
    private final BiFunction<T, Integer, Object> valueGetter;
    private final Consumer<T> rowUpdater; // used when editing, can be no-op

    public ListTableModel(String[] columnNames,
                          List<T> rows,
                          BiFunction<T, Integer, Object> valueGetter,
                          Consumer<T> rowUpdater) {
        this.columnNames = columnNames;
        this.rows = rows;
        this.valueGetter = valueGetter;
        this.rowUpdater = rowUpdater;
    }

    @Override
    public int getRowCount() { return rows.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T row = rows.get(rowIndex);
        return valueGetter.apply(row, columnIndex);
    }

    public T getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public void addRow(T row) {
        rows.add(row);
        int idx = rows.size() - 1;
        fireTableRowsInserted(idx, idx);
    }

    public void removeRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            return;
        }
        rows.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void updateRow(int rowIndex, T updated) {
        rows.set(rowIndex, updated);
        rowUpdater.accept(updated);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
