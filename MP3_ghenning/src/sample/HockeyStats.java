package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.util.ArrayList;

public class HockeyStats extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bar Chart Sample");
        //init our data vars
        ArrayList<String> teams = new ArrayList<String>();
        ArrayList<Integer> goals= new ArrayList<Integer>();
        //read in file and set our data
        try(BufferedReader br = new BufferedReader(new FileReader("/Users/georgehenning/IdeaProjects/MP3_ghenning/src/sample/mp3_hockey_stats.txt"))) {
            String line = br.readLine();
            while (line != null) {

                //break on comma delimiter
                String[] tempArr = line.split(",");
                teams.add(tempArr[0]);
                goals.add(Integer.parseInt(tempArr[1]));
                
                //read new line
                line = br.readLine();
            }
        }
        //build our bar graph!!

        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);
        bc.setTitle("NHL Goals by Team");
        xAxis.setLabel("Goals");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Teams");

        XYChart.Series series = new XYChart.Series();
        series.setName("2018-19");
        for(int i=0; i < teams.size(); i++){
            series.getData().add(new XYChart.Data(goals.get(i),teams.get(i)));
        }

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
