package xwh.March.Text2;

import java.io.*;
import java.util.Hashtable;
import java.util.Objects;

/**
 * @author 许伟鸿
 * @version 2.0
 */
public class CustomMap implements Serializable{

    //存储用户数据的 Hashtable
    private static Hashtable<Integer, Customer> customerMap = new Hashtable<>();
    private static final String filePath = "C:\\Users\\86135\\Desktop\\program\\java\\School assignments\\src\\CustomMap.dat";

    public static void addCustomer(Customer customer) {
        if (customerMap.containsKey(customer.ID)) {
            System.out.println("该用户已经存在，创建失败");
            return;
        }
        customerMap.put(customer.ID, customer);
        System.out.println("创建成功");
        saveMap();
    }

    public static void deleteCustomer(int id) {
        if (!customerMap.containsKey(id)) {
            System.out.println("该用户不存在，删除失败");
            return;
        }
        if (Objects.requireNonNull(searchCustomer(id)).confirmPassword()) {
            customerMap.remove(id);
            System.out.println("删除成功");
            saveMap();
        }
    }

    public static Customer searchCustomer(int id) {
        if (!customerMap.containsKey(id)) {
            return null;    //不存在
        }
        return customerMap.get(id);
    }

    public static void initMap(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            customerMap = (Hashtable<Integer, Customer>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("初始化数据失败");
            return;
        }
        System.out.println("数据初始化成功");
    }

    public static void saveMap()  {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(customerMap);
            oos.close();
        } catch (IOException e) {
            System.out.println("数据保存失败");
        }
    }
}
