package rui.bean;

public class Client {
    public static void main(String[] s ){
        IComponent iComponent,iComponentSB ,iComponentBB;
        iComponent = new Window();
        iComponentSB = new ScrollBarDecorator(iComponent);
        iComponentBB = new BlackBorderDecorator(iComponentSB);
        iComponentBB.display();
    }
}
