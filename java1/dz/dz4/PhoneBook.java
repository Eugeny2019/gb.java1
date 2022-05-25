package java1.dz.dz4;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String, String> phoneBook = new HashMap<>();

    public void add(String phone, String name) {
        phoneBook.put(phone, name);
    }

    public String get(String name) {
        String phone = "";
        for (String searchPhone : phoneBook.keySet()) {
            if (phoneBook.get(searchPhone).equals(name)) {
                phone = searchPhone;
                break;
            }
        }
        return phone;
    }
}
