package com.itheima.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class SimplePaintApp extends JFrame {
    private DrawingPanel drawingPanel;
    private JPanel toolPanel;
    private JButton lineButton, rectButton, circleButton;
    private JComboBox<String> colorComboBox;
    private JSlider thicknessSlider;

    private Color currentColor = Color.BLACK;
    private int currentThickness = 3;
    private String currentTool = "Line";

    public SimplePaintApp() {
        setTitle("简易画图应用");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 绘图面板
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);

        // 工具栏
        toolPanel = new JPanel();
        lineButton = new JButton("线条");
        rectButton = new JButton("矩形");
        circleButton = new JButton("圆形");

        colorComboBox = new JComboBox<>(new String[]{"黑色", "红色", "蓝色"});
        colorComboBox.setSelectedIndex(0);

        thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 3);
        thicknessSlider.setMajorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);

        toolPanel.add(lineButton);
        toolPanel.add(rectButton);
        toolPanel.add(circleButton);
        toolPanel.add(new JLabel("颜色:"));
        toolPanel.add(colorComboBox);
        toolPanel.add(new JLabel("粗细:"));
        toolPanel.add(thicknessSlider);

        add(drawingPanel, BorderLayout.CENTER);
        add(toolPanel, BorderLayout.NORTH);

        // 事件处理
        lineButton.addActionListener(e -> currentTool = "Line");
        rectButton.addActionListener(e -> currentTool = "Rectangle");
        circleButton.addActionListener(e -> currentTool = "Circle");

        colorComboBox.addItemListener(e -> {
            switch (colorComboBox.getSelectedIndex()) {
                case 0:
                    currentColor = Color.BLACK;
                    break;
                case 1:
                    currentColor = Color.RED;
                    break;
                case 2:
                    currentColor = Color.BLUE;
                    break;
            }
        });

        thicknessSlider.addChangeListener(e -> currentThickness = thicknessSlider.getValue());

        drawingPanel.addMouseListener(new MouseAdapter() {
            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch (currentTool) {
                    case "Line":
                        drawingPanel.drawLine(startPoint, e.getPoint());
                        break;
                    case "Rectangle":
                        drawingPanel.drawRect(startPoint, e.getPoint());
                        break;
                    case "Circle":
                        drawingPanel.drawCircle(startPoint, e.getPoint());
                        break;
                }
            }
        });

        setVisible(true);
    }

    class DrawingPanel extends JPanel {
        private Graphics2D g2;

        public DrawingPanel() {
            super();
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        public void drawLine(Point start, Point end) {
            g2.setColor(currentColor);
            g2.setStroke(new BasicStroke(currentThickness));
            g2.draw(new Line2D.Double(start, end));
            repaint();
        }

        public void drawRect(Point start, Point end) {
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);

            g2.setColor(currentColor);
            g2.setStroke(new BasicStroke(currentThickness));
            g2.draw(new Rectangle2D.Double(x, y, width, height));
            repaint();
        }

        public void drawCircle(Point start, Point end) {
            int diameter = Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
            int x = start.x < end.x ? start.x : start.x - diameter;
            int y = start.y < end.y ? start.y : start.y - diameter;

            g2.setColor(currentColor);
            g2.setStroke(new BasicStroke(currentThickness));
            g2.draw(new Ellipse2D.Double(x, y, diameter, diameter));
            repaint();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimplePaintApp::new);
    }
}

