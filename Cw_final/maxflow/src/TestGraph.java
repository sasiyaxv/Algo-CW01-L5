
import java.util.LinkedList;

public class TestGraph {

    public static float FordFulkerson(Graph graph, int start, int destination) {

        int noOfVertices = graph.getVertices();

        // Handling possible errors
        if (start == destination) {
            System.out.println("Destination cannot be same as the start.");
            return 0;
        } else if (destination > noOfVertices-1){
            System.out.println("Destination does not exist in the graph.");
            return 0;
        }


        // Code to create the residual graph
        Graph residualGraph = new Graph(noOfVertices);
        for (int i = 0; i < noOfVertices; i++) {
            for (int j = 0; j < noOfVertices; j++) {
                residualGraph.getAdjacencyMatrix()[i][j] = graph.getAdjacencyMatrix()[i][j];
            }
        }

        // filled by BFS to store path
        int parent[] = new int[noOfVertices];

        float max_flow = 0; // max flow value

        // while a path exists from start to destination loop
        while (bfs(residualGraph, start, destination, parent)) {
            // to store path flow
            float path_flow = Float.MAX_VALUE;

            // find maximum flow of path filled by bfs
            for (int i = destination; i != start; i = parent[i]) {
                int j = parent[i];
                path_flow = Math.min(path_flow, residualGraph.getAdjacencyMatrix()[j][i]);
            }

            // update residual graph capacities
            // reverse edges along the path
            for (int i = destination; i != start; i = parent[i]) {
                int j = parent[i];
                residualGraph.getAdjacencyMatrix()[j][i] -= path_flow;
                residualGraph.getAdjacencyMatrix()[i][j] += path_flow;
            }

            // Add path flow to max flow
            max_flow += path_flow;
        }

        return max_flow;
    }

    public static boolean bfs(Graph rg, int source, int dest, int parent[]) {
        //  to store visited vertices
        boolean[] seen = new boolean[rg.getVertices()];
        for (int i = 0; i < rg.getVertices(); i++) {
            seen[i] = false;
        }
        LinkedList<Integer> q = new LinkedList<Integer>();

        // visit source
        q.add(source);
        seen[source] = true;
        parent[source] = -1;

        // iterate through all nodes
        while (!q.isEmpty()) {
            int i = q.poll();
            // check neighbours of vertex i
            for (Integer j : rg.neighbours(i)) {
                // if not visited and positive value then visit
                if ((seen[j] == false) && (rg.getAdjacencyMatrix()[i][j] > 0)) {
                    q.add(j);
                    seen[j] = true;
                    parent[j] = i;
                }
            }
        }

        // checks if we ended up at the destination
        return seen[dest];
    }

}