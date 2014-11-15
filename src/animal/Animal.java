package animal;
import io.IO;

public class Animal {

	public static void main(String[] args) {
		//takes the text file name
		BinaryTree a = readFile(IO.readFile("src/animal/knowledge.txt"));
		BinaryTree newTree=new AnimalTree(null, null, null);
		BinaryTree oldTree=a;
		boolean again=true;

		//asks player if they want to play
		while (again){
			newTree=playGame(oldTree);
			again=IO.affirmative(IO.prompt("Would you like to play again?"));
			oldTree=newTree;
		}
		IO.stdout.println("Thanks for playing ;)");
		String s=newTree.toFile(); 
		IO.printFile(s);	
	}
	//takes in the file format of the binary tree and creates data structure
	// commas separate elements of a binary tree round brackets indicate a binary tree
	private static BinaryTree readFile (String contents){
		contents=contents.substring(1,contents.length());
		if (!(contents.contains(")"))){
			String s[] =contents.split(",");
			if (s.length> 2) return new AnimalTree(s[0], new BinaryTree(s[1]), new BinaryTree(s[2]));
			else return new AnimalTree(s[0]);
		}
		else{
			char[] ch=contents.toCharArray(); int count=0;
			String s=")";
			char p=s.charAt(0);
			for (char c: ch){
				if (c==p) count++;
			}
			if (count > 1){
				String head=contents.substring(0,contents.indexOf(","));
				String one=contents.substring(contents.lastIndexOf("(")+1,contents.indexOf(")"));
				String two=contents.substring(head.length()+1,contents.indexOf(")"));
				BinaryTree a = new AnimalTree(head, readFile(two), readFile(one));
				return a;
			}
			else{
				String head=contents.substring(0,contents.indexOf(","));
				if ((contents.indexOf(")")) > contents.lastIndexOf(",")){
					String one=contents.substring(contents.lastIndexOf("(")+1,contents.indexOf(")"));
					String two=contents.substring(contents.indexOf("), "));
					BinaryTree a = new AnimalTree(head, readFile(one), readFile(two));
					return a;
				}
				String two=contents.substring(contents.lastIndexOf("(")+1,contents.indexOf(")"));
				String one=contents.substring(head.length(),contents.lastIndexOf("("));
				BinaryTree a = new AnimalTree(head, readFile(one), readFile(two));
				return a;

			}
		}

	}
	//game logic that helps the player add nodes to the tree
	// only accounts for yes or no answers 
	public static BinaryTree playGame(BinaryTree a){
		if (IO.affirmative(IO.prompt(a.isLeaf() ? ("Is this your animal? y/n:  "+ a.toString()) : a.data().toString()))){
			if (!(a.isLeaf())){
				playGame(a.left());}
		}
		else{
			if (!(a.isLeaf())){
				playGame( a.right());}
			else{
				String animal=IO.prompt(("What is your animal?")); 
				String q=IO.prompt("Input a differentiating y/n question where your animal satisfies the condition");
				a.setLeft(new BinaryTree(a.data())); a.setData(q); a.setRight(new BinaryTree(animal));
			}
		}
		return a;
	}

}
