/**
 * This class finds the minimum cost path for a robot to move from the top-left corner to the
 * bottom-right corner of a matrix while considering two different cost sets.
 * The robot can ONLY move to the Right, Down and Diagonally(Right-Down).
 *
 * @author Furqan Ali R00224511
 */
public class RobotMoving {

    /**
     * The main method that solves the robot moving problem using a bottom-up approach.
     */
    public static void main(String[] args) {
        //the size of the matrix
        int n = 10;

        //if for when n is less than or equal to zero, an error is prompted
        if (n <= 0) {
            System.err.println("Error: The size of the matrix must be greater than zero.");
            return;
        }

        //printing the size of the matrix
        System.out.println("The size of the matrix is " + n);
        //adding space
        System.out.println();

        //array for the costs to move Right, Down and Diagonally(Right-Down) respectively
        double[] cost1 = {1.1, 1.3, 2.5};
        double[] cost2 = {1.5, 1.2, 2.3};

        //if for when the length of the two costs arrays is not equal to 3 or
        //for when the length of the two costs arrays do not match each other
        //this is because the lengths are fixed. They can be changed but
        //cannot be removed or any new can not be added.
        if (cost1.length != 3 || cost2.length != 3 || cost1.length != cost2.length) {
            System.err.println("Error: Both cost arrays must have exactly 3 elements and be of the same length.");
            return;
        }

        //calls methods in main
        findMinimumCostPath(n, cost1, "Cost 1");
        System.out.println();
        findMinimumCostPath(n, cost2, "Cost 2");
    }

    /**
     * Calculates the minimum cost path for a robot to move from the top-left corner
     * to the bottom-right corner of a matrix, considering a given set of costs.
     *
     * @param n        the size of the matrix
     * @param costs    the cost set for moving Right, Down, and Diagonally(Right-Down)
     * @param costName the name of the cost set, used for display purposes
     */
    public static void findMinimumCostPath(int n, double[] costs, String costName) {
        //2D arrays to store minimum cost and path for each cell in the matrix
        double[][] minCost = new double[n + 1][n + 1];
        String[][] path = new String[n + 1][n + 1];

        //for loop to go through the matrix's rows and columns
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                //if for when n is 1
                if (i == 1 && j == 1) {
                    //set the min cost to 0
                    minCost[i][j] = 0;
                    //set the path to empty
                    path[i][j] = "";
                }
                //else for anything but 1
                else {
                    //initializes variable to store current min cost
                    double currentMinCost = Double.MAX_VALUE;
                    //initializes variable to store current path
                    String currentPath = "";

                    //if to check cost of moving Right
                    //if j the current column is greater than 1
                    if (j > 1) {
                        //variable to store the calculated cost of moving Right to the current cell (i, j)
                        //this is done by adding the cost of moving Right (costs[0]) to the minimum cost
                        //of the previous cell (i, j - 1), which is stored in minCost array
                        double cost = minCost[i][j - 1] + costs[0];
                        //if the calculated cost of moving Right to the current cell (i, j) is less than the current minimum cost
                        if (cost < currentMinCost) {
                            //update the current minimum cost to the new cost
                            currentMinCost = cost;
                            //update the current path by appending "Right" to the path of the previous cell (i, j - 1)
                            currentPath = path[i][j - 1] + "Right ";
                        }
                    }

                    //if to check cost of moving Down
                    //if j the current column is greater than 1
                    if (i > 1) {
                        //variable to store the calculated cost of moving Down to the current cell (i, j)
                        //this is done by adding the cost of moving Down (costs[1]) to the minimum cost
                        //of the previous cell (i - 1, j), which is stored in the minCost array
                        double cost = minCost[i - 1][j] + costs[1];
                        //if the calculated cost of moving Down to the current cell (i, j) is less than the current minimum cost
                        if (cost < currentMinCost) {
                            //update the current minimum cost to the new cost
                            currentMinCost = cost;
                            //update the current path by appending "Down" to the path of the previous cell (i - 1, j)
                            currentPath = path[i - 1][j] + "Down ";
                        }
                    }

                    //if to check cost of moving Diagonally(Right-Down)
                    //if both the current row (i) and column (j) are greater than 1
                    if (i > 1 && j > 1) {
                        //variable to store the calculated cost of moving Diagonally(Right-Down) to the current cell (i, j)
                        //this is done by adding the cost of moving Diagonally(Right-Down) (costs[2]) to the minimum cost
                        //of the previous cell (i - 1, j - 1), which is stored in the minCost array
                        double cost = minCost[i - 1][j - 1] + costs[2];
                        //if the calculated cost of moving Diagonally(Right-Down) to the current cell (i, j) is less than the current minimum cost
                        if (cost < currentMinCost) {
                            //update the current minimum cost to the new cost
                            currentMinCost = cost;
                            //update the current path by appending "Right-Down" to the path of the previous cell (i - 1, j - 1)
                            currentPath = path[i - 1][j - 1] + "Right-Down ";
                        }
                    }
                    //update the minCost array with the current minimum cost
                    minCost[i][j] = currentMinCost;
                    //update the path array with the current path
                    path[i][j] = currentPath;
                }
            }
        }
        //print the minimum cost and path using the given cost set
        System.out.println("Minimum cost using " + costName + ": " + minCost[n][n]);
        System.out.println("Path using " + costName + ":");

        //rearranges the path by trimming any leading or trailing spaces and then splits the string by spaces
        String[] pathRearranged = path[n][n].trim().split(" ");

        //prints the rearranged path with line breaks after every 15 paths
        for (int i = 0; i < pathRearranged.length; i++) {
            //prints rearranged path with spaces b/w them
            System.out.print(pathRearranged[i] + " ");
            //checks if 15 paths have been completed by seeing if (i) is divisible by 15
            if ((i + 1) % 15 == 0) {
                //adds space when it is true
                System.out.println();
            }
        }

        //if for when path is not a multiple of 15
        //so that the last moves are printed and not left out
        if (pathRearranged.length % 15 != 0) {
            System.out.println();
        }
    }
}