package xwh.Utility;

import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class Utility {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * 功能：读取键盘输入的整型，数值介于 min 和 max 之间
     * @param max 可以返回的最大值
     * @param min 可以返回的最小值
     * @return 整数
     */
    public static int readInt(int min, int max) {
        int n = 0;
        boolean loop = true;
        while (loop) {
            try {
                n = scanner.nextInt();
                if (n < min || n > max) {
                    System.out.println("输入错误，请重新输入");
                } else {
                    loop = false;
                }
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }

    /**
     * 功能：读取键盘输入的整型
     *
     * @return 整数
     */
    public static int readInt() {
        int n = 0;
        boolean loop = true;
        while (loop) {
            try {
                n = scanner.nextInt();
                loop = false;
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }

    /**
     * 功能： 读取一个字符串
     * 输入大于limit的长度，就会提示重新输入。
     *
     * @param limit 读取的长度
     * @return 字符串
     */
    public static String readString(int limit) {
        String line = "";
        boolean loop = true;
        while (loop) {
            try {
                line = scanner.next();
                if (line.length() > limit) {
                    System.out.println("输入长度过长，请重新输入");
                } else {
                    loop = false;
                }
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                scanner.next();
            }
        }
        return line;
    }

    /**
     * 功能：读取键盘输入的浮点数，数值介于 min 和 max 之间
     * @param max 可以返回的最大值
     * @param min 可以返回的最小值
     * @return 浮点数
     */
    public static double readDouble(int min, int max) {
        double n = 0;
        boolean loop = true;
        while (loop) {
            try {
                n = scanner.nextDouble();
                if (n < min || n > max) {
                    System.out.println("输入错误，请重新输入");
                } else {
                    loop = false;
                }
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }

    /**
     * 功能：读取键盘输入的浮点数
     *
     * @return 浮点数
     */
    public static double readDouble() {
        double n = 0;
        boolean loop = true;
        while (loop) {
            try {
                n = scanner.nextDouble();
                loop = false;
            } catch (Exception e) {
                System.out.println("输入错误，请重新输入");
                scanner.next();
            }
        }
        return n;
    }
}
