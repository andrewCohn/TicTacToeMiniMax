package model;
// Author: Andrew Cohn
public class GameTree {
    private class Node{
        private TicTacToeGame gameState;
        private int score;
        public LinkedList<Node> children;

        public Node(TicTacToeGame game){
            // constuctor method for the node
            // note: leaves score unit. we will set it in the recursive exploration stage of the algorithm
            this.gameState = game;
        }
        public void setGameState(TicTacToeGame board){
            this.gameState = board;
        }
        public TicTacToeGame getGameState(){
            return gameState;
        }
        public void addChild(Node n){
            children.add(n);
        }
        public GameTree getChildren(){
            GameTree outTree = new GameTree();
            outTree.setRoot(this);
            return outTree;
        }


    }
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }
}
