Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
I used a union-find structure to simulate the grid, as each union can be used to
simulate a connected component of the grid.
opened, full, and containsBottom are three auxillary arrays used
to contain the states of each cluster of the percolation.
opened represents the leaders that contain open cells rather than blocked ones.
full represents the leaders of the clusters that are full and do percolate
from the top.
containsBottom represents the leaders of the clusters that also contain
a cell in the bottom row. Together, these three arrays contain the
information necessary to percolate with no backwash.
numOpen represents the number of open squares.
n represents the side length of the square.
percolates represents whether the percolation is successful.


/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): Firstly, the cell is opened if it is not already open, updating
numOpen.-
It then calls an auxillary methood merge in order to merge thecell
and its properties with each of its adjacent neighbors. Merge uses
the union-find data structure to handle the correct clusters merging,
while also updating our arrays to indicate the state of each cluster.
Furthermore, merge sets percolates to true when a cluster contains cells
from both the top and bottom row.
isOpen(): Looks up whether the leader from our array is open with an
array access from opened.
isFull(): Looks up whether the leader from our array is full with an
array access from full.
numberOfOpenSites(): Returns numOpen.
percolates(): Returns percolates.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100
 max n = 263
 n          time (seconds)
--------------------------
100         0.837
200         16.598
250         31.96
260         55.933
263         59.377
300         126.894
/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I first started off with ballpark values like 100, then moved up to
300. When 300 was too much, I went down to 250, then slowly
stepped upwards to 260 and 263 until I reached the limit
of this algorithm in 1 minute.


/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100
 1900 < max n < 1910
 n          time (seconds)
--------------------------
500             1.099
1000            5.57
1500            22.517
1900            49.538
1910            68.934
2000            75.423

/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

This implementation uses two excess auxillary arrays to prevent
backwashing. This seems a bit wasteful but I am not sure how
I might optimize it down a bit.
There are stylistic warnings about not having a main
functions - but a main function is not necessary for
percolation.


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

None.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
I enjoyed engineering this algorithm from scratch!
It's fun working on a problem with real-world solutions.
