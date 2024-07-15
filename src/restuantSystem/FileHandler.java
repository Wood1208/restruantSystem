package restuantSystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    //从文件中读取数据
    public List<Dish> readDishesFromFile() {
        List<Dish> dishes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length < 4) {
                    // 如果分割后的部分不足4个，可能是数据不完整，可以跳过或者打印错误信息
                    System.err.println("Incomplete data line: " + line);
                    continue; // 跳过当前行，继续下一行
                }
                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String imgPath = parts[3];
                    Dish dish = new Dish(id, name, price, imgPath);
                    dishes.add(dish);
                } catch (NumberFormatException e) {
                    // 捕获解析异常
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                    // 可以选择继续处理下一行或者抛出异常中断
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    //把数据写入文件
    public void writeDishesToFile(List<Dish> dishes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Dish dish : dishes) {
                    writer.write(dish.getId() + " " + dish.getName() + " " + dish.getPrice() + " " + dish.getImagePath());
                    writer.newLine();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //个人购物单
    public void writeDishToFile(Dish dish) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\IDEA\\restruantSystem\\CustomerDishes.txt", true))) {
            writer.write(dish.getId() + " " + dish.getName() + " " + dish.getPrice() + " " + dish.getImagePath());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //把数据写入个人消费总额
    public void writeSalesToFile(List<Sales> CustomerSales, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) {
            for (Sales sale : CustomerSales) {
                writer.write(sale.getDate() + " " + sale.getAccount());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //把销售数据读出
    public List<Sales> readSalesFromFile(String filename) {
        List<Sales> salesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String date = parts[0];
                    LocalDate dateObj = LocalDate.parse(date);
                    double amount = Double.parseDouble(parts[1]);
                    Sales sale = new Sales(dateObj, amount);
                    salesList.add(sale);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesList;
    }

}

