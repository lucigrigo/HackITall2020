package sample;

public class MutableBoolean {
    private boolean value;

    MutableBoolean(boolean v) {
        value = v;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
