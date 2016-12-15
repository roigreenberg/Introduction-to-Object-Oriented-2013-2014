
/**
 * This class is used to wrap java collection so they could use together with
 * SimpleSet classes.
 * @author RoiGreenberg
 */
public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {
    java.util.Collection<java.lang.String> collection;
    /** 
     * 
     * @param javaCollection - collection data structure
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> javaCollection){
        this.collection = javaCollection;
    }
    
    public boolean add(java.lang.String newValue){
        return collection.add(newValue);
    }
    
    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }
    
    public boolean delete(java.lang.String toDelete){
        return collection.remove(toDelete);
    }
    
    public int size(){
        return collection.size();
    }
    
}
