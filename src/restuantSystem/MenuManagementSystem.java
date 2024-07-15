package restuantSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuManagementSystem extends JFrame implements ActionListener {
    //显示菜谱信息
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Dish> dishes;
    private FileHandler fileHandler;
    private JFileChooser fileChooser = new JFileChooser();//文件选择器

    JButton addButton = new JButton("添加菜品");
    JButton deleteButton = new JButton("删除菜品");
    JButton returnButton = new JButton("返回");

    public MenuManagementSystem(String filePath) {
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

        setVisible(true);
    }

    private void initTable() {
        String[] columnNames = {"序号", "菜名", "价格"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

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

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        returnButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String openImage(){
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == addButton) {
            //弹出对话框
            String name = JOptionPane.showInputDialog(this, "请输入菜名:");
            if(name != null){
                String priceStr = JOptionPane.showInputDialog(this, "请输入价格:");
               if(priceStr != null){
                   String imgPath = openImage();
                   //检查输入是否有效
                   if (name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty()) {
                       double price = Double.parseDouble(priceStr);//把字符串转换成double
                       int nextId = dishes.isEmpty() ? 1 : dishes.get(dishes.size() - 1).getId() + 1;
                       Dish newDish = new Dish(nextId, name, price,imgPath);
                       dishes.add(newDish);//将新菜品添加到dishes列表
                       tableModel.addRow(new Object[]{newDish.getId(), newDish.getName(), newDish.getPrice()});//添加到数据模型
                       fileHandler.writeDishesToFile(dishes);//写入文件
                   }
               }
            }


        } else if (obj == deleteButton) {
            //获取用户当前选择的表格行
            int selectedRow = table.getSelectedRow();
            //确定用户已经选择表格行
            if (selectedRow != -1) {
                int dishId = (int) table.getValueAt(selectedRow, 0);//获取菜品ID
                for (Dish dish : dishes) {
                    if (dish.getId() == dishId) {
                        dishes.remove(dish);
                        break;
                    }
                }
                tableModel.removeRow(selectedRow);//从数据模型中移除
                fileHandler.writeDishesToFile(dishes);//写入文件
            }
        }else if(obj == returnButton){
            new Root();
            this.setVisible(false);
        }
    }
}

