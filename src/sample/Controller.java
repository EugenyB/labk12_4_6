package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Figure;
import view.MyView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Canvas canvas;

    @FXML
    Pane pane;

    Figure figure = new Figure();

    MyView view = new MyView();

    public void draw(ActionEvent actionEvent) {
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        view.drawFigure(figure, gc);
    }


    public void moveRight(ActionEvent actionEvent) {
        view.move(10,0,0);
        draw();
    }

    public void rotateXplus(ActionEvent actionEvent) {
        view.rotateX(10);
        draw();
    }

    public void rotateYplus(ActionEvent actionEvent) {
        view.rotateY(10);
        draw();
    }

    public void rotateZplus(ActionEvent actionEvent) {
        view.rotateZ(10);
        draw();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.widthProperty().addListener(e -> draw());
        canvas.heightProperty().addListener(e -> draw());
    }
}
