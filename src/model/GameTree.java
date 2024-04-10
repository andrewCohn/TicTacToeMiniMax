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

    private void assignScores() {

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
                    if (!temp.isOver()){
                        buildTreeRecursively(childNode, player == 'X' ? 'O' : 'X'); // Switch player for the next move
                    }

                }
            }
        }
    }
    public Point getBestMove(char player) {
        int bestScore = Integer.MIN_VALUE; // start by assuming the worst
        Node bestMove = null; // we haven't found a best move yet

        for (Node child : root.getChildren()) { // for each follow up move we have in this position:
            int score = minimax(child,5, true,player);  // get the score of the current child
            if (score > bestScore) { // if new score is better than old best
                bestScore = score; // update best score
                bestMove = child; // update best move
            }
        }

        // find the move that led to the best score
        if (bestMove != null) {
            return root.gameState.diff(bestMove.getGameState());
        }

        return null; // no best move found
    }

    private int minimax(Node node, int depth, boolean isMaximizingPlayer,char player) {
        // Base case: leaf node
        if (node.gameState.isOver()) { // when the game is over, get the best score
            return evaluate(node,player,isMaximizingPlayer);
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (Node child : node.getChildren()) {

                int score = minimax(child,depth-1, false,(player == 'X') ? 'O' : 'X');
                bestScore = Math.max(bestScore, score);
            }
            node.score = bestScore;
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Node child : node.getChildren()) {
                int score = minimax(child,depth-1,true,(player == 'X') ? 'O' : 'X');
                bestScore = Math.min(bestScore, score);
            }
            node.score = bestScore;
            return bestScore;
        }
    }

    private int evaluate(Node n, char currentPlayer,boolean isMax) {
        if (n.gameState.didWin(currentPlayer)) {
            return isMax ? 1 : n.gameState.tied()? 0: -1; // Favor maximizing player and penalize minimizing player
        } else if (n.gameState.didWin((currentPlayer == 'X') ? 'O' : 'X')) {
            return isMax ? -1 : n.gameState.tied()? 0: 1; // Favor minimizing player and penalize maximizing player
        } else {
            return 0; // Draw
        }
    }
     /*
    private int evaluate(Node n, char currentPlayer,boolean isMax) {
        if (n.gameState.didWin('X')) {
            return -1; // Favor maximizing player and penalize minimizing player
        } else if (n.gameState.didWin('O')){
            return  1; // Favor minimizing player and penalize maximizing player
        } else {
            return 0; // Draw
        }
    }
    */



}
