package jrtrees;

/**
 * 
 * @author jregan
 *
 */
public class StringRotator {

    public char[] storage;
    
    private int positions = 0;
    
    private char[] str;
    
    /**
     * 
     * @param str
     * @param positions
     */
    public StringRotator(char[] str, int positions) {
        this.positions = positions;
        this.str = str;
        storage = new char[positions];
    }
    
    /**
     * 
     * @return
     */
    public char[] rotate1() {
        
        //store off
        for(int i = 0; i < positions; i++) {
            storage[i] = str[i];
        }
        
        //move left
        for(int i = positions; i < str.length; i++) {
            str[i - positions] = str[i];
        }
        
        //add back storage
        int j = 0;
        for(int i = str.length - positions; i < str.length; i++) {
            str[i] = storage[ j ];
            j++;
        }
        
        return str;
    }
    
    /**
     * 
     * @return
     */
    public char[] rotate2() {
        
        char temp;
        
        for(int i = 0; i < positions; i++) {
            
            temp = str[0];
            
            for(int j = 0; j < str.length - 1; j++) {
                str[j] = str[j + 1];
            }
            
            str[str.length - 1] = temp;
        }
        
        return str;
    }
}
