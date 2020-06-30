package ru.job4j.collection;

import java.util.*;

public class HashData<K, V> implements Iterable<V> {
    private Node<K, V>[] hashTable;
    private int size = 0;
    private int modCount;
    private static final float LOAD_FACTOR = 0.75f;

    public HashData() {
        hashTable = new Node[16];
    }

    public boolean insert(K key, V value) {
        if (size + 1 >= hashTable.length * LOAD_FACTOR) {
            arrayDoubling();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }
        List<Node<K, V>> node = hashTable[index].getNext();
        for (Node<K, V> nodeList : node) {
            if (keyExistButValueNew(newNode, nodeList)) {
                return true;
            }
        }
        return false;
    }

    private boolean keyExistButValueNew(Node<K, V> newNode, Node<K, V> node) {
        if (newNode.key.hashCode() == node.key.hashCode()
                && Objects.equals(newNode.key, node.key)
                && !Objects.equals(newNode.value, node.value)) {
            newNode.value = node.value;
            return true;
        }
        return false;
    }

    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNext().add(newNode);
        size++;
        modCount++;
        return true;
    }

    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[hashTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> n : node.getNext()) {
                    insert(n.key, n.value);
                }
            }
        }
    }

    V get(K key) {
        V result = null;
        int index = hash(key);
        if (hashTable[index] != null) {
            for (Node<K, V> node : hashTable[index].getNext()) {
                if (key.hashCode() == node.key.hashCode() && Objects.equals(key, node.key)) {
                    result = node.value;
                    break;
                }
            }
        }
        return result;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            return false;
        }
        if (hashTable[index].getNext().size() == 1) {
            hashTable[index] = null;
            return true;
        }
        if (hashTable[index] != null) {
            for (Node<K, V> newNode : hashTable[index].getNext()) {
                if (key.hashCode() == newNode.key.hashCode()
                        && Objects.equals(key, newNode.key)) {
                    hashTable[index].getNext().remove(newNode);
                    size--;
                    modCount++;
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(K key) {
        int h = key.hashCode();
        h = h ^ (h >>> 16);
        return h & (hashTable.length - 1);
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int arrayCounter = 0;
            int valuesCounter = 0;
            int count = modCount;
            Iterator<Node<K, V>> subIterator = null;

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (valuesCounter == size) {
                    return false;
                }
                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[arrayCounter].getNext().iterator();
                    } else {
                        return false;
                    }
                }

                return subIterator.hasNext();
            }

            private boolean moveToNextCell() {
                arrayCounter++;

                while (arrayCounter < hashTable.length && hashTable[arrayCounter] == null) {
                    arrayCounter++;
                }
                return arrayCounter < hashTable.length && hashTable[arrayCounter] != null;
            }

            @Override
            public V next() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                valuesCounter++;
                return subIterator.next().getValue();
            }
        };
    }

    private  class Node<K, V> {
        private List<Node<K, V>> next;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = new LinkedList<>();
        }

        public List<Node<K, V>> getNext() {
            return next;
        }

        public V getValue() {
            return value;
        }
    }
}