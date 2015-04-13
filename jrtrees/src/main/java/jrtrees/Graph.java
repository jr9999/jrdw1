package jrtrees;


public class Graph {
    
    public GraphVert parent;
    
    public boolean[][] adjacencyMatrix;

    public Graph() {
        parent = null;
    }
    
    public void setParent(GraphVert vert) {
        this.parent = vert;
    }
    
    public void connectEdge(int i, int j) {
        adjacencyMatrix[i][j] = true;
    }
    
    public void connectDirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = true;
        adjacencyMatrix[j][i] = true;
    }
    
    public void disconnectEdge(int i, int j) {
        adjacencyMatrix[i][j] = false;
    }
}
