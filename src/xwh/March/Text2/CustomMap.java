package xwh.March.Text2;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author 许伟鸿
 * @version 1.0
 */
public class CustomMap {

    private static final HashMap<Integer, Customer> customerMap = new HashMap<>();

    public static void addCustomer(Customer customer) {
        if (customerMap.containsKey(customer.ID)) {
            System.out.println("该用户已经存在，创建失败");
            return;
        }
        customerMap.put(customer.ID, customer);
        System.out.println("创建成功");
    }

    public static void deleteCustomer(int id) {
        if (!customerMap.containsKey(id)) {
            System.out.println("该用户不存在，删除失败");
            return;
        }
        if (Objects.requireNonNull(searchCustomer(id)).confirmPassword()) {
            customerMap.remove(id);
            System.out.println("删除成功");
        }
    }

    public static Customer searchCustomer(int id) {
        if (!customerMap.containsKey(id)) {
            return null;    //不存在
        }
        return customerMap.get(id);
    }

}
