package LinkedList;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Node next;
        private Node prev;
        private final T element;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * Add given element in the list on index 0.
     *
     * @param element is element to be added.
     * @return 0 (element index position)
     */
    public int add(T element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
        return 0;
    }

    /**
     * Insert element of given index position in the list.
     *
     * @param element is the element to add.
     * @param pos is the index to be inserted.
     * @return int index of insertion.
     */
    public int add(T element, int pos) {
        validateIndex(pos);
        Node newNode = new Node(element);
        if (pos == 0) {
            add(element);
        } else {
            Node node = this.head;
            while (--pos > 0) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }
        this.size++;
        return pos;
    }

    /**
     * Delete given element from the list.
     *
     * @param element that needs to be removed.
     * @return index of removed element.
     */
    public int remove(T element) {
        int index = 0;
        Node node = this.head;
        Node prevNode = null;
        while (node != null) {
            if (element.equals(node.element)) {
                if (prevNode == null) {
                    this.head = node.next;
                } else {
                    prevNode.next = node.next;
                }
                this.size--;
                return index;
            } else {
                prevNode = node;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    /**
     * Get element by his index position in the list.
     *
     * @param pos is index of needed element.
     * @return needed element.
     */
    public T get(int pos) {
        validateIndex(pos);
        Node currentNode = this.head;
        int currentIndex = 0;
        T element = null;
        while (currentNode != null) {
            if (pos == currentIndex) {
                element = currentNode.element;
                break;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }
        return element;
    }

    /**
     * Delete element from the list by given index.
     *
     * @param pos is index of element that needs to be removed.
     * @return removed element.
     */
    public T remove(int pos) {
        validateIndex(pos);
        Node currentNode = this.head;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.next;
        }
        if (pos == 0) {
            this.head = currentNode.next;
        } else {
            currentNode.prev.next = currentNode.next;
        }
        this.size--;
        return currentNode.element;
    }

    /**
     * Search element in list.
     *
     * @param element is searched element.
     * @return index of searched element.
     */
    public int find(T element) {
        if (this.head == null) {
            return -1;
        }

        int index = 0;
        Node temp = this.head;

        while (temp != null) {
            if (temp.equals(element)) {
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }

    /**
     * @return int size of list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Check that list is empty, zero size.
     *
     * @return boolean: true or false.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks that given index is in range of size of list.
     *
     * @param index is index to be checked.
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

}
