package sample;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class RandomCards extends Application{

    public void start(Stage primaryStage) throws Exception{
        Button dealBtn = new Button("Deal Cards");
        Image backCard = new Image("backCard.png");
        ImageView card1 = new ImageView(backCard);
        ImageView card2 = new ImageView(backCard);
        ImageView card3 = new ImageView(backCard);
        ImageView card4 = new ImageView(backCard);
        String[] cardIds = {"0","0","0","0"};

        dealBtn.setOnAction(e ->{
            Random rand = new Random();
            for(int i=0; i<4; i++){
                Integer matches=0;
                Integer id = rand.nextInt(51) +1;
                Boolean unique=false;
                while(!unique){
                    for (String element:cardIds) {
                        if(id == Integer.parseInt(element)){
                            matches++;

                        }
                    }
                    if (matches == 0){
                        cardIds[i]=id.toString();
                        unique=true;
                    }
                    else{
                        id = rand.nextInt(51) +1;
                        matches=0;
                    }
                }

            }
            card1.setImage(backCard);
            card2.setImage(backCard);
            card3.setImage(backCard);
            card4.setImage(backCard);
        });


        card1.setOnMouseClicked(e ->{
            Image newCard = new Image(cardIds[0]+".png");
            card1.setImage(newCard);
        });
        card2.setOnMouseClicked(e ->{
            Image newCard = new Image(cardIds[1]+".png");
            card2.setImage(newCard);
        });
        card3.setOnMouseClicked(e ->{
            Image newCard = new Image(cardIds[2]+".png");
            card3.setImage(newCard);
        });
        card4.setOnMouseClicked(e ->{
            Image newCard = new Image(cardIds[3]+".png");
            card4.setImage(newCard);
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(dealBtn,0,0);
        gridPane.add(card1,0,1);
        gridPane.add(card2,1,1);
        gridPane.add(card3,2,1);
        gridPane.add(card4,3,1);
        Scene scene = new Scene(gridPane,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
