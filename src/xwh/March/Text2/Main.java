package xwh.March.Text2;

import xwh.Utility.Utility;

import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("\t\t1\t用户登入");
            System.out.println("\t\t2\t添加新用户");
            System.out.println("\t\t3\t删除旧用户");
            System.out.println("\t\t0\t退出系统");
            System.out.print("请输入：");
            int n = Utility.readInt();
            switch (n) {
                case 1 -> {
                    Customer customer = login();
                    if (customer != null) {
                        customer.run();
                    }
                }
                case 2 -> addCus();
                case 3 -> {
                    System.out.print("请输入要删除用户的ID：");
                    int id = Utility.readInt();
                    CustomMap.deleteCustomer(id);
                }
                case 0 -> {
                    System.out.println("退出系统");
                    flag = false;
                }
                default -> System.out.println("输入错误");
            }
        }
    }

    public static void addCus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========添加用户==========");
        System.out.print("请输入姓名：");
        String name = scanner.next();
        System.out.print("请输入id：");
        int id = Utility.readInt();
        System.out.print("请输入密码：");
        String pwd = scanner.next();
        System.out.print("请输入存款：");
        double money = scanner.nextDouble();
        CustomMap.addCustomer(new Customer(id, pwd, name, money));
    }

    public static Customer login() {
        Customer customer;
        System.out.print("请输入账号：");
        int id = Utility.readInt();
        customer = CustomMap.searchCustomer(id);
        if (customer == null) {
            System.out.println("用户不存在");
            return null;
        }

        if (customer.confirmPassword()) {
            return customer;
        }
        return null;
    }
}

