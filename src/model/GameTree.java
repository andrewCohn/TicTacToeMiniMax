package model;
// Author: Andrew Cohn
public class GameTree {
    public class Node{
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
    public Point getBestMove(char player) {
        int bestScore = Integer.MIN_VALUE;
        Node bestMove = null;

        for (Node child : root.getChildren()) {
            int score = minimax(child, 0, false, player);
            if (score > bestScore) {
                bestScore = score;
                bestMove = child;
            }
        }

        // Find the move that led to the best score
        for (int i = 0; i < root.getChildren().length; i++) {
            if (root.getChildren()[i] == bestMove) {
                return root.gameState.diff(bestMove.getGameState());
            }
        }

        return null; // No best move found
    }

    private int minimax(Node node, int depth, boolean isMaximizingPlayer, char player) {
        // Base case: leaf node
        if (node.getChildren() == null || depth >= 4) { // Adjust depth as needed
            return evaluate(node.getGameState(), player);
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (Node child : node.getChildren()) {
                int score = minimax(child, depth + 1, false, player);
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Node child : node.getChildren()) {
                int score = minimax(child, depth + 1, true, player);
                bestScore = Math.min(bestScore, score);
            }
            return bestScore;
        }
    }

    private int evaluate(TTTGame gameState, char player) {
        if (gameState.didWin(player)){
            return 1;
        }
        if (gameState.didWin(player == 'X' ? 'O' : 'X')){
            return -1;
        }
        if (gameState.tied()){
            return 0;
        }
        return 0;
    }
}
