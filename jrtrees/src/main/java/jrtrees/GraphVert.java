package jrtrees;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jregan
 * 
 */
public class GraphVert {

    public int value;

    public List<GraphVert> adjacencyList = null;

    /**
     * 
     */
    public GraphVert() {

        value = 0;

        adjacencyList = new ArrayList<GraphVert>();
    }

    /**
     * 
     * @param value
     */
    public GraphVert(int value) {
        this.value = value;
    }
}
