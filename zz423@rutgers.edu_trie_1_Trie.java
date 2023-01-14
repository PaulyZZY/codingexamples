package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */



public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		TrieNode trieNode = new TrieNode(null,null,null);
		if(allWords.length == 0) {
			return null;
		}
		else if (allWords.length == 0) {
			return null;
		}
		String firststr = allWords[0];		
		String secondstr= allWords[0];
		String second2str= allWords[0];
		String second4str= allWords[0];
		trieNode.firstChild = new TrieNode(new Indexes(0,(short)0,(short)(firststr.length()-1)),null,null);
		
		if(allWords.length == 1) {
			return trieNode;
		}else if (allWords.length == 1) return trieNode;
		
		for(int i = 1; i < allWords.length; i ++) {
			boolean s = false;
			String insertStr = allWords[i];
			int end_ins = insertStr.length()-1;
			Indexes insert = new Indexes(i,(short)0,(short)end_ins);
			boolean xxxx1 = true;
			boolean xxxx12 = true;
			boolean xxxx123 = false;
			boolean xxxx1234 = false;

			TrieNode temp1 = trieNode;
			TrieNode temp2 = trieNode.firstChild; 
			TrieNode temp3 = trieNode.firstChild; 
			TrieNode temp4 = trieNode.firstChild; 
			int endind = -1;
			String str;
				do {
					if(temp2.substr.endIndex == allWords[temp2.substr.wordIndex].length()-1) {
						 str = allWords[temp2.substr.wordIndex].substring(temp2.substr.startIndex);
					}else {
						str = allWords[temp2.substr.wordIndex].substring(temp2.substr.startIndex,temp2.substr.endIndex+1);
						temp4=null;
					}	
					xxxx123 = false;
					xxxx1234 = false;
					for(int b = temp2.substr.startIndex, a = 0; b < insertStr.length() && a < str.length(); a ++,b++) {
							if(str.charAt(a) == insertStr.charAt(b)) {
								endind ++;
								xxxx123 = true;
								if(b == temp2.substr.endIndex) {
									xxxx1234 = true;
									xxxx1 = false;
									temp4=null;
									temp3=null;
									
								}
							}else {
								temp4=null;
								temp3=null;
								break;
							}
					}
					Boolean InTrue;
					if(temp2.sibling == null) {
						InTrue = false;
					}else {
						InTrue = true;
						xxxx12 = false;
						temp4=null;
						temp3=null;
					}

					if(xxxx123 == false && InTrue == true) {
						temp1 = temp2;
						temp2 = temp2.sibling;
						s = true;
						xxxx12 = false;
						temp4=null;
						temp3=null;
					}
					
					else if(xxxx1234 && temp2.firstChild != null) {
						temp1 = temp2;
						temp2 = temp2.firstChild;
						endind = -1;
						s = false;
						xxxx12 = false;
						temp4=null;
						temp3=null;
					}
					else {
						temp4=null;
						temp3=null;
						break;
					}
			
				}while(xxxx1234 == true || xxxx123 == false);
				if(!xxxx123) {
					insert.startIndex = temp2.substr.startIndex;
					temp2.sibling = new TrieNode (insert,null,null);
				}
				if(xxxx123) {
					if(!s) {						
					temp1.firstChild = new TrieNode(new Indexes(temp2.substr.wordIndex,(short)(temp2.substr.startIndex),(short)(endind+temp2.substr.startIndex)),temp2,temp2.sibling);
					temp2.substr.startIndex = (short) (temp1.firstChild.substr.endIndex + 1);
					insert.startIndex = temp2.substr.startIndex;
					temp2.sibling = new TrieNode(insert,null,null);
					}else {
						temp1.sibling = new TrieNode(new Indexes(temp2.substr.wordIndex, (short)temp2.substr.startIndex, (short)(endind+temp2.substr.startIndex)),temp2,temp2.sibling);
						temp2.substr.startIndex = (short) (temp1.sibling.substr.endIndex + 1);
						insert.startIndex = temp2.substr.startIndex;
						temp2.sibling = new TrieNode(insert,null,null);
						xxxx12 = false;
						
					}
				}
		}
		
		return trieNode;
	}
	
	private static String stringFindSame(String temp1, String str ) {
		String temp = null;
		
		if(temp1.length() < str.length()) {
			
			for(int a = 0; a < temp1.length(); a ++) {
				
				if(temp1.charAt(a) == str.charAt(a)) {
					
						temp += temp1.charAt(a);
				}else
					break;
			}
		}else {
			for(int a = 0; a < str.length(); a ++) {
				
				if(temp1.charAt(a) == str.charAt(a)) {
					
					if(temp == null) {
						
						temp = Character.toString(str.charAt(a));
						
					}else {
						
						temp += Character.toString(str.charAt(a));
						
					}
				}else {
					
					break;
				}
			}
		}
		return temp;
	}


	
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		if(root == null) {
			return null;
		}
		
		String string = prefix;
		TrieNode temp2 = root.firstChild;
		TrieNode tempXYZ = root;
		int tempX=0;
		
		ArrayList<TrieNode> arrayList = new ArrayList<>();
		while (!string.equals("")){
			
			while (! matchtheelement (string, allWords[temp2.substr.wordIndex].substring((int)temp2.substr.startIndex,(int)temp2.substr.endIndex + 1))) {
				tempXYZ = temp2;
				temp2 = temp2.sibling;

		
			}
			
		
			
			String stringstr = allWords[temp2.substr.wordIndex];
		
			stringstr = stringstr.substring((int) temp2.substr.startIndex,(int)(temp2.substr.endIndex + 1));
			String stringFindSame = stringFindSame(string,stringstr);
			
			if(string.length() <= stringstr.length() && stringFindSame.length()<=stringstr.length()) {
				string = "";
			}else {
				
				if(prefix.length() < (int)temp2.substr.endIndex +1) {
					
				string = string.substring(prefix.length() - temp2.substr.startIndex);
				
				}else {
					
					string = string.substring(temp2.substr.endIndex + 1 - temp2.substr.startIndex);
					
				}
				}
				tempXYZ = temp2;
				temp2 = temp2.firstChild;	
		}
		
			TrieNode temp2_new = null;
			if(temp2 == null) {
				arrayList.add(tempXYZ);
				return arrayList;
			}
			
			while(temp2!=null) {
				temp2_new = temp2;
				
				while(temp2_new.firstChild != null) {
					
					temp2_new = temp2_new.firstChild;
				}
				while(temp2_new != null) {
					arrayList.add(temp2_new);
					if(temp2_new.equals(temp2)) {
						break;
					}
					temp2_new = temp2_new.sibling;
				}	
				temp2 = temp2.sibling;
			}
		
		return arrayList;	
	}
	public static void printElement(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	private static boolean matchtheelement(String node, String string) {
		String str="";
		String str2="";
		String str3="";
		String str4="";
		if(node.length()< string.length()) {
			
			for(int k = 0; k < node.length(); k ++) {
				
				if(node.charAt(k) == string.charAt(k)) {
					str2="";
					str3="";
					str4="";
					continue;
					
				}else {
					str3="";
					str4="";
					return false;
				}
			}
		}
		else {
			for(int k = 0; k < string.length(); k ++) {
				
				if(node.charAt(k) == string.charAt(k)) {
					str3="";
					str4="";
					continue;
					
				}else {
					str="";
					str4="";
					return false;
				}
			}
		}
		return true;
	}
	private static int SameElement(String node, String string) {
		String str="";
		String str2="";
		String str3="";
		String str4="";
		if(node.length()< string.length()) {
			
			for(int k = 0; k < node.length(); k ++) {
				
				if(node.charAt(k) == string.charAt(k)) {
					str3="";
					str4="";
					continue;
					
				}else {
					return -1;
				}
			}
		}
		else {
			for(int k = 0; k < string.length(); k ++) {
				
				if(node.charAt(k) == string.charAt(k)) {
					str3="";
					str4="";
					continue;
					
				}else {
					str="";
					str3="";
					return -1;
				}
			}
		}
		return 1;
		
	}
	

	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
