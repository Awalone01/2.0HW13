package com.company;

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
        storage[index] = item
        return previousElement;
    }

    @Override
    public String remove(String item) {
        checkArrayIfNull(item);
        int removeElementByIndex = indexOf(item);
        if (removeElementByIndex == -1) {
            throw new NotFoundException("Запрашиваемый элемент не найден");
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String item) {
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
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }
}
