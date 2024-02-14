public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size++;
        Node x = new Node(item, sentinel.next);
        sentinel.next.prev = x;
        sentinel.next = x;
        x.prev = sentinel;

    }

    public void addLast(T item) {
        size++;
        Node x = new Node(item, sentinel);
        x.prev = sentinel.prev;
        sentinel.prev.next = x;
        sentinel.prev = x;

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        size--;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        size--;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        return item;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        Node p = sentinel.next;
        while (index > 0 && p != sentinel) {
            index--;
            p = p.next;
        }
        if (p == sentinel) return null;
        else return p.item;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.print("\n");
    }

    private T getRecursive(Node head, int index) {
        if (head == sentinel || head == null) return null;
        if (index == 0) return head.item;
        else return getRecursive(head.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
            prev = null;
        }

        public Node() {
            next = null;
            prev = null;
        }
    }

}

