package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node nodePtr=null;
        Node x = null;
		Node x1= null;
        Node x2 = null;
		Node x3= null;
        while (poly1 != null && poly2 != null) {
            x = null;
            if (poly1.term.degree==poly2.term.degree) {
            	float coefficent1 = poly1.term.coeff;
            	float coefficent2=poly2.term.coeff;
            	int degree1= poly1.term.degree;
            	nodePtr = helperAd(nodePtr , coefficent1 + coefficent2 , degree1) ;
            	poly2 = poly2.next;
                poly1 = poly1.next;
                
            }        
            else if (poly1.term.degree < poly2.term.degree) {
            	x1= null;
            	float coe1 = poly1.term.coeff;
            	int degree1= poly1.term.degree;
            	nodePtr = helperAd (nodePtr, coe1, poly1.term.degree) ;
                poly1 = poly1.next;
            } else {
            	x1= null;
            	int degree2= poly2.term.degree;
            	nodePtr = helperAd(nodePtr, poly2.term.coeff, degree2);
                poly2 = poly2.next;
            }
        }
        while (poly2 != null) {x1= null;
        	int degree2= poly2.term.degree;
        	nodePtr = helperAd(nodePtr, poly2.term.coeff,  degree2);
            poly2 = poly2.next;
        }
        while (poly1 != null) {x1= null;
        	int degree1= poly1.term.degree;
        	nodePtr = helperAd( nodePtr , poly1.term.coeff, degree1);
            poly1 = poly1.next;
        }
        x1= null;
        return nodePtr;
	}
	
	
	private static Node helperAd(Node poly, float numF, int numInd) {
        Node x = null;
		Node x1= null;
        Node x2 = null;
		Node x3= null;
        if (numF == 0) {	
            return poly;

        }
        x1= null;
        x2= null;
        x3= null;
        Node ptrNew = new Node(numF, numInd, null);
        
        if (poly == null) {
            return ptrNew;
        }
        Node ptr = poly;
        
        while (ptr.next != null) {
            ptr =ptr.next;
            
        }
        x1= null;
        x2= null;
        x3= null;
        ptr.next = ptrNew;
        
        return poly;

    }
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/

        Node nodePtr = null;
        Node x = null;
		Node x1= null;
        Node x2 = null;
		Node x3= null;
        while (poly1 != null) {  	
            Node ptr = poly2;           
            Node ptrNew = null;
            x1= null;
            x2= null;
            x3= null;
            
            while (ptr != null) {
                ptrNew = helperAd(ptrNew, ptr.term.coeff * poly1.term.coeff, ptr.term.degree + poly1.term.degree);
                ptr = ptr.next;
            }
            nodePtr = Polynomial.add ( nodePtr, ptrNew);
            x1= null;
            x2= null;
            x3= null;
            poly1 = poly1.next;
        }

        return nodePtr;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		float finalR=0;
        Node nodeX = null;
		Node x1= null;
        Node x2 = null;
		Node x3= null;
		while(poly!=null){
			
			if(poly.term.degree!=0){
	            x1= null;
	            x2= null;
	            x3= null;
	            
				finalR =finalR+(float)Math.pow(x,poly.term.degree) * poly.term.coeff;
				poly=poly.next;	
			}else{
	            x1= null;
	            x2= null;
	            x3= null;
				finalR+=poly.term.coeff;
				poly=poly.next;
			}

		}
		return finalR;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
