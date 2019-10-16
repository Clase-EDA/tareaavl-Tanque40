package AVLTree;

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {
    private int cont;
    private Node<T> root;

    public AVLTree(){
        cont = 0;
        root = null;
    }
    public AVLTree(T element){
        this();
        root = new Node<T>(element);
    }
    public AVLTree(Node<T> root){
        this();
        this.root = root;
    }


    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }
    

    public boolean contains(T element){
        return contains(element, root);
    }
    private boolean contains(T element, Node<T> current){
        if(current == null)
            return false;
        if(element.compareTo(current.getElement()) < 0)
            return contains(element, current.getLeft());
        if(element.compareTo(current.getElement()) > 0)
            return contains(element, current.getRight());
        return true;
    }

    public int height(Node<T> current){
        if(current == null)
            return -1;
        else
            return current.getHeight();
    }

    public void updateBalance(Node<T> current) {
        if (current == null) {
            return;
        }
        if (current.getLeft() != null) {
            updateBalance(current.getLeft());
        }
        if (current.getRight() != null) {
            updateBalance(current.getRight());
        }
        current.setBalance(height(current.getRight()) - height(current.getLeft()));
    }
    
    public Node<T> balance(T element, Node<T> current) {
        if (current == null) {
            return current;
        } else {
            if (element.compareTo(current.getElement()) < 0) {
                current.setLeft(balance(element, current.getLeft()));
                if (current.getBalance() == -2) {// Aqui
                    if (current.getRight() == null) {
                        current = rotateLeft(current);
                    } else {
                        current = rotateDoubleLeft(current);
                    }
                }
                if (current.getBalance() == 2) {
                    if (current.getLeft() == null) {
                        current = rotateRight(current);
                    } else {
                        current = rotateDoubleRight(current);
                    }
                }
            } else {
                current.setRight(balance(element, current.getRight()));// Recorre
                if (current.getBalance() == -2) {// Aqui
                    if (current.getRight() == null) {
                        current = rotateLeft(current);
                    } else {
                        current = rotateDoubleLeft(current);
                    }
                }
                if (current.getBalance() == 2) {
                    if (current.getLeft() == null) {
                        current = rotateRight(current);
                    } else {
                        current = rotateDoubleRight(current);
                    }
                }
            }
        }
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1);
        current.setBalance((height(current.getRight()) - height(current.getLeft())));
        return current;
    }

    public boolean insert(T element) {
        if (contains(element)) {
            return false;
        } else {
            root = insert(element, root);
            updateBalance(root);
            cont++;
            return true;
        }
    }
    private Node<T> insert(T element, Node<T> current) {
        if (current == null) {// Agrega el element
            current = new Node<T>(element);
            current.setBalance(height(current.getRight()) - height(current.getLeft()));
        } else {
            if (element.compareTo(current.getElement()) < 0) {
                current.setLeft(insert(element, current.getLeft()));// Recorre
                if (height(current.getLeft()) - height(current.getRight()) == 2) {
                    if (element.compareTo(current.getLeft().getElement()) < 0) {
                        current = rotateLeft(current);
                    } else {
                        current = rotateDoubleLeft(current);
                    }
                }
            } else {
                current.setRight(insert(element, current.getRight()));// Recorre
                if (height(current.getRight()) - height(current.getLeft()) == 2) {
                    if (element.compareTo(current.getRight().getElement()) > 0) {
                        current = rotateRight(current);
                    } else {
                        current = rotateDoubleRight(current);
                    }
                }
            }
        }
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1);
        current.setBalance(height(current.getRight()) - height(current.getLeft()));
        return current;
    }

    public boolean remove(T element) {
        if (!contains(element)) {
            return false;
        }
        Node<T> current, father = root, temp;
        current = root; // Busca
        while (current.getElement().compareTo(element) != 0) {
            father = current;
            if (element.compareTo(current.getElement()) <= 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (current == root && (current.getLeft() == null || current.getRight() == null)) {// caso especial
            if (root.getLeft() != null) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
            cont--;
            return true;
        }
        if (current.getLeft() == null && current.getRight() == null) {// caso hoja
            if (current.getElement().compareTo(father.getElement()) < 0) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
        } else {
            if (!(current.getLeft() != null && current.getRight() != null)) {// un hijo
                father.hang(current.getLeft());
                father.hang(current.getRight());
            } else {// buscar suc-in order, 2 hijos
                temp = current;
                current = current.getRight();
                father = current;
                while (current.getLeft() != null) {
                    father = current;
                    current = current.getLeft();
                }
                temp.setElement(current.getElement());
                if (father == current) {
                    temp.setRight(current.getRight());
                } else {
                    father.setLeft(current.getRight());
                }
            }
            cont--;
        }

        updateBalance(root);
        balance(element, root);
        updateBalance(root);
        return true;
    }



    public Node<T> rotateLeft(Node<T> current) {
        Node<T> temp = current.getLeft();
        current.setLeft(temp.getRight());
        temp.setRight(current);
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1);
        temp.setHeight(Math.max(height(temp.getLeft()), current.getHeight()) + 1);
        return temp;
    }
    public Node<T> rotateDoubleLeft(Node<T> current) {
        current.setLeft(rotateRight(current.getLeft()));
        return rotateLeft(current);
    }

    public Node<T> rotateRight(Node<T> current) {
        Node<T> temp = current.getRight();
        current.setRight(temp.getLeft());
        temp.setLeft(current);
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1);
        temp.setHeight(Math.max(height(temp.getRight()), current.getHeight() + 1));
        return (temp);
    }
    public Node<T> rotateDoubleRight(Node<T> current) {
        current.setRight(rotateLeft(current.getRight()));
        return rotateRight(current);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Node<T>> aux = new ArrayList<>();
        ArrayList<T> lista = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        aux.add(root);
        Node<T> temp;
        while (!aux.isEmpty()) {
            temp = aux.remove(0);
            System.out.println(temp.getElement());
            lista.add(temp.getElement());
            lista2.add(temp.getBalance());
            if (temp.getLeft() != null) {
                aux.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                aux.add(temp.getRight());
            }
        }
        while (lista.iterator().hasNext()) {
            sb.append("Element: " + lista.remove(0) + "\n");
            sb.append("Balance: " + lista2.remove(0) + "\n");
        }

        return sb.toString();
    }
}
