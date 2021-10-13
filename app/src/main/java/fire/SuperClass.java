package fire;

import android.os.Looper;
import android.os.MessageQueue;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dispatcher;

public class SuperClass {

    static {
        System.out.println("SuperClass init！");
        value = 456;
    }

    public static int value = 123;

//
//    {
//        System.out.println("SuperClass object init！");
//    }

    public SuperClass(int value) {
        this.value = value;
//        System.out.println("SuperClass con init！");
    }

    public void setValue(int value) {
        this.value = value;
    }


    // 5.一只蜗牛掉进了井里，井深x米，它每30分钟可以向上爬1米，
    // 每爬行30分钟就需要休息y分钟，休息的时候，每分钟下滑2厘米
    // ，蜗牛的体质不同，所需休息时间不同，尝试编写一个函数
    // ，计算不同的蜗牛从不同深度的井爬出来所需要的时间（20分）

    static int getMin(int x, int y) {
        // 用厘米做单位，不会精度丢失
        int ans = 0;
        int dis = 0;
        int time = 30; // 定义30分钟1米
        int up = 100, down = 2;
        x *= 100;
        // 必须是30分钟才能上爬1米？
        // 2厘米，1米
        while (dis < x) {
            dis += up;
            ans += time;
            if (dis >= x) break;// 爬上来就不会再下滑了
            dis -= down * y;
            ans += y;
        }
        return ans;
    }


    // 6.输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
    // 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
    // 并保证奇数和奇数，偶数和偶数之间的相对位置不变（20分）

    static int[] fun(int[] arr) {
        int l = -1, r = 0;
        int n = arr.length;
        int[] h = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if ((arr[i] & 1) == 1) {
                h[idx++] = arr[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if ((arr[i] & 1) == 0) {
                h[idx++] = arr[i];
            }
        }
        return h;
    }

    public static void main(String[] args) throws InterruptedException {

//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//        System.out.println(c == d); // true
//        System.out.println(e == f); // false
//        System.out.println(c == (a + b)); // true
//        System.out.println(c.equals(a + b)); // true
//        System.out.println(g == (a + b));// true
//        System.out.println(g.equals(a + b)); // false
    }

}


