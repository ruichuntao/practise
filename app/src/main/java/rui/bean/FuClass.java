package rui.bean;

public class FuClass {
    String s = "fff";

    protected void a(){
        System.out.println("父类");
    }

    public FuClass() {
//        System.out.println(this.getClass());
    }

    public FuClass(int a) {
        s="123";
        System.out.println(a);
    }
    public static void getName(){
        System.out.print("父类");
    }
}
