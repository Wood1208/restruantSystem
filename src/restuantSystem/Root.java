package restuantSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Root extends JFrame implements ActionListener {

    JButton CheckMenu = new JButton("查看甜点信息");
    JButton CheckCustomer = new JButton("查看消费记录");
    JButton returnButton = new JButton("返回");
    private String filePath = "menu.txt";

    public Root() {
        //初始化界面
        initJFrame();
        //初始化图形
        initView();
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
        this.setLayout(null);
    }

    private void initView(){

        //字体设置
        Font font = new Font("萝莉体", Font.PLAIN, 15);

        //欢迎语
        JLabel usernameText = new JLabel("欢迎使用甜点屋系统~");
        usernameText.setBounds(0,0,500,50);
        Font usernameFont = new Font("萝莉体", Font.PLAIN, 30);
        usernameText.setFont(usernameFont);
        this.getContentPane().add(usernameText);

        //菜品信息查看按钮
        CheckMenu.setBounds(100,300,150,40);
        CheckMenu.setBackground(Color.pink);
        CheckMenu.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        CheckMenu.setFont(font);
        CheckMenu.addActionListener(this);
        this.getContentPane().add(CheckMenu);

        //顾客信息查看按钮
        CheckCustomer.setBounds(400,300,150,40);
        CheckCustomer.setBackground(Color.pink);
        CheckCustomer.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        CheckCustomer.setFont(font);
        CheckCustomer.addActionListener(this);
        this.getContentPane().add(CheckCustomer);

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
        if(obj == CheckMenu){
            new MenuManagementSystem(filePath);
            this.setVisible(false);
        }else if(obj == CheckCustomer){
            new showCustomerCost();
            this.setVisible(false);
        }else if(obj == returnButton) {
            new RestaurantSystem();
            this.setVisible(false);
        }
    }
}
