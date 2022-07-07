package processor;

import java.util.Scanner;

class Processor {

    Scanner sc = new Scanner(System.in);

    private final int choice;

    public Processor(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }

    public Matrix getMatrix() {

        int row = sc.nextInt();
        int col = sc.nextInt();

        return new Matrix(row, col);
    }

    void process() {

        switch (getChoice()) {

            case 1:
                System.out.println("Enter size of first matrix:");
                Matrix matrix1 = getMatrix();
                matrix1.fill();
                System.out.println("Enter size of second matrix:");
                Matrix matrix2 = getMatrix();
                matrix2.fill();
                if (matrix1.getRow() != matrix2.getRow() && matrix1.getCol() != matrix2.getCol()) {
                    System.out.println("The operation cannot be performed.");
                    break;
                }
                System.out.println("The result is:");
                matrix1.addMatrices(matrix2);
                break;

            case 2:
                System.out.println("Enter size of matrix:");
                Matrix matrix = getMatrix();
                matrix.fill();
                System.out.println("Enter the constant:");
                int constant = sc.nextInt();
                matrix.multiplyByConstant(constant);
                break;

            case 3:
                System.out.println("Enter size of first matrix:");
                Matrix matrixA = getMatrix();
                matrixA.fill();
                System.out.println("Enter size of second matrix:");
                Matrix matrixB = getMatrix();
                matrixB.fill();
                if (matrixA.getCol() != matrixB.getRow()) {
                    System.out.println("The operation cannot be performed.");
                    break;
                }
                matrixA.matrixMultiplication(matrixB);
                break;

            case 4:
                System.out.println("1. Main diagonal");
                System.out.println("2. Side diagonal");
                System.out.println("3. Vertical line");
                System.out.println("4. Horizontal line");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        Matrix mainDiagonal = getMatrix();
                        mainDiagonal.fill();
                        mainDiagonal.mainDiagonalTranspose();
                        break;

                    case 2:
                        Matrix sideDiagonal = getMatrix();
                        sideDiagonal.fill();
                        sideDiagonal.sideDiagonalTranspose();
                        break;

                    case 3:
                        Matrix verticalLine = getMatrix();
                        verticalLine.fill();
                        verticalLine.verticalLineTranspose();
                        break;

                    case 4:
                        Matrix horizontalLine = getMatrix();
                        horizontalLine.fill();
                        horizontalLine.horizontalLineTranspose();
                        break;

                    default:
                        System.out.println("This operation is not supported, choose another:");
                }
                break;

            case 5:
                System.out.println("Enter matrix size:");
                int m = sc.nextInt();
                int n = sc.nextInt();
                System.out.println("Enter matrix:");
                double[][] mat = new double[m][m];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        mat[i][j] = sc.nextDouble();
                    }
                }
                if (m != n) {
                    System.out.println("Determinant can be calculated only for square matrix.");
                }
                System.out.println(Matrix.determinantOfMatrix(mat, m));
                break;

            case 6:
                System.out.println("Enter matrix size:");
                int row = sc.nextInt();
                int col = sc.nextInt();
                System.out.println("Enter matrix:");
                double[][] gMatrix = new double[row][row];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < row; j++) {
                        gMatrix[i][j] = sc.nextDouble();
                    }
                }
                if (row != col) {
                    System.out.println("Determinant can be calculated only for square matrix.");
                }
                double[][] inverseMatrix = new double[row][row];
                System.out.println("The result is:");
                if (Matrix.inverse(gMatrix, inverseMatrix, row)) {
                    Matrix.displaySquareMatrix(inverseMatrix);
                }
                break;

            case 0:
                System.exit(1);
                break;

            default:
                System.out.println("Do not support this operation, choose another one:");
        }
    }
}