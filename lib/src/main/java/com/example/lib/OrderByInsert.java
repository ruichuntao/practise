package com.example.lib;

public class OrderByInsert {

    public static void main(String a[]){
        int s[] = {1,3,2,6,4,3};
        orderByInsert(s);
        for (int i = 0;i<s.length;i++){
            System.out.println(s[i]);
        }
    }

    public static int[] orderByInsert(int[] a){
        for(int i=1;i<a.length;i++){
            int temp=a[i];//保存当前将要用于插入的值
            int j=i-1;//用于遍历已经排好序的子集的下标
            if(temp<a[j]){//判断子集的最大值与当前的值的大小，如果当前值大，则不需要循环
                while(j>=0 && a[j]>temp){//如果子集的元素大于当前值，则修改当前值的位置
                    a[j+1]=a[j];//将j的位置的值向前移动,用于存放当前值
                    j--;//进入下一次循环
                }
                a[j+1]=temp;//循环结束后子集中所有大于temp的值都向前移动了一步，这时候j+1的位置就是temp应该插入的位置
            }
        }
        return a;
    }
}
