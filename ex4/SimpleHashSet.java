




/**
 *
 * @author RoiGreenberg
 */
public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet{
    protected float lowerLoadFactor;
    protected float upperLoadFactor; 
    protected int currentCapacity;
    protected int hashIndex;
    protected int size = 0;
    protected static final int INITIAL_CAPACITY = 16;
    protected static final float DEFAULT_LOWER =  (float) 0.25;
    protected static final float DEFAULT_UPPER =  (float) 0.75;
    protected int oldCapacity;
    protected boolean isDelIn = false;
    /**
     * A default constructor. 
     * Constructs a new, empty table with default initial capacity (16), 
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public SimpleHashSet(){
        currentCapacity = INITIAL_CAPACITY;
        upperLoadFactor = DEFAULT_UPPER; 
        lowerLoadFactor = DEFAULT_LOWER;
    }
    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public SimpleHashSet(float upper, float lower){
        currentCapacity = INITIAL_CAPACITY;
        upperLoadFactor = upper; 
        lowerLoadFactor = lower;
    }
    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    
    /**
     * Resizing the table capacity if needed
     * @param change - determine if to increase of decrease table size
     */
    protected void resize(String change){

        oldCapacity = currentCapacity;
        switch (change){
            case "increase":
            	currentCapacity <<=1;

                break;
            case "decrease":

            	currentCapacity >>=1;
                break;
      
        }
        
        refillTable();
 
    }
    /**
     * the function copy the table then recreate the table with current
     * capacity then add the values from the copied table to the table. 
     */
    protected void refillTable(){}
}
