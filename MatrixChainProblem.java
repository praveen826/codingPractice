package com.coding.problem;

public class MatrixChainProblem {
    protected int[][]m;
    protected int[][]s;
    
    public static void main(String[] args) {
    	int [] dims = {10,30,5,60};
    	MatrixChainProblem matrix = new MatrixChainProblem();
    	//opt.matrixChainOrder(dims);
    	matrix.printParentheses(dims);
    }
    public void matrixChainOrder(int[] dims) {
        int n = dims.length - 1;
        m = new int[n][n];
        s = new int[n][n];

        for (int lenMinusOne = 1; lenMinusOne < n; lenMinusOne++) {
            for (int i = 0; i < n - lenMinusOne; i++) {
                int j = i + lenMinusOne;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k+1][j] + dims[i]*dims[k+1]*dims[j+1];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public void printParentheses(int[] dims) {
    	matrixChainOrder(dims);
        boolean[] inAResult = new boolean[s.length];
        printParentheses(s, 0, s.length - 1, inAResult);
    }

    void printParentheses(int[][]s, int i, int j,  /* for pretty printing: */ boolean[] inAResult) {
        if (i != j) {
            printParentheses(s, i, s[i][j], inAResult);
            printParentheses(s, s[i][j] + 1, j, inAResult);
            String istr = inAResult[i] ? "_result " : " ";
            String jstr = inAResult[j] ? "_result " : " ";
            System.out.println(" A_" + i + istr + "* A_" + j + jstr);
            inAResult[i] = true;
            inAResult[j] = true;
        }
    }
}