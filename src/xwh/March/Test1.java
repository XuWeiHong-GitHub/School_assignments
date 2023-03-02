package xwh.March;

import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        //定义
        Scanner scanner = new Scanner(System.in);
        int myScanner[] = new int[7];
        int ran[] = new int[7];
        int errorNum = 0;
        int same = 0;
        System.out.println("=========中国福利彩票 35选7=========");
        //算法
        for (int i = 0; i < 7; i++) {
            if (errorNum >= 3) {
                System.out.println("输入大于三次，自动退出系统，欢迎下次光临");
                System.exit(0);
            }
            System.out.print("请输入第" + (i + 1) + "个数：");
            try {
                myScanner[i] = scanner.nextInt();
            } catch (Exception e) {
                errorNum++;
                System.out.println("输入错误，请输入 1 到 35 之间的数" + "剩余输入次数：" + (3 - errorNum));
                i--;
                scanner.next();
                continue;
            }
            if (myScanner[i] > 35 || myScanner[i] < 1) {
                errorNum++;
                System.out.println("输入错误，请输入 1 到 35 之间的数" + "剩余输入次数：" + (3 - errorNum));
                i--;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (myScanner[j] == myScanner[i]) {
                    errorNum++;
                    System.out.println("该数值已被输入，请从新输入" + "剩余输入次数：" + (3 - errorNum));
                    i--;
                    break;
                }
            }
        }
        System.out.println("===========输入完毕===========");
        for (int i = 0; i < 7; i++) {
            double rand = Math.random();
            ran[i] = (int) (rand * 35 + 1);
            for (int j = 0; j < i; j++) {
                if (ran[j] == ran[i]) {
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (ran[i] == myScanner[j]) {
                    same++;
                }
            }
        }
        //输出
        System.out.print("你的输入：");
        for (int i = 0; i < 7; i++) {
            System.out.print(myScanner[i] + " ");
        }
        System.out.println();
        System.out.print("中奖号码：");
        for (int i = 0; i < 7; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(ran[i] + " ");
        }
        System.out.println();
        System.out.println("相同号码个数：" + same);
        if (same == 7) {
            System.out.println("恭喜你，拥有了凡人无法匹敌的运气");
        }
    }
}
