package me.vlad.sortingproject.sortingalgorithms;

import java.util.Stack;

import static me.vlad.sortingproject.SortManager.logs;

/**
 * Asdf
 */
public class TreeSort implements Sorter {
    BinaryTree tree;

    public TreeSort() {
        tree = new BinaryTree();
    }

    @Override
    public int[] sortArray(int[] arrayToSort) {

        tree.bonsai();

        tree.nodeCount++;

        for (int i = 1; i < arrayToSort.length; i++) {
            tree.insert(arrayToSort[i]);
        }
        logs.info("All elements have been inserted");
        logs.debug("nodeCount: " + tree.nodeCount);


        return tree.inOrder();
    }


    private class BinaryTree {

        private Node groot;
        private int nodeCount;

        /**
         * Prune the whole tree
         */
        public void bonsai() {
            groot = null;
            // Nudge the garbage collector to actually do its thing
            System.gc();
            nodeCount = 0;
        }

        /**
         * Stolen from GeeksforGeeks
         * <p>
         * Traverses the tree in ascending order
         *
         * @return sorted array
         * @throws NullPointerException
         * @throws ArrayIndexOutOfBoundsException
         */
        public int[] inOrder() throws NullPointerException, ArrayIndexOutOfBoundsException {

            Stack<Node> stack = new Stack<Node>();


            Node currentNode = groot;

            int i = 0;
            int[] returnArr = new int[nodeCount];

            while (currentNode != null || stack.size() > 0) {
                while (currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.l;
                }
                currentNode = stack.pop();
                returnArr[i++] = currentNode.value;

                currentNode = currentNode.r;

            }
            return returnArr;
        }

        public void insert(int val) throws NullPointerException {

            if (groot == null) {
                groot = new Node(val);
                nodeCount++;
                return;
            }

            Node currentNode = groot;
            while (true) {
                if (val < currentNode.value) {
                    if (currentNode.l == null) {
                        currentNode.l = new Node(val);
                        nodeCount++;
                        break;
                    } else {
                        currentNode = currentNode.l;
                    }
                }
                /*else if(currentNode.value == val)
                {
                    break;
                }*/
                else {
                    if (currentNode.r == null) {
                        currentNode.r = new Node(val);
                        nodeCount++;
                        break;
                    } else {
                        currentNode = currentNode.r;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "I am groot";
        }

        private class Node {
            protected int value;
            protected Node l, r;

            Node(int val) {
                value = val;
            }
        }
    }


}
