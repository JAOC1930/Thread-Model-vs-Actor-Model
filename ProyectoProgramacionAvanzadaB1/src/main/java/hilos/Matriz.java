package hilos;

import java.util.ArrayList;
import java.util.List;

public class Matriz {
    private int[][] values;

    public Matriz(int[][] values) {
        this.values = values;
    }

    public int[] getRow(int rowIndex) {
        if( rowIndex >= 0 && rowIndex < values.length) {
            return values[rowIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }

    public int[] getColumn(int colIndex) {
        int[] output = new int[values.length];
        if( colIndex < values[0].length)  {
            for (var i = 0; i < values.length; i ++) {
                output[i] = values[i][colIndex];
            }
        }
        return output;
    }

    public String toString() {
        String output = "";
        for(var fila : values) {
            output += "{";
            for( var value : fila) {
                output += value + "\t";
            }
            output += "}\n";
        }
        return "{\n" + output + "}";
    }



    public Matriz multiplyWithThreads(Matriz mat2) throws InterruptedException {
        List<TaskCalcElement> threads;
        if (values.length == mat2.values[0].length){
            int [][] output = new int[values.length][mat2.values[0].length];
            threads = new ArrayList<>();
            for (var i = 0; i< output.length;i++){
                for(var j = 0; j < output[0].length; j++){
                    TaskCalcElement thread = new TaskCalcElement(this, mat2, i, j);
                    thread.start();
                    threads.add(thread);
                }
            }

            SetWaitThreads(threads);

            for (var t: threads){
                output[t.getRowIndex()][t.getCloIndex()] = t.getElement();
            }
            return new Matriz(output);
        } else {
            throw new IllegalArgumentException("No se puede multiplicar las matrices.");
        }
    }

    private void SetWaitThreads(List<TaskCalcElement> threads) throws InterruptedException{
        for (var t: threads){
            t.join();
        }
    }

    private int calcValue (int[] row, int[] col) {
        int element = 0;
        for(var i = 0; i < row.length; i ++ ) {
            element += row[i] * col[i];
        }
        return element;
    }
}