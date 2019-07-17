package rui.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TestGh {
    public static void reorderOddEven(int[] arr) {
        // 对于输入的数组为空，或者长度小于2的只接返回
        if (arr == null || arr.length < 2) {
            return;
        }

        // 从左向右记录偶数的位置
        int start = 0;
        // 从右向左记录奇数的位置
        int end = arr.length - 1;
        // 开始调整奇数和偶数的位置
        while (start < end) {
            // 找偶数
            while (start < end && arr[start] % 2 != 0) {
                start++;
            }
            // 找奇数
            while (start < end && arr[end] % 2 == 0) {
                end--;
            }

            // 找到后就将奇数和偶数交换位置
            // 对于start=end的情况，交换不会产生什么影响
            // 所以将if判断省去了
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
        }
    }

    public static void quickSort(int[] A, int left, int right) {

        if (left < right) {
            // 一次划分
            int mid = partion(A, left, right);
            quickSort(A, 0, mid - 1);
            quickSort(A, mid + 1, right);
        }
    }

    public static void swap(int[] A, int l, int r) {
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;

    }

    public static int partion(int[] a, int left, int right) {
        // 轴值，默认选取数组的第一个数字
        while (left < right) {
            while (left < right && a[left] <= a[right]) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
            }
            while (left < right && a[left] <= a[right]) {
                left++;
            }
            if (left < right) {
                swap(a, left, right);
            }
        }
        return left;
    }


    public static void hebing(){
        int a[] = {1, 13, 15, 17, 19};
        int b[] = {2, 4, 6, 8, 10};
        int c[] = new int[a.length + b.length];
        int x = 0, y = 0;
        for (int i = 0; i < c.length; i++) {
            if (x == a.length) {
                System.arraycopy(b, y, c, i, c.length - i);
                break;
            }
            if (y == b.length) {
                System.arraycopy(a, x, c, i, c.length - i);
                break;
            }
            if (a[x] < b[y]) {
                c[i] = a[x];
                x++;
            } else {
                c[i] = b[y];
                y++;
            }
        }
//        if (x ==a.length){
//            for (int i = y; i < b.length; i++) {
//                c[i+a.length] = b[y++];
//            }
//        }else if (y==b.length){
//            for (int i = x; i < a.length; i++) {
//                c[i+b.length] = a[x++];
//            }
//        }
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
    }

    public static int[] merge(int[] a, int[] b) {
        int lena = a.length;
        int lenb = b.length;
        int[] c = new int[lena + lenb];
        int i = 0, j = 0, k = 0;//分别代表数组a ,b , c 的索引
        while (i < lena && j < lenb) {
            if (a[i] < b[j])
                c[k++] = a[i++];
            else
                c[k++] = b[j++];
        }
        while (i < lena)
            c[k++] = a[i++];
        while (j < lenb)
            c[k++] = b[j++];
        return c;
    }

    public static void permutation(char[] chars, int begin) {
        // 如果是最后一个元素了，就输出排列结果
        if (chars.length - 1 == begin) {
            System.out.print(new String(chars) + " ");
        } else {
            char tmp;
            // 对当前还未处理的字符串进行处理，每个字符都可以作为当前处理位置的元素
            for (int i = begin; i < chars.length; i++) {
                // 下面是交换元素的位置
                tmp = chars[begin];
                chars[begin] = chars[i];
                chars[i] = tmp;

                // 处理下一个位置
                permutation(chars, begin + 1);
            }
        }
    }

    public static void uglynum(List<Integer> list,int x){
        if (x>1500){
            System.out.print("结束");
        }else {
            x*=2;
            list.add(x);
            uglynum(list,x);
            x*=3;
            list.add(x);
            uglynum(list,x);
            x*=5;
            list.add(x);
            uglynum(list,x);
        }
    }

    public static void main(String[] s) {
        List<Integer> list = new ArrayList<>();
        uglynum(list,1);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }

    }


}
