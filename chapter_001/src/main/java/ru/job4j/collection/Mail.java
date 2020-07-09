package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Mail {

    private Set<String> mail;

    public Mail(Set<String> mail) {
        this.mail = mail;
    }

    public Set<String> getMail() {
        return mail;
    }

    private boolean equalsMail(Set<String> one, Set<String> two) {
        boolean b = false;
        for (String set : one) {
            if (two.contains(set)) {
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mail mail1 = (Mail) o;
        return equalsMail(mail, mail1.mail);
    }

    @Override
    public int hashCode() {
        return 42; //Objects.hash(mail);
    }

    public static Map<String, Mail> users(Map<String, Mail> mail) {
        Map<Mail, Map.Entry<String, Mail>> map = new HashMap<>();
        for (Map.Entry<String, Mail> user : mail.entrySet()) {
            Map.Entry<String, Mail> m = map.get(user.getValue());
            if (m == null) {
                map.put(user.getValue(), user);
            } else {
                map.remove(user.getValue());
                m.getValue().getMail().addAll(user.getValue().getMail());
                map.put(m.getValue(), m);
            }
        }
        Map<String, Mail> mails = new HashMap<>();
        for (Map.Entry<String, Mail> v : map.values()) {
            mails.put(v.getKey(), v.getValue());
        }
        return mails;
    }
}
