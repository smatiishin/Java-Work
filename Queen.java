/* Sam Matiishin
CSC 103
N-Queen Solver Program
*/

public class Queen{
   // Invariant of the Queen class:
   //   1. The instance variable that represents a queen object's row coordinate is row. 
   //   
   //   2. The instance variable that represents a queen object's column coordinate is column. 
   private int row;
   private int column;
   
   /**
   * Initialize a Queen object with row and column as parameters
   * @param r
   *  The row that the Queen object is initialized in
   * @param c
   *  The column that the Queen object is initialized in
   * @precondition
   *   The parameter must be a integer input
   * @postcondition
   *   This Queen object is initialized with a row and column as location coordinates
   **/
   public Queen(int r, int c){
      row = r;
      column = c;
   }
      
   /**
   * This method generates a string representation of this Queen
   * @param - none
   * @return
   *   A string representation of this Queen object
   * @exception - IllegalStateException
   *   Indicates that the stack is empty
   **/ 
   public String toString(){
      return "(row = " + row + " column = " + column + ")\t ";
   }
   /**
   * This method compares Queen objects' coordinates and if any coordinate matches or conflicts, the return is true, otherwise false. 
   * @param - otherQueen
   * @return
   *   true (queen objects are attacking eachother) or false (queen objects are not attacking eachother)
   **/ 
   public boolean conflict(Queen otherQueen){
      
      // Initialize objects and variables
      int i;
      int j;
      
      /* Obtain the highest value betweem row and column coordinates for both queens and use this as a limit to check
      for conflicts in the diagonals, rather than to have i and j increment to integer.maxValue() and slow
      down the processor tremendously */
      int limit = Math.max((Math.max(row, otherQueen.row)),(Math.max(column, otherQueen.column)));
      
       
      // Evaluate up and down, left and right to determine if there is a conflict
      if (otherQueen.row == this.row || otherQueen.column == this.column){
         return true;
      }
      
      // Evaluate the top left diagonal
      for (i=row, j=column; i>=(-limit) && j<=limit; i--, j++){ 
         if (otherQueen.row == i && otherQueen.column == j){
            return true;
         }
      }
      // Evaluate the top right diagonal
      for (i=row, j=column; i>=(-limit) && j>=(-limit); i--, j--){ 
         if (otherQueen.row == i && otherQueen.column == j){
            return true;
         }
      }
      // Evaluate the bottom left diagonal
      for (i=row, j=column; i<=limit && j>=(-limit); i++, j--){ 
         if (otherQueen.row == i && otherQueen.column == j){
            return true;
         }
      }
      // Evaluate the bottom right diagonal
      for (i=row, j=column; i<=limit && j<=limit; i++, j++){ 
         if (otherQueen.row == i && otherQueen.column == j){
            return true;
         }
      }
      return false;
   }
   
   /**
   * This accessor method obtains a queen's column value and returns it
   * @param - none
   * @return
   *  The column integer value of the queen object
   **/
   public int getColumn(){
      return column;
   }
   
   /**
   * This accessor method obtains a queen's row value and returns it
   * @param - none
   * @return
   *  The row integer value of the queen object
   **/
   public int getRow(){
      return row;
   }
}   