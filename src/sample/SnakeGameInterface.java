package sample;

import java.util.Random;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SnakeGameInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int sceneWidth = 460;
        int sceneHeight = 460;
        int rowNum = 20;
        int colNum = 20;

        final GridPane grid = new GridPane();
        Random rand = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                int n = rand.nextInt(4);
                Rectangle rec = new Rectangle();
                rec.setWidth(sceneWidth / colNum);
                rec.setHeight(sceneHeight / rowNum);
                rec.setFill(colors[n]);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }

        Scene scene = new Scene(grid, sceneWidth, sceneHeight);

        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed(event.getCode(), grid);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void keyPressed(KeyCode code, GridPane grid) {
        if (code == KeyCode.UP) {
            Rectangle rectangle = getNodeByRowColumnIndex(0, 0,grid);
            rectangle.setFill(Color.DEEPPINK);
        }
    }

    private static Rectangle getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("Ivalid grid coordinates: " + row + " " + column);
        }

        return (Rectangle) result;
    }
}
