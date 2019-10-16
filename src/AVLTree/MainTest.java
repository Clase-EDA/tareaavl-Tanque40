package AVLTree;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree<Integer> avltree = new AVLTree<Integer>();
		
		avltree.insert(5);
		avltree.insert(2);
		avltree.insert(8);
		avltree.insert(1);
		avltree.insert(9);
		avltree.insert(7);
		avltree.insert(3);
		avltree.insert(4);
		avltree.insert(10);
		avltree.insert(6);

		System.out.println(avltree.toString());

		avltree.remove(10);
		avltree.remove(5);

		System.out.println(avltree.toString());
	}

}
