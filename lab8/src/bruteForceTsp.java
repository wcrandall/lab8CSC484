//https://github.com/williamfiset/Algorithms/blob/master/com/williamfiset/algorithms/graphtheory/TspBruteForce.java

public class bruteForceTsp{

    // if a graph with equal rows and columns is input this finds
    // the shortest distance between the cities without visiting one more than once
    public static returnVals tsp(double[][] matrix) {

        // get the length of the incoming matrix and create a list of that length
        int n = matrix.length;
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) permutation[i] = i;

        int[] bestTour = permutation.clone();
        double bestTourCost = Double.POSITIVE_INFINITY;

        // Try all n! tours
        do {

            double tourCost = computeTourCost(permutation, matrix);

            if (tourCost < bestTourCost) {
                bestTourCost = tourCost;
                bestTour = permutation.clone();
            }

        } while (nextPermutation(permutation));

        int []newBestTour = new int[bestTour.length + 1];
        for (int i = 0; i < newBestTour.length; i++)
        {
            if (i < bestTour.length) {
                newBestTour[i] = bestTour[i];
            }
            else
            {
                newBestTour[i] = 0;
            }
        }
        newBestTour[bestTour.length] = 0;
        returnVals returns = new returnVals((int)bestTourCost,newBestTour);
        return returns;
    }

    public static double computeTourCost(int[] tour, double[][] matrix) {

        double cost = 0;

        // Compute the cost of going to each city
        for (int i = 1; i < matrix.length; i++) {
            int from = tour[i - 1];
            int to = tour[i];
            cost += matrix[from][to];
        }

        // Compute the cost to return to the starting city
        int last = tour[matrix.length - 1];
        int first = tour[0];
        return cost + matrix[last][first];
    }

    // generates next permutation
    public static boolean nextPermutation(int[] sequence) {
        int first = getFirst(sequence);
        if (first == -1) return false;
        int toSwap = sequence.length - 1;
        while (sequence[first] >= sequence[toSwap]) --toSwap;
        swap(sequence, first++, toSwap);
        toSwap = sequence.length - 1;
        while (first < toSwap) swap(sequence, first++, toSwap--);
        return true;
    }

    private static int getFirst(int[] sequence) {
        for (int i = sequence.length - 2; i >= 0; --i) if (sequence[i] < sequence[i + 1]) return i;
        return -1;
    }

    private static void swap(int[] sequence, int i, int j) {
        int tmp = sequence[i];
        sequence[i] = sequence[j];
        sequence[j] = tmp;
    }
}