public class ArrayDeque<T> {
    private T[] elems;
    private int logicalSize;
    private int allocatedSize = 8;

    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        logicalSize = 0;
        elems = (T[]) new Object[allocatedSize];
        nextFirst = 4;
        nextLast = 5;
    }

    public boolean isEmpty() {
        return (logicalSize == 0);
    }

    public int size() {
        return logicalSize;
    }


    public T get(int index) {
        index = (nextFirst + index + 1) % allocatedSize;

        return elems[index];
    }

    public void printDeque() {
        int index = (nextFirst + 1) % allocatedSize;
        while (index != nextLast) {
            System.out.print(elems[index]);
            System.out.print(" ");
            index = (index + 1) % allocatedSize;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (logicalSize == 0) {
            return null;
        }
        logicalSize--;
        nextFirst = (nextFirst + 1) % allocatedSize;
        T item = elems[nextFirst];

        if (logicalSize < 0.25 * allocatedSize && logicalSize > 16) {
            int capacity = (int) (0.5 * allocatedSize);
            resize(capacity);
        }

        return item;
    }

    public T removeLast() {
        if (logicalSize == 0) {
            return null;
        }
        logicalSize--;
        nextLast = (nextLast - 1 + allocatedSize) % allocatedSize;
        T item = elems[nextLast];

        if (logicalSize < 0.25 * allocatedSize && logicalSize > 16) {
            int capacity = (int) (0.5 * allocatedSize);
            resize(capacity);
        }

        return item;
    }

    public void addFirst(T item) {
        if (logicalSize == allocatedSize - 1) {
            int capacity = 2 * allocatedSize;
            resize(capacity);
        }

        elems[nextFirst] = item;
        logicalSize++;
        nextFirst = (nextFirst - 1 + allocatedSize) % allocatedSize;
    }

    public void addLast(T item) {
        if (logicalSize == allocatedSize - 1) {
            int capacity = 2 * allocatedSize;
            resize(capacity);
        }

        elems[nextLast] = item;
        logicalSize++;
        nextLast = (nextLast + 1) % allocatedSize;
    }

    private void resize(int capacity) {
        T[] newlist = (T[]) new Object[capacity];
        int first = (nextFirst + 1) % allocatedSize;
        int last = (nextLast - 1 + allocatedSize) % allocatedSize;

        int pos = (int) (0.25 * capacity);
        if (first <= last) System.arraycopy(elems, 0, newlist, pos, logicalSize);

        else {
            int num = logicalSize - last - 1;
            System.arraycopy(elems, first, newlist, pos, num);
            System.arraycopy(elems, 0, newlist, pos + num, last + 1);
        }

        nextFirst = pos - 1;
        nextLast = (nextFirst + logicalSize + 1) % capacity;
        elems = newlist;
        allocatedSize = capacity;
    }


}
