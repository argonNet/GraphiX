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


    public enum PROBLEMS {
        MinimalTree,
        ShortestPath
    }

    public enum ALGORITHM {
        Kruskal,
        Prim,
        Djikstra
    }

    public static Map<PROBLEMS,String> problemListDef = new HashMap<PROBLEMS,String>(){{
        put(PROBLEMS.MinimalTree,"Minimal Tree");
        put(PROBLEMS.ShortestPath,"Shortest Path");
    }};

    public static Map<ALGORITHM,String> algoListDef = new HashMap<ALGORITHM,String>(){{
        put(ALGORITHM.Kruskal,"Kruskal");
        put(ALGORITHM.Prim,"Prim");
        put(ALGORITHM.Djikstra,"Djikstra");
    }};

    public static Map<PROBLEMS, ArrayList<ALGORITHM>> problemAndSoluctionList = new HashMap<PROBLEMS, ArrayList<ALGORITHM>>(){{
        put(PROBLEMS.MinimalTree, new ArrayList<ALGORITHM>(){{
            add(ALGORITHM.Kruskal);
            add(ALGORITHM.Prim);
        }});
        put(PROBLEMS.ShortestPath, new ArrayList<ALGORITHM>(){{
            add(ALGORITHM.Djikstra);
        }});
    }};


}
