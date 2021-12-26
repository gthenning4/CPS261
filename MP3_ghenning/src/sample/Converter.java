package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import java.math.BigDecimal;

public class Converter extends Application {
    RadioButton distRB = new RadioButton("km - mi");
    RadioButton massRB = new RadioButton("lb - kg");
    RadioButton tempRB = new RadioButton("F - C");
    ToggleGroup tg = new ToggleGroup();
    TextField text1 = new TextField();
    TextField text2 = new TextField();
    Label label1= new Label("Kilometers: ");
    Label label2= new Label("Miles: ");

    private static BigDecimal truncateDecimal(double x,int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        distRB.setToggleGroup(tg);
        massRB.setToggleGroup(tg);
        tempRB.setToggleGroup(tg);
        gridPane.add(distRB,0,0);
        gridPane.add(massRB,1,0);
        gridPane.add(tempRB,2,0);
        gridPane.add(label1,0,1);
        gridPane.add(text1, 1,1);
        gridPane.add(label2,0,2);
        gridPane.add(text2, 1,2);

        Scene scene = new Scene(gridPane, 400, 400);

        distRB.setOnAction(e ->{
            label1.setText("Kilometers: ");
            label2.setText("Miles: ");
        });
        massRB.setOnAction(e ->{
            label1.setText("lbs: ");
            label2.setText("kg: ");
        });
        tempRB.setOnAction(e ->{
            label1.setText("Fahrenheit: ");
            label2.setText("Celsius: ");
        });

        text1.setOnAction(e->{
            if(tg.getSelectedToggle() == distRB){
                //km to miles
                double km= Double.parseDouble(text1.getText());
                double mi = km/1.609;
                text2.setText(String.valueOf(truncateDecimal(mi,2)));
            }
            else if(tg.getSelectedToggle() == massRB){
                //lb to kg
                double lb = Double.parseDouble(text1.getText());
                double kg = lb/2.205;
                text2.setText(String.valueOf(truncateDecimal(kg,2)));
            }
            else if(tg.getSelectedToggle() == tempRB){
                //f to c
                double f= Double.parseDouble(text1.getText());
                double c = (f-32)*5/9;
                text2.setText(String.valueOf(truncateDecimal(c,2)));
            }
        });
        text2.setOnAction(e->{
            if(tg.getSelectedToggle() == distRB){
                //mi to km
                double mi= Double.parseDouble(text2.getText());
                double km = mi*1.609;
                text1.setText(String.valueOf(truncateDecimal(km,2)));
            }
            else if(tg.getSelectedToggle() == massRB){
                //kg to lb
                double kg = Double.parseDouble(text2.getText());
                double lb = kg*2.205;
                text1.setText(String.valueOf(truncateDecimal(lb,2)));
            }
            else if(tg.getSelectedToggle() == tempRB){
                //c to f
                double c= Double.parseDouble(text2.getText());
                double f = (c*9/5) + 32;
                text1.setText(String.valueOf(truncateDecimal(f,2)));
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
