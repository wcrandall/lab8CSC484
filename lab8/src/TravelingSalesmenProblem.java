import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class TravelingSalesmenProblem {
    private Random random;

    public TravelingSalesmenProblem()
    {
        random = new Random();
    }

//    public returnVals bruteTravelingSalesmen(int start, int end, int[] tourNodes, int nodeCount, int tourNodeCount,int[][] distances, returnVals returns)
//    {
//        int[] path;
//        if(tourNodeCount == 1)
//        {
//            path = new int[nodeCount];
//            path[nodeCount - 1] = tourNodes[0];
//
//            returns.setPath(path);
//            returns.distance = distances[start][tourNodes[0]] + distances[tourNodes[0]][end];
//            return returns;
//        }
//        else
//        {
//            for(int i = 0; i < tourNodeCount; i++)
//            {
//                int newStart = tourNodes[i];
//                returnVals tmpReturns = new returnVals(nodeCount);
//
//                //get new tournodes list without the current one
//                int [] newTourNodes = new int[tourNodeCount -1];
//                int k = 0;
//                for(int j = 0; j < tourNodeCount; j++)
//                {
//                    if(tourNodes[j]!=newStart)
//                    {
//                        newTourNodes[k] = tourNodes[j];
//                        k++;
//                    }
//                }
//
//
//                tmpReturns = bruteTravelingSalesmen(newStart,end,newTourNodes,nodeCount,tourNodeCount-1,distances,tmpReturns);
//
//
//                path = tmpReturns.path;
//                path[start] = newStart;
//                tmpReturns.setPath(path);
//
//                int c = tmpReturns.distance + distances[start][newStart];
//
//                if(c < returns.distance)
//                {
//                    System.out.println("sadklf;ajfjklafsakfd");
//                    for(int d = 0; d <tmpReturns.path.length; d++)
//                    {
//                        System.out.println(tmpReturns.path[d] + " dl;asjldfjasklf;");
//                    }
//                    System.out.println("a;lkdfsjlasf");
//                    returns.distance=c;
//                    returns.setPath(tmpReturns.path);
//                }
//            }
//
//            return returns;
//        }
//
//    }

    public returnVals greedyTravelingSalesmen(int[][] distances)
    {
        int start = 0;
        int[] path = new int[distances[0].length];
        Arrays.fill(path, Integer.MAX_VALUE);

        int distance = 0;
        int k = distances[0].length -1;
        int hold = k;
        int i = -1;


        // put hold at the largest largest distance
        for(int d = 0; d < distances[0].length; d++)
        {
            if (distances[start][d] > distances[start][hold])
            {
                hold = d;
            }
        }

        // finds the the lowest weighted edge connected to each vertex
        // that isn't the one it came from or the one to its self

        while(i != start)
        {
            // init i to 0 because 0 is the starting point
            if(i == -1) {
                i = start;
            }

            // loop through distances to find the shortest path coming off
            // the current vertex that isn't itself or the what that was before it
            for(int j = 0; j < distances[0].length; j++)
            {
                // if on the last vertex it must go back to 0
                if(k == 0)
                {
                    path[i] = start;
                    hold=start;
                    break;
                }

                // make sure that its not going to itself
                if(j!=i) {

                        // make sure it hasn't already been visited
                        if(path[j] == Integer.MAX_VALUE) {
                            // if its smaller than current update hold and path[i]
                            if (distances[i][hold] > distances[i][j]) {
                                hold = j;

                                path[i] = j;

                            }
                        }

                }





            }


            // update i to the new value
            i = hold;

            // put hold at the largest largest distance
            for(int d = 0; d < distances[0].length; d++)
            {
                if (distances[i][d] > distances[i][hold])
                {
                    hold = d;
                }
            }

            k--;

        }

        for(i = 0; i < distances[0].length; i++)
        {
            if(path[i] != Integer.MAX_VALUE) {
                distance += distances[i][path[i]];
            }
        }


//        for(i = 0; i < distances[0].length; i++)
//        {
//            System.out.println("it goes from " + i + " to " + path[i]);
//        }
//        System.out.println("the distance is " + distance);
        returnVals returns = new returnVals(distance,path);
     return returns;
    }

    public int[][] generateRandomCircularGraphCostMatrix(int vertices, int radius)
    {
        Vertex[] vertexArray = new Vertex[vertices];
        int[] verticesDistanceFromZero = new int[vertices];
        int[][] distances = new int[vertices][vertices];
        int angles = 360 / (vertices-1);
        double distanceBetweenEach = ((angles/360) * (2 * 3.14 * radius));

        int currentDistanceFromZero = 0;
        int k = 1;

        // finding x and y for each vertex
        for(int i = 0; i < vertexArray.length; i++)
        {
            verticesDistanceFromZero[i] = currentDistanceFromZero;
            currentDistanceFromZero+=angles;

            int x = (int)Math.round(radius * Math.cos(verticesDistanceFromZero[i]));
            int y = (int)Math.round(radius * Math.sin(verticesDistanceFromZero[i]));

            vertexArray[i] = new Vertex(x,y);
        }


        // calculating distances
        for(int i = 0; i < vertexArray.length; i++)
        {
            for(int j = k; j < vertexArray.length; j++)
            {
                distances[i][j] = distanceFormula(vertexArray[i].x,vertexArray[i].y,vertexArray[j].x,vertexArray[j].y);
                distances[j][i] = distances[i][j];
            }
            k++;
        }



        return distances;
    }

    // finds distance between two points
    public int distanceFormula(int x, int y, int x2, int y2)
    {

        int xSub = (int)Math.pow(x2-x,2);
        int ySub = (int)Math.pow(y2-y, 2);

        int distance = (int) Math.round(Math.sqrt(xSub+ySub));
        return distance;
    }

    public int[][] generateRandomEuclideanCostMatrix(int vertices,int maxX, int maxY)
    {
        // I created a vertex class to hold x, y, and distance
        Vertex[] vertexArray = new Vertex[vertices];
        int k = 1;
        int[][] distances = new int[vertices][vertices];

        // this loop puts random x and y values in all the used vertices
        for (int i = 0; i < vertexArray.length; i++)
        {
            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);
            vertexArray[i] = new Vertex(x,y);
        }

        // finds the distance for each indice using the distance formula and stores it
        for (int i = 0; i < vertices; i++)
        {
            for(int j = k; j < vertices; j++ )
            {
                int distance = distanceFormula(vertexArray[i].x,vertexArray[i].y,vertexArray[j].x,vertexArray[j].y);
                distances[i][j] = distance;
                distances[j][i]= distance;
            }
            k++;
        }
        printMatrice(distances);
        return distances;

    }



    // generates a cost matrice
    public int[][] generateRandomCostMatrix(int vertices, int maxEdgeCost)
    {
        int[][] array = new int[vertices][vertices];
        // the matrice must start at (0,1) to avoid (0,0)
        // in order to avoid other (n,n) ordered pairs k is incremented at the end of each i loop
        int k = 1;



        for (int i = 0; i < vertices; i++)
        {
            for(int j = k; j < vertices; j++ )
            {
                array[i][j] = random.nextInt(maxEdgeCost);
                array[j][i] = array[i][j];
            }
            k++;
        }



        return array;
    }


    // prints a cost matrice
    public void printMatrice(int[][] array)
    {
        for(int i = 0; i < array[0].length;i++)
        {
            for(int j = 0; j < array[0].length; j++) {
                System.out.println("i j [" + i + "] [" + j + "] " + array[i][j] + " and j i is [" + j + "] [" + i + "] " +  array[j][i]);
            }
        }
    }
}