package rui.bean;

public class ZiClass extends FuClass implements IFuFace {
    static int x = 0;
    static int y = 0;
    static int t[] = {1,2,3,4};
    static int q[][] = new int[5][5];
    String s = "zzz";

    @Override
    public void a() {
        System.out.println("子类");
        System.out.println(super.getClass().getName());
        System.out.println(getClass().getSuperclass().getName());
    }

    public ZiClass() {
//        super(1);
//        System.out.print(s);
    }

    public static void getName(){
        System.out.print("子类");
    }

    public static void main(String[] a) {
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                q[i][j] = fun(j+i+1);
//            }
//        }
//        for (int i = 0; i <5 ; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(q[i][j]);
//            }
//                        System.out.println();
//
//        }
//        hanoi(5,'x','y','z');
//        pai(t, 0, 4);
//        ZiClass ziClass = (ZiClass) new FuClass();
//        ziClass.a();
//        FuClass fuClass =(FuClass) new ZiClass();
//        fuClass.a();
        ZiClass ziClass = new ZiClass();
        System.out.println(ziClass.s);
        System.out.println(((FuClass)ziClass).s);
        FuClass fuClass = new FuClass();
        System.out.println(fuClass.s);
        FuClass fuClass1 = new ZiClass();
        System.out.println(fuClass1.s);
        System.out.println(((ZiClass)fuClass1).s);
//        transfer(ziClass);
//        transfer(((FuClass)ziClass));
//        transfer(fuClass);
//        transfer(fuClass1);
        ((FuClass)ziClass).a();
        fuClass.a();
        fuClass1.a();
    }

    public static void transfer(FuClass b){
        System.out.println("TransferFu");
        System.out.println(b.s);
    }

    public static void transfer(ZiClass c){
        System.out.println("TransferZi");
        System.out.println(c.s);

    }

    public static int fun(int m) {
        if (m == 0){
            return 0;
        }else {
            return fun(m - 1)+1;
        }
    }

    public static int func(int m) {
        if (m == 1 || m == 2) {
            return 1;
        } else {
            return func(m - 1) + func(m - 2);
        }
    }

    public static int funx(int m) {
        if (m == 1) {
            return 1;
        } else {
            return funx(m - 1) * m;
        }
    }

    public static void fund(int m) {
        System.out.print(m % 10);
        if (m > 10) {
            fund(m / 10);
        }
    }

    public static void hanoi(int n, char from, char tmp, char to) {
        if (n > 0) {
            hanoi(n - 1, from, to, tmp);
            System.out.println("take " + n + " from " + from + " to " + to);
            hanoi(n - 1, tmp, from, to);
        }
    }

    public static void pai(int[] t, int k, int n) {
        if (k == n - 1) {//输出这个排列
            for (int i = 0; i < n; i++) {
                System.out.print(t[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = k; i < n; i++) {
                int tmp = t[i];
                t[i] = t[k];
                t[k] = tmp;//一次挑选n个字母中的一个,和前位置替换
                pai(t, k + 1, n);                      //再对其余的n-1个字母一次挑选
                tmp = t[i];
                t[i] = t[k];
                t[k] = tmp;    //再换回来
            }
        }
    }
}
