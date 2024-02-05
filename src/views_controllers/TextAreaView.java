package views_controllers;

/**
 * This is the beginning of one view of a Tic Tac Toe game using
 * two TextField objects and one TextArea. The other two views
 * of ButtonView and DrawingView follow the same structure as this.
 * 
 * @author Rick Mercer and YOUR NAME 
 */
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
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
   
    TextArea message = new TextArea();
    message.setStyle("-fx-border-color: blue; border-width: 10;");
    Font font = new Font("Arial", 22);
    message.setFont(font);
    message.setText("\nTextAreaView\nUnder\nConstruction");
    this.setCenter(message);
    this.setTop(new Button("Set top"));
    this.setBottom(new Button("Bottom"));
  }
 
  // This method is called by Observable's notifyObservers()
  @Override
  public void update(Object observable) {
    System.out.println("update called from OurObservable TicTacToeGame " + theGame); 
  }


}