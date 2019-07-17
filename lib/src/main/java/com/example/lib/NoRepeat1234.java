package com.example.lib;

public class NoRepeat1234 {
    public static void main(String [] args) {
        int i, j, k;
        int m=0;
        for(i=1;i<=4;i++)
            for(j=1;j<=4;j++)
                for(k=1;k<=4;k++){
                    if(i!=j&&k!=j&&i!=k){
                        System.out.println(""+i+j+k);
                        m++;
                    }
                }
        System.out.println("能组成："+m+"个");
    }
}
