package restuantSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableManagementUI extends JFrame implements ActionListener {
    private TableManager tableManager;

    private JLabel lblTableNumber;
    private JTextField txtTableNumber;
    private JButton btnAssignTable;
    private JButton btnReleaseTable;
    private JButton btnAddTable;
    private JButton btnRemoveTable;
    private JTextArea txtAreaTables;

    public TableManagementUI(TableManager tableManager) {
        this.tableManager = tableManager;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("分配桌子");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        // 创建界面组件
        lblTableNumber = new JLabel("请输入桌子序号:");
        txtTableNumber = new JTextField(20);
        btnAssignTable = new JButton("分配桌子");
        btnReleaseTable = new JButton("释放桌子");
        btnAddTable = new JButton("添加桌子");
        btnRemoveTable = new JButton("移除桌子");
        txtAreaTables = new JTextArea(10, 30);
        txtAreaTables.setEditable(false);

        // 布局管理
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(lblTableNumber);
        panel.add(txtTableNumber);
        panel.add(btnAssignTable);
        panel.add(btnReleaseTable);
        panel.add(btnAddTable);
        panel.add(btnRemoveTable);

        JScrollPane scrollPane = new JScrollPane(txtAreaTables);
        panel.add(scrollPane);

        add(panel);

        // 绑定按钮事件
        btnAssignTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTable();
            }
        });

        btnReleaseTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                releaseTable();
            }
        });

        btnAddTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTable();
            }
        });

        btnRemoveTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTable();
            }
        });
        refreshTableStatus();

        setVisible(true);
    }

    private void refreshTableStatus() {
        txtAreaTables.setText("");
        List<Table> tables = tableManager.getTables();
        for (Table table : tables) {
            txtAreaTables.append("桌子 " + table.getTableNumber() + " - " + (table.isOccupied() ? "正在使用" : "空") + "\n");
        }
    }

    private void assignTable() {
        try {
            int tableNumber = Integer.parseInt(txtTableNumber.getText().trim());
            tableManager.assignTable(tableNumber);
            refreshTableStatus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid table number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void releaseTable() {
        try {
            int tableNumber = Integer.parseInt(txtTableNumber.getText().trim());
            tableManager.releaseTable(tableNumber);
            refreshTableStatus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid table number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTable() {
        try {
            int tableNumber = Integer.parseInt(txtTableNumber.getText().trim());
            tableManager.addTable(tableNumber);
            refreshTableStatus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid table number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeTable() {
        try {
            int tableNumber = Integer.parseInt(txtTableNumber.getText().trim());
            tableManager.removeTable(tableNumber);
            refreshTableStatus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid table number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
