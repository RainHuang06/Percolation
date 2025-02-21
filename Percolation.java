import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // grid: union-find data structure representing our grid,
    // and each cluster of adjacent open
    // cells is represented as one union in the
    // data structures.
    private WeightedQuickUnionUF grid; // [row, column] stored internally
    // as row * n + column

    // opened: represents the open grid
    // clusters of the grid. leader of each cluster has
    // true stored in the array cell corresponding to
    // its position, and blocked cells contain false
    private boolean[] opened;

    // full: represents the grid clusters that are full.
    private boolean[] full;

    // containsBottom: represents the grid clusters that are able to reach the bottom.
    private boolean[] containsBottom;

    // numOpen: represents the number of open squares.
    private int numOpen;

    // n: represents the side length of our percolation square.
    private int n;

    // percolates: false if our percolator does not percolate, true if it does.
    private boolean percolates = false;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int num) {
        if (num <= 0) {
            throw (new IllegalArgumentException("Size must be greater than 0"));
        }
        grid = new WeightedQuickUnionUF(num * num);
        opened = new boolean[num * num];
        full = new boolean[num * num];
        containsBottom = new boolean[num * num];
        n = num;
    }

    // convert coordinate into correct form for union-find
    private int convert(int row, int col) {
        return (row * n + col);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw (new IllegalArgumentException("Index out of range"));
        }
        int curCell = convert(row, col);
        if (!opened[curCell]) {
            opened[curCell] = true;
            numOpen++;
            if (curCell < n) {
                full[grid.find(curCell)]
                        = true; // An open cell in the top row is always full.
            }
            if (curCell >= n * (n - 1)) {
                containsBottom[grid.find(curCell)] = true; // Contains the bottom row.
            }
            if (curCell >= n) { // cell directly above
                merge(curCell, curCell - n);
            }
            if (curCell % n != 0) { // cell to the left
                merge(curCell, curCell - 1);
            }
            if (curCell % n != n - 1) { // cell to the right
                merge(curCell, curCell + 1);
            }
            if (curCell < n * (n - 1)) { // cell below
                merge(curCell, curCell + n);
            }
            if (n == 1) {
                percolates = true; /* edge case
                this code fails to detect
                a 1x1 grid that percolates
                but if a square is set to open
                this will automatically percolate */
            }
        }
    }

    // Precondition: curCell is empty
    // Merges curCell with otherCell - are they full / open?
    private void merge(int curCell, int otherCell) {
        boolean needFill = false;
        boolean hasBottom = false;
        if (opened[otherCell]) { // Other cell is open
            if (full[grid.find(curCell)] || full[grid.find(otherCell)]) {
                needFill = true;
            }
            if (containsBottom[grid.find(curCell)] ||
                    containsBottom[grid.find(otherCell)]) {
                hasBottom = true;
            }
            grid.union(curCell, otherCell);
            if (needFill) {
                full[grid.find(curCell)] = true;
            }
            if (hasBottom) {
                containsBottom[grid.find(curCell)] = true;
            }
            if (needFill && hasBottom) {
                percolates = true;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw (new IllegalArgumentException("Index out of range"));
        }
        return (opened[convert(row, col)]);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw (new IllegalArgumentException("Index out of range"));
        }
        return full[grid.find(convert(row, col))];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return (numOpen);
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    public static void main(String[] args) {
        Percolation tester = new Percolation(2);
        tester.open(0, 0);
        tester.open(1, 0);
        System.out.println(tester.isFull(0, 0));
        System.out.println(tester.isOpen(0, 1));
        System.out.println(tester.percolates());
        System.out.println(tester.numberOfOpenSites());

    }
}
