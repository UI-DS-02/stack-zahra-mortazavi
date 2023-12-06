package stack;

import stack.SinglyLinkedList;
import stack.Stack;

public class LinkedStack<E> implements Stack<E> {
    SinglyLinkedList<E> singlyLinkedList = new SinglyLinkedList<>();

    public SinglyLinkedList<E> getSinglyLinkedList() {
        return singlyLinkedList;
    }

    public void setSinglyLinkedList(SinglyLinkedList<E> singlyLinkedList) {
        this.singlyLinkedList = singlyLinkedList;
    }

    @Override
    public int size() {
        return getSinglyLinkedList().size();
    }

    @Override
    public boolean isEmpty() {
        return getSinglyLinkedList().isEmpty();
    }

    @Override
    public void push(E e) throws Exception {
        getSinglyLinkedList().addFirst(e);
    }

    @Override
    public E top() {
        return getSinglyLinkedList().first();
    }

    @Override
    public E pop() {
        return getSinglyLinkedList().removeFirst();
    }
}
