package AVLTree;

public class Node<T extends Comparable<T>>{
    private T element;
    private Node<T> left;
    private Node<T> right;
    private Node<T> father;
    private int balance;
    private int height;

    public Node(){
        left = null;
        right = null;
    }
    public Node(T element) {
        this();
        this.element = element;
        balance = 0;
        height = 0;
    }

    public void hang(Node current){
        if (current == null) {
            return;
        }
        if (current.getElement().compareTo(this.getElement()) <= 0) {
            setLeft(current);
        } else {
            setRight(current);
        }
        current.setFather(this);
    }

    public T getElement() {
        return element;
    }
    public void setElement(T element) {
        this.element = element;
    }
    public Node<T> getLeft() {
        return left;
    }
    public void setLeft(Node<T> left) {
        this.left = left;
    }
    public Node<T> getRight() {
        return right;
    }
    public void setRight(Node<T> right) {
        this.right = right;
    }
    public Node<T> getFather() {
        return father;
    }
    public void setFather(Node<T> father) {
        this.father = father;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
