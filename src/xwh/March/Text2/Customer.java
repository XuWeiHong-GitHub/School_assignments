package xwh.March.Text2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class Customer {
    public int ID;
    private String password;
    private final String name;
    private double money;
    private String detail = "";
    Date date;       //data表示日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于日期格式化

    Scanner scanner = new Scanner(System.in);

    public Customer(int ID, String password, String name, double money) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.money = money;
        date = new Date();
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
        double money = scanner.nextDouble();
        this.money += money;
        date = new Date();
        detail += sdf.format(date) + "\t存入" + money + "\n";
    }

    private void withdrawMoney() {
        System.out.print("\n请输入取款金额：");
        double money = scanner.nextDouble();
        if (money > this.money) {
            System.out.println("余额不足");
            return;
        }
        this.money -= money;
        date = new Date();
        detail += sdf.format(date) + "\t取出" + money + "\n";
    }

    private void transferAccounts() {
        System.out.print("\n请输入要转账的对象ID：");
        int id = scanner.nextInt();
        Customer payee = CustomMap.searchCustomer(id);
        if (payee == null) {
            System.out.println("不存在该用户");
            return;
        }
        System.out.print("请输入要转账的金额：");
        double payMoney = scanner.nextDouble();
        if (payMoney > this.money) {
            System.out.println("余额不足");
            return;
        }
        this.money -= payMoney;
        payee.money += payMoney;
        date = new Date();
        this.detail += sdf.format(date) + "\t转出" + payMoney + "给 " + payee.getName() + "\n";
        payee.detail += sdf.format(date) + "\t转入" + payMoney + "来自 " + this.getName() + "\n";
    }

    public boolean confirmPassword() {
        System.out.print("请输入密码：");
        String pwd = scanner.next();
        return pwd.equals(this.password);
    }

    private void changePassword() {
        System.out.println("请输入修改密码：");
        this.password = scanner.next();
        System.out.println("修改成功");
    }

    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println("================" + name + "的操作界面================");
            System.out.println("\t\t1\t 查询\t\t2\t 存款");
            System.out.println("\t\t3\t 取款\t\t4\t 转账");
            System.out.println("\t\t5\t 修改密码\t0\t 退出");
            System.out.print("请输入对应的操作序号：");
            int n = scanner.nextInt();
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
