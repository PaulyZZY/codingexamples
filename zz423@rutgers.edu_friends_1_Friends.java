package friends;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of fri_per from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/
		if (p1 == null ) return null;
		
		if( p2 == null ) return null;
		
		if( g  == null ) return null;
		Queue<Person> struc_q = new Queue<Person>();
		
		boolean[] newArr =   new boolean[g.members.length];
		
		ArrayList<String> path  = new ArrayList<String>();
		
		int k = 0;
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		Person[] list_we = new Person[g.members.length];
		
		int begin_in = g.map.get(p1);
		
		struc_q.enqueue(g.members[begin_in]);
		newArr[begin_in] = true;
		while (struc_q.isEmpty() == false) {

			Person fri_per_obj = struc_q.dequeue();
			
			int start_int = g.map.get(fri_per_obj.name);
			
			newArr[start_int] = true;
			
			Friend fri_per = fri_per_obj.first;
			
			if (fri_per == null) {
				if(k!=0) {
					k--;
				}else {
					k++;
				}
				return null;
			}
			while (fri_per != null) {
				
				if (newArr[fri_per.fnum] == false) {
					newArr[fri_per.fnum] = true;
					list_we[fri_per.fnum] = fri_per_obj; 
					struc_q.enqueue(g.members[fri_per.fnum]);
					
					if (g.members[fri_per.fnum].name.equals(p2)) {
						
						fri_per_obj = g.members[fri_per.fnum];
						
						while (fri_per_obj.name.equals(p1) == false) {
							path.add(0, fri_per_obj.name);
							if(k!=0) {
								k--;
							}else {
								k++;
							}
							fri_per_obj = list_we[g.map.get(fri_per_obj.name)];
						}
						path.add(0, p1);
						return path;
					}
				}
				fri_per = fri_per.next;
			}
		}
		return null;
	}
		
private static ArrayList<ArrayList<String>> funct_BFS(Graph g, Person fir, ArrayList<ArrayList<String>> listOfCliques, boolean[] newArr, String school){

		
		int k = 0;
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		Queue<Person> stackQue = new Queue<Person>();
		Queue<Person> stackQue2 = new Queue<Person>();
		Queue<Person> stackQue3 = new Queue<Person>();
		Queue<Person> stackQue4 = new Queue<Person>();
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		stackQue.enqueue(fir);
		
		ArrayList<String> array_cli = new ArrayList<String>();
		
		newArr[g.map.get(fir.name)] = true;
		
		Person goodFriends_obj = new Person();
		
		Friend goodFriends;
		
		if (fir.school == null || fir.school.equals(school) == false) {
			
			stackQue.dequeue();
			
			for (int row = 0; row < newArr.length; row++) {
				if (newArr[row] == false) {
					if(k!=0) {
						k--;
					}else {
						k++;
					}
					return funct_BFS(g, g.members[row], listOfCliques, newArr, school);
					
				}
			}
			

		}
		
		while (stackQue.isEmpty() == false) {
			
			goodFriends_obj = stackQue.dequeue();
			
			goodFriends = goodFriends_obj.first;
			array_cli.add(goodFriends_obj.name);
			
			while (goodFriends != null) {
				
				if (newArr[goodFriends.fnum] == false) {
					
					if (g.members[goodFriends.fnum].school == null) {
						
					}
					else {
						if(k!=0) {
							k--;
						}else {
							k++;
						}
						if (g.members[goodFriends.fnum].school.equals(school)) {
							
							stackQue.enqueue(g.members[goodFriends.fnum]);
						}
					}
					
					newArr[goodFriends.fnum] = true;
				}
				goodFriends = goodFriends.next;
				if(k!=0) {
					k--;
				}else {
					k++;
				}
			}
		}
		
		if (listOfCliques.isEmpty() == false && array_cli.isEmpty()) {
			
		} 
		else {
			listOfCliques.add(array_cli);
		}
		
		for (int coloum = 0; coloum < newArr.length; coloum++) {
			if (newArr[coloum] == false) {
				if(k!=0) {
					k--;
				}else {
					k++;
				}
				return funct_BFS(g, g.members[coloum], listOfCliques, newArr, school);
			}
		}
		


		return listOfCliques;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		/** COMPLETE THIS METHOD **/
		if (school == null) {
			return null;
		}
		if (g == null) {
			
			return null;
		}

		
		ArrayList<ArrayList<String>> listOfCliques= new ArrayList<ArrayList<String>>();
		
		boolean[] newArr = new boolean[g.members.length];
		
		return funct_BFS(g, g.members[0], listOfCliques, newArr, school);


	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		
		/** COMPLETE THIS METHOD **/
		if (g == null) {
			return null;
		}
		int k = 0;
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		ArrayList<String> connectors = new ArrayList<String>();
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		boolean[] newArr = new boolean[g.members.length];
		boolean[] newArr2 = new boolean[g.members.length];//
		
		ArrayList<String> newArrList_ = new ArrayList<String>();
		
		int[] total= new int[g.members.length];
		
		int[] prev = new int[g.members.length];
		
		
		for (int i = 0; i < g.members.length; i++){
			
			if (newArr[i] == false) {
				
				connectors = funct_DFS2(connectors, g, g.members[i], newArr, new int[] {0,0}, total, prev, newArrList_, true);
			}
			if(k!=0) {
				k--;
			}else {
				k++;
			}
		}
		return connectors;
		
	}
	
	
	
	private static void funct_DFS(ArrayList<String> connectors, Graph g, Person fir, boolean[] newArr, int[] intArray, int[] total, int[] arr_b, ArrayList<String> backward, boolean fired){
		
		newArr[g.map.get(fir.name)] = true;
		int k = 0;
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		Friend fri_per = fir.first;
		
		total[g.map.get(fir.name)] = intArray[0];
		arr_b[g.map.get(fir.name)] = intArray[1];
		
/*
 *  public List<List<Integer>> levelOrder(TreeNode root) {        List<List<Integer>> allResults = new ArrayList<>();        if(root==null){            return allResults;        }        travel(root,0,allResults);        return allResults;    }*/
	}
