package stack;

public class SinglyLinkedList<E> {

    private Node<E> head;

    private Node<E> tail;
    private int size = 0;

    public SinglyLinkedList() {

    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public Node<E> getHead() {
        return head;
    }


    public Node<E> getTail() {
        return tail;
    }

    public E first() {
        if (isEmpty())
            return null;
        else
            return getHead().getElement();
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static class Node<E> {
        private E element;
        private Node<E> next;


        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;

        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        Node<E> node = getHead();
        if (size == 1) {
            setTail(null);
            setHead(null);
        } else {
            setHead(getHead().getNext());
        }
        setSize(size() - 1);
        return node.getElement();
    }

    public void addFirst(E element) {
        Node<E> node = new Node<>(element, getHead());
        if (isEmpty())
            setTail(node);
        setHead(node);
        size++;

    }
}
