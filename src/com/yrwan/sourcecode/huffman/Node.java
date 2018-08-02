package com.yrwan.sourcecode.huffman;
/**
 * �ο���Դ��http://blog.csdn.net/kimylrong/article/details/17022319
 * Huffman�����㷨��Ҫ�õ������ݽṹ����ȫ������(full binary tree)�����ȼ����С�
 * �����õ���Java.util.PriorityQueue��ǰ���Լ�ʵ��(��Ϊ�ڲ���)��
 */
public class Node  implements Comparable<Node> {
    public String chars = "";
    public int frequence = 0;
    public Node parent;
    public Node leftNode;
    public Node rightNode;

    public int compareTo(Node n) {
        return frequence - n.frequence;
    }

    public boolean isLeaf() {
        return chars.length() == 1;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.leftNode;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

}
class Tree{
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}