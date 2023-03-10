package xwh.March.Text2;

import xwh.Utility.Utility;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 2.0
 */
public class Customer implements Serializable {
    public int ID;
    private String password;
    private final String name;
    private double money;
    private String detail = "";
    Date date;       //data表示日期

    public Customer(int ID, String password, String name, double money) {
        this.ID = ID;
        this.password = Utility.stringEncryption(password);
        this.name = name;
        this.money = money;
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于日期格式化
        detail += sdf.format(date) + "\t初始" + money + "\n";
    }

    public String getName() {
        return name;
    }

    private void print() {
        System.out.println("\n您的当前存款为" + money);
        System.out.println("=============历史记录=============");
        System.out.print(detail);
        System.out.println("=================================\n");
    }

    private void saveMoney() {
        System.out.print("\n请输入存款金额：");
        double money = Utility.readDouble();
        this.money += money;
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于日期格式化
        detail += sdf.format(date) + "\t存入" + money + "\n";
        CustomMap.saveMap();
    }

    private void withdrawMoney() {
        System.out.print("\n请输入取款金额：");
        double money = Utility.readDouble();
        if (money > this.money) {
            System.out.println("余额不足");
            return;
        }
        this.money -= money;
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于日期格式化
        detail += sdf.format(date) + "\t取出" + money + "\n";
        CustomMap.saveMap();
    }

    private void transferAccounts() {
        System.out.print("\n请输入要转账的对象ID：");
        int id = Utility.readInt();
        Customer payee = CustomMap.searchCustomer(id);
        if (payee == null) {
            System.out.println("不存在该用户");
            return;
        }
        System.out.print("请输入要转账的金额：");
        double payMoney = Utility.readDouble();
        if (payMoney > this.money) {
            System.out.println("余额不足");
            return;
        }
        this.money -= payMoney;
        payee.money += payMoney;
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于日期格式化
        this.detail += sdf.format(date) + "\t转出" + payMoney + "给 " + payee.getName() + "\n";
        payee.detail += sdf.format(date) + "\t转入" + payMoney + "来自 " + this.getName() + "\n";
        CustomMap.saveMap();
    }

    public boolean confirmPassword() {
        System.out.print("请输入密码：");
        Scanner scanner = new Scanner(System.in);
        String pwd = scanner.next();
        return Utility.stringEncryption(pwd).equals(this.password);
    }

    private void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入修改密码：");
        this.password = Utility.stringEncryption(scanner.next());
        System.out.println("修改成功");
        CustomMap.saveMap();
    }

    public static void addCustomer() {
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

    public static Customer CustomerLogin() {
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

    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println("================" + name + "的操作界面================");
            System.out.println("\t\t1\t 查询\t\t2\t 存款");
            System.out.println("\t\t3\t 取款\t\t4\t 转账");
            System.out.println("\t\t5\t 修改密码\t0\t 退出");
            System.out.print("请输入对应的操作序号：");
            int n = Utility.readInt();
            switch (n) {
                case 1 -> print();
                case 2 -> saveMoney();
                case 3 -> withdrawMoney();
                case 4 -> transferAccounts();
                case 5 -> changePassword();
                case 0 -> {
                    System.out.println("退出程序");
                    flag = false;
                }
                default -> System.out.println("输出错误请重新操作");
            }
        }
    }
}
