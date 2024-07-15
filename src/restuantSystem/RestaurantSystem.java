package restuantSystem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RestaurantSystem extends JFrame implements ActionListener {
    //负责系统的整体管理和调度

    JButton root = new JButton("管理员系统");
    JButton user = new JButton("用户点餐");

    JMenu aboutJMenu = new JMenu("关于我们");
    JMenuItem accountItem = new JMenuItem("投喂处");

    public RestaurantSystem() {

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

        //管理员登录按钮
        root.setBounds(100,300,150,40);
        root.setFont(font);
        root.addActionListener(this);
        this.getContentPane().add(root);
        root.setBackground(Color.pink);
        root.setBorder(BorderFactory.createLineBorder(Color.white, 4));


        //用户按钮
        user.setBounds(350,300,100,40);
        user.setFont(font);
        user.addActionListener(this);
        this.getContentPane().add(user);
        user.setBackground(Color.pink);
        user.setBorder(BorderFactory.createLineBorder(Color.white, 4));

        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        aboutJMenu.add(accountItem);
        jMenuBar.add(aboutJMenu);
        accountItem.addActionListener(this);

        //给界面设置菜单
        this.setJMenuBar(jMenuBar);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("Img\\background.png"));
        background.setBounds(0,0,632,640);
        this.getContentPane().add(background);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == user){
            new User();
            this.setVisible(false);
        }else if(obj == root){
            new LoginDialog();
            this.setVisible(false);
        }else if(obj == accountItem){
            JDialog jDialog = new JDialog();//弹框对象
            JLabel jLable = new JLabel(new ImageIcon("Img/account.jpg"));
            jLable.setBounds(0,0,1000,1000);
            jDialog.getContentPane().add(jLable);
            jDialog.setSize(1000,1000);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);//弹框不关闭无法操作下面界面
            jDialog.setVisible(true);
        }
    }

}
