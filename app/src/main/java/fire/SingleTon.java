package fire;

import androidx.core.app.ActivityCompat;

public class SingleTon {
    private static final SingleTon INSTANCE = new SingleTon();

    private SingleTon() {
    }

    static {
        System.out.println("Single加载");
    }

//    private static class SingleTonHolder {
//        private static SingleTon INSTANCE = new SingleTon();
//        static {
//            System.out.println("holder加载");
//        }
//    }

    public static SingleTon getInstance() {
//        return SingleTonHolder.INSTANCE;
        return INSTANCE;
    }

    public static void main(String[] args) {
//        System.out.println(SingleTon.getInstance());
    }
}
