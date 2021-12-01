package Uebungsblatt5;

import java.util.ArrayList;
import java.util.Comparator;

import static Uebungsblatt5.Tree.*;

public class Main {

    public static void main(String[] args) {
        CodeElement[] C = new CodeElement[6];
        C[0] = new CodeElement("a", 45);
        C[1] = new CodeElement("b", 13);
        C[2] = new CodeElement("c", 12);
        C[3] = new CodeElement("d", 16);
        C[4] = new CodeElement("e", 9);
        C[5] = new CodeElement("f", 5);

        for (CodeElement codeElement : C) {
            System.out.println(codeElement.toString());
        }

        HUFFMAN(C);
    }

    public static void HUFFMAN (CodeElement[] C) {
        int n = C.length;
        ArrayList<Tree> Q = convertToMinHeap(C);
        System.out.println(Q);
        for(int i = 0; i < n - 1; i++) {
            Tree z = new Tree(0, null, null, null);
            Tree x = extractMin(Q);
            Tree y = extractMin(Q);
            z.left = x;
            z.right = y;
            z.freq = x.freq + y.freq;
            insert(Q,z);
            System.out.println(Q);
        }
    }

    public static Tree extractMin(ArrayList<Tree> Q) {
        if(Q.size() < 1) {
            System.out.println("Error: Heap Underflow");
            System.exit(1);
        }
        Tree max = Q.get(0);
        Q.set(0, Q.get(Q.size() - 1));
        Q.remove(Q.size() - 1);
        //int heapsize = Q.size() - 1;
        buildMinHeap(Q); // Warum?
        //minHeapify(Q,0, heapsize);
        return max;
    }

    public static void insert(ArrayList<Tree> Q, Tree z) {
        Q.add(z);
        buildMinHeap(Q);
    }

    public static ArrayList<Tree> convertToMinHeap (CodeElement[] C) {
        ArrayList<Tree> res = new ArrayList<>();
        for (CodeElement codeElement : C) {
            res.add(new Tree(codeElement.freq, null, null, null));
        }
        res.sort(Comparator.comparing(Tree::getFreq));
        return res;
    }

    public static void buildMinHeap(ArrayList<Tree> arr) {
        int heapsize = arr.size();
        for (int i = (arr.size() / 2 - 1); i >= 0; i--) {
            minHeapify(arr, i, heapsize);
        }
    }

    public static void minHeapify(ArrayList<Tree> arr, int i, int heapsize) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heapsize && arr.get(l).freq < arr.get(i).freq) {
            smallest = l;
        }
        if (r < heapsize && arr.get(r).freq < arr.get(smallest).freq) {
            smallest = r;
        }
        if (smallest != i) {
            Tree temp = arr.get(i);
            arr.set(i, arr.get(smallest));
            arr.set(smallest, temp);
            minHeapify(arr, smallest, heapsize);
        }
    }
}
