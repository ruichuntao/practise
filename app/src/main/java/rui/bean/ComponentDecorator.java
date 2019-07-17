package rui.bean;

public class ComponentDecorator implements IComponent {

    private IComponent component;

    public ComponentDecorator(IComponent component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
