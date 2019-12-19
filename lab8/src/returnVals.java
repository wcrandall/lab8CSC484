import java.util.Arrays;
import java.util.List;

public class returnVals {
    int path[];
    int distance;
    List<Integer> pathExacts;
    double distanceDouble;

    public returnVals(int size) {
        path = new int[size];
        for (int i = 0; i < path.length; i++)
        {
            path[i] = Integer.MAX_VALUE;
        }
        distance = Integer.MAX_VALUE;


    }
    public returnVals(double distance, List<Integer> path)
    {
        pathExacts = path;
        distanceDouble = distance;

    }
    public returnVals(int distance, int[] path)
    {
        this.distance = distance;
        this.path = new int[path.length];
        for(int i = 0; i < path.length; i++)
        {
            this.path[i] = path[i];
        }
    }



    public void setPath(int[] path)
    {


        for(int i = 0; i < path.length; i++)
        {
            this.path[i] = path[i];
        }
    }
}


