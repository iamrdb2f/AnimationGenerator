package com.la_fed.animation_generator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class AnimationGenerator extends Application {

    private static final String[] NAMES = {
            "Rich Muna", "Emile Rogombe", "Issa Mohamed Ratanga", "Leslie Mapouya Tsiakonga",
            "Carel Benga", "Miguel Ekambou Oyandja", "Jean Tocko", "Bkr Dexx",
            "Alexandre Nguema", "Emeline Issougou", "Mirabelle Mboumba", "Mohamed Traore",
            "Menzoghe Marianne", "Prevy's Molango", "Djibi Dieng", "Mariette Boucka",
            "Sincère Chris Ndjiba", "Benixe Lophelle", "Gredjy Darel", "Vianney'y-big Angoue",
            "Moussadji Benevien Sorian", "Lousiana Ndoutoume", "Ma Ï Cka", "Teddy Tchikaya",
            "Dan Koualy Awobo", "Kerly Navaro", "Kebila Magambou", "Lherry Leboulangoye",
            "Gervais Zime", "Jérémy Engoang", "Nerval Léon", "Hope Bouanga",
            "Justine Etome Nkogo", "Danddys Mahitsa", "Destin Le Seul", "Père Emma Sayindo",
            "Mirna La Sentinelle", "Désormais C'est Emie", "Lucien Mouele Tanda",
            "Bleck Ferdinand Anguillet", "Fabrice Mbadinga", "Hermiline Mbenga",
            "Sahusize Kasereka Roy", "Tching'son le Kemet", "Târa Ontsoura Mo'o",
            "Lionel Émerick", "Torres Le Métis Noir", "Jorgy Mendouze Nzamba",
            "Anastasie Obone", "Bobo Pongui", "Sawadogo Sawadogo Michel", "C&M Multiservices",
            "M'baye Luc Béni", "Sara Soro", "Asse Felix Koffi", "Kouame Blaise Konenan",
            "Camavinga Houda", "Merveille Koffi", "Konan Armand Yao", "Dikarace De la Nuevo",
            "Peguy Mba Mba", "Wensley Ndong", "Konaté Lasso", "Mathias Guehi", "Ayeri Yane Ottou",
            "Mp Services", "Loh Alexandre Guida", "Jean Marc Kouassi", "Jardin de Longévité",
            "Kpatajeanclaude Atse", "INJOY GROUP", "Lewandoski MillionnaireAshad",
            "Rufix Koffi Golgotchia", "Germain Mael Soro", "Aurel-omer Brovou", "Idrissa Lobané",
            "Seigneur Raidenn", "Ismaél Ødiløn Køngø", "Falley Toure", "Ano Judicaël Romaric Djongo",
            "Soro Adam London", "Nikson Zaba", "Erver Kouadio Kouakou", "Mamadou Ky",
            "Ibrahim Kogo", "Iroko Dailly", "Auguste L'intouchable", "Bolou Gaius", "Riche Zeba",
            "Ibrahim Bakayoko", "Chadrac New Ouedraogo", "Sidibe Muss", "Boune Blé",
            "Mahamadi Quedraogo"
    };

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1920, 1080, Color.BLACK);

        try {
            String videoPath = new File("video/animation_generator.mp4").toURI().toString();
            Media videoMedia = new Media(videoPath);
            MediaPlayer videoPlayer = new MediaPlayer(videoMedia);
            videoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            videoPlayer.play();

            MediaView mediaView = new MediaView(videoPlayer);
            mediaView.setFitWidth(1920);
            mediaView.setFitHeight(1080);
            mediaView.setPreserveRatio(false);

            root.getChildren().add(mediaView);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur : Impossible de lire la vidéo.");
        }

        String cssPath = new File("css/styles.css").toURI().toString();
        scene.getStylesheets().add(cssPath);

        primaryStage.setTitle("Animation Générateur");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            String audioPath = new File("audio/between-the-spaces.mp3").toURI().toString();
            Media audioMedia = new Media(audioPath);
            mediaPlayer = new MediaPlayer(audioMedia);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur : Impossible de lire le fichier audio.");
        }

        playAnimation(root, 0);
    }

    private void playAnimation(StackPane root, int currentIndex) {
        if (currentIndex >= NAMES.length) {
            currentIndex = 0;
        }

        String name = NAMES[currentIndex];

        TextFlow textFlow = new TextFlow();

        Text line1 = new Text("1000\n");
        line1.getStyleClass().add("animated-text-mille");

        Text line2 = new Text("followers\n");
        line2.getStyleClass().add("animated-text-followers");

        Text line3 = new Text("MERCI\n");
        line3.getStyleClass().add("animated-text-thanks");

        Text line4 = new Text(name.toUpperCase() + " ");
        line4.getStyleClass().add("animated-text-name");

        textFlow.getChildren().addAll(line1, line2, line3, line4);
        textFlow.setTextAlignment(TextAlignment.CENTER);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().add(textFlow);
        vBox.getStyleClass().add("text-flow-container");

        root.getChildren().removeIf(node -> node instanceof VBox);

        root.getChildren().add(vBox);
        StackPane.setAlignment(vBox, Pos.CENTER);

        final int nextIndex = currentIndex + 1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> playAnimation(root, nextIndex)));
        timeline.play();
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
