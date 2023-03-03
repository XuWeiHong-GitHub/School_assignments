package xwh.March.Text2;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class Customer {
    public int ID;
    private String password;
    private String name;
    private double money;
    private String detail;

    Scanner scanner = new Scanner(System.in);

    public Customer(int ID, String password, String name, double money) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.money = money;
    }

    private void print() {
        System.out.println("您的当前存款为" + money);
    }

    private void saveMoney() {
        System.out.print("请输入存款金额：");
        double money = scanner.nextDouble();
        this.money += money;
    }

    private void withdrawMoney() {
        System.out.print("请输入取款金额：");
        double money = scanner.nextDouble();
        this.money -= money;
    }

    private void transferAccounts() {
        System.out.print("请输入要转账的对象ID：");
        int id = scanner.nextInt();
        Customer payee = CustomMap.searchCustomer(id);
        if (payee == null) {
            System.out.println("不存在该用户");
            return;
        }
        System.out.print("请输入要转账的金额：");
        double payMoney = scanner.nextDouble();
        this.money -= payMoney;
        payee.money += payMoney;
    }

    public boolean confirmPassword() {
        System.out.print("请输入密码：");
        String pwd = scanner.next();
        return pwd.equals(this.password);
    }

    private void changePassword(){
        System.out.println("请输入修改密码：");
        String pwd = scanner.next();
        this.password = pwd;
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
                case 1:
                    print();
                    break;
                case 2:
                    saveMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferAccounts();
                    break;
                case 5:
                    changePassword();
                    break;
                case 0:
                    System.out.println("退出程序");
                    flag = false;
                    break;
                default:
                    System.out.println("输出错误请重新操作");
            }
        }
    }
}
