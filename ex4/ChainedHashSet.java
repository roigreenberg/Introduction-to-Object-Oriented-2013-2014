
import java.util.*;


/**
 *
 * @author RoiGreenberg
 */
public class ChainedHashSet extends SimpleHashSet{
    ArrayList<ArrayList<String>> tempTable; 
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    
    
    
    /**
     * A default constructor. 
     * Constructs a new, empty table with default initial capacity (16), 
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ChainedHashSet(){
        super (DEFAULT_UPPER,DEFAULT_LOWER);
        createEmptyTable();
    }
    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ChainedHashSet(float upperLoadFactor,
              float lowerLoadFactor){
        super (upperLoadFactor,lowerLoadFactor);

        createEmptyTable();
    }
    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     *  Duplicate values should be ignored. The new table has the default values
     *   of initial capacity (16), upper load factor (0.75), 
     *   and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public ChainedHashSet(java.lang.String[] data){
        super (DEFAULT_UPPER,DEFAULT_LOWER);
        createEmptyTable();
        for (String singleData: data)
            this.add(singleData);
    }
    /**
     * Add a specified element to the set.
     * check if the value not in the table, add it and resize the table if needed
     * @param newValue - New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {

        if (!this.contains(newValue)){

            size++;
            addToSet(newValue);
            if ((float)this.size()/currentCapacity>upperLoadFactor){
                resize("increase");
            }
            return true;
        }
        return false;
    }
    
    /**
     * Add a specified element to the set.
     * find the right hashcode and insert the value to the table
     * @param newValue - New value to add to the set
     */
    protected void addToSet(String newValue) {
    	hashIndex = hash(newValue);
    	table.get(hashIndex).add(newValue);
    }

    
    /**
     * the function copy the table then recreate the table with current
     * capacity then readd the values from the copied table. 
     */
    protected void refillTable() {
    	tempTable = table;
    	createEmptyTable();
    	for (ArrayList<String> arr: tempTable){
    		for (String value: arr){
    			this.addToSet(value);
    		}
    	}
    }
    
    
    
    /**
     * Look for a specified value in the set.
     * @param searchVal - Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
    	hashIndex = hash(searchVal);
    	return (table.get(hashIndex).contains(searchVal));
        
    }
    /**
     * Remove the input element from the set.
     * @param toDelete - Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {

        if (this.contains(toDelete)){
        	hashIndex = hash(toDelete);
            table.get(hashIndex).remove(toDelete);
            size--;

            if ((double)this.size()/currentCapacity<lowerLoadFactor){

                resize("decrease");
            }
            return true;
        }
        return false;
    }
    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {

        return this.size;
    }
    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return this.currentCapacity;
    }

    /**
	 * reset the table according to new capacity
	 */
    protected void createEmptyTable() {
    	table = new ArrayList<ArrayList<String>>();
    	for (int i=0;i<currentCapacity;i++)
            table.add(i, new ArrayList<String>());
	}
    
    /**
     * return the hash-key for the value
     * @param value - the value needed to be hashed
     * @return the hash key
     */
    private int hash(String value){
    	int capacity = currentCapacity - 1;
    	return Math.abs((value.hashCode())&(capacity));
   }
    
} 
