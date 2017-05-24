//package edu.uncc.ITCS2215.ConvexHull;

/**
 * Created by novabrains on 2/23/15.
 */
public class Edge implements Comparable<Edge>
{
    String point1;
    String point2;

    double weight;

    public Edge(String _point1, String _point2, double _weight)
    {
        point1 = _point1;
        point2 = _point2;
        weight = _weight;
    }

    @Override
    public int compareTo(Edge o)
    {
        if (weight == o.weight)
            return 0;
        if (weight > o.weight)
            return 1;

        return -1;
    }
}