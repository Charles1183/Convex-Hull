/*This was an assignment I did for my algorithm analysis and design
 * class which uses the ConvexHull to get the path of the outer
 * most point on a graph. */

//package edu.uncc.ITCS2215.ConvexHull;

//import com.sun.org.apache.xpath.internal.operations.Bool;

/*import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;*/
import java.util.*;

/**
 * Created by novabrains on 2/23/15.
 * Modified by Charles Bryant
 */

public class Main
{
    public static void main(String[] args)
    {
        // Here, you will want to write code that reads input.txt
        //
        //
        String fileLines[] = new String[6];//5];
        fileLines[0] = "A,3,B,4,E,7,F";//"A,5,B,2,C"; //"A,3,B,4,E,7,F"
        fileLines[1] = "B,3,A,5,C,8,F";//"B,5,A,2,D,4,G"; //"B,3,A,5,C,8,F"
        fileLines[2] = "C,5,B,4,D,6,F";//"C,2,A,3,D,1,G"; //"C,5,B,4,D,6,F"
        fileLines[3] = "D,4,C,2,E,8,F";//"D,2,B,3,C"; //"D,4,C,2,E,8,F"
        fileLines[4] = "E,4,A,2,D,5,F";//"G,4,B,1,C"; //"E,2,D,4,A,5,F"
        fileLines[5] = "F,7,A,8,B,6,C,8,D,5,E";

       Edge[] allEdge = new Edge[10];//6]//count line in txt file for array size11

        HashMap<String, Boolean> edgeAdded = new HashMap<String, Boolean>();

        int counter = 0;

        HashMap<String, String> parentMap = new HashMap<String, String>();

        for (int i = 0; i < fileLines.length; i++)
        {
            String currLine[] = fileLines[i].split(",");
            parentMap.put(currLine[0], currLine[0]);

            for (int j = 1; j < currLine.length; j = j+2)
            {
                String opposite = currLine[j+1]+currLine[0];

                if (edgeAdded.get(opposite) == null)
                {
                    Edge myEdge = new Edge(currLine[0], currLine[j+1], Double.parseDouble(currLine[j]));
                    allEdge[counter] = myEdge;
                    counter = counter + 1;
                    edgeAdded.put(currLine[0]+currLine[j+1], true);
                }
            }
        }
 
         Arrays.sort(allEdge);
         double weightTotal = 0;
    
        for (int i = 0; i < allEdge.length; i ++)
        {
            String node1 = allEdge[i].point1;
            String node2 = allEdge[i].point2;
            
            if (!parentMap.get(node2).equals(node2))
           {
            String nodeRoot2 = parentMap.get(node2);
            String nodeRoot1 = parentMap.get(node1);
            
            if (!parentMap.get(nodeRoot2).equals(nodeRoot1))
            {
              edgeAdded.put(node1+node2, false);
              parentMap.put(nodeRoot2, new String(nodeRoot1));
              weightTotal += allEdge[i].weight;
               
              for(int n = 0; n < parentMap.size(); n++)
              {
               String nodeF = allEdge[n].point2;
               
               if (parentMap.containsValue(nodeRoot2)) {parentMap.put(nodeF, new String(nodeRoot1));}
              }
            }
           }
            String nodeRoot1 = parentMap.get(node1);
            String nodeRoot2 = parentMap.get(node2);
            
            if (parentMap.get(node2).equals(node2)&&(!parentMap.get(node1).equals(nodeRoot2)))
           {
            edgeAdded.put(node1+node2, false);
            parentMap.put(node2, new String(nodeRoot1));
            weightTotal += allEdge[i].weight;
              
            for (int n = 0; n < parentMap.size(); n++)
            {
             String nodeF = allEdge[n].point2;
                  
             if (parentMap.containsValue(nodeRoot2)) {parentMap.put(nodeF, new String(nodeRoot1));}
            }
          }
        }
       
       //The for loop below prints line in order of weight------------------------------------------------------
       /* for (int p = 0; p<edgeAdded.size(); p++)
        {
          String node1 = allEdge[p].point1;
          String node2 = allEdge[p].point2;
          double nodeWeight = allEdge[p].weight;
          
          if(edgeAdded.get(node1+node2).equals(false))
          {
            System.out.println("point 1 "+node1+", point 2 "+node2+", weight "+nodeWeight);
            edgeAdded.put(node1+node2, true);
          }
        }*/
        
        //Loop below prints the root line first then all others in order of weight-------------------------------
        for (int c = 0; c<edgeAdded.size(); c++)
        {
         String node1 = allEdge[c].point1;
         String node2 = allEdge[c].point2;
         double lineWeight = allEdge[c].weight;
          
         if(parentMap.get(node1).equals(node1)&&(edgeAdded.get(node1+node2).equals(false)))
         {
          System.out.println(node1+"->"+node2+", weight "+lineWeight);
          edgeAdded.put(node1+node2, true);
         }
        }

        for (int p = 0; p<edgeAdded.size(); p++)
        {
         String node1 = allEdge[p].point1;
         String node2 = allEdge[p].point2;
         double lineWeight = allEdge[p].weight;
          
         if(edgeAdded.get(node1+node2).equals(false))
         {
          System.out.println(node1+"->"+node2+", weight "+lineWeight);
          edgeAdded.put(node1+node2, true);
         }
        }
        System.out.println(weightTotal);
    }
}
