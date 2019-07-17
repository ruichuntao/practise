package rui.todd;

public class Box implements Cloneable {
    public int num;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        return object;
    }
}
