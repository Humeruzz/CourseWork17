package CodePart;

import java.io.*;
import java.util.Arrays;

public class Matrix {
    private int A[][];
    private int row, column;

    public Matrix(int num, boolean empty) {
        row = column = num;
        A = new int[num][num];
        if (empty) {
            for (int i = 0; i < num; i++){
                for (int j = 0; j < num; j++){
                    A[i][j] = 0;
                }
            }
        } else{
            for (int i = 0; i < num; i++){
                for (int j = 0; j < num; j++){
                    String temp = Double.toString(Math.random()*10);
                    A[i][j] = Integer.parseInt(Character.toString(temp.charAt(0)));
                }
            }
        }
    }

    public Matrix(int num1, int num2, boolean empty) {
        column = num2; row = num1;
        A = new int[num1][num2];
        if (empty) {
            for (int i = 0; i < num1; i++){
                for (int j = 0; j < num2; j++){
                    A[i][j] = 0;
                }
            }
        } else{
            for (int i = 0; i < num1; i++){
                for (int j = 0; j < num2; j++){
                    String temp = Double.toString(Math.random()*10);
                    A[i][j] = Integer.parseInt(Character.toString(temp.charAt(0)));
                }
            }
        }
    }

    public boolean MEqualCR (Matrix obj1){
        if ((obj1.row == this.row)&&(obj1.column == this.column)){
            return true;
        } else {
            return false;
        }
    }

    public boolean MFullEqual (Matrix obj1){
        if ((obj1.row == this.row)&&(obj1.column == this.column)){
            boolean equal = true;
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.column; j++){
                    if (this.A[i][j] == obj1.A[i][j]){
                    } else{
                        equal = false;
                    }
                }
            }
            if (equal){
            return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void setMatrixFile(String filePath) throws FileNotFoundException {
        int num1 = 0, num2 = 0;
        try{
            BufferedReader br = new BufferedReader( new FileReader(filePath));
            String line;
            int k = 0;
            for (int l = 0; l < 2; l++) {
                line = br.readLine();
                if (k < 2) {
                    if (k == 0) {
                        num1 = Integer.parseInt(line);
                    } else {
                        num2 = Integer.parseInt(line);
                    }
                    k++;
                }// Main numbers
            }
            Matrix M = new Matrix(num1, num2, true);
            for (int i = 0; i < num1; i ++){
                for (int j = 0; j < num2; j++){
                    line = br.readLine();
                    M.setOnIndex(i,j,Integer.parseInt(line));
                }
            }
            br.close();
            this.setM(M);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMatrixFile(String filePath){
        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.println(this.row);
            pw.println(this.column);
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.column; j++){
                    pw.println(this.A[i][j]);
                }
            }
            pw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sumMatrix(Matrix obj1){
        if (this.MEqualCR(obj1)){
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.column; j++){
                    this.A[i][j] = this.A[i][j] + obj1.A[i][j];
                }
            }
        }
    }

    public void subMatrix(Matrix obj1){
        if (this.MEqualCR(obj1)){
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.column; j++){
                    this.A[i][j] = this.A[i][j] - obj1.A[i][j];
                }
            }
        }
    }

    public void multiMatrixNum(int num) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.A[i][j] = this.A[i][j] * num;
            }
        }
    }

    public void multiMatrixVec(int [] num){
        if (this.row == num.length){
            Matrix tempM = new Matrix(num.length, 1, true);
            int tempI = 0;
            for (int i = 0; i < this.row; i++){
                for (int j = 0; j < this.column; j++){
                    tempI += this.A[i][j] * num[j];
                }
                tempM.setOnIndex(i,0, tempI);
                tempI = 0;
            }
            this.setM(tempM);
        }
    }

    public void multiMatrixMat(Matrix obj){
        if (this.row == obj.column){
            int tempI = 0;
            Matrix tempM = new Matrix(this.row,obj.column, true);
            for (int i = 0; i < this.row; i++ ){
                for (int k = 0; k < obj.column; k++) {
                    for (int j = 0; j < this.column; j++) {
                        tempI += this.A[i][j] * obj.A[j][k];
                    }
                    tempM.setOnIndex(i,k,tempI);
                    tempI = 0;
                }
            }
            this.setM(tempM);
        }
    }

    public void setOnIndex (int num1, int num2, int value) {
        A[num1][num2] = value;
    }

    public void setM(Matrix obj){
        this.row = obj.row;
        this.column = obj.column;
        this.A = obj.A;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        String line = "Matrix{ A=\n";
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.column; j++){
                if (this.A[i][j] >9) {
                    line += this.A[i][j];
                    line += ", ";
                }
                else {
                    line += this.A[i][j];
                    line += " , ";
                }
            }
            line += '\n';
        }
        String line2 = "row=" + row + ", column=" + column +'}';
        return line + line2;
    }
}
