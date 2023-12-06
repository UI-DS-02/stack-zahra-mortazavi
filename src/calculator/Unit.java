package calculator;

public class Unit {
    private int index;
    private int priority;
    private String data;

    public Unit(int index, String data, int priority) {
        this.index = index;
        this.data = data;
        this.priority = priority;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
