package com.itheima.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginJFrame extends JFrame {
    //登录界面
//    static ArrayList<User> list = new ArrayList<User>();
//    static {
//        list.add(new User("qaq","123"));
//        list.add(new User("QAQ","321"));
//    }

    public LoginJFrame(){
        //初始化界面
        initJFrame();
        //在界面添加内容
        initView();
        //显示
        this.setVisible(true);

    }

    private void initJFrame(){
        this.setSize(488,430);
        this.setTitle("拼图单机版 v1.2---登录");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        this.setLayout(null);//关闭默认布局
    }

    private void initView(){
        //添加用户名文字
        JLabel usernameText = new JLabel("用户名");
        usernameText.setBounds(142,140,60,30);
        Font usernameFont = new Font("萝莉体", Font.PLAIN, 15);
        usernameText.setFont(usernameFont);
        this.getContentPane().add(usernameText);

        //添加用户名输入框
        JTextField username = new JTextField();
        username.setBounds(202,140,200,30);
        this.getContentPane().add(username);

        //添加密码文字
        JLabel passwordText = new JLabel("密码");
        passwordText.setBounds(142,170,60,30);
        Font passwordFont = new Font("萝莉体", Font.PLAIN, 15);
        passwordText.setFont(passwordFont);
        this.getContentPane().add(passwordText);

        //添加密码输入框
        JTextField password = new JTextField();
        password.setBounds(202,170,200,30);
        this.getContentPane().add(password);

        //添加登录按钮
        JButton login = new JButton("登录");
        login.setBounds(142,300,100,30);
        //login.setIcon(new ImageIcon());
        login.setFont(usernameFont);
        //去除按钮默认边框
        //login.setBorderPainted(false);
        //去除按钮背景
        //login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //添加注册按钮
        JButton register = new JButton("注册");
        register.setBounds(262,300,100,30);
        //register.setIcon(new ImageIcon());
        register.setFont(passwordFont);
        //去除按钮默认边框
        //register.setBorderPainted(false);
        //去除按钮背景
        //setContentAreaFilled(false);
        this.getContentPane().add(register);

        //添加背景图片
        //JLabel background = new JLabel(new ImageIcon());
        //background.setBounds();
        //this.getContentPane().add(background);

    }

    public void showJDialog(String content){
        JDialog jDialog = new JDialog();
        jDialog.setSize(200,150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        //创建JLabel对象管理文字并添加到弹框中
        JLabel warning = new JLabel(content);
        warning.setBounds(0,0,250,150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }


}
