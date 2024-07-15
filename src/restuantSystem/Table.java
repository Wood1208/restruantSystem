package restuantSystem;

public class Table {
    private int tableNumber;
    private boolean isOccupied;
    // 其他可能的桌子属性

    public Table(int tableNumber,boolean isOccupied) {
        this.tableNumber = tableNumber;
        this.isOccupied = isOccupied;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupyTable(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public void freeTable() {
        isOccupied = false;
    }
}

