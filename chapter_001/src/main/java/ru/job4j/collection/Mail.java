package ru.job4j.collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Mail {

    public static Map<String, Set<String>> mails(Map<String, Set<String>> res) {
        Map<String, Set<String>> user = new TreeMap<>();
        Map<String, String> result = new TreeMap<>();
        for (String map : res.keySet()) {
            String users = null;
            for (String use : res.get(map)) {
                users = result.get(use);
                if (users == null) {
                    result.put(use, map);
                } else {
                    break;
                }
            }
            if (users == null) {
                user.put(map, res.get(map));
            } else {
                user.get(users).addAll(res.get(map));
                for (String s : res.get(map)) {
                    result.put(s, users);
                }
            }
        }
        return user;
    }
}
