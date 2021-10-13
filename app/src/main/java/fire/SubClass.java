package fire;

public class SubClass extends SuperClass{
    public static int value = 123;

    static {
        System.out.println("SubClass init");
    }

    public SubClass(int value) {
        super(value);
    }
}
