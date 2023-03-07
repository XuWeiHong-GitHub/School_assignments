package xwh.March.Text2;

import xwh.Utility.Utility;

/**
 * @author 许伟鸿
 * @version 2.0
 */
public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        CustomMap.initMap();
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
                    Customer customer = Customer.CustomerLogin();
                    if (customer != null) {
                        customer.run();
                    }
                }
                case 2 -> Customer.addCustomer();
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
}

