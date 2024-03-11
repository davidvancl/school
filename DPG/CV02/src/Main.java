import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int min = 1;
        int max = 10000;
        int count = 1000;

        runAndMeasureQuickSort(min, max, count);
        runAndMeasureParallelQuickSort(min, max, count);
        runAndMeasureMergeSort(min, max, count);
        runAndMeasureParallelMergeSort(min, max, count);
        System.out.println("===========END===========");
    }

    public static void runAndMeasureQuickSort(int min, int max, int count) {
        int[] array = IntStream.generate(() -> new Random().nextInt(max) + min).limit(count).toArray();

        System.out.println("==========QuickSort==========");
        long startTime = System.currentTimeMillis();

        QuickSort.run(array);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Čas běhu: " + elapsedTime + " milisekund");

//        printArray("QuickSort", array);
    }

    public static void runAndMeasureParallelQuickSort(int min, int max, int count) {
        int[] array = IntStream.generate(() -> new Random().nextInt(max) + min).limit(count).toArray();

        System.out.println("==========ParallelQuickSort==========");
        long startTime = System.currentTimeMillis();

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            ParallelQuickSort quickSortTask = new ParallelQuickSort(array, 0, array.length - 1);
            forkJoinPool.invoke(quickSortTask);
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Čas běhu: " + elapsedTime + " milisekund");

//        printArray("ParallelQuickSort", array);
    }

    public static void runAndMeasureMergeSort(int min, int max, int count) {
        int[] array = IntStream.generate(() -> new Random().nextInt(max) + min).limit(count).toArray();

        System.out.println("==========MergeSort==========");
        long startTime = System.currentTimeMillis();

        MergeSort.run(array);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Čas běhu: " + elapsedTime + " milisekund");

//        printArray("MergeSort", array);
    }

    public static void runAndMeasureParallelMergeSort(int min, int max, int count) {
        int[] array = IntStream.generate(() -> new Random().nextInt(max) + min).limit(count).toArray();

        System.out.println("==========ParallelMergeSort==========");
        long startTime = System.currentTimeMillis();

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            ParallelMergeSort mergeSortTask = new ParallelMergeSort(array, 0, array.length - 1);
            forkJoinPool.invoke(mergeSortTask);
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Čas běhu: " + elapsedTime + " milisekund");

//        printArray("ParallelMergeSort", array);
    }

    public static void printArray(String type, int[] array) {
        System.out.printf("Sorted by '%s': ", type);
        System.out.println(Arrays.toString(array));
    }
}