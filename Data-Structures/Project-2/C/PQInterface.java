package C;



public interface PQInterface<T> {

    void insert(T item);

    City getMax();
    
    City max();
    
    boolean isEmpty();
    
    public City remove(int id);
    
    int size();
    
    
}
