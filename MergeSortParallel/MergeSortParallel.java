package mergesortparallel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeSortParallel {

    public static void main(String[] args) {
        int n = 10000000;
        int[] actual = new int[n];
        System.out.println("MERGE SORT EN PARALELO");
        
        for (int i=0;i<n;i++){
            actual[i] = (int) (Math.random()*20)+1;
            //System.out.print(""+actual[i]+" ");
        }
        System.out.println("");
        
        long inicio = System.currentTimeMillis();
        
        
        //mergeSort(actual, actual.length);
        mergeSortParallel(actual, actual.length, 2);

        long fin = System.currentTimeMillis();
        double tiempo = (double) (fin - inicio);
        System.out.println(tiempo +" milisegundos");
        
        showArraySorted(actual);
        
    }

    private static Thread mergeSortThread(int[] a, int n, int numThreads) {
        return new Thread() {
            @Override
            public void run() {
                mergeSortParallel(a, n, numThreads/2);
            }
        };

    }

    public static void mergeSortParallel(int[] a, int n, int numThreads) {
        if (numThreads <= 1) {
            mergeSort(a, n);
            return;
        } else {
            if (n < 2) {
                return;
            }
            int mid = n / 2;
            int[] l = new int[mid];
            int[] r = new int[n - mid];

            for (int i = 0; i < mid; i++) {
                l[i] = a[i];
            }
            for (int i = mid; i < n; i++) {
                r[i - mid] = a[i];
            }
            
            Thread leftThread = mergeSortThread(l,mid, numThreads);
            Thread rightThread = mergeSortThread(r,n-mid, numThreads);

            leftThread.start();
            rightThread.start();
            
            try {
                leftThread.join();
                rightThread.join();
                merge(a, l, r, mid, n-mid);
            } catch (InterruptedException ex) {
                Logger.getLogger(MergeSortParallel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*secuencial*/
    public static void mergeSort(int[] a, int n) {
        long inicio = System.currentTimeMillis();
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void showArraySorted(int[] array) {
        System.out.println("Lista ordenada: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print("" + array[i] + " ");
        }
        System.out.println("");
    }
}
