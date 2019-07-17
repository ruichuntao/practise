package rui.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTest {
    public static void main(String[] args) throws IOException {
        // 三个测试方法
//        test01();
//        test02();
//        test03();
//        test04();
//        test07();
//        int a[] = {5, 7, 9, 13, 15, 17, 21, 23, 25, 33, 37, 39, 45, 51, 53, 57};
//        for (int i = 0; i < a.length; i++) {
//            System.out.print((a[i]+1)/2+" ,");
//        }
//        String s = "abc";
//        String s1 = new String("abc");
//        String s3 = new String("abc");
//        String s2 = "abc";
//        int i = 1;
//        Integer i1 = new Integer(1);
//        int i3 = 128;
//        Integer i2 = new Integer(128);
//        Integer f1 = Integer.valueOf(100);
//        Integer f2 = 100;
//        Integer f6 = new Integer(100);
//        Integer f3 = Integer.valueOf(150);
//        Integer f4 = 150;
//        Integer f5 = new Integer(150);
//        System.out.println(s == s1);
//        System.out.println(s == s2);
//        System.out.println(s == s3);
//        System.out.println(f1 == f2);
//        System.out.println(f6 == f2);
//        System.out.println(f1 == f6);
//        System.out.println(f3 == f4);
//        System.out.println(f5 == f4);
//        System.out.println(f3 == f5);
        System.out.println(uniquePaths(3,3));
    }

    public static int uniquePaths(int m,int n){
        if(m == 1 || n == 1) return 1;
        return uniquePaths(m-1, n)+ uniquePaths(m,n-1);

    }

    public static void test01() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符");
        char c;
        c = (char) bufferedReader.read();
        System.out.println("你输入的字符为" + c);
    }


    public static void test02() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符，按 q 键结束");
        char c;
        do {
            c = (char) bufferedReader.read();
            System.out.println("你输入的字符为" + c);
        } while (c != 'q');
    }

    public static void test03() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一行字符");
        String str = bufferedReader.readLine();
        System.out.println("你输入的字符为" + str);
    }

    public static void test04() throws IOException {
        byte[] bytes = {12, 21, 34, 11, 21};
        FileOutputStream fileOutputStream = new FileOutputStream(new File("").getAbsolutePath() + "/io/test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public static void test05() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("").getAbsolutePath() + "/io/test.txt");
        int c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = fileInputStream.read()) != -1) {
            System.out.print(c);
        }
    }

    public static void test06() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath() + "/io/test.txt");
        fileWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        fileWriter.write("不会覆盖文件原本的内容\n");
//        fileWriter.write(null); 不能直接写入 null
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    public static void test07() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath() + "/io/test.txt", false); // 关闭追加模式，变为覆盖模式
        fileWriter.write("Hello，world！欢迎来到 java 世界\n");
        fileWriter.write("我来覆盖文件原本的内容");
        fileWriter.append("我是下一行");
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    public static void test08() throws IOException {
        FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "/io/test.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        fileReader.close();
        bufferedReader.close();
    }

    public static void test09() throws IOException {
        FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "/io/test.txt");
        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.print((char) c);
        }
    }

}
