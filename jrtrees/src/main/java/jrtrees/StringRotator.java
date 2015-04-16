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

        // store off
        for (int i = 0; i < positions; i++) {
            storage[i] = str[i];
        }

        // move left
        for (int i = positions; i < str.length; i++) {
            str[i - positions] = str[i];
        }

        // add back storage
        int j = 0;
        for (int i = str.length - positions; i < str.length; i++) {
            str[i] = storage[j];
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

        for (int i = 0; i < positions; i++) {

            temp = str[0];

            for (int j = 0; j < str.length - 1; j++) {
                str[j] = str[j + 1];
            }

            str[str.length - 1] = temp;
        }

        return str;
    }

    /**
     * 
     * @return
     */
    public char[] rotate3(char[] s, int p) {

        char[] a = new char[p];
        char[] b = new char[s.length - p];
        char[] bl;
        char[] br;

        for (int i = 0; i < p; i++) {
            a[i] = s[i];
        }

        for (int i = p - 1; i < s.length; i++) {
            b[i] = s[i];
        }

        if (b.length > a.length) {

            bl = new char[b.length - a.length];

            for (int i = 0; i < a.length; i++) {
                bl[i] = b[i];
            }

            br = new char[a.length];

            for (int i = a.length; i < b.length; i++) {
                br[i] = b[i];
            }
            
            for(int i = 0; i < br.length; i++) {
                s[i] = br[i];
            }
            
            for(int i = 0; i < bl.length; i++) {
                s[br.length + i] = bl[i];
            }
            
            for(int i = 0; i < a.length; i++) {
                s[br.length + bl.length + i] = a[i];
            }
            
            str = rotate3(s, p - 1);
        }

        return s;
    }
}
