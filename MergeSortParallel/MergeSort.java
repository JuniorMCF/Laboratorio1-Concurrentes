/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergesortparallel;

/**
 *
 * @author Usuario
 */
public class MergeSort {
    
    public static void main(String[] args) {
        int n = 10000000;
        int[] actual = new int[n];
        System.out.println("MERGE SORT SECUENCIAL");
        for (int i=0;i<n;i++){
            actual[i] = (int) (Math.random()*20)+1;
            //System.out.print(""+actual[i]+" ");
        }
        System.out.println("");
        long inicio = System.currentTimeMillis();
        
        mergeSort(actual, actual.length);
        long fin = System.currentTimeMillis();
        double tiempo = (double) (fin - inicio);
        System.out.println(tiempo +" milisegundos");
        
        //showArraySorted(actual);
    }
     /*secuencial*/
    public static void mergeSort(int[] a, int n) {
        
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
