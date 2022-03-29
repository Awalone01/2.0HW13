package com.company.list.impl;

import com.company.exception.InputNullException;
import com.company.exception.NotFoundException;
import com.company.exception.WrongIndexException;
import com.company.list.IntegerList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private static final int defaultSize = 10;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[defaultSize];
    }

    @Override
    public Integer add(Integer item) {
        checkArrayIfNull(item);
        if (size == storage.length) {
            increaseArray();
        }
        storage[size++] = item;
        return item;
    }

    private void increaseArray() {
        int newSize = (int) (storage.length * 1.5);
        storage = Arrays.copyOf(storage, newSize);
    }



    private void checkArrayIfNull(Integer item) {
        if (item == null) {
            throw new InputNullException("Входящее значение является пустым");
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        checkArrayIfNull(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        if (size == storage.length) {
            increaseArray();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkArrayIfNull(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        Integer previousElement = storage[index];
        storage[index] = item;
        return previousElement;
    }

    @Override
    public Integer remove(Integer item) {
        checkArrayIfNull(item);
        int removeElement = indexOf(item);
        if (removeElement == -1) {
            throw new NotFoundException("Запрашиваемый элемент не найден");
        }
        System.arraycopy(storage, removeElement, storage, removeElement,
                size - removeElement);
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        Integer removeElementByIndex = storage[index];
        System.arraycopy(storage, index + 1, storage, index,
                size - index);
        size--;
        return removeElementByIndex;
    }

    @Override
    public boolean contains(Integer item) {
        checkArrayIfNull(item);
        quickSort(storage, 0, storage.length - 1);
        return binarySearch(storage, item) != -1;
    }

    private void quickSort(Integer[] storage, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(storage, begin, end);

            quickSort(storage, begin, partitionIndex - 1);
            quickSort(storage, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private Integer binarySearch(Integer[] array, Integer item) {
        int min  = 0;
        int max = size - 1;
        while (min <= max) {
            int average = (min+max)/2;

            if (item.equals(array[average])) {
                return average;
            }
            if (item < array[average]) {
                max = average - 1;
            } else {
                min = average + 1;
            }
        }
        return -1;
    }

    private static void swapElements(Integer[] firstArray, int j, int i) {
        int tmp = firstArray[i];
        firstArray[i] = firstArray[j];
        firstArray[j] = tmp;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkArrayIfNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size - 1; i++) {
            get(i);
            otherList.get(i);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        storage = new Integer[defaultSize];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
