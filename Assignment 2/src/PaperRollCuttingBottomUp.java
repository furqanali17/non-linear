/**
 * This class demonstrates a bottom-up approach to the paper roll cutting problem.
 *
 * @author Furqan Ali R00224511
 */
public class PaperRollCuttingBottomUp {

    /**
     * The main method that solves the paper roll cutting problem using a bottom-up approach.
     */
    public static void main(String[] args) {
        //the length of the paper roll
        int n = 30;

        //if for when n is less than or equal to zero, an error is prompted
        if (n <= 0) {
            System.err.println("Error: The length of the paper roll must be greater than zero.");
            return;
        }

        //printing the length of the paper roll
        System.out.println("The length of the paper roll is " + n);
        //adding space
        System.out.println();

        //array for prices of the roll-pieces
        double[] prices = {1.2, 3, 5.8, 10.1};
        //array for the lengths of the roll-pieces
        int[] lengths = {1, 2, 3, 5};

        //if for when the length of the prices and lengths array is not equal to 4 or
        //for when the length of the prices array does not match the length of the lengths array
        //this is because the lengths are fixed, so are the prices. The prices can be changed but
        //cannot be removed or any new prices can not be added.
        if (prices.length != 4 || lengths.length != 4 || prices.length != lengths.length) {
            System.err.println("Error: Both arrays must have exactly 3 elements and be of the same length.");
            return;
        }

        //array to store max revenue
        double[] revenue = new double[n + 1];
        //2D array to store the number of cuts made for each roll-piece and their respective prices
        int[][] cuts = new int[n + 1][prices.length + 1];

        //calls methods in main
        calculateOptimalCuts(n, prices, lengths, revenue, cuts);
        printResults(revenue, cuts, lengths, n);
    }

    /**
     * Calculates the optimal cuts using a bottom-up approach.
     *
     * @param n       The length of the paper roll
     * @param prices  The prices of roll-pieces
     * @param lengths The lengths of roll-pieces
     * @param revenue The array containing maximum revenue for each iteration
     * @param cuts    The array containing the number of cuts of each roll-piece for each iteration
     */
    public static void calculateOptimalCuts(int n, double[] prices, int[] lengths, double[] revenue, int[][] cuts) {
        //for loop to go through all values of roll 1 to n
        for (int i = 1; i <= n; i++) {
            //initializes variable to store current max revenue
            double currentMaxRevenue = Integer.MIN_VALUE;
            //array to store optimal cuts
            int[] maxCuts = new int[prices.length];
            //nested loop to go through each roll and check if it can be used to cut roll of length i
            for (int j = 0; j < prices.length; j++) {
                //if it is greater or equal it can be used to cut
                if (i >= lengths[j]) {
                    //variable to store the current revenue after cutting current roll-piece j
                    //and the max revenue from the remaining lengths at j
                    double currentRevenue = prices[j] + revenue[i - lengths[j]];
                    //if the current revenue is greater than current max revenue
                    if (currentRevenue > currentMaxRevenue) {
                        //then update current revenue to max revenue
                        //this shows the current cut is most optimal
                        currentMaxRevenue = currentRevenue;
                        //loop to update current optimal cuts to max cuts array
                        //the optimal cuts at i are updated
                        /*for (int k = 0; k < prices.length; k++) {
                            maxCuts[k] = cuts[i - lengths[j]][k];
                        }*/
                        System.arraycopy(cuts[i - lengths[j]], 0, maxCuts, 0, prices.length);
                        //increments the roll-piece j showing it has been used up
                        maxCuts[j]++;
                    }
                }
            }
            //update the revenue array with all max revenues
            revenue[i] = currentMaxRevenue;
            //update the cuts array with all max cuts
            cuts[i] = maxCuts;
        }
    }

    /**
     * Prints the results of the paper roll cutting problem.
     *
     * @param revenue The array containing maximum revenue gained from each iteration
     * @param cuts    The array containing the number of cuts of each roll-piece for each iteration
     * @param lengths The lengths of roll-pieces
     * @param n       The length of the paper roll
     */
    public static void printResults(double[] revenue, int[][] cuts, int[] lengths, int n) {
        System.out.println("Best revenue: " + revenue[n] + "€");
        System.out.println("Roll-pieces cut: ");
        for (int i = 0; i < 4; i++) {
            System.out.println("Roll-piece of length " + lengths[i] + ": " + cuts[n][i] + " pieces");
        }
    }
}