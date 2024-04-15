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
        buildTreeRecursively(root, player, 0);
    }

    private void buildTreeRecursively(Node node, char player, int depth) {
        if (node.getGameState().isOver() || depth == 9) {  // Use a constant or configuration for max depth
            node.score = evaluate(node, depth, player);
            return;
        }

        char opponent = (player == 'X') ? 'O' : 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (node.getGameState().available(i, j)) {
                    TTTGame temp = TTTGame.makeCopy(node.getGameState());
                    temp.makeMove(player, new Point(i, j));

                    Node childNode = new Node(temp);
                    node.addChild(childNode);

                    if (immediateWinPossible(temp, opponent)) {
                        childNode.score = -1000;  // Discourage this move significantly
                    }
                    buildTreeRecursively(childNode, opponent, depth + 1);

                }
            }
        }
    }

    private boolean immediateWinPossible(TTTGame gameState, char player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState.available(i, j)) {
                    TTTGame testState = TTTGame.makeCopy(gameState);
                    testState.makeMove(player, new Point(i, j));
                    if (testState.didWin(player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Point getBestMove(char player) {
        int bestScore = Integer.MIN_VALUE; // start by assuming the worst
        Node bestMove = null; // we haven't found a best move yet

        for (Node child : root.getChildren()) { // for each follow up move we have in this position:
            int score = minimax(child,0, true,player);  // get the score of the current child
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

    private int minimax(Node node, int depth, boolean isMaximizingPlayer, char player) {
        if (node.getGameState().isOver() || depth == 0) {
            return node.score;
        }

        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            if (child != null) {
                int currentScore = minimax(child, depth - 1, !isMaximizingPlayer, (player == 'X' ? 'O' : 'X'));
                if (isMaximizingPlayer) {
                    bestScore = Math.max(bestScore, currentScore);
                } else {
                    bestScore = Math.min(bestScore, currentScore);
                }
            }
        }
        return bestScore;
    }


    private int evaluate(Node node, int depth, char player) {
        TTTGame gameState = node.getGameState();
        if (gameState.didWin(player)) {
            return 10 - depth;  // Reward quicker wins more
        } else if (gameState.didWin((player == 'X' ? 'O' : 'X'))) {
            return -10 + depth;  // Less penalty for losses that occur later
        }
        return 0;
    }
}