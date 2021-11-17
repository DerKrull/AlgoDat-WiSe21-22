import java.util.Arrays;
import java.util.Random;

public class Testat1 {
    public static void main(String[] args) {
        /*
        TODO write output to file
         */
        System.out.println("Algorythm; Merge; Randomized Quick Sort; Counting Sort; Heap Sort");
        /*
        ########################################################################
        #                          Variable Länge                              #
        ########################################################################
         */
        int[] testArray11 = generateArray(10,20,0);
        int[] testArray12 = generateArray(100, 200, 0);
        int[] testArray13 = generateArray(1000, 2000, 0);
        int[] testArray14 = generateArray(10000, 20000, 0);
        int[] testArray15 = generateArray(100000, 200000, 0);
        int[] testArray16 = generateArray(1000000, 2000000, 0);

        System.out.println("Variable Länge: 10, 100, 1000, 10000, 100000, 1000000");

        runAlgorythms(testArray11,20);
        runAlgorythms(testArray12, 200);
        runAlgorythms(testArray13, 2000);
        runAlgorythms(testArray14, 20000);
        runAlgorythms(testArray15, 200000);
        runAlgorythms(testArray16, 2000000);

        System.out.println();
        System.out.println();

        /*
        ########################################################################
        #                      Variable Wertebereich                           #
        ########################################################################
         */
        int[] testArray21 = generateArray(1000, 1000, 0);
        int[] testArray22 = generateArray(1000, 10000, 0);
        int[] testArray23 = generateArray(1000, 100000, 0);
        int[] testArray24 = generateArray(1000, 1000000, 0);
        int[] testArray25 = generateArray(1000, 1500000, 0);
        int[] testArray26 = generateArray(1000, 2000000, 0);

        System.out.println("Variable Wertebereich: 1000, 10000, 100000, 1000000, 1500000, 2000000");

        runAlgorythms(testArray21, 1000);
        runAlgorythms(testArray22, 10000);
        runAlgorythms(testArray23, 100000);
        runAlgorythms(testArray24, 1000000);
        runAlgorythms(testArray25, 1500000);
        runAlgorythms(testArray26, 2000000);

        System.out.println();
        System.out.println();

        /*
        ########################################################################
        #              Variable Anzahl identsicher Elemente                    #
        ########################################################################
        */
        int[] testArray31 = generateArray(100000, 10, 0);
        int[] testArray32 = generateArray(100000, 1000, 0);
        int[] testArray33 = generateArray(100000, 100000, 0);
        int[] testArray34 = generateArray(100000, 10000000, 0);

        System.out.println("Variable identische Elemente: " + getIdentical(testArray31) + ", " + getIdentical(testArray32) + ", " + getIdentical(testArray33) + ", "
                + getIdentical(testArray34));

        runAlgorythms(testArray31, 10);
        runAlgorythms(testArray32, 1000);
        runAlgorythms(testArray33, 100000);
        runAlgorythms(testArray34, 10000000);

        System.out.println();
        System.out.println();

        /*
        TODO Define presorted array
            - 1 2 3 4 1 2 3 4 1 2 3 4 1 2 3 4
            - 1 2 5 3 4 10 6 7 20 12 13 14
         */
        /*
        ########################################################################
        #                    Variable Vorsortierung 1                          #
        ########################################################################

        int[] testArray41 = generatePartialSortedArray(100000,200000,100000);
        int[] testArray42 = generatePartialSortedArray(100000,200000,10000);
        int[] testArray43 = generatePartialSortedArray(100000,200000,1000);
        int[] testArray44 = generatePartialSortedArray(100000,200000,100);
        int[] testArray45 = generatePartialSortedArray(100000,200000,10);
        int[] testArray46 = generatePartialSortedArray(100000,200000,1);

        System.out.println("Variable Vorsortierung: " + getRandomness(testArray41) + ", " + getRandomness(testArray42) + ", " + getRandomness(testArray43) + ", "
                        + getRandomness(testArray44) + ", " + getRandomness(testArray45) + ", " + getRandomness(testArray46));

        runAlgorythms(testArray41, 200000);
        runAlgorythms(testArray42, 200000);
        runAlgorythms(testArray43, 200000);
        runAlgorythms(testArray44, 200000);
        runAlgorythms(testArray45, 200000);
        runAlgorythms(testArray46, 200000);
        */

    }

    /*
    TODO define what kind of presorted array to generate
     */
    public static int[] generatePartialSortedArray (int count, int bound, int sorted) {
        int[] arr = new int[count];
        arr = generateArray(count, bound, 0);
        for(int i = 0; i < sorted; i++) {
            Arrays.sort(arr,arr.length/sorted * i,arr.length/sorted * (i+1));
        }
        return  arr;
    }

