package com.argonnet.Utils;

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

    public enum ALGORITHM {
        Kruskal,
        Prim,
        Djikstra
    }

    public static Map<PROBLEM_LIST,String> problemListDef = new HashMap<PROBLEM_LIST,String>(){{
        put(Constants.PROBLEM_LIST.MinimalTree,"Minimal Tree");
        put(PROBLEM_LIST.ShortestPath,"Shortest Path");
    }};

    public static Map<ALGORITHM,String> algoListDef = new HashMap<ALGORITHM,String>(){{
        put(ALGORITHM.Kruskal,"Kruskal");
        put(ALGORITHM.Prim,"Prim");
        put(ALGORITHM.Djikstra,"Djikstra");
    }};

    public static Map<Constants.PROBLEM_LIST, ArrayList<ALGORITHM>> problemAndSoluctionList = new HashMap<Constants.PROBLEM_LIST, ArrayList<ALGORITHM>>(){{
        put(Constants.PROBLEM_LIST.MinimalTree, new ArrayList<ALGORITHM>(){{
            add(ALGORITHM.Kruskal);
            add(ALGORITHM.Prim);
        }});
        put(PROBLEM_LIST.ShortestPath, new ArrayList<ALGORITHM>(){{
            add(ALGORITHM.Djikstra);
        }});
    }};


}
