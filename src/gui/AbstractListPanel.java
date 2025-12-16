package gui;
import javax.swing.*;
import java.awt.*;

public abstract class AbstractListPanel extends JPanel {

    protected JTable table;
    protected JButton btnAdd;
    protected JButton btnEdit;
    protected JButton btnDelete;

    public AbstractListPanel(String title) {
        setLayout(new BorderLayout());

        table = new JTable(); // controller will set model
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createTitledBorder(title));
    }

    public JTable getTable() { return table; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
}
