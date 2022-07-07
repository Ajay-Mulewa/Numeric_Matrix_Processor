package processor;

import java.util.Scanner;

public class Matrix {

    Scanner scanner = new Scanner(System.in);

    private final int row;
    private final int col;

    private double[][] matrix;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;

        matrix = new double[row][col];
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public double getAtIndex(int row, int col) {
        return matrix[row][col];
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setAtIndex(int row, int col, double value) {
        this.matrix[row][col] = value;
    }

    public void fill () {
        System.out.println("Enter Matrix:");
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                this.matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    private void display(int row, int col, double[][] matrix) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void displaySquareMatrix(double[][] matrix) {
        int dimension = matrix.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    void addMatrices(Matrix other) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + other.getAtIndex(i, j)  + " ");
            }
            System.out.println();
        }
    }

    void multiplyByConstant(int constant) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] * constant + " ");
            }
            System.out.println();
        }
    }

    void matrixMultiplication(Matrix other) {
        double[][] resultantMatrix = new double[this.getRow()][other.getCol()];
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < other.getCol(); j++) {
                for (int k = 0; k < getCol(); k++) {
                    resultantMatrix[i][j] += this.matrix[i][k] * other.getAtIndex(k, j);
                }
            }
        }
        display(this.row, other.getCol(), resultantMatrix);
    }

    void mainDiagonalTranspose() {
        double[][] transposedMatrix = new double[row][col];
        for (int i = 0 ; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        display(row, col, transposedMatrix);
    }

    void sideDiagonalTranspose() {
        double[][] transposedMatrix = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposedMatrix[i][j] = matrix[col - 1 - j][row - 1 - i];
            }
        }
        display(row, col, transposedMatrix);
    }

    void verticalLineTranspose() {
        double[][] transposedMatrix = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposedMatrix[i][j] = matrix[i][col - 1 - j];
            }
        }
        display(row, col, transposedMatrix);
    }

    void horizontalLineTranspose() {
        double[][] transposedMatrix = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposedMatrix[i][j] = matrix[row - 1 - i][j];
            }
        }
        display(row, col, transposedMatrix);
    }

    public static double determinantOfMatrix(double[][] matrix, int size) {

        double determinant = 0;
        if (size == 1) {
            return matrix[0][0];
        } else {

            double[][] mod = new double[size - 1][size - 1];

            determinant = 0;

            int sign = 1;

            for (int a = 0; a < size; a++) {

                int m = 0;
                int n = 0;

                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {

                        if (row != 0 && col != a) {
                            mod[n][m] = matrix[row][col];
                            m++;
                            if (m == size - 1) {
                                m = 0;
                                n++;
                            }
                        }
                    }
                }

                determinant += sign * matrix[0][a] * determinantOfMatrix(mod, size - 1);
                sign *= -1;
            }
        }
        return determinant;
    }


    public static double[][] transpose(double[][] matrix, int dimension) {
        double[][] transposeOfMatrix = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                transposeOfMatrix[j][i] = matrix[i][j];
            }
        }
        return transposeOfMatrix;
    }



    static void getCofactor(double[][] A, double[][] temp, int p, int q, int n)
    {
        int i = 0;
        int j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = A[row][col];

                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Recursive function for finding determinant of matrix.
    n is current dimension of A[][]. */
    static double determinant(double[][] A, int n)
    {
        double D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (n == 1)
            return A[0][0];

        double[][] temp = new double[n][n]; // To store cofactors

        int sign = 1; // To store sign multiplier

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of A[0][f]
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);

            // terms are to be added with alternate sign
            sign = -sign;
        }

        return D;
    }

    // Function to get adjoint of A[N][N] in adj[N][N].
    static void adjoint(double[][] A,double[][] adj, int n)
    {
        if (n == 1)
        {
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        double[][] temp = new double[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                // Get cofactor of A[i][j]
                getCofactor(A, temp, i, j, n);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                double sign = Math.pow(-1, 2 + i + j);

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinant(temp, n-1));
            }
        }
    }

    // Function to calculate and store inverse, returns false if
// matrix is singular
    static boolean inverse(double A[][], double [][]inverse, int n) {
        // Find determinant of A[][]
        double det = determinantOfMatrix(A, n);
        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        // Find adjoint
        double[][] adj = new double[n][n];
        adjoint(A, adj, n);

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inverse[i][j] = adj[i][j] / (float) det;

        return true;
    }

}
