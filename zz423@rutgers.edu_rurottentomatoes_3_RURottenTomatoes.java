/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author:
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {

		int r = Integer.parseInt(args[0]);
		int c = Integer.parseInt(args[1]);
		int starting = 2;
		int arr[][]= new int[r][c];
		
		for (int i = 0; i<r; i++){
			for (int j = 0; j<c; j++)
			{arr[i][j]=Integer.parseInt(args[starting++]);
			} 
		}
		int columnSum =0, maxSum=0, minSum=0, minIndex=0,index =0;
		for (int j=0;j<=0;j++){
			for (int i = 0;i<r;i++){
				columnSum = columnSum + arr[i][j];}
		maxSum = columnSum; minSum=columnSum;}
		for (int j=0;j<c;j++){
			columnSum = 0;
			for (int i = 0;i<r;i++){
				columnSum = columnSum + arr[i][j];
				
			}
		if (columnSum>maxSum){maxSum = columnSum; index = j;}
		
			
		}System.out.println(index);
		

	}
}
