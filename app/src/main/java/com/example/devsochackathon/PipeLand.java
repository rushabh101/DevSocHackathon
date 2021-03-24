package com.example.devsochackathon;

import android.util.Log;

import java.util.Random;

public class PipeLand {

    private static final String TAG = "PipeLand";

    int i1, j1, i2, j2;

    boolean swapActive = false;

    int[][] layout = new int[8][6];

    PipeLand() {
        Random rand = new Random();
        int[] pipes = {8, 8, 8, 8, 6, 8};

        // Randomly allocating the pipe positions, while the start and end pipes are fixed on corners
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 6; j++) {
                if(i == 0 && j == 0) {
                    layout[i][j] = -1;
                    continue;
                }
                else if(i == 7 && j == 5) {
                    layout[i][j] = -2;
                    continue;
                }

                int t = rand.nextInt(6);
                while(pipes[t] == 0) t = rand.nextInt(6);

                layout[i][j] = t;
                pipes[t]--;
            }
        }
    }

    public boolean swap(String id) {
        if(!swapActive) {
            i1 = Integer.parseInt(id.substring(9, 10))-1;
            j1 = Integer.parseInt(id.substring(10))-1;

            if ((i1 == 0 && j1 == 0) ||(i1 == 7 && j1 == 5)) {
                return false;
            }
        }
        else {
            i2 = Integer.parseInt(id.substring(9, 10))-1;
            j2 = Integer.parseInt(id.substring(10))-1;

            if ((i2 == 0 && j2 == 0) ||(i2 == 7 && j2 == 5)) {
                return false;
            }

            int temp = layout[i1][j1];
            layout[i1][j1] = layout[i2][j2];
            layout[i2][j2] = temp;
        }

        swapActive = !swapActive;
        return !swapActive;

    }

    public boolean checkPath() {
        int pout = 2, i = 0, j = 0;
        while (i != 7 || j != 5) {
            if(pout == 0) i--;
            else if(pout == 1) j++;
            else if(pout == 2) i++;
            else if(pout == 3) j--;

            if(i < 0 || i > 7 || j < 0 || j > 5) return false;

            pout = pipeMove((pout+2)%4, layout[i][j]);
            Log.d(TAG, "number" + pout);
            if(pout == -1) return false;

        }
        return true;
    }

    public int pipeMove(int in, int pipeType) {

        if(pipeType == -2) {
            if(in != 0) return -1;
            else return -2;
        }

        int[][] moveList = {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {0, 2}, {1, 3}};

        if(moveList[pipeType][0] != in && moveList[pipeType][1] != in) return -1;

        int temp = -1;
        for(int i = 0; i < 2; i++) {
            if(moveList[pipeType][i] != in) temp = moveList[pipeType][i];
        }

        return temp;
    }

}
