package stack;

public interface Stack<E> {
    int size();

    boolean isEmpty();

    void push(E e) throws Exception;

    E top();

    E pop();

}
