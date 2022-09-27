package fire;


import android.os.Message;

import androidx.collection.ArrayMap;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Micro {
    public long minimumRemoval(int[] beans) {
        long res = 0;

        Arrays.sort(beans);
        long sum = 0;
        for (int i = 0; i < beans.length; i++) {
            sum += beans[i];
            long cur = (beans.length - i) * (long) beans[i];
            System.out.println(cur);
            res = Math.max(cur, res);
        }
        return sum - res;
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(2000));
    }
}
