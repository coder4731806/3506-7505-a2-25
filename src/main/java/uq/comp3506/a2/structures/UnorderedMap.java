// @edu:student-assignment

package uq.comp3506.a2.structures;

import java.util.ArrayList;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 * <p>
 * NOTE: You should go and carefully read the documentation provided in the
 * MapInterface.java file - this explains some of the required functionality.
 */
public class UnorderedMap<K, V> implements MapInterface<K, V> {


    /**
     * you will need to put some member variables here to track your
     * data, size, capacity, etc...
     */
    private int exampleVariable = -1;

    /**
     * you will need to put some member variables here to track your
     * track data
     */
    private ArrayList<Entry<K, V>>[] data;

    private int size;
    private int capacity;


    /**
     * Constructs an empty UnorderedMap
     */
    public UnorderedMap() {
        this.capacity=20;
        this.data=new ArrayList[this.capacity];
        this.size=0;    }

    /**
     * returns the size of the structure in terms of pairs
     * @return the number of kv pairs stored
     */
    @Override
    public int size() {
        // Implement me!
        return size;
    }

    /**
     * helper to indicate if the structure is empty or not
     * @return true if the map contains no key-value pairs, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Clears all elements from the map. That means, after calling clear(),
     * the return of size() should be 0, and the data structure should appear
     * to be "empty".
     */
    @Override
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            if(data[i]!=null){
                data[i].clear();
            }

        }
        this.size=0;    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value
     * is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the payload data value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no such key
     */
    @Override
    public V put(K key, V value) {
        if(this.size>=this.capacity*0.75){
            resize();
        }
        int index=key.hashCode()%this.capacity;
        if (data[index]== null){
            data[index]=new ArrayList<>();
        }
        for (Entry<K, V> num : data[index]) {
            if (num.getKey().equals(key)){
                V oldie=num.getValue();
                num.setValue(value);
                return oldie;
            }
        }
        data[index].add(new Entry<>(key, value));
        this.size++;
        return null;
    }

    public void resize(){

        int NewCapacity=this.capacity*2;
        ArrayList<Entry<K, V>>[] newData=new ArrayList[NewCapacity];
        for(ArrayList<Entry<K, V>> list:data){
            if(list!=null){
                for (Entry<K, V> entry : list) {
                    int index=entry.getKey().hashCode()%NewCapacity;
                    if(newData[index]==null){
                        newData[index]=new ArrayList<>();
                    }
                    newData[index].add(entry);
                }
            }
        }
        data=newData;
        this.capacity=NewCapacity;
    }

    /**
     * Looks up the specified key in this map, returning its associated value
     * if such key exists.
     *
     * @param key the key with which the specified value is to be associated
     * @return the value associated with key, or null if there was no such key
     */
    @Override
    public V get(K key) {

        if(data[key.hashCode()%this.capacity]!=null){
            for (Entry<K, V> num : data[key.hashCode()%this.capacity]) {
                if (num.getKey().equals(key)){
                    return num.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Looks up the specified key in this map, and removes the key-value pair
     * if the key exists.
     *
     * @param key the key with which the specified value is to be associated
     * @return the value associated with key, or null if there was no such key
     */
    @Override
    public V remove(K key) {
        int loc = key.hashCode()%this.capacity;
        if(data[loc]!=null){
            for (int i = 0; i < data[loc].size(); i++) {
                Entry<K, V> entry = data[loc].get(i);
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    data[loc].remove(i);
                    size--;
                    return oldValue;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean firstEntry = true;
        for (ArrayList<Entry<K, V>> bucket : data) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!firstEntry) {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                    firstEntry = false;
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }


}
