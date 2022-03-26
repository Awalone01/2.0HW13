package com.company.list.impl;

import com.company.exception.InputNullException;
import com.company.exception.NotFoundException;
import com.company.exception.WrongIndexException;
import com.company.list.StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] storage;
    private static final int defaultSize = 10;
    private int size;

    public StringListImpl() {
        this.storage = new String[defaultSize];
    }

    @Override
    public String add(String item) {

        checkArrayIfNull(item);
        if (size == storage.length) {
            increaseArray();
        }
        storage[size++] = item;
        return item;
    }

    private void increaseArray() {
        int newSize = storage.length * 2;
        storage = Arrays.copyOf(storage, newSize);
    }

    private void checkArrayIfNull(String item) {
        if (item == null) {
            throw new InputNullException("Входящее значение является пустым");
        }
    }

    @Override
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        checkArrayIfNull(item);
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        String previousElement = storage[index];
        storage[index] = item;
        return previousElement;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        String removeElementByIndex = storage[index];
        System.arraycopy(storage, index + 1, storage, index,
                size - index);
        size--;
        return removeElementByIndex;
    }

    @Override
    public boolean contains(String item) {
        checkArrayIfNull(item);
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkArrayIfNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index > size) {
            throw new WrongIndexException("Введён неправильный индекс");
        }
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        storage = new String[defaultSize];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
