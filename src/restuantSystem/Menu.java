package restuantSystem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Menu extends JFrame implements ActionListener {
    Font font = new Font("萝莉体", Font.PLAIN, 15);
    int MenuIndex = 0;//选择下标
    //存储菜品信息，读取信息
    private List<Dish> dishes;
    private FileHandler fileHandler;

    //菜单类，包含菜品信息和价格
    JButton returnButton = new JButton("返回");
    JButton nextDish = new JButton("下一张");
    JButton lastDish= new JButton("上一张");
    JButton sureDish = new JButton("确定购买");
    JButton showDish = new JButton("已选甜点");

    public Menu(String filePath){
        //赋值
        this.fileHandler = new FileHandler(filePath);
        this.dishes = fileHandler.readDishesFromFile();

        //初始化界面
        initJFrame();

        //初始化信息
        initView();

        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(632,640);
        this.setTitle("~餐厅管理系统 v1.2~");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        this.setLayout(null);

        //添加事件(如果放在下面会添加多次，血的教训)
        returnButton.addActionListener(this);
        nextDish.addActionListener(this);
        lastDish.addActionListener(this);
        sureDish.addActionListener(this);
        showDish.addActionListener(this);
    }

    private void initView() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();

        //欢迎语
        JLabel usernameText = new JLabel("欢迎使用甜点屋系统~");
        usernameText.setBounds(0,0,500,50);
        Font usernameFont = new Font("萝莉体", Font.PLAIN, 30);
        usernameText.setFont(usernameFont);
        this.getContentPane().add(usernameText);

        //返回
        returnButton.setBounds(20,550,100,50);
        returnButton.setBackground(Color.pink);
        returnButton.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        returnButton.setFont(font);
        this.getContentPane().add(returnButton);

        //下一张
        nextDish.setBounds(260,550,100,50);
        nextDish.setBackground(Color.pink);
        nextDish.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        nextDish.setFont(font);
        this.getContentPane().add(nextDish);

        //上一张
        lastDish.setBounds(140,550,100,50);
        lastDish.setBackground(Color.pink);
        lastDish.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        lastDish.setFont(font);
        this.getContentPane().add(lastDish);

        //确定
        sureDish.setBounds(380,550,100,50);
        sureDish.setBackground(Color.pink);
        sureDish.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        sureDish.setFont(font);
        this.getContentPane().add(sureDish);

        //展示已选菜单
        showDish.setBounds(500,550,100,50);
        showDish.setBackground(Color.pink);
        showDish.setBorder(BorderFactory.createLineBorder(Color.white, 4));
        showDish.setFont(font);
        this.getContentPane().add(showDish);


        //显示甜点图片
        JLabel food = new JLabel(new ImageIcon(dishes.get(MenuIndex).getImagePath()));
        food.setBounds(70,130,320,250);
        this.getContentPane().add(food);
        //显示价格
        double price = dishes.get(MenuIndex).getPrice();
        JLabel showPrice = new JLabel("价格：" + price);
        showPrice.setFont(new Font("萝莉体", Font.PLAIN, 30));
        showPrice.setBounds(430,200,300,100);
        this.getContentPane().add(showPrice);
        //显示名字
        String name = dishes.get(MenuIndex).getName();
        JLabel nameText = new JLabel(name);
        nameText.setFont(new Font("萝莉体", Font.PLAIN, 30));
        nameText.setBounds(70,400,300,100);
        this.getContentPane().add(nameText);
        //给图片添加边框
        food.setBorder(new MatteBorder(10, 8, 10, 8, Color.pink));


        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("Img\\background.png"));
        background.setBounds(0,0,632,640);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == returnButton){
            new User();
            this.setVisible(false);
        }else if(obj == nextDish){
           if(MenuIndex + 1 < dishes.size()){
               MenuIndex++;
               initView();
           }else{
               JDialog jDialog = new JDialog();//弹框对象
               jDialog.setTitle("提示~");
               JLabel jLable = new JLabel("已经是最后了哟~");
               jLable.setFont(new Font("萝莉体", Font.PLAIN, 20));
               jLable.setBounds(0,0,150,150);
               jDialog.getContentPane().add(jLable);
               jDialog.setSize(200,200);
               jDialog.setAlwaysOnTop(true);
               jDialog.setLocationRelativeTo(null);
               jDialog.setModal(true);//弹框不关闭无法操作下面界面
               jDialog.setVisible(true);
           }
        }else if(obj == lastDish){
            if(MenuIndex - 1 >= 0){
                MenuIndex--;
                initView();
            }else{
                JDialog jDialog = new JDialog();//弹框对象
                jDialog.setTitle("提示~");
                JLabel jLable = new JLabel("已经是开始了哟~");
                jLable.setFont(new Font("萝莉体", Font.PLAIN, 20));
                jLable.setBounds(0,0,150,150);
                jDialog.getContentPane().add(jLable);
                jDialog.setSize(200,200);
                jDialog.setAlwaysOnTop(true);
                jDialog.setLocationRelativeTo(null);
                jDialog.setModal(true);//弹框不关闭无法操作下面界面
                jDialog.setVisible(true);
            }
        }else if(obj == sureDish){
            // 处理确定选择按钮点击事件
            int choice = JOptionPane.showConfirmDialog(this,
                    "确定选择该甜点吗？", "提示~", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // 获取当前选择的菜品
                Dish selectedDish = dishes.get(MenuIndex);
                // 将菜品信息写入文件
                FileHandler fileHandler2 = new FileHandler("CustomerDishes.txt");
                fileHandler.writeDishToFile(selectedDish);
                // 可以添加其他操作，比如更新界面显示等
            } else {
                // 用户点击了取消按钮，可以不做任何操作或者添加相应的提示
            }
        }else if(obj == showDish){
            new CustomerDish("CustomerDishes.txt");
            this.setVisible(false);
        }
    }
}
