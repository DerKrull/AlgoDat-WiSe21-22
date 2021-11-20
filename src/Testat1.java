import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Testat1 {

    static int[] testArray1 = {10, 1000, 10000000}; // Länge der Arrays
    static int[] testArray2 = {100, 1000000, Integer.MAX_VALUE/1000}; // Wertebereich der Test Arrays
    static int[] testArray3 = {0, 10, 50}; // Identische Elemente in %
    static int[] testArray4 = {2, 5, 10}; // Vorsortierung an welchen Stellen die randoms eingefügt wird

    public static void main(String[] args) {
        /*
        TODO write output to file
        */

        createFile();
        writeToFile("================ Start ===================", "");

        double[] results = new double[4];
        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                if(i != j) {
                    for(int x = 0; x < 3; x++) {
                        for(int y = 0; y < 3; y++) {
                            System.out.println("" + i + x + " " + j + y);
                            results = runAlgorithms(generateTest(i, x, j, y));
                            writeToFile("" + i + x + " " + j + y, "" + results[0] + " " + results[1] + " " + results[2] + " " + results[3]);
                        }
                    }
                }
            }
        }
    }

    public static int[] generateTest(int i1, int i2, int j1, int j2){

        //Default Werte
        int laenge = 1000;
        int wertebereich = 1000;
        int identical = 0;
        int vorsortiert = 0;

        if(i1 == 1){
            //Länge
            if(j1 == 1){
                //Länge  -> Leer
                return null;
            }else if(j1 == 2){
                //Wertebereich
                laenge = testArray1[i2];
                wertebereich = testArray2[j2];

                int[] res = generateRandomArray(laenge, wertebereich);
                return res;
            }else if(j1 == 3){
                //identische Elemente
                laenge = testArray1[i2];
                identical = testArray3[j2];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(j1 == 4){
                //Vorsortierung
                laenge = testArray1[i2];
                vorsortiert = testArray4[j2];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }

        }else if(i1 == 2){
            //Wertebereich
            if(j1== 1){
                //Länge
                wertebereich = testArray2[i2];
                laenge = testArray1[j2];

                int [] res = generateRandomArray(laenge, wertebereich);
                return res;
            }else if(j1 == 2 ){
                //Wertebereich -> Leer
                return null;
            }else if(j1 == 3){
                //identische Elemente
                wertebereich = testArray2[i2];
                identical = testArray3[j2];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(j1 == 4){
                //Vorsortierung
                wertebereich = testArray2[i2];
                vorsortiert = testArray4[j2];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }
        }else if(i1  == 3){
            //identische Elemente
            if(j1== 1){
                //Länge
                identical = testArray3[i2];
                laenge = testArray1[j2];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(j1 == 2){
                //Wertebereich
                identical = testArray3[i2];
                wertebereich = testArray2[j2];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(j1 == 3){
                //identische Elemente -> Leer
                return null;
            }else if(j1 == 4){
                //Vorsortierung
                identical = testArray3[i2];
                vorsortiert = testArray4[j2];

                int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
                return res;
            }
        }else if(i1 == 4){
            //Vorsortierung
            if(j1==1){
                //Länge
                vorsortiert = testArray4[i2];
                laenge = testArray1[j2];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }else if(j1 == 2){
                //Wertebereich
                vorsortiert = testArray4[i2];
                wertebereich = testArray2[j2];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }else if(j1 == 3){
                //identische Elemente
                vorsortiert = testArray4[i2];
                identical = testArray3[j2];

                int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
                return res;
            }else if(j1 == 4){
                //Vorsortierung -> Leer
                return null;
            }
        }
        return null;
    }

    public static void createFile() {
        try {
            File myObj = new File("testat1.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
                myObj.delete();
                createFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String testcase, String data) {
        try {
            FileWriter myWriter = new FileWriter("testat1.csv", true);
            myWriter.write(testcase + " " + data + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int[] combinedTest(int amount, int percentageIdentical, int maxRandomNumber, int randomLevel) {
        int[] arr1 = generateIdenticalArray(amount/2, percentageIdentical, maxRandomNumber);
        int[] arr2 = generatePartialSortedArray(amount/2, 0, maxRandomNumber, randomLevel);
        int[] res = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = arr1[j];
                j++;
            } else {
                res[i] = arr2[k];
                k++;
            }
        }
        return res;
    }

    public static int[] generateIdenticalArray(int amount, int percentageIdentical, int maxRandomNumber) {
        if(percentageIdentical > 100 || percentageIdentical < 0) {
            System.out.println("Percentage Identical has to be between 0 and 100");
            System.exit(42);
        }
        if(percentageIdentical == 0) {
            percentageIdentical = 1;
        }
        int inputIdentical = 100/percentageIdentical;
        int identicalAmount = amount / inputIdentical;
        int restAmount = amount - identicalAmount;

        int[] percentageIdenticalArray = new int[identicalAmount];
        int randomNumber = (int) Math.floor(Math.random() * (maxRandomNumber));
        for(int i = 0; i < percentageIdenticalArray.length; i++) {
            percentageIdenticalArray[i] = randomNumber;
        }

        int[] restArray = generateRandomArray(restAmount, maxRandomNumber);
        int[] result = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < result.length; i++) {
            if(i % inputIdentical == inputIdentical - 1) {
                result[i] = percentageIdenticalArray[j];
                j++;
            } else {
                result[i] = restArray[k];
                k++;
            }
        }
        return result;
    }

    public static int[] generatePartialSortedArray(int amount, int startSorted, int maxRandomNumber, int randomLevel) {
        int[] arrSorted = generateSortedArray(amount, startSorted);
        int[] arrRandom = generateRandomArray(amount, maxRandomNumber);
        int[] arrMixed = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < amount; i++) {
            if(i % randomLevel == randomLevel - 1) {
                arrMixed[i] = arrRandom[j];
                j++;
            } else {
                arrMixed[i] = arrSorted[k];
                k++;
            }
        }

        return arrMixed;
    }

    public static int [] generateSortedArray(int amount, int startSorted) {
        int[] arr = new int[amount];
        int i = 0;
        for(int j = startSorted; j < amount + startSorted; j++) {
            arr[i] = j;
            i++;
        }
        return arr;
    }

    public static int[] generateRandomArray(int amount, int maxRandomNumber) {
        int[] arr = new int[amount];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*(maxRandomNumber));//note, this generates numbers from [0,9]
        }
        return arr;
    }

    public static double[] runAlgorithms(int[] arr) {
        int[] sort1 = new int[arr.length];
        System.arraycopy(arr, 0, sort1, 0, arr.length);
        double timeMerge = System.nanoTime();
        mergeSort(sort1,0, sort1.length - 1);
        timeMerge = (System.nanoTime() - timeMerge) / 1000;

        int[] sort2 = new int[arr.length];
        System.arraycopy(arr, 0, sort2, 0, arr.length);
        double timeQuick = System.nanoTime();
        try {
            randomizedQuicksort(sort2,0, sort2.length - 1);
            timeQuick = (System.nanoTime() - timeQuick) / 1000;
        } catch (StackOverflowError e) {
            System.out.println("--- StackOverFlow ---");
            timeQuick = -1;
        }


        int[] sort3 = new int[arr.length];
        System.arraycopy(arr, 0, sort3, 0, arr.length);
        int[] secondArray = new int[arr.length];
        double timeCount = System.nanoTime();
        countingSort(sort3, secondArray);
        timeCount = (System.nanoTime() - timeCount) / 1000;

        int[] sort4 = new int[arr.length];
        System.arraycopy(arr, 0, sort4, 0, arr.length);
        double timeHeap = System.nanoTime();
        heapSort(sort4);
        timeHeap = (System.nanoTime() - timeHeap) / 1000;

        double[] timeAll = {timeMerge, timeQuick, timeCount, timeHeap};
        return timeAll;
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

    public static int[] randomizedQuicksort (int[] A, int p, int r) {
        if (p < r) {
            Analysis.addComparison();
            int q = randomizedPartition(A,p,r);
            randomizedQuicksort(A, p, q-1);
            randomizedQuicksort(A,q+1,r);
        }
        return A;
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

    public static int[] countingSort (int[] A, int[] B) {
        int k = A[0];
        for(int i = 0; i < A.length; i++) {
            if(A[i] > k ) {
                k = A[i];
            }
        }
        int[] C = new int[k+1];

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
        return B;
    }

    public static int[] heapSort(int[] arr) {
        buildMaxHeap(arr);
        int heapsize = arr.length;
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapsize = heapsize - 1;
            maxHeapify(arr, 0, heapsize);
        }
        return arr;
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


