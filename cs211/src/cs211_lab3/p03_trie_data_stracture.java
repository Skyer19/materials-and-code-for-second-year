package cs211_lab3;

/*
 * Mr.Lin said that I can put off handing my lab
 * assignment until 8 o'clock 
 * Thanks.
 */

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class trie {
	private Node root = new Node();

	// startsWith method
	public boolean startsWith(String prefix) {
		if (searchNode(prefix) == null)
			return false;
		else
			return true;
	}

	// searchNode method
	private Node searchNode(String str) {
		Node c = root;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (c.children.containsKey(ch)) {
				c = c.children.get(ch);
			} else {
				return null;
			}
		}
		return c;
	}

	// search method
	public boolean search(String word) {
		Node tn = searchNode(word);
		if (tn != null && tn.leaf) {
			return true;
		} else {
			return false;
		}
	}

	public List<String> searchWords(String prefix) {
		Node c = root;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (c.children.containsKey(ch)) {
				return null;
			} else {
				sb.append(ch);
				c = c.children.get(ch);
			}
		}
		List<String> list = new ArrayList<>();
		dfs(c, sb.toString(), list);
		return list;
	}

	public void dfs(Node node, String prefix, List<String> list) {
		if (node.leaf) {
			list.add(prefix);
		}
		for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
			dfs(entry.getValue(), prefix + entry.getKey(), list);
		}
	}

	// delete method
	public boolean delete(String word) {
		Node c = root;
		Node lastBranchNode = null;
		Character lastBrachChar = null;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (c.children.containsKey(ch)) {
				if (c.children.size() > 1) {
					lastBranchNode = c;
					lastBrachChar = ch;
				}
				c = c.children.get(ch);
			} else {
				return false;
			}
		}

		if (c.children.size() > 0) {
			c.leaf = false;
			return true;
		}
		if (lastBranchNode != null) {
			lastBranchNode.children.remove(lastBrachChar);
			return true;
		} else {
			root.children.remove(word.charAt(0));
			return true;
		}
	}
	
	// insert method
	public void insert(String word) {
		Node c = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!c.children.containsKey(ch)) {
				c.children.put(ch, new Node());
			}
			c = c.children.get(ch);
		}

		c.leaf = true;
	}

}

// create the node
class Node {
	public Map<Character, Node> children;
	public boolean leaf;

	public Node() {
		children = new HashMap<Character, Node>();
		leaf = false;
	}
}

// main method
public class p03_trie_data_stracture {

	public static void main(String[] args) {
	
		// date String
		String[] data = { "to", "tea", "ted", "test", "a", "abstract", "in", "inn", "indoor", "zoo" };
		
		trie dic = new trie();
		groupInsert(data, dic);
		
		if (dic.search("the")) {
			System.out.println("Output: the");
		} else {
			System.out.println("Output: false");
		}
		if (dic.search("ted")) {
			dic.delete("ted");
			System.out.println("Output: success");
		} else {
			System.out.println("Output: false");
		}
		
		showHeight(data);
		data = update("in the end", data);
		dic.insert("in the end");
		showHeight(data);
		
		
		if (dic.search("end")) {
			System.out.println("Output: end");
		} else {
			System.out.println("Output: false");
		}
		if (dic.search("in")) {
			dic.delete("in");
			System.out.println("Output: success");
		} else {
			System.out.println("Output: false");
		}
		showHeight(data);
		data = update("i love data structure and java.", data);
		dic.insert("i love data structure and java.");
		showHeight(data);
	}

	// groupInsert method
	public static void groupInsert(String[] data, trie dic) {
		for (int i = 0; i < data.length; i++) {
			dic.insert(data[i]);
		}
	}

	// update method
	public static String[] update(String a, String[] val) {
		String[] s = new String[val.length + 1];
		for (int i = 0; i < val.length; i++) {
			s[i] = val[i];
		}
		s[val.length] = a;
		return s;
	}

	// showHeight method
	public static void showHeight(String[] val) {
		int height = -1;
		for (int i = 0; i < val.length; i++) {
			height = Math.max(height, val[i].length());
		}
		System.out.println("Height= " + height);
	}

}