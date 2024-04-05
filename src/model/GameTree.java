package model;
// Author: Andrew Cohn
public class GameTree {
    private class Node{
        private TTTGame gameState;
        private int score;
        public Node[] children;
        private int childIndex = 0;

        public Node(TTTGame game){
            // constuctor method for the node
            // note: leaves score unit. we will set it in the recursive exploration stage of the algorithm
            this.gameState = game;
            this.children= new Node[game.maxMovesRemaining()];
        }
        public void setGameState(TTTGame board){
            this.gameState = board;
        }
        public TTTGame getGameState(){
            return gameState;
        }
        public void addChild(Node game){
            children[childIndex++]= game;
        }
        public Node[] getChildren(){
            return children;
        }


    }
    private Node root;

    public GameTree(TTTGame game){
        this.root = new Node(game);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void buildTree(char player) {
        buildTreeRecursively(root, player);
    }

    private void buildTreeRecursively(Node node, char player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TTTGame temp = TTTGame.makeCopy(node.gameState);
                if (temp.available(i, j)) {
                    temp.makeMove(player, new Point(i, j));
                    Node childNode = new Node(temp);
                    node.addChild(childNode);
                    buildTreeRecursively(childNode, player == 'X' ? 'O' : 'X'); // Switch player for the next move
                }
            }
        }
    }
}
