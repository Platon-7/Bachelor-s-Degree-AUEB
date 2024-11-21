package B;

import java.util.Comparator;


public class PQ<T extends City> implements PQInterface<T> {

    private T[] queue;
    int arr[];
    private final float filledqueue = 0.75f * 100;
    static int size = 0; // trexon megethos ouras
    private Comparator<T> comparator; 
    private static final int AUTOGROW_SIZE = 4; 

    public PQ(int arraysize, Comparator<T> comparator) {
        queue = (T[]) new City[arraysize + 1];//afhnw thn prwth thesh ths oyras kenh kai ths dinw ena arxiko megethos
        arr=new int[999];
        this.comparator = comparator;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T x) { //prosthetoume to neo stoixeio sthn oura
        queue[++size] = x;//auksanw to size apo prin gia na mhn bgw out of bounds
        int b = swim(size);//O(logn)
        arr[x.getID()]=b;
        float s = ((float) size / queue.length) * 100;
        if (s > filledqueue) { //elegxoume an exei gemisei to 75% ths ouras
            grow();
        }
    }

    private void grow() { //megalwnoume to megethos ths ouras
        T[] newHeap = (T[]) new City[queue.length + AUTOGROW_SIZE];
        for (int i = 0; i <= size; i++) {
            newHeap[i] = queue[i];
        }
        queue = newHeap;
    }

    private int swim(int size) { //vazoume to stoixeio sthn swsth thesh apo katw pros ta panw
        int index = size;
        if (size == 1) {
            return index;
        }
        int parent = (size) / 2;
        // sigkrinoume gonea me paidi 
        while ((size) != 1 && queue[size] != null && queue[parent] != null && comparator.compare(queue[size], queue[parent]) > 0) {
            exch(size, parent);
            index = (size) / 2;
            size = parent;
            parent = size / 2;

        }

        return index;

    }

    private void exch(int i, int j) { //ginetai to swap metaksi 2 stoixeiwn
        if (i == j) {
        } else {
            int temporary = arr[queue[j].getID()];
            arr[queue[j].getID()]=arr[queue[i].getID()];// allagh kai ston pinaka array poy krataei ta indices
            arr[queue[i].getID()]=temporary;
            T temp = queue[i];
            queue[i] = queue[j];
            queue[j] = temp;

        }
    }

    private void sink(int i) { //vazoume to stoixeio sthn swsth thesh apo panw pros ta katw
        int left = 2 * i; //prosdiorizoume to aristero kai to deksi paidi
        int right = left + 1;
        
        if (left > size) {
            return;
        }
        while (left <= size) {
            int max = left;
            if (right <= size) {
                if (comparator.compare(queue[left], queue[right]) < 0) {
                    max = right;
                }
            }
            if (comparator.compare(queue[i], queue[max]) >= 0) { 
                return;
            } else {
                exch(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

    @Override
    public City max() {
        if (size == 0) {
            return null;
        }
        return queue[1];
    }

    @Override
    public City remove(int id) { //kanoume remove to stoixeio me to id pou tou dinoume kai epistrefei thn polh
        int index = arr[id];
        if(index==0){
            return null;
        }
        City temp = queue[index];
        if (size >= 3) {
            exch(index, size);
            sink(1);
            queue[size] = null;
        }
        if (size == 2) {
            queue[size] = null;
        }
        if (size <= 1) {
            System.out.println("The queue is empty, nothing to remove");
        }
        size--;
        return temp;
    }

    @Override
    public City getMax() { //afairoume kai epistrefoume to antikeimeno me thn megisth proteraiothta
        City max = queue[1];
        exch(1, size); //vazoume to megalitero stoixeio sto telos kai to afairoume
        queue[size] = null;
        size--;
        arr[queue[1].getID()]=0;
        sink(1);//O(logn)
        return (T) max;
    }
}
