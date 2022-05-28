package hilos;

public class RunMatriz {
    public static void main(String[] args) {
        int[][] mat1Values = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz m1 = new Matriz(mat1Values);
        int[][] mat2Values = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz m2 = new Matriz(mat2Values);

        Matriz result = null;
        try {
            result = m1.multiplyWithThreads(m2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }

    private static void printArray(int[] arr, boolean vertical) {
        var escapeSquence = vertical ? "\n" : "\t";

        for(var i = 0; i < arr.length; i ++) {
            System.out.printf("%d%s", arr[i], escapeSquence);
        }
    }
}
