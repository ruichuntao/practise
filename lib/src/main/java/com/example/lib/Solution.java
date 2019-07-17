package com.example.lib;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private long trailingZeros(long n) {// 阶乘尾部的0
        long sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }
    private int digitCounts(int k, int n) {// 统计在数组中出现的数字
        int num = 0;
        char sk = Character.forDigit(k, 10);
        for (int i = 0; i <= n; i++) {
            String s = String.valueOf(i);
            for (int y = 0; y < s.length(); y++) {
                if (sk == s.charAt(y)) {
                    num++;
                }
            }
        }
        return num;
    }
    private int nthUglyNumber(int n) {//第n位丑数
        int x = 0;
        while (n > 0) {
            x++;
            if (diGuiUgly(x)) {
                n--;
            }
        }
        return x;
    }
    private boolean diGuiUgly(int d) {//第n位丑数
        if (d == 1) {
            return true;
        } else if (d % 5 == 0) {
            d /= 5;
            return diGuiUgly(d);
        } else if (d % 3 == 0) {
            d /= 3;
            return diGuiUgly(d);
        } else if (d % 2 == 0) {
            d /= 2;
            return diGuiUgly(d);
        } else {
            return false;
        }
    }
    private int[] mergeSortedArray(int[] A, int[] B) {//2个有序数组插入排序
        int r[] = new int[A.length + B.length];
        int a = 0, b = 0;
        for (int i = 0; i < r.length; i++) {
            if(a==A.length){
                r[i]=B[b];
                b++;
                continue;
            }
            if (b==B.length){
                r[i]=A[a];
                a++;
                continue;
            }
            if (A[a]<=B[b]){
                r[i]=A[a];
                a++;
            }else {
                r[i]=B[b];
                b++;
            }
        }
        return r;
    }
    private List<String> fizzBuzz(int n) {// 3,5整除输出
        List<String> s = new ArrayList<>();
        for (int i=1;i<=n;i++){
            s.add((i%3==0)?((i%5==0)?"fizz buzz":"fizz"):((i%5==0)?"buzz":String.valueOf(i)));
        }
        return s;
        // write your code here
    }
    private static void fullSort(int[] arr, int start, int end) {//递归全排列，不会

        if (start == end) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            fullSort(arr, start + 1, end);
            swap(arr, i, start);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private int reverseInteger(int number) {//翻转3位数
        int a = number/100;
        int b = number/10-a*10;
        int c = number-a*100-b*10;
        return c*100+b*10+a;
    }
    private int maxSubArray(int[] nums) {
        int M = -99999999;
        for (int i=0;i<nums.length-1;i++){
            if (M<nums[i]){
                M=nums[i];
            }
            for (int j = i;j<nums.length;j++){
                if (M<=M+nums[j]){
                    M+=nums[j];
                }else break;
            }
        }
        return M;
        // write your code here
    }
    public static void main(String a[]) {
        Solution so = new Solution();
        int s[]={1,2,3,4,5};
        System.out.println(so.maxSubArray(s));
    }
}
