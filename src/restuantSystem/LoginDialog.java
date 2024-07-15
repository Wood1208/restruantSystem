package restuantSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JFrame implements ActionListener {
    Font font = new Font("微软雅黑", Font.PLAIN, 20);

    JButton loginButton = new JButton("登录");
    JButton returnButton = new JButton("返回");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    private String username = "admin";
    private String password = "admin";

    public LoginDialog() {
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

        //欢迎语
        JLabel Text = new JLabel("欢迎使用甜点屋系统~");
        Text.setBounds(0,0,500,50);
        Font usernameFont = new Font("萝莉体", Font.PLAIN, 30);
        Text.setFont(usernameFont);
        this.getContentPane().add(Text);

        //登录按钮
        loginButton.setBounds(140,550,100,50);
        loginButton.setBackground(Color.pink);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        loginButton.setFont(font);
        loginButton.addActionListener(this);
        this.getContentPane().add(loginButton);

        //返回按钮
        returnButton.setBounds(20,550,100,50);
        returnButton.setBackground(Color.pink);
        returnButton.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        returnButton.setFont(font);
        returnButton.addActionListener(this);
        this.getContentPane().add(returnButton);

        JLabel username = new JLabel("用户名：");
        JLabel password = new JLabel("密码：");
        username.setFont(font);
        password.setFont(font);
        username.setBounds(130,200,100,50);
        password.setBounds(150,300,80,50);
        this.getContentPane().add(username);
        this.getContentPane().add(password);

        usernameField.setBounds(250,200,200,40);
        passwordField.setBounds(250,300,200,40);
        this.getContentPane().add(usernameField);
        this.getContentPane().add(passwordField);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\IDEA\\restruantSystem\\Img\\background.png"));
        background.setBounds(0,0,632,640);
        this.getContentPane().add(background);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == loginButton){
            String usernameText = usernameField.getText();
            String passwordText = new String(passwordField.getPassword());//返回是字符数组
            if(usernameText.equals(username) && passwordText.equals(password)){
                new Root();
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        }else if(obj == returnButton){
            new RestaurantSystem();
            this.setVisible(false);
        }
    }
}