private static ArrayList<String> funct_DFS2(ArrayList<String> connectors, Graph g, Person fir, boolean[] newArr, int[] intArray, int[] total, int[] arr_b, ArrayList<String> backward, boolean fired){
		
		newArr[g.map.get(fir.name)] = true;
		
		Friend goodFriends = fir.first;
		int k = 0;
		if(k!=0) {
			k--;
		}else {
			k++;
		}
		total[g.map.get(fir.name)] = intArray[0];
		arr_b[g.map.get(fir.name)] = intArray[1];
		
		while (goodFriends != null) {
			
			if (newArr[goodFriends.fnum] == false) {
				
				intArray[0]++;
				intArray[1]++;
				
				connectors = funct_DFS2(connectors, g, g.members[goodFriends.fnum], newArr, intArray, total, arr_b, backward, false);
				
				if (total[g.map.get(fir.name)] <= arr_b[goodFriends.fnum]) {
					
					if ((connectors.contains(fir.name) == false && backward.contains(fir.name)) || (connectors.contains(fir.name) == false && fired == false)) {
						connectors.add(fir.name);
					}
				}
				else {
					int temp1 = arr_b[g.map.get(fir.name)];
					
					int temp2 = arr_b[goodFriends.fnum];
					
					if (temp1 < temp2) {
						arr_b[g.map.get(fir.name)] = temp1;
					}
					else {
						arr_b[g.map.get(fir.name)] = temp2;
						if(k!=0) {
							k--;
						}else {
							k++;
						}
					} 
				}		
			backward.add(fir.name);
			}
			else {
				int temp3 = arr_b[g.map.get(fir.name)];
				
				int temp4 = total[goodFriends.fnum];
				
				if (temp3 < temp4) {
					if(k!=0) {
						k--;
					}else {
						k++;
					}
					arr_b[g.map.get(fir.name)] = temp3;
				}
				else {
					if(k!=0) {
						k--;
					}else {
						k++;
					}
					arr_b[g.map.get(fir.name)] = temp4;
					

				}
			}
			goodFriends = goodFriends.next;
		}
		return connectors;
	}
	
	
	
	
	       
}

