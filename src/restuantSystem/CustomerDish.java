package restuantSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerDish extends JFrame implements ActionListener {

    //显示菜谱信息
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Dish> dishes;
    private List<Sales> salesCost = new ArrayList<>();
    private FileHandler fileHandler;
    private JLabel totalPriceLabel;//总价格标签

    JButton returnButton = new JButton("返回");
    JButton pay = new JButton("结算");

    public CustomerDish(String filePath) {
        //赋值
        this.fileHandler = new FileHandler(filePath);
        this.dishes = fileHandler.readDishesFromFile();

        //初始化界面
        this.setSize(632, 640);
        this.setTitle("~餐厅管理系统 v1.2~");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        //取消默认居中位置
        //this.setLayout(null);;

        //初始化功能
        initTable();
        initButtons();

        // 添加总价格显示
        totalPriceLabel = new JLabel("总价格: " + calculateTotalPrice());
        totalPriceLabel.setFont(new Font("萝莉体", Font.BOLD, 25));
        add(totalPriceLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void initTable() {
        String[] columnNames = {"序号", "菜名", "价格"};
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
        for (Dish dish : dishes) {
            Object[] rowData = {dish.getId(), dish.getName(), dish.getPrice()};
            tableModel.addRow(rowData);
        }
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel();

        returnButton.addActionListener(this);
        pay.addActionListener(this);

        buttonPanel.add(returnButton);
        buttonPanel.add(pay);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == returnButton) {
            new Menu("menu.txt");
            this.setVisible(false);
        }else if(obj == pay) {
            //结算逻辑，把时间金额存入文件
            double money = calculateTotalPrice();
            LocalDate date = LocalDate.now();
            Sales sales = new Sales(date,money);
            salesCost.add(sales);
            fileHandler.writeSalesToFile(salesCost,"CustomerCost");
            //弹出提示框
            JDialog jDialog = new JDialog();//弹框对象
            jDialog.setTitle("提示~");
            JLabel goodbye = new JLabel("结算成功~欢迎下次光临~");
            goodbye.setFont(new Font("萝莉体", Font.PLAIN, 18));
            goodbye.setBounds(0,0,500,300);
            jDialog.getContentPane().add(goodbye);
            jDialog.setSize(300,250);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);//弹框不关闭无法操作下面界面
            jDialog.setVisible(true);
            new Menu("menu.txt");
            this.setVisible(false);
            //清空当前购物单
            try (FileOutputStream fos = new FileOutputStream("CustomerDishes.txt")) {
                // 清空文件内容
                fos.getChannel().truncate(0);
            } catch (IOException E) {
                E.printStackTrace();
            }
        }
    }
}
