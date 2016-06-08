package com.argonnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for all the global constants
 */
public class Constants {

    public final static int GRAPH_REF_HEIGHT = 1000;
    public final static int GRAPH_REF_WIDTH = 1000;


    public enum PROBLEM_LIST{
        MinimalTree,
        ShortestPath
    }

    public enum ALGORYTHM{
        Kruskal,
        Djikstra
    }

    public static Map<PROBLEM_LIST,String> problemListDef = new HashMap<PROBLEM_LIST,String>(){{
        put(Constants.PROBLEM_LIST.MinimalTree,"Minimal Tree");
        put(PROBLEM_LIST.ShortestPath,"Shortest Path");
    }};

    public static Map<Constants.ALGORYTHM,String> algoListDef = new HashMap<Constants.ALGORYTHM,String>(){{
        put(Constants.ALGORYTHM.Kruskal,"Kruskal");
        put(ALGORYTHM.Djikstra,"Djikstra");
    }};

    public static Map<Constants.PROBLEM_LIST, ArrayList<ALGORYTHM>> problemAndSoluctionList = new HashMap<Constants.PROBLEM_LIST, ArrayList<ALGORYTHM>>(){{
        put(Constants.PROBLEM_LIST.MinimalTree, new ArrayList<Constants.ALGORYTHM>(){{
            add(Constants.ALGORYTHM.Kruskal);
        }});
        put(PROBLEM_LIST.ShortestPath, new ArrayList<Constants.ALGORYTHM>(){{
            add(ALGORYTHM.Djikstra);
        }});
    }};


}
