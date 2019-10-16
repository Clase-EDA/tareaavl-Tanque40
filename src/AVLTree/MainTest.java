package AVLTree;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree<Integer> avltree = new AVLTree<Integer>();
		
		avltree.insert(100);
		avltree.insert(300);
		avltree.insert(400);
		avltree.insert(350);
		avltree.insert(375);
		avltree.insert(50);
		avltree.insert(200);
		avltree.insert(360);
		avltree.insert(380);
		avltree.insert(500);
		avltree.insert(390);

		System.out.println(avltree.toString());

		/**
		 * avltree.remove(10); avltree.remove(5);
		 * 
		 * System.out.println(avltree.toString());
		 */
	}

}
