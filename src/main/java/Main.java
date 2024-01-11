import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    public static Image slikaRampa = new Image("rampa.png");
    public static GridPane gridPane1 = new GridPane();
    public static ImageView carinski1Slika = new ImageView(slikaRampa);
    public static ImageView carinski2Slika = new ImageView(slikaRampa);
    public static ImageView policijski1Slika = new ImageView(slikaRampa);
    public static ImageView policijski2Slika = new ImageView(slikaRampa);
    public static ImageView policijskiZaKamioneSlika = new ImageView(slikaRampa);
    public static StackPane carinski1 =new StackPane();
    public static StackPane carinski2 =new StackPane();
    public static StackPane policijski1 =new StackPane();
    public static StackPane policijski2 =new StackPane();
    public static StackPane policijskiZaKamione =new StackPane();
    public static VBox red = new VBox();
    public static TilePane tilePaneDrugeScene =new TilePane();
    private static final Object lock=new Object();


    @Override
    public  void start(Stage stage)
    {
        try {
            Image ikonica = new Image("ikonica1.png");
            stage.getIcons().add(ikonica);

            //PRVA SCENA//
            StackPane root = new StackPane();
            Image pozadina1 = new Image("pozadina1.jpeg");
            BackgroundImage backgroundImage1 = new BackgroundImage(pozadina1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background1 = new Background(backgroundImage1);

            Scene prvaScena = new Scene(root, 500, 650);
            root.setBackground(background1);

            for (int i = 0; i < 4; i++) {

                RowConstraints row = new RowConstraints(100);
                gridPane1.getRowConstraints().add(row);

                ColumnConstraints col = new ColumnConstraints(100);
                gridPane1.getColumnConstraints().add(col);
            }
            RowConstraints lastRowConstraints = new RowConstraints(300); // Širi poslednji red (duplo širi)
            gridPane1.getRowConstraints().add(lastRowConstraints);

            Button dugmeZaDruguScenu = new Button("Druga scena");
            GridPane.setRowIndex(dugmeZaDruguScenu, 0);
            GridPane.setColumnIndex(dugmeZaDruguScenu, 4);
            gridPane1.getChildren().add(dugmeZaDruguScenu);

            carinski1Slika.setFitWidth(80);
            carinski1Slika.setFitHeight(80);
            carinski1.getChildren().add(carinski1Slika);
            GridPane.setRowIndex(carinski1, 1);
            GridPane.setColumnIndex(carinski1, 1);
            gridPane1.getChildren().add(carinski1);

            carinski2Slika.setFitWidth(70);
            carinski2Slika.setFitHeight(70);
            carinski2.getChildren().add(carinski2Slika);
            GridPane.setRowIndex(carinski2, 1);
            GridPane.setColumnIndex(carinski2, 3);
            gridPane1.getChildren().add(carinski2);

            policijski1Slika.setFitWidth(70);
            policijski1Slika.setFitHeight(70);
            policijski1.getChildren().add(policijski1Slika);
            GridPane.setRowIndex(policijski1, 2);
            GridPane.setColumnIndex(policijski1, 0);
            gridPane1.getChildren().add(policijski1);

            policijski2Slika.setFitWidth(70);
            policijski2Slika.setFitHeight(70);
            policijski2.getChildren().add(policijski2Slika);
            GridPane.setRowIndex(policijski2, 2);
            GridPane.setColumnIndex(policijski2, 2);
            gridPane1.getChildren().add(policijski2);

            policijskiZaKamioneSlika.setFitWidth(70);
            policijskiZaKamioneSlika.setFitHeight(70);
            policijskiZaKamione.getChildren().add(policijskiZaKamioneSlika);
            GridPane.setRowIndex(policijskiZaKamione, 2);
            GridPane.setColumnIndex(policijskiZaKamione, 4);
            gridPane1.getChildren().add(policijskiZaKamione);


            GridPane.setRowIndex(red, 4);
            GridPane.setColumnIndex(red, 2);
            gridPane1.getChildren().add(red);

            Button dugmeZaPauzu = new Button("Pauza");
            GridPane.setRowIndex(dugmeZaPauzu, 0);
            GridPane.setColumnIndex(dugmeZaPauzu, 0);
            gridPane1.getChildren().add(dugmeZaPauzu);

            root.getChildren().add(gridPane1);
            stage.setScene(prvaScena);

            //DRUGA SCENA//

            StackPane root2 = new StackPane();
            Scene drugaScena = new Scene(root2, 700, 500);

            Image parking = new Image("parking.png");
            BackgroundImage backgroundImage2 = new BackgroundImage(parking, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background background2 = new Background(backgroundImage2);
            root2.setBackground(background2);


            dugmeZaDruguScenu.setOnAction(e -> {
                stage.setScene(drugaScena);
            });

            Button dugmeZaPauzu2 = new Button("Pauza");
            GridPane.setRowIndex(dugmeZaPauzu2, 0);
            GridPane.setColumnIndex(dugmeZaPauzu2, 0);

            Button dugmeZaPrvuScenu = new Button("Prva scena");
            GridPane.setRowIndex(dugmeZaPrvuScenu, 0);
            GridPane.setColumnIndex(dugmeZaPrvuScenu, 7);

            dugmeZaPrvuScenu.setOnAction(e -> {
                stage.setScene(prvaScena);
            });

            Button dugmeZaTrecuScenu = new Button("Treća scena");
            GridPane.setRowIndex(dugmeZaTrecuScenu, 0);
            GridPane.setColumnIndex(dugmeZaTrecuScenu, 8);

            root2.setAlignment(dugmeZaTrecuScenu,Pos.TOP_RIGHT);
            root2.setMargin(dugmeZaTrecuScenu, new Insets(10.0, 10.0, 0.0, 10.0));

            root2.setAlignment(dugmeZaPauzu2,Pos.TOP_CENTER);
            root2.setMargin(dugmeZaPauzu2, new Insets(10.0, 10.0, 0.0, 10.0));

            root2.setAlignment(dugmeZaPrvuScenu,Pos.TOP_LEFT);
            root2.setMargin(dugmeZaPrvuScenu, new Insets(10.0, 10.0, 0.0, 10.0));

            root2.setAlignment(tilePaneDrugeScene, Pos.BOTTOM_CENTER);
            root2.setMargin(tilePaneDrugeScene, new Insets(100.0, 10.0, 0.0, 10.0));

            root2.getChildren().addAll(dugmeZaPrvuScenu,dugmeZaPauzu2,dugmeZaTrecuScenu, tilePaneDrugeScene);

            //TRECA SCENA//

            StackPane root3 = new StackPane();
            Scene trecaScena = new Scene(root3, 600, 600);

            Image parking1 = new Image("trecaScena.png");
            BackgroundImage backgroundImage3 = new BackgroundImage(parking1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background background3 = new Background(backgroundImage3);
            root3.setBackground(background3);

            dugmeZaTrecuScenu.setOnAction(e -> {
                stage.setScene(trecaScena);
            });

            Button dugmeZaDruguScenuIzTrece=new Button("Druga scena");
            root3.setAlignment(dugmeZaDruguScenuIzTrece,Pos.TOP_LEFT);
            root3.setMargin(dugmeZaDruguScenuIzTrece, new Insets(10.0, 10.0, 0.0, 10.0));

            dugmeZaDruguScenuIzTrece.setOnAction(e -> {
                stage.setScene(drugaScena);
            });

            root3.getChildren().add(dugmeZaDruguScenuIzTrece);

            stage.setResizable(false);
            stage.show();

        }
        catch(Exception e)
        {
            Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
        }
    }
   public static void dodajURedNarvuScenu(Vozilo vozilo){
        if(vozilo instanceof Automobil){
            Image auto=new Image("auto.png");
            ImageView autoImage=new ImageView(auto);
            autoImage.setFitWidth(50);
            autoImage.setFitHeight(50);
            red.getChildren().add(autoImage);
        } else if (vozilo instanceof Autobus) {
            Image autobus=new Image("autobus.png");
            ImageView autobusImage=new ImageView(autobus);
            autobusImage.setFitWidth(50);
            autobusImage.setFitHeight(50);
            red.getChildren().add(autobusImage);
        }else if(vozilo instanceof Kamion){
            Image kamion=new Image("kamion.png");
            ImageView kamionImage=new ImageView(kamion);
            kamionImage.setFitHeight(50);
            kamionImage.setFitWidth(50);
            red.getChildren().add(kamionImage);
        }
   }
   public static void dodajNaDruguScenu(Vozilo vozilo) {
       if(vozilo instanceof AutomobilInterface) {
           Image auto=new Image("auto.png");
           ImageView autoImage=new ImageView(auto);

           autoImage.setFitWidth(50);
           autoImage.setFitHeight(50);

           tilePaneDrugeScene.getChildren().add(autoImage);
       }
       else if(vozilo instanceof AutobusInterface) {
           Image autobus=new Image("autobus.png");
           ImageView autobusImage=new ImageView(autobus);

           autobusImage.setFitWidth(50);
           autobusImage.setFitHeight(50);

           tilePaneDrugeScene.getChildren().add(autobusImage);
       }
       else if(vozilo instanceof KamionInterface) {
           Image kamion=new Image("kamion.png");
           ImageView kamionImage=new ImageView(kamion);

           kamionImage.setFitHeight(50);
           kamionImage.setFitWidth(50);

           tilePaneDrugeScene.getChildren().add(kamionImage);
       }
   }
   public static void prebaciIzRedaNaPolicijski(int brojTerminala){
        Platform.runLater(()-> {
            //if(!red.getChildren().isEmpty()) {
                Node vozilo=red.getChildren().remove(0);
                if(brojTerminala==1){
                    policijski1.getChildren().add(vozilo);
                }
                else if (brojTerminala==2) {
                    policijski2.getChildren().add(vozilo);
                }else if(brojTerminala==3){
                    policijskiZaKamione.getChildren().add(vozilo);
                }
           // }
            if(!tilePaneDrugeScene.getChildren().isEmpty()) {
                vozilo=tilePaneDrugeScene.getChildren().remove(0);
                red.getChildren().add(vozilo);
            }
        });

    }

    public static void prebaciSaPolicijskihNaCarinski(int brojTerminalaSaKogDolazi){
        Platform.runLater(()-> {
           if(brojTerminalaSaKogDolazi==1) {
              Node vozilo=policijski1.getChildren().remove(policijski1.getChildren().size()-1);
               carinski1.getChildren().add(vozilo);
           }
           else if(brojTerminalaSaKogDolazi==2){
               Node vozilo=policijski2.getChildren().remove(policijski2.getChildren().size()-1);
               carinski1.getChildren().add(vozilo);
           } else if (brojTerminalaSaKogDolazi==3) {
               Node vozilo=policijskiZaKamione.getChildren().remove(policijskiZaKamione.getChildren().size()-1);
               carinski2.getChildren().add(vozilo);
           }});
    }
    public static void izbrisiSaPolicijskog(int brojPolicijskog)// AKO PADNE NA POLICIJSKOM BRISE SE SA NJEGA
    {
        Platform.runLater(()-> {
            if(brojPolicijskog==1) {
                policijski1.getChildren().remove(policijski1.getChildren().size()-1);
            }else if(brojPolicijskog==2){
                policijski2.getChildren().remove(policijski2.getChildren().size()-1);
            }
            else if(brojPolicijskog==3) {
                policijskiZaKamione.getChildren().remove(policijskiZaKamione.getChildren().size()-1);
            }});
    }
    public static void izbrisiSaCarinskog(int brojCarinskog)
    {
        Platform.runLater(()-> {
            if(brojCarinskog==1) {
                carinski1.getChildren().remove(carinski1.getChildren().size()-1);
            }else if(brojCarinskog==2){
                carinski2.getChildren().remove(carinski2.getChildren().size()-1);
            }});
    }
    public static void main(String[] args) {

        for (int i = 0; i < Simulacija.BROJ_KAMIONA; i++) {
            Simulacija.pomocniRed.add(new Kamion());
        }
        for (int i = 0; i < Simulacija.BROJ_AUTA; i++) {
            Simulacija.pomocniRed.add(new Automobil());
        }
        for (int i = 0; i < Simulacija.BROJ_AUTOBUSA; i++) {
            Simulacija.pomocniRed.add(new Autobus());
        }
        Collections.shuffle(Simulacija.pomocniRed);
        for(int i=0; i<Simulacija.pomocniRed.size();i++) {
            Simulacija.granicniRed.add(Simulacija.pomocniRed.get(i));
            if(i<5) {
                dodajURedNarvuScenu(Simulacija.pomocniRed.get(i));
            }else dodajNaDruguScenu(Simulacija.pomocniRed.get(i));
        }
        for (Vozilo vozilo : Simulacija.granicniRed) {
            vozilo.start();
        }
        launch(args);
    }
}