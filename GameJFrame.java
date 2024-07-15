package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏界面

    //创建二维数组，加载图片会根据二维数组加载
    int[][] date = new int[4][4];
    int x = 0, y = 0;//0图片(即空白方块)的位置

    //定义一个变量，记录当前展示图片路径
    String path = "puzzlegame\\image\\pokemon\\pokemon1\\";

    //胜利数组
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    int step = 0;//统计步数

    //条目
    JMenuItem changeItem = new JMenuItem("更换图片");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("投喂处");

    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据(PS:我把数据的英文单词拼错了，请不要在意orz)
        initDate();

        //初始化图片
        initImage();

        //显示界面（最后）
        this.setVisible(true);
    }

    private void initImage() {
        //清空原本已经出现的图片
        this.getContentPane().removeAll();

        if(victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("D:\\IDEA\\PuzzleGame\\puzzlegame\\image\\win.png"));
            winJLabel.setBounds(10,160,150,150);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数:" + step);
        stepCount.setBounds(100,60,100,30);
        Font stepFont = new Font("萝莉体", Font.PLAIN, 25);
        stepCount.setFont(stepFont);
        this.getContentPane().add(stepCount);

        JLabel remain = new JLabel("提示: 长按空格键显示原图~");
        remain.setBounds(45,550,300,30);
        Font remainFont = new Font("萝莉体", Font.PLAIN, 18);
        remain.setFont(remainFont);
        this.getContentPane().add(remain);

        //循环放置图片
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                //获取当前要加载图片的序号
                int number = date[i][j];
                //创建一个JLabel对象（管理容器）
                JLabel jLabel1 = new JLabel(new ImageIcon(path + number + ".jpg"));
                //指定位置
                jLabel1.setBounds(65 * j + 150,65 * i + 175,65,65);
                //给图片添加边框
                jLabel1.setBorder(new BevelBorder(0));//0:凸起来，1:凹下去
                //把管理容器添加到界面
                this.getContentPane().add(jLabel1);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background1.jpg"));
        background.setBounds(0,0,800,1000);
        //把背景图片添加到界面
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }

    private void initDate(){
        //定义一维数组
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取随机索引
            int index = r.nextInt(tempArr.length);
            //遍历数据与随机索引交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //把一维数组的数据放入二维数组
        for (int i = 0; i < tempArr.length; i++) {
            date[i / 4][i % 4] = tempArr[i];
            if(tempArr[i] == 0){
                x = i / 4; y = i % 4;
            }
        }

    }

    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();

        //选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //将条目添加到选项中
        functionJMenu.add(changeItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        changeItem.addActionListener(this);

        //将选项添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(600,680);
        this.setTitle("~拼图单机版 v1.2~");
        this.setAlwaysOnTop(true);//设置置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
        //取消默认居中位置
        this.setLayout(null);
        //给界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //按空格键显示完整图片
        if(code == 32){
            //把界面中所有的图片全部删除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.png"));
            all.setBounds(130,130,260,260);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("puzzlegame\\image\\background1.jpg"));
            background.setBounds(0,0,800,1000);
            //把背景图片添加到界面
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        //对上下左右进行判断
        int code = e.getKeyCode();
        System.out.println(code);
        if(code == 37 || code == 65){
            if(y == 3) return;
            System.out.println("向左移动~");
            date[x][y] = date[x][y + 1];
            date[x][y + 1] = 0;
            y++;
            step++;
            //调用方法加载新图片
            initImage();
        } else if (code == 38 || code == 87) {
            if(x == 3) return;
            System.out.println("向上移动~");
            date[x][y] = date[x + 1][y];
            date[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        }else if(code == 39 || code == 68){
            if(y == 0) return;
            System.out.println("向右移动~");
            date[x][y] = date[x][y - 1];
            date[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        }else if(code == 40 || code == 83){
            if(x == 0) return;
            System.out.println("向下移动~");
            date[x][y] = date[x - 1][y];
            date[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }else if(code == 32){
            //按空格键显示完整图片
            initImage();
        }else if(code == 112){
            date = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    public boolean victory(){
        for (int i = 0; i < date.length; i++) {
            for (int j = 0; j < date[i].length; j++) {
                if(date[i][j] != win[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("重新游戏~");
            initDate();
            step = 0;
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("重新登陆~");
            this.setVisible(false);//关闭当前界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏~");
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("公众号");
            JDialog jDialog = new JDialog();//弹框对象
            JLabel jLable = new JLabel(new ImageIcon("D:\\IDEA\\PuzzleGame\\puzzlegame\\image\\神奇宝贝特别篇.png"));
            jLable.setBounds(0,0,150,150);
            jDialog.getContentPane().add(jLable);
            jDialog.setSize(200,200);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);//弹框不关闭无法操作下面界面
            jDialog.setVisible(true);
        }else if(obj == changeItem){
            Random r = new Random();
            int choice = r.nextInt(5) + 1;
            path = "puzzlegame\\image\\pokemon\\pokemon" + choice + "\\";
            initDate();
            step = 0;
            initImage();
        }
    }
}
