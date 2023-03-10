package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.*;
import java.io.*;
import java.util.logging.*;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    // BFS的openList
    private LinkedList<JigsawNode> openList;
    // BFS的closeList
    private LinkedList<JigsawNode> closeList;

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     *
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     * （实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     *
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true, 失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        // 初始化openList和closeList
        openList = new LinkedList<JigsawNode>();
        closeList = new LinkedList<JigsawNode>();
        beginJNode = new JigsawNode(bNode);
        endJNode = new JigsawNode(eNode);
        currentJNode = null;
        int searchedNodesNum = 0;
        // 将初始节点加入openList
        openList.add(beginJNode);
        while (true) {
            currentJNode = openList.poll();  // 从openList中取出一个节点，记为currentJNode
            ++searchedNodesNum;
            // 如果currentNode是目标节点，则搜索成功，返回true
            if (currentJNode.equals(endJNode)) {
                getPath();
                break;
            }
            // 将currentJNode加入closeList
            closeList.add(currentJNode);
            // 找出currentJNode的所有子节点，并加入openList
            int pos[] = currentJNode.canMove();
            for (int i = 0; i < pos.length; i++) {
                if (pos[i] == 1) {
                    JigsawNode childJNode = new JigsawNode(currentJNode);
                    childJNode.move(i);
                    // 如果childJNode不在openList和closeList中，则加入openList
                    if (!openList.contains(childJNode) && !closeList.contains(childJNode)) {
                        openList.add(childJNode);
                    }
                }
            }
            // 如果openList为空，则搜索失败，返回false
            if (openList.isEmpty()) {
                break;
            }
        }
        // 打印搜索结果
        Logger logger = Logger.getLogger("printResult");
        try {
            printResult(null);
            System.out.println("[勘误]\nTotal number of searched nodes:" + searchedNodesNum);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "printResult error...", e);
        }
        return isCompleted();
    }


    /**
     * （Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     *
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int dimension = JigsawNode.getDimension();  // 获取维度
        int uncorrectAfter = 0;  // 后续节点不正确的数码个数
        int uncorrectPlace = 0;  // 数码不在正确位置的个数
        int manhattanDistance = 0;  // 曼哈顿距离
        double euclideanDistance = 0;  // 欧几里得距离

        for (int index = 1; index < dimension * dimension; index++) {
            // 计算后续节点不正确的数码个数
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                uncorrectAfter++;
            }
            if (jNode.getNodesState()[index] != index && index != jNode.getNodesState()[0]) {
                // 计算数码不在正确位置的个数
                uncorrectPlace++;
                // 计算当前位置与目标位置坐标，单位为1
                int currentX = (index - 1) / dimension;
                int currentY = (index - 1) % dimension;
                int targetX = (jNode.getNodesState()[index] - 1) / dimension;
                int targetY = (jNode.getNodesState()[index] - 1) % dimension;
                // 计算曼哈顿距离
                manhattanDistance += Math.abs(currentX - targetX) + Math.abs(currentY - targetY);
                // 计算欧几里得距离
                // euclideanDistance += Math.sqrt(Math.pow(currentX - targetX, 2) + Math.pow(currentY - targetY, 2));
            }
        }
        // 计算预测值，按比例加权求和（比例是任意设置的）
        int estimate = (int) (uncorrectAfter * 3 + manhattanDistance * 6 + (int)euclideanDistance * 3 + uncorrectPlace * 2);
        // 设置节点jNode的代价估计值
        jNode.setEstimatedValue(estimate);
    }
}
