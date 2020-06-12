package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
        private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rs1 = false;
       int i = indexOf(id);
       if (i != -1) {
           mem.set(i, model);
           rs1 = true;
       }
        return rs1;
    }

    @Override
    public boolean delete(String id) {
       boolean rs1 = false;
       int i = indexOf(id);
       if (i != -1) {
           mem.remove(i);
           rs1 = true;
       }
        return rs1;
    }

    @Override
    public T findById(String id) {
        T rs1 = null;
       int i = indexOf(id);
       if (i != -1) {
           rs1 = mem.get(i);
       }
        return rs1;
    }

    public int indexOf(String id) {
        int i = -1;
        for (int j = 0; j < mem.size(); j++) {
            if (id.equals(mem.get(j).getId())) {
                i = j;
                break;
            }
        }
        return i;
    }
}
