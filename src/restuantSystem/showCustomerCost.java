package restuantSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class showCustomerCost extends JFrame implements ActionListener {
    //字体设置
    Font font = new Font("萝莉体", Font.PLAIN, 20);

    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalPriceLabel;//总价格标签
    private FileHandler fileHandler;
    private List<Sales> salesList;

    JButton returnButton = new JButton("返回");

    public showCustomerCost(){
        fileHandler = new FileHandler("CustomerCost");
        this.salesList = fileHandler.readSalesFromFile("CustomerCost");

        //初始化界面
        initJFrame();
        //初始化功能
        initTable();
        initButtons();
        //添加总价格显示
        totalPriceLabel = new JLabel("总销售额: " + calculateTotalPrice());
        totalPriceLabel.setFont(new Font("萝莉体", Font.BOLD, 25));
        add(totalPriceLabel, BorderLayout.NORTH);
        //显示
        this.setVisible(true);

    }


    private void initJFrame() {
        this.setSize(632,640);
        this.setTitle("~餐厅管理系统 v1.2~");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        //取消默认居中位置
        //this.setLayout(null);
    }


    private void initTable() {
        String[] columnNames = {"日期", "销售额"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {//禁止用户进行编辑
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 显示菜单数据
        for (Sales sales : salesList) {
            Object[] rowData = {sales.getDate(),sales.getAccount()};
            tableModel.addRow(rowData);
        }
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel();

        returnButton.addActionListener(this);

        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for(Sales sales : salesList){
            totalPrice += sales.getAccount();
        }
        return totalPrice;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == returnButton){
            new Root();
            this.setVisible(false);
        }
    }

}
