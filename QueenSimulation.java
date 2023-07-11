/* Sam Matiishin
CSC 103
Queen Simulation Program
Description: This is the driver program of the Queen class 
*/ 

// Import Scanner to obtain user inputs
import java.util.Scanner;

// Import to use in try/catch in case of an error for an empty stack
import java.util.EmptyStackException;

// Import to use in try/catch in case of an error in stack
import java.util.NoSuchElementException;

public class QueenSimulation{
   public static void main(String[] args){
      
      // Initialize objects and variables
      Scanner keyboard = new Scanner(System.in);
      int input = 0;
      int counter = 0;
      
      // Initialize variables to be used in the for loops
      
      int i; // Row
      int j; // Column
      int k; // Location in stack to compare coordinates
      int l; // Location in stack to display coordinates
      
      // Initialize queen objects
      Queen queen = new Queen(2,3);
      Queen newQueen = new Queen(0,0);
      Queen queenChecker = new Queen(0,0);
      
      // Initialize stack
      LinkedStack solution = new LinkedStack();
      
      // Obtain user input for N queens and NxN board
      System.out.println("Enter a number to test the Queen program: ");
      input = keyboard.nextInt();
      

      try{
         
         // For rows 1 through n
         for (i=1; i<= input; i++){
            // For columns 1 through n
            for (j=1; j<= input; j++){
               
               // If the row is 1, create a new queen with (row,column) coordinates and add the queen to the stack, and move on the the next row
               if (i == 1){
                  queen = new Queen(i,j);
                  solution.push(queen);
                  j = input + 1;
                  
               } else {
               
                  // Create a new queen with (row,column) coordinates
                  newQueen = new Queen(i,j);
                  
                  // Evaluate if this newQueen creates a conflict with any of the values in the stack
                  for (k=solution.size(); k>0; k-=1){
                  
                     // Typecast the queenChecker object to be the coordinate in the stack at a specific location in the stack
                     queenChecker = (Queen) solution.itemAt(k);
                     
                     // Boolean statement that runs if there is no conflict and all values in the stack have been compared to the newQueen object
                     if (newQueen.conflict(queenChecker) == false && k == 1){
                     
                        // Add the newQueen's coordinates to the stack, and move on to the next row
                        solution.push(newQueen);
                        j = input + 1;
                        
                        // If the stack has grown to the user input size, there is a solution, and display this solution
                        if (solution.size() == input){
                        
                           // Increase the counter variable
                           counter = counter + 1;
                           System.out.println("Here is solution " +counter+ ": ");
                           
                           // Loop that displays all the values in the stack
                           for (l = solution.size(); l>0; l-=1){
                              queenChecker = (Queen) solution.itemAt(l);
                              System.out.print(queenChecker);
                              if (l == 1){
                                 System.out.println("\n");
                              }
                           }
                           // Reset the queenChecker object to equal the top value in the stack, and make the row and column numbers equal this coordinate
                           queenChecker = (Queen) solution.peek();
                           i = queenChecker.getRow();
                           j = queenChecker.getColumn();
                           
                           // Remove the top coordinate in the stack
                           solution.pop();
                           
                           // If the coordinate is at the end of the row, reset the queenChecker variable to the top coordinate
                           // and remove this coordinate from the stack
                           if (j == input){
                              queenChecker = (Queen) solution.peek();
                              i = queenChecker.getRow();
                              j = queenChecker.getColumn();
                              solution.pop();
                           }
                        }
                        
                     // Boolean statement that runs if there is a conflict and the end of the row has been reached
                     } else if (newQueen.conflict(queenChecker) == true && j == input){
                     
                        // Typecast the queenChecker object to equal the top coordinate in the stack, and set the row and column to match this value
                        queenChecker = (Queen) solution.peek();
                        i = queenChecker.getRow();
                        j = queenChecker.getColumn();
                        
                        // Remove the top coordinate in the stack
                        solution.pop();
                        
                        // Check if in row 1 and (final column + 1), if true, end the program
                        if (queenChecker.getRow() == 1 && queenChecker.getColumn() == input){
                           i = input + 1;
                           j = input + 1;
                           k = 0;
                           
                        } else {
                        
                           // Check if the column previous to the current location is at the end of the row, if
                           // so, retrive the top stack value, and match the updated row and column 
                           if (queenChecker.getColumn() == input){
                              queenChecker = (Queen) solution.peek();
                              i = queenChecker.getRow();
                              j = queenChecker.getColumn();
                           }
                           
                           // End the execution of the stack checker
                           k = 0;
                           
                           // Remove the top stack value if not at the end of row 1 and column (user input)
                           if (i == 1 && j + 1 != input){
                              solution.pop();
                           }
                        }
                        
                     // If there is a conflict with the newQueen and a value in the stack, end the execution of the
                     // stack checker and move on to the next coordinate without adding the conflicting value to the stack
                     } else if (newQueen.conflict(queenChecker) == true){
                        k = 0;
                     }
                  }
               }
            }
         }

         // If the loop ends and the stack is empty, display the total number of solutions
         if (solution.size() == 0 || solution == null){
            System.out.println("There are " +counter+ " solution(s) for N = " + input);
         }
         
         // If the loop ends and the stack size is 1, inform the user of the only solution 
         // (This really just catches an input of 1)
         if (solution.size() == 1){
            counter = counter + 1;
            System.out.println("There are " +counter+ " solution(s) for N = " + input);
            System.out.println("Here is solution " + input + ": " + solution.peek());
         }

      // Shouldn't happen, but in case catch any possible exceptions
      }catch (NullPointerException e){
         System.out.println(e.getMessage());
      }catch (IllegalArgumentException e){
         System.out.println(e.getMessage());
      }catch (NoSuchElementException e){
         System.out.println(e.getMessage());
      }catch (EmptyStackException e){
         System.out.println(e.getMessage());
      }catch (IllegalStateException e){
         System.out.println(e.getMessage());
      }
   }
}
      