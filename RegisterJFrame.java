package com.itheima.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //注册界面

    public RegisterJFrame() {
        this.setSize(488,500);
        this.setTitle("拼图单机版 v1.2---注册");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        this.setVisible(true);
    }
}
