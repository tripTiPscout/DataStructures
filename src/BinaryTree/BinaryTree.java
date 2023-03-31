package BinaryTree;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BinaryTree<T extends Comparable<T>> {

    private Node root;
    private int size;
    Set<T> elements;

    private class Node {
        T element;
        Node left;
        Node right;
        Node parent;

        private Node(T element) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public boolean add(T element) {
        Node newNode = new Node(element);
        if (this.root == null) {
            this.root = newNode;
            this.size++;
            return true;
        } else {
            Node current = this.root;
            Node parent;
            while (true) {
                parent = current;
                if (elementIsSmaller(current, element)) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        this.size++;
                        return true;
                    }
                } else if (elementIsBigger(current, element)) {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        this.size++;
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(T e) {
        if (contains(e)) {
            delete(e);
            this.size--;
            return true;
        }
        return false;
    }

    public void delete(T key) {
        if (root == null) {
            return;
        }
        if (root.element == key) {
            root = deleteNode(root);
            return;
        }
        Node current = root;
        while (true) {
            if (key.compareTo(current.element) > 0) {
                if (current.right == null )
                    break;
                else if (current.right.element == key) {
                    Node oldParent = current.right.parent;
                    current.right = deleteNode(current.right);
                    if (current.right != null)
                        current.right.parent = oldParent;
                    break;
                }
                current = current.right;
            } else {
                if (current.left == null)
                    break;
                else if (current.left.element == key) {
                    Node oldParent = current.right.parent;
                    current.left = deleteNode(current.left);
                    if (current.left != null)
                        current.left.parent = oldParent;
                    break;
                }
                current = current.left;
            }
        }
    }

    private Node deleteNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.left;
        }
        Node current = node.right;
        while (current.left != null) {
            current = current.left;
        }
        current.left = node.left;
        if (node.left!=null) {
            node.left.parent = current;
        }
        return node.right;
    }

    public boolean contains(T e) {
        return search(this.root, e);
    }

    private boolean search(Node node, T element) {
        while (node != null) {
            if (node.element.equals(element))
                return true;
            if (elementIsSmaller(node, element)) {
                node = node.left;
            } else if (elementIsBigger(node, element)) {
                node = node.right;
            }
        }
        return false;
    }

    public void print(Order o) {
        this.elements = new LinkedHashSet<>();
        if (o.toString().equalsIgnoreCase("INCREASING")) {
            System.out.println(collectElementsIncreasing(this.root).stream().map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } else if (o.toString().equalsIgnoreCase("DECREASING")) {
            System.out.println(collectElementsDecreasing(this.root).stream().map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
    }

    private Set<T> collectElementsIncreasing(Node node) {
        if (node != null) {
            collectElementsIncreasing(node.left);
            this.elements.add(node.element);
            collectElementsIncreasing(node.right);
        }
        return this.elements;
    }

    private Set<T> collectElementsDecreasing(Node node) {
        if (node != null) {
            collectElementsDecreasing(node.right);
            this.elements.add(node.element);
            collectElementsDecreasing(node.left);
        }
        return this.elements;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    private boolean elementIsSmaller(Node node, T element) {
        return element.compareTo(node.element) < 0;
    }

    private boolean elementIsBigger(Node node, T element) {
        return element.compareTo(node.element) > 0;
    }

}
