package com.example.lib;

public class DiguiJC {
    public static long fac(int n){
        if(n<=0) return 0;
        else if(n==1)    return 1;
        else return n*fac(n-1);
    }
    public static void main(String [] args) {
        System.out.println(fac(6));
    }
}
