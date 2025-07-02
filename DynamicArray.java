/**
 * Object adjusts the size of underlying array to accommodate any number of new
 * elements added to it. The class is written as generic.
 */
public class DynamicArray {

    private static final int DEFAULT_SIZE = 4;
    private static final int RESIZE_FACTOR = 2;

    /** The underlying array of the data structure */
    private String[] underlying;
    /** How many elements have been added to the underlying array */
    private int occupancy;

    /** Basic constructor */
    public DynamicArray(int size) {
        // Guard statement here to ensure that the user-provided size is legit.
        if (size < 1) {
            size = DEFAULT_SIZE;
        }
        // Create an Object array of the requested size, then cast it
        // as an array of objects E
        this.underlying = new String[size];
        // At the beginning the underlying array is empty
        this.occupancy = 0;
    } // basic constructor

    /** Default constructor */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Resize the underlying array as needed.
     */
    private void resize() {
        // Create temporary array of DOUBLE the size of the underlying array
        String[] temp = new String[RESIZE_FACTOR * this.underlying.length];
        for (int i = 0; i < this.underlying.length; i++) {
            temp[i] = this.underlying[i];
        }
        this.underlying = temp;
    } // method resize

    public void add(String string) {
        // Is there room in the underlying array?
        if (this.occupancy == this.underlying.length) {
            this.resize();
        }
        // At this point there is guaranteed room in the array, either
        // because we just doubled it in size or because there was enough
        // room for one more element to begin with.
        this.underlying[this.occupancy] = string;
        this.occupancy++;
    } // method add

    /**
     * method to find the position of an element in the underlying array
     * 
     * @return -1 if string not present, otherwise underlying array position of
     *         first occurrence of string.
     */
    public int indexOf(String string) {
        for (int i = 0; i < this.occupancy; i++) {
            if (this.underlying[i].equals(string)) {
                return i;
            }
        }
        return -1;
    } // method indexOf

    /** Method to tell if a string exists in the underlying array */
    public boolean contains(String string) {
        return indexOf(string) != -1;
    }

    /** Method to count how many times a string appears in the underlying array */
    public int countOf(String string) {
        int count = 0;
        for (int i = 0; i < this.occupancy; i++) {
            if (this.underlying[i].equals(string)) {
                count++;
            }
        }
        return count;
    }

    /** 
     * method to remove items from the underlying array 
     * @return removed element or null if index invalid
     */
    public String remove(int index) {
        if (index < 0 || index >= this.occupancy) {
            return null;
        }
        String removed = this.underlying[index];
        // Shift elements left
        for (int i = index; i < this.occupancy - 1; i++) {
            this.underlying[i] = this.underlying[i + 1];
        }
        this.underlying[this.occupancy - 1] = null; // Clear last element
        this.occupancy--;
        return removed;
    }

    /** 
     * overload remove - removes first occurrence of string 
     * @return removed element or null if not found
     */
    public String remove(String string) {
        int index = indexOf(string);
        return (index != -1) ? remove(index) : null;
    }

    /** Complete this method */
    public String toString() {
        if (this.occupancy == 0) {
            return "Empty DynamicArray";
        }
        StringBuilder sb = new StringBuilder("DynamicArray[");
        for (int i = 0; i < this.occupancy; i++) {
            sb.append(this.underlying[i]);
            if (i < this.occupancy - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
} // class DynamicArray