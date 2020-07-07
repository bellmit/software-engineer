package com.runtime.algorithm.structure.graph;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/6 20:08
 * @Description
 */
public class GraphBasis {
    /***
     * graph 的核心 有
     *  - Vertex 顶点
     *  - Edge 边
     *  - 路径
     *  无线图
     *  有向图
     *  带权图
     */
    public static void main(String[] args) {
        int n = 5;
        GraphUtils graphUtils = new GraphUtils(n);
        String Vertexs[] = {"A", "B", "C", "D", "E"};

        for (String vertex : Vertexs) {
            graphUtils.insertVertex(vertex);
        }


        graphUtils.insertEdge(0, 1, 1);
        graphUtils.insertEdge(0, 2, 1);
        graphUtils.insertEdge(1, 2, 1);
        graphUtils.insertEdge(1, 3, 1);
        graphUtils.insertEdge(1, 4, 1);

        graphUtils.showGraph();
        graphUtils.dfs();
        System.out.println();
        graphUtils.bfs();


    }
}
