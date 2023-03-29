package LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedListCustom<E> implements Iterable<E> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Node next;
        private Node prev;
        private final E element;

        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * @return int size of list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Check that list is empty, zero size.
     * @return boolean: true or false.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Add element in front of the list on index position 0.
     *
     * @param element is the element to be added.
     */
    public void addFirst(E element) {
        Node newNode = new Node(element);
        if (this.head != null) {
            newNode.next = this.head;
            this.head.prev = newNode;
        }
        this.head = newNode;
        if (isEmpty()) {
            this.tail = this.head;
        }
        this.size++;
    }

    /**
     * Add element in the end of the list
     * on last index position (index: size - 1).
     *
     * @param element is the element to be added.
     */
    public void addLast(E element) {
        if (isEmpty()) {
            addFirst(element);
            return;
        }
        Node newNode = new Node(element);
        this.tail.next = newNode;
        newNode.prev = this.tail;
        this.tail = newNode;
        this.size++;
    }

    /**
     * Insert element on specific index position.
     *
     * @param index is index position that element to be added.
     * @param element is the element to be added.
     */
    public void add(int index, E element) {
        validateIndex(index);
        Node newNode = new Node(element);
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else if (index == this.size) {
            addLast(element);
        } else {
            Node node = this.head;
            while (--index > 0) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }
        this.size++;
    }

    /**
     * Removes the first element (element on index: 0) in the list.
     *
     * @return the removed element.
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Can not remove element from empty list.");
        }
        E removedElement = this.head.element;
        this.head = this.head.next;
        if (this.size > 1) {
            this.head.prev = null;
        }
        this.size--;
        if (this.isEmpty()) {
            this.head = this.tail = null;
        }
        return removedElement;
    }

    /**
     * Removes the last element (element on last index: size - 1) in the list.
     *
     * @return the removed element.
     */
    public E removeLast() {
        if (this.size < 2) {
            return removeFirst();
        }
        E removedElement = this.tail.element;
        this.tail = this.tail.prev;
        this.tail.next = null;
        this.size--;
        return removedElement;
    }

    /**
     * Removes element from specific index position in the list.
     *
     * @param index is the index of element to be removed.
     * @return the removed element.
     */
    public E remove(int index) {
        validateIndex(index);
        Node currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        if (index == 0) {
            this.head = currentNode.next;
        } else {
            currentNode.prev.next = currentNode.next;
        }
        this.size--;
        return currentNode.element;
    }

    /**
     * Delete given element from the list.
     *
     * @param element is the element to be removed.
     * @return the index position of the removed element.
     */
    public int remove(E element) {
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
        return noSuchElementMessage();
    }

    /**
     * Get the element on last index (size - 1) in the list.
     *
     * @return the last element in the list.
     */
    public E getLast() {
        return this.tail.element;
    }

    /**
     * Get the element on the first index (0) in the list.
     *
     * @return the first element in the list.
     */
    public E getFirst() {
        return this.head.element;
    }

    /**
     * Get element on specific index position in the list
     * or exception if given index is invalid.
     *
     * @param index is the index position of searched element.
     * @return the element of given index position.
     */
    public E getBy(int index) {
        validateIndex(index);
        Node currentNode = this.head;
        int currentIndex = 0;
        E element = null;
        while (currentNode != null) {
            if (index == currentIndex) {
                element = currentNode.element;
                break;
            }
            currentIndex++;
            currentNode = currentNode.next;
        }
        return element;
    }

    /**
     * Get the index position of given element from the list
     * or exception if no such element.
     *
     * @param element is the element that index is searched.
     * @return the index position of given element.
     */
    public int getIndexOf(E element) {
        int index = 0;
        for (E e : this) {
            if (e.equals(element)) {
                return index;
            }
            index++;
        }
        return noSuchElementMessage();
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

    /**
     * Exception message for invalid element.
     */
    private int noSuchElementMessage() {
        throw new IllegalStateException("There is no such element");
    }

    /**
     * Print all elements in the list on one line separated by empty space.
     */
    public void print() {
        StringBuilder result = new StringBuilder();
        Node node = this.head;
        while (node != null) {
            result.append(node.element).append(" ");
            node = node.next;
        }
        result.deleteCharAt(result.length() - 1);
        System.out.println(result);
    }

    /**
     * Performs the given action for each element of the Iterable list.
     *
     * @param consumer is the action to be performed for each element.
     */
    public void forEach(Consumer<? super E> consumer) {
        Node currentNode = this.head;
        while (currentNode != null) {
            consumer.accept(currentNode.element);
            currentNode = currentNode.next;
        }
    }

    /**
     * @return iterator over elements of the iterable list.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                E element = currentNode.element;
                currentNode = currentNode.next;
                return element;
            }
        };
    }

    /**
     * @return String representation of all elements in the list.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("List elements::->");
        Node temp = this.head;
        while (temp != null) {
            builder.append(temp.element).append("->");
            if (temp == this.tail) builder.append("::End");
            temp = temp.next;
        }
        return builder.toString();
    }

}
