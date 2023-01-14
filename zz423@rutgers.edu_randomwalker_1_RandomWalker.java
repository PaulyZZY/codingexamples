/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author:
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker {

    public static void main(String[] args) {

    int x= 0,y=0;
    int inputNumber = Integer.parseInt(args[0]);
    Double squaredDistance = 0.0;
    int east=0,west=0,north=0,south=0;
    int finishX = 0, finishY=0;
    System.out.println("("+x+","+y+")");
    for (int i = 0; i < inputNumber; i++) {
        double possibility = Math.random();
        if (possibility <= 0.25){east++;}
        else {if (possibility <= 0.5){west++;}
                else {if (possibility <= 0.75){north++;}
                        else {south++;}}}
    int newX = east - west, newY = north - south;
    System.out.println("("+newX+","+newY+")");finishX = newX; finishY=newY;   
    }
    int xSquare = (finishX - x)*(finishX - x);
    int ySquare =  (finishY - y)*(finishY-y);
    squaredDistance = Double.valueOf(xSquare+ySquare);
    System.out.println("Squared distance is "+ squaredDistance);
}
}
