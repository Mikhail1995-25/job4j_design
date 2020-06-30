package ru.ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);

    }

    @Override
    public boolean add(E parent, E child) {
        boolean rs1 = false;
        Optional<Node<E>> par = findBy(parent);
        if (par.isPresent()) {
            Optional<Node<E>> ch = findBy(child);
            if (!ch.isPresent()) {
                Node node = par.get();
                node.children.add(new Node(child));
                rs1 = true;
            }
        }
        return rs1;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rs1 = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rs1 = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rs1;
    }
    public boolean isBinary() {
        boolean rs1 = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() <= 2) {
                rs1 = true;
                break;
            }
        }
        return rs1;
    }
}
