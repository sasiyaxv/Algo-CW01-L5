import java.util.List;
import java.util.ArrayList;

public class Graph {
    //    No of nodes in the graph
    private int vertices;
    //    Adjacency matrix
    private int[][] adjacencyMatrix;


    //    Constructor for the graph
    public Graph(int vertices) {
        this.vertices = vertices;

        adjacencyMatrix = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getVertices() {
        return vertices;
    }

    //    Method to add an edge to the graph. It takes starting node, Ending node and weight as parameters
    public void addEdgeToMatrix(int start, int end, int weight) {
        adjacencyMatrix[start][end] = weight;
    }


    //    To check if a edge exists
    public boolean hasEdge(int start, int end) {
        if (adjacencyMatrix[start][end] != 0) {
            return true;
        }
        return false;
    }

    //    To delete an edge
    public void removeEdge(int start, int end) {
        adjacencyMatrix[start][end] = 0;
    }

    //    To add an edge
    public void addEdge(int start, int end,int weight) {
        adjacencyMatrix[start][end] = weight;
    }

    public List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int i = 0; i < vertices; i++)
            if (hasEdge(vertex, i))
                edges.add(i);
        return edges;
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            List<Integer> edges = neighbours(i);
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(edges.get(j) + " ");
            }
            System.out.println();
        }
    }

    //Method to print the adjacency matrix
    public void printMatrix() {

        System.out.println("Adjacency Matrix : ");
//Iterating through rows
        for (int row = 0; row < adjacencyMatrix.length; row++) {
//            Iterating through columns
            for (int col = 0; col < adjacencyMatrix[row].length; col++) {
                System.out.printf("%4d", adjacencyMatrix[row][col]);
            }
//            To create a new row
            System.out.println();
        }

    }
}