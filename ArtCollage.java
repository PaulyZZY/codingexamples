/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {

       
    	//set the field to 4*100
    	String file = filename;
        collageDimension=4;
        tileDimension=100;
        original=new Picture(file);
        collage=new Picture (collageDimension*tileDimension,collageDimension*tileDimension);
        for(int c=0;c <400;c++){
            for(int r=0;r<400;r++){
                int cols=(c*original.width())/(400);
                int rows=(r*original.height())/(400);
                Color color=original.get(cols,rows);
                collage.set(c,r,color);
            }
        }
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {

        
        collageDimension=cd;
        tileDimension=td;
        String file = filename;
        original=new Picture(file);
        collage=new Picture (collageDimension*tileDimension,collageDimension*tileDimension);
        for(int c=0;c <400;c++){
            for(int r=0;r<400;r++){
                int cols=(c*original.width())/(400);
                int rows=(r*original.height())/(400);
                Color color=original.get(cols,rows);
                collage.set(c,r,color);
            }
        }
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

        
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

        
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

        
         return original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

       
        return collage;
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {

        
        original.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {

        
        collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        
        Picture pict=new Picture(filename);
        for(int c=0;c<tileDimension;c++){
            for(int r=0;r<tileDimension;r++){
                int cols=(c*pict.width())/(tileDimension);
                int rows=(r*pict.height())/(tileDimension);
                Color picColumn=pict.get(cols,rows);
                collage.set(c+collageCol*tileDimension,r+collageRow*tileDimension,picColumn);
            }
        }  
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */

    public void makeCollage () {

        
        for(int i=0;i<collageDimension;i++){
            for(int j=0;j<collageDimension;j++){
                for(int x=0;x<tileDimension;x++){
                    for(int y=0;y<tileDimension;y++){
                        int cols=(x*original.width())/(tileDimension);
                        int rows=(y*original.height())/(tileDimension);
                        Color picCol=original.get(cols,rows);
                        collage.set(x+i*tileDimension,y+j*tileDimension,picCol);
                    }
                }    
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {

        
        for (int c = 0; c <tileDimension; c++) {
            for (int r = 0; r < tileDimension; r++) 
            {
                Color picColumn = collage.get(collageCol * tileDimension+c, collageRow * tileDimension+r);
                int red = picColumn.getRed();
                int blue = picColumn.getBlue();
                int green = picColumn.getGreen();


                if (component.equals("red")) 
                {
                    Color re=new Color(red,0,0);
                    collage.set(collageCol * tileDimension+c, collageRow * tileDimension+r, re);
                }
                else if (component.equals("blue")) 
                {
                    Color bl=new Color(0,0,blue);
                    collage.set(collageCol * tileDimension+c, collageRow * tileDimension+r, bl);

                }else if (component.equals("green")) 
                {
                    Color gr=new Color(0,green,0);
                    collage.set(collageCol * tileDimension+c, collageRow * tileDimension+r, gr);
                } 


            }
        }
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {

        
        for (int i = 0; i <tileDimension; i++) 
        {
            for (int j = 0; j < tileDimension; j++) 
            {
                Color picColor = collage.get(collageCol * tileDimension+i,collageRow * tileDimension+j);
                Color gray = Luminance.toGray(picColor);
                collage.set(collageCol * tileDimension+i, collageRow * tileDimension+j, gray);
            }
        }
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {
        ArtCollage art = new ArtCollage(args[0], 200, 2);
        art.makeCollage();
        
        // Replace 3 tiles 
        art.replaceTile(args[1],0,1);
        art.replaceTile(args[2],1,0);
        art.replaceTile(args[3],1,1);
        art.colorizeTile("green",0,0);
        art.showCollagePicture();

    }
}
