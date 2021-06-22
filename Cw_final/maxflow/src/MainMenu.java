/* -------------------------------------------------------------------------------------------

Name - Sashminda Withanage
UOW - W1790117
IIT ID - 2019586
Description - Algorithms theory design and implementation course work one
Last Updated - 7/4/2021

 -------------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.Scanner;

public class MainMenu {

    public static void menu() throws IOException {

        Runtime runtime = Runtime.getRuntime();
        long maxMem = runtime.maxMemory();


        TestGraph testGraph = new TestGraph();

//        boolean flag to iterate through the menu
        boolean flag = true;

        //No of nodes in the graph
        int vertices = 0;

        //Add the file
        File file = new File("src\\Input\\bridge_1.txt");

        //Bufferreader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(file));


        //Getting the first line
        String line = reader.readLine();
        //Removing white space
        String text = line.replaceAll("\\s+", "");

        //First line contains the number of vertices
        vertices = Integer.parseInt(text);
        //Creating graph object
        Graph graph = new Graph(vertices);

        //Ignoring the first line and continue the reading
        line = null;


        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
            String[] currentLine = line.trim().split(" ");


            //Adding edges to the adjacency list
            graph.addEdgeToMatrix(Integer.parseInt(currentLine[0]), Integer.parseInt(currentLine[1]), Integer.parseInt(currentLine[2]));

        }


        while (flag) {

            System.out.println("==== Welcome to the Max-flow calculator ====");
            System.out.println();
            System.out.println("1. Adjacency Matrix.");
            System.out.println("2. Max Flow.");
            System.out.println("3. Print Graph.");
            System.out.println("4. Add Edge.");
            System.out.println("5. Remove Edge.");
            System.out.println("6. Search For Edge.");
            System.out.println("7. Quit.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter option : ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
// Print matrix
                case 1:
                    graph.printMatrix();
                    System.out.println();
                    break;
// Calculate max flow
                case 2:
                    System.out.println("Max flow between node " + 0 + " and " + (vertices - 1) + " is " + TestGraph.FordFulkerson(graph, 0, (vertices - 1)));
                    System.out.println();
                    long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                    System.out.println(usedMemory);
                    break;

// Print the graph
                case 3:
                    System.out.println("Graph connections : ");
                    graph.printGraph();
                    break;

// Add edge
                case 4:
                    System.out.print("Enter start node : ");
                    int startNew = scanner.nextInt();
                    System.out.print("Enter end node : ");
                    int endNew = scanner.nextInt();
                    System.out.print("Enter Weight : ");
                    int weightNew = scanner.nextInt();

                    graph.addEdge(startNew, endNew, weightNew);
                    break;
// Remove edge
                case 5:
                    System.out.print("Enter start vertex : ");
                    int startRemove = scanner.nextInt();
                    System.out.print("Enter end vertex : ");
                    int endRemove = scanner.nextInt();

                    graph.removeEdge(startRemove, endRemove);
                    break;
                case 6:
                    System.out.print("Enter start vertex : ");
                    int startNode = scanner.nextInt();
                    System.out.print("Enter end vertex : ");
                    int endNode = scanner.nextInt();

                    boolean nodeExist = graph.hasEdge(startNode, endNode);

                    if (nodeExist) {

                        System.out.println();
                        System.out.println("RESULT => Node was found in the Graph.");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("RESULT => Node was not detected in the Graph.");
                        System.out.println();
                    }
                    break;


//Quit
                case 7:
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input Error. Try again.");
            }

        }

    }


    public static void main(String[] args) throws IOException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();
    }
}
