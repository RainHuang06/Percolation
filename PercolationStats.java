import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static final double CONFIDENCE_INTERVAL_CONST = 1.96;
    // store each resulting percolation proportion as
    // a double.
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw (new IllegalArgumentException("Index out of range"));
        }
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            int needed = 0; // how many squares must we open?
            boolean percolates = false;
            Percolation tester = new Percolation(n);
            while (!percolates) {
                needed++;
                int row;
                int column;
                // generate random rows and columns until cell non-empty
                do {
                    row = StdRandom.uniformInt(n);
                    column = StdRandom.uniformInt(n);
                } while (tester.isOpen(row, column));
                tester.open(row, column);
                percolates = tester.percolates();
            }
            results[i] = (double) needed / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return (StdStats.mean(results));
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return (StdStats.stddev(results));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        // confidence interval term
        double toSub = (CONFIDENCE_INTERVAL_CONST * this.stddev()
                / Math.sqrt(results.length));
        return this.mean() - toSub;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        // confidence interval term
        double toAdd = (CONFIDENCE_INTERVAL_CONST * this.stddev()
                / Math.sqrt(results.length));
        return this.mean() + toAdd;
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats statistics = new PercolationStats(Integer.parseInt(args[0]),
                                                           Integer.parseInt(args[1]));
        System.out.println("mean() = " + statistics.mean());
        System.out.println("stddev() = " + statistics.stddev());
        System.out.println("confidenceLow() = " + statistics.confidenceLow());
        System.out.println("confidenceHigh() = " + statistics.confidenceHigh());
        System.out.println("Elapsed time = " + stopwatch.elapsedTime());
    }

}
