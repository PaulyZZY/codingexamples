/*************************************************************************
 * Compilation: javac PolygonTransform.java Execution: java PolygonTransform
 *
 * @author:
 *
 *************************************************************************/

public class PolygonTransform {

    // Returns a new array that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array) {

        double[] newArr = new double[array.length];
        for (int i = 0; i < array.length; i++){
            newArr[i]=array[i];
        }return newArr;
    }

    // Scales the given polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha) {

        for (int i = 0; i<x.length; i++){
            x[i] *= alpha;
            y[i] *= alpha;
        }
    }

    // Translates the given polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy) {

        for (int i = 0; i<x.length; i++){
            x[i] += dx;
            y[i] += dy;
        }
    }

    // Rotates the given polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta) {

        double convertTheta = theta*(Math.PI/180);
        double sin = Math.sin(convertTheta);
        double cos = Math.cos(convertTheta);
        for (int i = 0 ; i<x.length; i++){
            double newX = x[i]*cos - y[i]*sin;
            double newY = y[i]*cos + x[i]*sin;
            x[i]= newX;
            y[i]=newY;
        }

    }

    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {

        
        
    }
}
