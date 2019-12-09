package rui.bean;

abstract class Car {

    String name = "Car";

    public String getName() {
        return name;
    }

    public abstract void demarre();
}

class B extends Car {
    String name = "B";

    public String getName() {
        return name;
    }

    public void demarre() {
        System.out.println(getName() + " demarre");
    }
}

class C extends B {
    String name = "C";

    public String getName() {
        return name;
    }

    public void demarreWithSuper() {
        System.out.println(super.getName() + " demarre");
    }

    public void demarreNoSuper() {
        System.out.println(getName() + " demarre");
    }
}

class D extends B {
    public String getName() {
        return name;
    }

    public void demarreNoSuper() {
        System.out.println(getName() + " demarre");
    }
}

public class Test {
    public static void main(String[] args) {
        B b = new B();
        b.demarre();

        Car bCar = new B();
        bCar.demarre();

        C c = new C();
        c.demarre(); // c 里并没有定义这个函数
        c.demarreWithSuper();
        c.demarreNoSuper();

        D d = new D();
        d.demarre();

        transfer(c);    // TransferC
        transfer((B) c); // TransferB
        transfer(d);    // TransferB
    }

    public static void transfer(B b) {
        System.out.println("TransferB");
        b.demarre();
    }

    public static void transfer(C c) {
        System.out.println("TransferC");
        c.demarre();
    }

}
