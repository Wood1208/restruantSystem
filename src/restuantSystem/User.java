package restuantSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame implements ActionListener {
    //菜单路径
    private String filePath = "menu.txt";
    TableManager tableManager = new TableManager("tables.txt");

    JButton table = new JButton("餐桌信息");
    JButton menu = new JButton("甜点信息");
    JButton returnButton = new JButton("返回");

    public User() {
        //初始化界面
        initJFrame();

        //初始化图形
        initView();

        //设置可见
        this.setVisible(true);

    }

    private void initJFrame() {
        this.setSize(632,640);
        this.setTitle("~餐厅管理系统 v1.2~");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        //取消默认居中位置
        this.setLayout(null);
    }

    private void initView() {
        Font font = new Font("萝莉体", Font.PLAIN, 15);

        //欢迎语
        JLabel usernameText = new JLabel("欢迎使用甜点屋系统~");
        usernameText.setBounds(0,0,500,50);
        Font usernameFont = new Font("萝莉体", Font.PLAIN, 30);
        usernameText.setFont(usernameFont);
        this.getContentPane().add(usernameText);

        //餐桌信息按钮
        table.setBounds(100,300,100,40);
        table.setFont(font);
        table.setBackground(Color.pink);
        table.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        table.addActionListener(this);
        this.getContentPane().add(table);

        //菜谱信息按钮
        menu.setBounds(250,300,100,40);
        menu.setBackground(Color.pink);
        menu.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        menu.setFont(font);
        menu.addActionListener(this);
        this.getContentPane().add(menu);

        //返回按钮
        returnButton.setBounds(0,550,90,60);
        returnButton.setBackground(Color.pink);
        returnButton.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        returnButton.setFont(font);
        returnButton.addActionListener(this);
        this.getContentPane().add(returnButton);


        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("Img\\background.png"));
        background.setBounds(0,0,632,640);
        this.getContentPane().add(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == table) {
            new TableManagementUI(tableManager);
            this.setVisible(false);
        }else if(obj == menu) {
            new Menu(filePath);
            this.setVisible(false);
        }else if(obj == returnButton) {
            new RestaurantSystem();
            this.setVisible(false);
        }
    }
}
