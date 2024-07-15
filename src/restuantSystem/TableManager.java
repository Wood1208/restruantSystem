package restuantSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private List<Table> tables;
    private String filename;

    public TableManager(String filename) {
        this.tables = new ArrayList<>();
        this.filename = filename;
        loadTablesFromFile();
    }

    private void loadTablesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int tableNumber = Integer.parseInt(parts[0].trim());
                boolean occupied = Boolean.parseBoolean(parts[1].trim());
                Table table = new Table(tableNumber, occupied);
                tables.add(table);
            }
        } catch (FileNotFoundException e) {
            // 如果文件不存在，不做任何操作，表格列表将保持为空
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTablesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Table table : tables) {
                writer.write(table.getTableNumber() + "," + table.isOccupied());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public void assignTable(int tableNumber) {
        Table table = findTable(tableNumber);
        if (table != null && !table.isOccupied()) {
            table.occupyTable(true);
            saveTablesToFile();
        }
    }

    public void releaseTable(int tableNumber) {
        Table table = findTable(tableNumber);
        if (table != null && table.isOccupied()) {
            table.occupyTable(false);
            saveTablesToFile();
        }
    }

    public void addTable(int tableNumber) {
        if (findTable(tableNumber) == null) {
            Table newTable = new Table(tableNumber, false);
            tables.add(newTable);
            saveTablesToFile();
        }
    }


    public void listTables() {
        for (Table table : tables) {
            System.out.println("Table " + table.getTableNumber() + " - " + (table.isOccupied() ? "Occupied" : "free"));
        }
    }

    public void removeTable(int tableNumber) {
        Table table = findTable(tableNumber);
        if (table != null) {
            tables.remove(table);
            saveTablesToFile();
        }
    }

    private Table findTable(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }
}
