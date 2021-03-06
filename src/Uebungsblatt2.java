import java.util.Arrays;
import java.util.Random;

public class Uebungsblatt2 {
    public static void main(String[] args) {
        int[] sortArray = generateArray(10000,5000, false);
        System.out.println(Arrays.toString(sortArray));

        System.out.println();

        System.out.println("--- Quick Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        quicksort(sortArray, 0, sortArray.length - 1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Randomized-Quick Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        randomizedQuicksort(sortArray, 0, sortArray.length - 1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Custom-Randomized-Quick Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        customRandomizedQuicksort(sortArray, 0, sortArray.length - 1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Merge Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        Uebungsblatt1.mergeSort(sortArray, 0, sortArray.length - 1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();
    }

    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            Analysis.addComparison();
            int q = partition(A,p,r);
            quicksort(A,p,q-1);
            quicksort(A,q+1,r);
        }
    }

    public static int partition(int[] A, int p, int r) {
        int x = A[r];
        Analysis.addArrayAccess();
        int i = p - 1;
        int temp;
        for (int j = p; j < r-1; j++) {
            if (A[j] <= x ) {
                Analysis.addComparison();
                Analysis.addArrayAccess();
                i = i + 1;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                Analysis.increaseArrayAccess(3);
            }
        }
        temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        Analysis.increaseArrayAccess(3);
        return i + 1;
    }

    public static void randomizedQuicksort (int[] A, int p, int r) {
        if (p < r) {
            Analysis.addComparison();
            int q = randomizedPartition(A,p,r);
            randomizedQuicksort(A, p, q-1);
            randomizedQuicksort(A,q+1,r);
        }
    }

    public static int randomizedPartition(int[] A, int p, int r) {
        Random randomNum = new Random();
        int i = randomNum.nextInt(r);
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        Analysis.increaseArrayAccess(3);
        return partition(A,p,r);
    }

    public static void customRandomizedQuicksort(int[] A, int p, int r) {
        if (p < r) {
            Analysis.addComparison();
            int q = customRandomizedPartition(A,p,r);
            randomizedQuicksort(A, p, q-1);
            randomizedQuicksort(A,q+1,r);
        }
    }

    public static int customRandomizedPartition(int[] A, int p, int r) {
        Random randomNum = new Random();
        int[] medArr = {A[randomNum.nextInt(r)], A[randomNum.nextInt(r)], A[randomNum.nextInt(r)]};
        Arrays.sort(medArr);
        int i = medArr[1];
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        Analysis.increaseArrayAccess(3);
        return partition(A, p, r);
    }

    public static int[] generateArray (int count, int bound, boolean distinct) {
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*bound);//note, this generates numbers from [0,9]

            if(distinct) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] == arr[j]) {
                        i--; //if a[i] is a duplicate of a[j], then run the outer loop on i again
                        break;
                    }
                }
            }
        }
        return arr;
    }
}
