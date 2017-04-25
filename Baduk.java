import javafx.event.Event;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.lang.Math;


public class Baduk extends Application {

  static final double STAGESIZE_X = 1000;
  static final double STAGESIZE_Y = 1000;
  static final int GAP = 40;
  static final int MARGIN_X = 100;
  static final int MARGIN_Y = 40;
  static final int BNUM = 19;

  @Override
  public void start(Stage primaryStage) {



    Pane pane = new Pane();



    for(int i = 0; i < 19; i++) {
      pane.getChildren().add(new BadukLine(MARGIN_X + i * GAP, MARGIN_Y, MARGIN_X + i * GAP, MARGIN_Y + (19 - 1) * GAP));
    }

    for(int i = 0; i < 19; i++) {
      pane.getChildren().add(new BadukLine(MARGIN_X, MARGIN_Y +  i * GAP, MARGIN_X + (19 - 1) * GAP, MARGIN_Y + i * GAP));
    }


    Scene scene = new Scene(pane, STAGESIZE_X, STAGESIZE_Y);

    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {

          //  if(Put.isValid(e.getX(), e.getY())) {
              BadukAl al1 = new BadukAl(e.getX(),e.getY(), Baduk.GAP/2, Color.GREY);
              pane.getChildren().addAll(al1);
          //  }

          }
      });
    primaryStage.setTitle("baduk");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}

class BadukAl extends Circle {
  private double X;
  private double Y;
  private int bX;
  private int bY;

  public BadukAl(){
    this(10, 10, 10, Color.BLACK);
  }

  public BadukAl(double centerX, double centerY, double radius, Paint fill) {
    super(Put.PuttingX(centerX), Put.PuttingY(centerY), radius, fill);
  }
}

class Put {

  public static boolean isValid(double inX, double inY) {
    return inX >= Baduk.MARGIN_X && inX <= Baduk.MARGIN_X * (Baduk.BNUM - 1)
        && inY >= Baduk.MARGIN_Y && inY <= Baduk.MARGIN_Y * (Baduk.BNUM - 1);
  }

  public static int PuttingX(double rare) {
    return wereMarginX(adjustDot(abortMarginX(rare)));
  }
  public static int PuttingY(double rare) {
    return wereMarginY(adjustDot(abortMarginY(rare)));
  }

  public static int abortMarginX(double rare) {
    return (int)rare - Baduk.MARGIN_X;
  }
  public static int abortMarginY(double rare) {
    return (int)rare - Baduk.MARGIN_Y;
  }

  public static int wereMarginX(int b) {
    return b + Baduk.MARGIN_X;
  }
  public static int wereMarginY(int b) {
    return b + Baduk.MARGIN_Y;
  }

  public static int adjustDot(int inPanDot) {

    int inPanDotDevidedByHalf = inPanDot / (Baduk.GAP/2);
    int b = 0;

    if(inPanDotDevidedByHalf % 2 == 1) {
      b = inPanDot/Baduk.GAP + 1;
      return b * Baduk.GAP;
    }
    else {
      b = inPanDot/Baduk.GAP;
      return b * Baduk.GAP;
    }
  }
}

class BadukLine extends Line {

  public BadukLine(double startX, double startY, double endX, double endY) {
    super(startX, startY, endX, endY);
  }
}
