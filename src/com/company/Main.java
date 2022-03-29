package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Integer[] firstArray = generateRandomArray();
        Integer[] secondArray = firstArray.clone();
        Integer[] thirdArray = firstArray.clone();
        
        long start1 = System.currentTimeMillis();
        bubbleSorter(firstArray);
        System.out.println("Time to bubbleSorter equals " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        selectionSort(secondArray);
        System.out.println("Time to selectionSort equals " + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        insertionSort(thirdArray);
        System.out.println("Time to insertionSort equals " + (System.currentTimeMillis() - start3));

    }

    private static Integer[] generateRandomArray() {
        Random random = new Random();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static void bubbleSorter(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(Integer[] firstArray, int j, int i) {
        int tmp = firstArray[i];
        firstArray[i] = firstArray[j];
        firstArray[j] = tmp;
    }

    private static void selectionSort(Integer[] arr) {
        int i = 0;
        while (i < arr.length) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swapElements(arr, i, min);
            i++;
        }
    }

    private static void insertionSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > value) {
                arr[j] = arr[j - 1];
                j--;
                }
            arr[j] = value;
        }
    }
}
