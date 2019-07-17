package rui.bean;

public class BlackBorderDecorator extends ComponentDecorator {

    public BlackBorderDecorator(IComponent component) {
        super(component);
    }

    public void display() {
        setBlackBorder();
//        super.display();
    }

    public void setBlackBorder() {
        System.out.println("设置黑框");
    }
}