    public static int[] generateArray(int count, int bound, int bottomBarrier) {
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*(bound-bottomBarrier) + bottomBarrier);//note, this generates numbers from [0,9]
        }
        return arr;
    }

    /*
    TODO how to measure to what degree an array is sorted?
     */
    public static double getRandomness(int[] arr) {
        int counter = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                counter++;
            }
        }
        return counter;
    }

    public static double getIdentical(int[] arr) {
        int counter = 0;
        int[] elements = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            elements[i] = arr[i];
        }
        Arrays.sort(elements);
        for(int i = 0; i < elements.length - 1; i++) {
            if(elements[i] == elements[i+1]) {
                counter++;
            }
        }
        return counter;
    }

    public static void runAlgorythms (int[] arr, int bound) {
        double switches = getRandomness(arr);
        double identical = getIdentical(arr);
        //System.out.println(Arrays.toString(arr));

        double timeMerge = System.nanoTime();
        mergeSort(arr,0, arr.length - 1);
        timeMerge = System.nanoTime() - timeMerge;

        double timeQuick = System.nanoTime();
        randomizedQuicksort(arr,0, arr.length - 1);
        timeQuick = System.nanoTime() - timeQuick;

        int[] secondArray = new int[arr.length];
        double timeCount = System.nanoTime();
        countingSort(arr, secondArray, bound);
        timeCount = System.nanoTime() - timeCount;

        double timeHeap = System.nanoTime();
        heapSort(arr);
        timeHeap = System.nanoTime() - timeHeap;

        /*
        Ausgabe für die Konsole
         */
        //System.out.println("Array: Länge: " + arr.length + " - Vorsortierung (benötigte Vertauschungen): " + switches + " - Wertebereich: " + bound + " - identische Elemente: " + identical);
        //System.out.println("Merge | Randomized Quick | Counting | Heap ");
        //System.out.println(timeMerge/1000 + " | " + timeQuick/1000 + " | " + timeCount/1000 + " | " + timeHeap/1000);

        /*
        TODO Output for file and write it to file
         */
    }

    public static int[] mergeSort(int[] arr, int startSort, int endSort) {
        if (startSort < endSort) {
            int q = (startSort + endSort) / 2;
            mergeSort(arr, startSort, q);
            mergeSort(arr, q + 1, endSort);
            merge(arr, startSort, q, endSort);
        }
        return arr;
    }

    public static void merge(int[] arr, int startFirstArray, int endFirstArray, int endSecondArray) {
        int lengthFirstArray = endFirstArray - startFirstArray + 1;
        int lengthSecondArray = endSecondArray - endFirstArray;
        int[] FirstArray = new int[lengthFirstArray + 1];
        int[] SecondArray = new int[lengthSecondArray + 1];
        int i;
        int j;
        for (i = 0; i < lengthFirstArray; i++) {
            FirstArray[i] = arr[startFirstArray + i];
        }
        for (j = 0; j < lengthSecondArray; j++) {
            SecondArray[j] = arr[endFirstArray + 1 + j];
        }
        FirstArray[lengthFirstArray] = Integer.MAX_VALUE;
        SecondArray[lengthSecondArray] = Integer.MAX_VALUE;
        i = 0;
        j = 0;
        for (int k = startFirstArray; k <= endSecondArray; k++) {
            if (FirstArray[i] <= SecondArray[j]) {
                arr[k] = FirstArray[i];
                i++;
            } else {
                arr[k] = SecondArray[j];
                j++;
            }
        }
    }

    public static void randomizedQuicksort (int[] A, int p, int r) {
        if (p < r) {
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
        return partition(A,p,r);
    }

    public static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        int temp;
        for (int j = p; j < r-1; j++) {
            if (A[j] <= x ) {
                i = i + 1;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        return i + 1;
    }

    public static void countingSort (int[] A, int[] B, int k) {
        int[] C = new int[k];

        // Count different Numbers in A
        for (int value : A) {  //for(int j = 0; j < A.length; j++) {
            C[value] = C[value] + 1;
        }

        // Convert sums to indices, by adding them up
        for(int i = 1; i < k; i++) {
            C[i] = C[i] + C[i-1];
        }

        // Convert indices down to Arrays starting with 0
        for(int i = 0; i < C.length; i++) {
            C[i]--;
        }

        // Write A[j] to the corresponding index in B
        for(int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]]] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }
    }

    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        int heapsize = arr.length;
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapsize = heapsize - 1;
            maxHeapify(arr, 0, heapsize);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        int heapsize = arr.length;
        for (int i = (arr.length / 2 - 1); i >= 0; i--) {
            maxHeapify(arr, i, heapsize);
        }
    }

    public static void maxHeapify(int[] arr, int i, int heapsize) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;
        if (l < heapsize && arr[l] > arr[i]) {
            largest = l;
        }
        if (r < heapsize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, heapsize);
        }
    }
}
