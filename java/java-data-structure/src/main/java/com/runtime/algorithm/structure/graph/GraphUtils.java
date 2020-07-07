package com.runtime.algorithm.structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/7 6:59
 * @Description
 */
public class GraphUtils {
    private ArrayList<String> vertexList; //顶点
    private int numOfEdges; //边
    private int[][] edges;//邻接矩阵
    private boolean[] isVisited;//是否被访问过

    //todo 初始化工作
    public GraphUtils(int n) {
        numOfEdges = 0;
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }


    //todo 图中常用的方法
    //todo 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //todo 显示对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //todo 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //todo 返回结点i(下标)对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //todo 返回 v1 v2 的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //todo 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //todo 添加边之间的关系
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


    //得到相邻接点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //************************************************************************************
    // ------------- todo  深度优先遍历 接力
    //************************************************************************************

    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        //System.out.println("当前节点 --> " + i);
        int w = getFirstNeighbor(i);
        //System.out.println("相邻接点 --> " + w);
        while (w != -1) {//说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
            //System.out.println("已被访问 --> " + w);
        }

    }

    //对dfs 进行一个重载, 遍历我们所有的结点，并进行 dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        System.out.print("DFS ->");
        //遍历所有的结点，进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //************************************************************************************
    // ------------- todo  广度优先遍历 走到黑
    //************************************************************************************
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历都有节点 进行bfs [回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; //表示 队列的头节点对应下标
        int w; // 邻接点w

        LinkedList queue = new LinkedList<>();
        //访问结点，输出结点信息
        System.out.print("BFS -> " + getValueByIndex(i) + " => ");
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标 w
            w = getFirstNeighbor(u);
            //System.out.println("头节点 --> " + u + " *****　当前相邻节点 --> " + w);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点 A->B  以 B 为邻接点
                w = getNextNeighbor(u, w);
                //System.out.println("头节点 -->" + u + " --　下一个相邻节点" + w);
            }
        }
    }
}
