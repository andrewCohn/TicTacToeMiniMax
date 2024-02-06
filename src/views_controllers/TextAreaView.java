package views_controllers;

/**
 * This is the beginning of one view of a Tic Tac Toe game using
 * two TextField objects and one TextArea. The other two views
 * of ButtonView and DrawingView follow the same structure as this.
 * 
 * @author Rick Mercer and YOUR NAME 
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.OurObserver;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements OurObserver {

  private TicTacToeGame theGame;
  
  public TextAreaView(TicTacToeGame theModel) {
    theGame = theModel;
    initializePanel();
  }

  private void initializePanel() {
    VBox panelContainer = new VBox();
    GridPane optionsPanel = new GridPane();
    optionsPanel.setPadding(new Insets(0,0,10,0));
    Button makeMove = new Button("Make Move!");

    TextArea rowText = new TextArea();
    TextArea colText = new TextArea();
    Label rowLabel = new Label("Row");
    Label colLabel = new Label("Column");
    optionsPanel.add(rowLabel, 0, 0);
    optionsPanel.add(rowText, 1, 0);
    rowText.setPrefSize(150,20);  // Set your desired width
    colText.setPrefSize(150,20);
    GridPane.setMargin(rowText, new Insets(0, 0, 10, 0));
    GridPane.setMargin(colText, new Insets(0, 0, 10, 0));
    rowLabel.setPadding(new Insets(10, 10, 10, 10)); // Adjust as needed
    colLabel.setPadding(new Insets(10, 10, 10, 10));
// Add colLabel and colText to the GridPane
    optionsPanel.add(colLabel, 0, 1);
    optionsPanel.add(colText, 1, 1);
    optionsPanel.add(makeMove, 0, 2, 2, 1);
   
    TextArea message = new TextArea();
    message.setStyle("-fx-border-color: blue; border-width: 10;");
    message.setEditable(false);
    Font font = new Font("Courier New", 25);
    message.setFont(font);
    message.setText(theGame.toString());

    panelContainer.getChildren().add(optionsPanel);
    panelContainer.getChildren().add(message);
    optionsPanel.setAlignment(Pos.CENTER);
    this.setCenter(panelContainer);
    makeMove.setOnAction(e->{
      // if the game is not already over, continue to making the move
      if (theGame.didWin('X') || theGame.didWin('O') || theGame.tied()){
        makeWarn("The game is over! Click new game to start a new game.");
      }
      else {
        try {
          // get the inputs the user entered
          int enterRow = Integer.parseInt(rowText.getText().trim());
          int enterCol = Integer.parseInt(colText.getText().trim());
          // check if the space is available
          if (theGame.available(enterRow, enterCol)) {
            theGame.humanMove(enterRow, enterCol, false); // if the space is available, the human moves and the computer moves
            message.setText(theGame.toString()); // update the output
            // check if we have a winner, change the button to let the user know
            if (theGame.didWin('X')) {
              makeMove.setText("X wins!");
            } else if (theGame.didWin('O')) {
              makeMove.setText("O wins!");
            } else if (theGame.tied()) {
              makeMove.setText("Tie game");
            }

          } else {
            makeWarn("That space is occupied!"); // if the space is not available, we hit this block
          }
        } catch (NumberFormatException exception) { // handles the case when the user enters a string into either box
          makeWarn("Make sure you entered one integer in each box!");
        } catch (ArrayIndexOutOfBoundsException exception) { // handles the case where the user enters a number out bounds
          makeWarn("You entered an input out of the game bounds!");
        }
      }
    });
    // draw the new game button
    Button ngButton = new Button("New Game!");
    this.setTop(ngButton);
    ngButton.setOnAction(e -> {
      this.theGame = new TicTacToeGame();
      message.setText(theGame.toString());
      makeMove.setText("Make Move!");
    } );


  }

  private void makeWarn(String s) {
    Alert warningBox = new Alert(Alert.AlertType.INFORMATION);
    warningBox.setTitle("Invalid Input!");
    warningBox.setContentText(s);
    warningBox.showAndWait();
  }

  // This method is called by Observable's notifyObservers()
  @Override
  public void update(Object observable) {
    System.out.println("update called from OurObservable TicTacToeGame " + theGame);

  }


}