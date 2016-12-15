


/**
 *
 * @author RoiGreenberg
 */
public class OpenHashSet extends SimpleHashSet{

    private int hashIndex;
    private String[] table, tempTable;
    private static final String DELETED = "<del>";
    private int index;
    
    /**
     * A default constructor. 
     * Constructs a new, empty table with default initial capacity (16), 
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super (DEFAULT_UPPER,DEFAULT_LOWER);
        

        table = new String[currentCapacity];
        
    }
    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor,
              float lowerLoadFactor){
        super (upperLoadFactor,lowerLoadFactor);
        
        table = new String[currentCapacity];


    }
    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     *  Duplicate values should be ignored. The new table has the default values
     *   of initial capacity (16), upper load factor (0.75), 
     *   and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        super (DEFAULT_UPPER,DEFAULT_LOWER);
        table = new String[currentCapacity];
        for (String singleData: data)
            this.add(singleData);
    }
    /**
     * Add a specified element to the set.
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
            // in case the added key is same as DELETED string
            if (newValue.equals(DELETED))
            	isDelIn =true;
            return true;
        }
        return false;
    }

    
    protected void addToSet(String newValue) {
    	if (index==0){
    		hashIndex = hash(newValue, index);
    		while (table[hashIndex]!=null  && (newValue != DELETED)) {
    			index++;
    			hashIndex = hash(newValue, index);
    		}
    	}
        table[hashIndex] = newValue;
	}
    

    /**
     * Look for a specified value in the set.
     * @param searchVal - Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
    	// in case the key is same as DELETED string
    	if (searchVal.equals(DELETED)){
    		return isDelIn;
    	}
    		
    	
        index = 0;
        hashIndex = hash(searchVal, index);

        	while (table[hashIndex]!=null) {

                if (table[hashIndex].equals(searchVal)) {
                    return true;
                } else {
                    index++;
                    hashIndex = hash(searchVal ,index);
                }
            }

        return false;
        
    }
    
    /**
     * Remove the input element from the set.
     * @param toDelete - Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if (this.contains(toDelete)){
            size--;
            table[hashIndex] = DELETED;
            if ((double)this.size()/currentCapacity<lowerLoadFactor){
                resize("decrease");
            }
            // in case the deleted key is same as DELETED string
            if (toDelete.equals(DELETED))
            	isDelIn = false;
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
     * the function copy the table then recreate the table with current
     * capacity then add the values from the copied table to the table. 
     */
    protected void refillTable() {
    	tempTable = table;

    	table = new String[currentCapacity];
    	for (String value: tempTable){
    		if ((value != null) && (value != DELETED)){
    			index = 0;
    			this.addToSet(value);    	
    		}
    	}
    	// add the DELETED string if the same key is at the table
    	if (isDelIn){
    		index = 0;
    		this.addToSet(DELETED);
    	}

    }
    
    
    
    /**
     * calculate the hash value
     * @param value - the value need to hash
     * @param i the counter for the probing
     * @return the hash value
     */
    private int hash(String value,int i){
    	int capacity = currentCapacity - 1;
    	if (i==0)
    		return Math.abs((value.hashCode())&capacity);
        return Math.abs((hashIndex+(i+i*i)/2)&capacity);
   }
   

    
}
