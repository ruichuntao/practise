package rui.bean;

public class ScrollBarDecorator extends ComponentDecorator {

    public ScrollBarDecorator(IComponent component) {
        super(component);
    }
    public void display(){
        setScrollBar();
//        super.display();
    }
    public void setScrollBar(){
        System.out.println("设置滚动条");
    }
}
