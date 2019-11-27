package CodePart;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix M = new Matrix(3,2,false);
        Matrix M1 = new Matrix(2,3, false);
        System.out.println(M.toString());
        System.out.println(M1.toString());
        M.multiMatrixMat(M1);
        System.out.println(M.toString());
    }
}
