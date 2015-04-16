package jrtrees;

/**
 * 
 * @author jregan
 * 
 */
public class BinarySearchAlg {

    int[] n;

    /**
     * 
     */
    public BinarySearchAlg() {
        n = new int[101];
        
        for(int i = 0; i < 101; i++) {
            n[i] = i + 1;
        }
    }

    /**
     * 
     * @param n
     * @return
     */
    public boolean search(int s) {

        boolean found = false;

        int[] range = n;

        while (!found && range != null && range.length > 1) {
            
            int halfway = range.length / 2;
            
            int leftover = range.length % 2;

            if (s == range[halfway]) {

                found = true;

            } else {
                
                int realHalfway = halfway + leftover;

                int[] newRange = new int[realHalfway];
              
                if (s > range[halfway]) {
                    
                    int j = 0;
                    
                    for (int i = realHalfway - 1; i < range.length; i++) {
                        newRange[j] = range[i];
                        j++;
                    }
                    
                    range = newRange;

                } else {
                    
                    // s < halfway
                    for (int i = 0; i < realHalfway; i++) {
                        newRange[i] = range[i];
                    }
                    
                    range = newRange;
                }
            }
        }

        return found;
    }
}
