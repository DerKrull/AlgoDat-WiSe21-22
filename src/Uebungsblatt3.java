//import java.util.Arrays;


public class Uebungsblatt3 {
    public static void main(String[] args) {
        int count = 1000000;
        int bound = 1000;
        int[] sortArray = generateArray(count,bound, 0,false);
        int[] sortedArray = new int[sortArray.length];
        int[] radixSortArr = generateArray(count,bound,100,false);
            /*Das ist ein Kommentar
                int[] radixSortedArr;
            System.out.println(Arrays.toString(sortArray));*/

        System.out.println();

        System.out.println("--- Counting Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        countingSort(sortArray,sortedArray,bound);
        //System.out.println("Sortiert: " + Arrays.toString(sortedArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Quick Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        //sortedArray =
                quicksort(sortArray,0,sortArray.length-1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Radix Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        //radixSortedArr =
                radixSort(radixSortArr,3);
        //System.out.println("Sortiert: " + Arrays.toString(radixSortArr));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();

        System.out.println("--- Quick Sort (Radix)---");
        Analysis.clearData();
        Analysis.startTime();
        //radixSortedArr =
                quicksort(radixSortArr,0,radixSortArr.length-1);
        //System.out.println("Sortiert: " + Arrays.toString(searchArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();

        System.out.println();
    }

    public static void countingSort (int[] A, int[] B, int k) {
        int[] C = new int[k];

        // Count different Numbers in A
        for (int value : A) {  //for(int j = 0; j < A.length; j++) {
            C[value] = C[value] + 1;
            Analysis.addArrayAccess();
        }

        // Convert sums to indices, by adding them up
        for(int i = 1; i < k; i++) {
            C[i] = C[i] + C[i-1];
            Analysis.addArrayAccess();
        }

        // Convert indices down to Arrays starting with 0
        for(int i = 0; i < C.length; i++) {
           C[i]--;
            Analysis.addArrayAccess();
        }

        // Write A[j] to the corresponding index in B
        for(int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]]] = A[j];
            C[A[j]] = C[A[j]] - 1;
            Analysis.addArrayAccess();
            Analysis.addArrayAccess();
        }
    }

    public static void radixSort(int[] A, int d) {
        for(int i = 0; i < d; i++) {
            A = radixCountingSort(A,i);
        }
    }

    public static int[] radixCountingSort(int[] A, int d) {
        int[] B = new int[A.length];
        int[] C = new int[10];

        for (int k : A) { //for(int j = 0; j < A.length; j++ ) {
            C[k % (int) Math.pow(10, d + 1) / (int) Math.pow(10, d)] = C[k % (int) Math.pow(10, d + 1) / (int) Math.pow(10, d)] + 1;
            Analysis.addArrayAccess();
        }

        for(int i = 1; i < 10; i++) {
            C[i] = C[i] + C[i-1];
            Analysis.addArrayAccess();
        }

        for(int i = 0; i < 10; i++) {
            C[i]--;
            Analysis.addArrayAccess();
        }

        for(int j = A.length-1; j >= 0; j--) {
            B[C[A[j] % (int) Math.pow(10,d+1) / (int) Math.pow(10,d)]] = A[j];
            C[A[j] % (int) Math.pow(10,d+1) / (int) Math.pow(10,d)] = C[A[j] % (int) Math.pow(10,d+1) / (int) Math.pow(10,d)] - 1;
            Analysis.addArrayAccess();
            Analysis.addArrayAccess();
        }
        return B;
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

    public static int[] generateArray (int count, int bound, int bottomBarrier, boolean distinct) {
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*(bound-bottomBarrier) + bottomBarrier);//note, this generates numbers from [0,9]

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
