import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Testat1 {

  static int[] verLaengen = {10, 1000, 10000000}; // Länge der Arrays
  static int[] verWertebereiche = {1000, 1000000, Integer.MAX_VALUE / 10}; // Wertebereich der Test Arrays
  static int[] prozentIdentisch = {0, 10, 50}; // Identische Elemente in %
  static int[] gradVorsortierung = {2, 4, 10}; // Vorsortierung an welchen Stellen die randoms eingefügt wird

  public static void main(String[] args) {
    //Run algorithm to alocate memory
    int[] test = generateRandomArray(10,100);
    mergeSort(test,0,0);
    createFile();
    writeToFile("================ Start", "Merge-Sort Quick-Sort Counting-Sort Heap-Sort");

    double[] results = new double[4];
    for (int attr1 = 1; attr1 < 5; attr1++) {
      for (int attr2 = 1; attr2 < 5; attr2++) {
        if (attr1 != attr2) {
          for (int valAttr1 = 0; valAttr1 < 3; valAttr1++) {
            for (int valAttr2 = 0; valAttr2 < 3; valAttr2++) {
              System.out.println("" + attr1 + valAttr1 + " " + attr2 + valAttr2);
              results = runAlgorithms(generateTest(attr1, valAttr1, attr2, valAttr2));
              writeToFile("" + attr1 + valAttr1 + " " + attr2 + valAttr2, "" + results[0] + " " + results[1] + " " + results[2] + " " + results[3]);
            }
          }
        }
      }
    }
    writeToFile("================Finished===================", "");
  }

  public static int[] generateTest(int attr1, int valAttr1, int attr2, int valAttr2) {

    //Default Werte
    int laenge = 1000;
    int wertebereich = 1000;
    int identical = 0;
    int vorsortiert = 0;

    if (attr1 == 1) {
      //Länge
      if (attr2 == 1) {
        //Länge  -> Leer
        return null;
      } else if (attr2 == 2) {
        //Wertebereich
        laenge = verLaengen[valAttr1];
        wertebereich = verWertebereiche[valAttr2];
        int[] res = generateRandomArray(laenge, wertebereich);
        return res;
      } else if (attr2 == 3) {
        //identische Elemente
        laenge = verLaengen[valAttr1];
        identical = prozentIdentisch[valAttr2];

        int[] res = generateIdenticalArray(laenge, identical, wertebereich);
        return res;
      } else if (attr2 == 4) {
        //Vorsortierung
        laenge = verLaengen[valAttr1];
        vorsortiert = gradVorsortierung[valAttr2];

        int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
        return res;
      }

    } else if (attr1 == 2) {
      //Wertebereich
      if (attr2 == 1) {
        //Länge
        wertebereich = verWertebereiche[valAttr1];
        laenge = verLaengen[valAttr2];

        int [] res = generateRandomArray(laenge, wertebereich);
        return res;
      } else if (attr2 == 2) {
        //Wertebereich -> Leer
        return null;
      } else if (attr2 == 3) {
        //identische Elemente
        wertebereich = verWertebereiche[valAttr1];
        identical = prozentIdentisch[valAttr2];

        int[] res = generateIdenticalArray(laenge, identical, wertebereich);
        return res;
      } else if (attr2 == 4) {
        //Vorsortierung
        wertebereich = verWertebereiche[valAttr1];
        vorsortiert = gradVorsortierung[valAttr2];

        int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
        return res;
      }
    } else if (attr1 == 3) {
      //identische Elemente
      if (attr2 == 1) {
        //Länge
        identical = prozentIdentisch[valAttr1];
        laenge = verLaengen[valAttr2];

        int[] res = generateIdenticalArray(laenge, identical, wertebereich);
        return res;
      } else if (attr2 == 2) {
        //Wertebereich
        identical = prozentIdentisch[valAttr1];
        wertebereich = verWertebereiche[valAttr2];

        int[] res = generateIdenticalArray(laenge, identical, wertebereich);
        return res;
      } else if (attr2 == 3) {
        //identische Elemente -> Leer
        return null;
      } else if (attr2 == 4) {
        //Vorsortierung
        identical = prozentIdentisch[valAttr1];
        vorsortiert = gradVorsortierung[valAttr2];

        int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
        return res;
      }
    } else if (attr1 == 4) {
      //Vorsortierung
      if (attr2 == 1) {
        //Länge
        vorsortiert = gradVorsortierung[valAttr1];
        laenge = verLaengen[valAttr2];

        int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
        return res;
      } else if (attr2 == 2) {
        //Wertebereich
        vorsortiert = gradVorsortierung[valAttr1];
        wertebereich = verWertebereiche[valAttr2];

        int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
        return res;
      } else if (attr2 == 3) {
        //identische Elemente
        vorsortiert = gradVorsortierung[valAttr1];
        identical = prozentIdentisch[valAttr2];

        int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
        return res;
      } else if (attr2 == 4) {
        //Vorsortierung -> Leer
        return null;
      }
    }
    return null;
  }

  public static void createFile() {
    try {
        File myObj = new File("testat1.txt");
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
      FileWriter myWriter = new FileWriter("testat1.txt", true);
      myWriter.write(testcase + " " + data + "\n");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }

  public static int[] combinedTest(int amount, int percentageIdentical, int maxRandomNumber, int randomLevel) {
    int[] arr1 = generateIdenticalArray(amount / 2, percentageIdentical, maxRandomNumber);
    int[] arr2 = generatePartialSortedArray(amount / 2, 0, maxRandomNumber, randomLevel);
    int[] res = new int[amount];
    int j = 0;
    int k = 0;
    for (int i = 0; i < res.length; i++) {
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
    if (percentageIdentical > 100 || percentageIdentical < 0) {
      System.out.println("Percentage Identical has to be between 0 and 100");
      System.exit(42);
    }
    if (percentageIdentical == 0) {
      percentageIdentical = 1;
    }
    int inputIdentical = 100 / percentageIdentical;
    int identicalAmount = amount / inputIdentical;
    int restAmount = amount - identicalAmount;

    int[] percentageIdenticalArray = new int[identicalAmount];
    int randomNumber = (int) Math.floor(Math.random() * (maxRandomNumber));
    for (int i = 0; i < percentageIdenticalArray.length; i++) {
      percentageIdenticalArray[i] = randomNumber;
    }

    int[] restArray = generateRandomArray(restAmount, maxRandomNumber);
    int[] result = new int[amount];
    int j = 0;
    int k = 0;
    for (int i = 0; i < result.length; i++) {
      if (i % inputIdentical == inputIdentical - 1) {
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
    for (int i = 0; i < amount; i++) {
      if (i % randomLevel == randomLevel - 1) {
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
    for (int j = startSorted; j < amount + startSorted; j++) {
      arr[i] = j;
      i++;
    }
    return arr;
  }

  public static int[] generateRandomArray(int amount, int maxRandomNumber) {
    int[] arr = new int[amount];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (maxRandomNumber)); //note, this generates numbers from [0,9]
    }
    return arr;
  }

  public static double[] runAlgorithms(int[] arr) {
    int[] sort1 = new int[arr.length];
    System.arraycopy(arr, 0, sort1, 0, arr.length);
    double timeMerge = System.nanoTime();
    mergeSort(sort1, 0, sort1.length - 1);
    timeMerge = (System.nanoTime() - timeMerge) / 1000;

    int[] sort2 = new int[arr.length];
    System.arraycopy(arr, 0, sort2, 0, arr.length);
    double timeQuick = System.nanoTime();
    try {
      randomizedQuicksort(sort2, 0, sort2.length - 1);
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
    int[] firstArray = new int[lengthFirstArray + 1];
    int[] secondArray = new int[lengthSecondArray + 1];
    int i;
    int j;
    for (i = 0; i < lengthFirstArray; i++) {
      firstArray[i] = arr[startFirstArray + i];
    }
    for (j = 0; j < lengthSecondArray; j++) {
      secondArray[j] = arr[endFirstArray + 1 + j];
    }
    firstArray[lengthFirstArray] = Integer.MAX_VALUE;
    secondArray[lengthSecondArray] = Integer.MAX_VALUE;
    i = 0;
    j = 0;
    for (int k = startFirstArray; k <= endSecondArray; k++) {
      if (firstArray[i] <= secondArray[j]) {
        arr[k] = firstArray[i];
        i++;
      } else {
        arr[k] = secondArray[j];
        j++;
      }
    }
  }

  public static int[] randomizedQuicksort(int[] A, int p, int r) {
    if (p < r) {
      int q = randomizedPartition(A, p, r);
      randomizedQuicksort(A, p, q - 1);
      randomizedQuicksort(A, q + 1, r);
    }
    return A;
  }

  public static int randomizedPartition(int[] A, int p, int r) {
    Random randomNum = new Random();
    int i = randomNum.nextInt(r);
    int temp = A[i];
    A[i] = A[r];
    A[r] = temp;
    return partition(A, p, r);
  }

  public static int partition(int[] A, int p, int r) {
    int x = A[r];
    int i = p - 1;
    int temp;
    for (int j = p; j < r - 1; j++) {
      if (A[j] <= x) {
        i = i + 1;
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
      }
    }
    temp = A[i + 1];
    A[i + 1] = A[r];
    A[r] = temp;
    return i + 1;
  }

  public static int[] countingSort(int[] A, int[] B) {
    int k = A[0];
    for (int i = 0; i < A.length; i++) {
      if (A[i] > k) {
        k = A[i];
      }
    }
    int[] C = new int[k + 1];

    // Count different Numbers in A
    for (int value : A) {  //for(int j = 0; j < A.length; j++) {
      C[value] = C[value] + 1;
    }

    // Convert sums to indices, by adding them up
    for (int i = 1; i < k; i++) {
      C[i] = C[i] + C[i - 1];
    }

    // Convert indices down to Arrays starting with 0
    for (int i = 0; i < C.length; i++) {
      C[i]--;
    }

    // Write A[j] to the corresponding index in B
    for (int j = A.length - 1; j >= 0; j--) {
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


