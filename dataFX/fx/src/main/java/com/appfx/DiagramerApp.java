package com.appfx;

//Wow, do we have enough imports?
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * DiagrammerApp.java    
 * @author zakacat    
 * 
 * This file contains the logic for the JavaFX interpretation of the
 * CSC backend designed by Alex McColm. Currently, this file consists
 * of both View and Controller while utilizing Alex's Model files, but 
 * I think there would be benefit and less clutter to separate the View
 * and Controller aspects into separate files.
 * 
 * It may not be pretty, but it is functional. It has been designed to be 
 * able to use Alex's backend files with little or no modifications needed
 * on them.
 * 
 * I also think that we should add a home button to be able to restart the app at 
 * any time including when the images are being displayed.
 */

public class DiagramerApp extends Application {

    
    /** 
     * Standard main() method.
     * 
     * launch() appears to initiate the JavaFX lifecycle and
     * start() gets called at some point.
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    /** 
     * start() appears to be a mandatory lifecycle method for JavaFX.
     * Methods within start() get executed in order much like a standard 
     * main() method. The stage (primaryStage) seems to represent the
     * application window and when a method must modify the existing window,
     * the primary stage must be accessed. There may be a way to use a get()
     * method to access the primaryStage directly, but I just passed in
     * the primaryStage to the necessary methods instead.
     * 
     * @param primaryStage
     */
    public void start(Stage primaryStage) {

        Text opener = new Text("Welcome to the Computer Sciences Data Structure Visualizer");

        Button button = new Button();
        button.setText("Enter"); //I think you can also instantiate a button with text
        button.setOnAction((event) -> {
            enterToMainMenu(primaryStage);
        });

        VBox vbox = new VBox(opener, button); //Vbox = vertical box. Organizes elements vertically
        vbox.setAlignment(Pos.CENTER); 

        Pane pane = new Pane(vbox); //

        Scene scene = new Scene(pane, 582, 448, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Structure Diagrammer");
        primaryStage.show();
    }

    
    /** 
     * @param primaryStage
     */
    private void enterToMainMenu(Stage primaryStage) {

        System.out.println("Enter button clicked!");

        TreeItem<String> rootItem = new TreeItem<>("Index");

        TreeItem<String> arrayItem = new TreeItem<>("Arrays");
        TreeItem<String> arrayVisItem = new TreeItem<>("Array Visualizer");
        arrayItem.getChildren().add(arrayVisItem);
        rootItem.getChildren().add(arrayItem);

        TreeItem<String> listItem = new TreeItem<>("Lists");
        listItem.getChildren().add(new TreeItem<>("Doubly Linked List Visualizer"));
        listItem.getChildren().add(new TreeItem<>("Singly Linked List Visualizer"));
        listItem.getChildren().add(new TreeItem<>("Queue Visualizer"));
        listItem.getChildren().add(new TreeItem<>("Stack Visualizer"));
        rootItem.getChildren().add(listItem);

        TreeItem<String> sortItem = new TreeItem<>("Sorting");
        sortItem.getChildren().add(new TreeItem<>("Bubble Sort Visualizer"));
        rootItem.getChildren().add(sortItem);

        TreeItem<String> treeItem = new TreeItem<>("Trees");
        treeItem.getChildren().add(new TreeItem<>("AVL Tree Visualizer"));
        treeItem.getChildren().add(new TreeItem<>("BST Visualizer"));
        treeItem.getChildren().add(new TreeItem<>("Trie Visualizer"));
        rootItem.getChildren().add(treeItem);

        TreeView<String> treeView = new TreeView<>();
        treeView.setRoot(rootItem);

        treeView.setShowRoot(false);

        treeView.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<Object>) new ChangeListener<Object>() {

                    @Override
                    public void changed(ObservableValue observable, Object oldValue,
                            Object newValue) {

                        TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                        System.out.println("Selected Text : " + selectedItem.getValue());

                        switch (selectedItem.getValue()) {

                            case "Array Visualizer": {
                                System.out.println("Array Visualizer Action");
                                break;
                            }

                            case "Doubly Linked List Visualizer": {
                                System.out.println("Doubly Linked List Visualizer Action");
                                break;
                            }

                            case "Singly Linked List Visualizer": {
                                System.out.println("Singly Linked List Action");
                                break;
                            }

                            case "Queue Visualizer": {
                                System.out.println("Queue Visualizer Action");
                                break;
                            }

                            case "Stack Visualizer": {
                                System.out.println("Stack Visualizer Action");
                                break;
                            }

                            case "Bubble Sort Visualizer": {
                                System.out.println("Bubble Sort Visualizer Action");

                                bubbleSort(primaryStage);

                                // Let's start with Bubble sort because we know it works.
                                break;
                            }

                            case "AVL Tree Visualizer": {
                                System.out.println("AVL Tree Visualizer Action");
                                break;
                            }

                            case "BST Visualizer": {
                                System.out.println("BST Visualizer Action");
                                break;
                            }

                            case "Trie Visualizer": {
                                System.out.println("Trie Visualizer Action");
                                break;
                            }

                            default: {
                                // Do nothing
                                break;
                            }
                        }
                    }

                });

        VBox vbox = new VBox(treeView);

        Scene scene = new Scene(vbox, 582, 448, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Structure Diagrammer");
        primaryStage.show();
    }

    
    /** 
     * @param primaryStage
     */
    private void bubbleSort(Stage primaryStage) {

        Text opener = new Text("Welcome to the Bubble Sort Visualizer");
        Text subOpener = new Text("Please input the values separated only by a comma.");
        Text extra = new Text("Then select the array type to continue.");

        TextArea textArea = new TextArea();

        textArea.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        textArea.setMinSize(200, 40);
        textArea.setMaxSize(562, 180);

        Button buttonInt = new Button();
        buttonInt.setText("Integer Array");
        buttonInt.setOnAction((event) -> {
            try {
                processIntArr(primaryStage, textArea.getText());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Button buttonString = new Button();
        buttonString.setText("String Array");
        buttonString.setOnAction((event) -> {
            // processStringArr(primaryStage, textArea.getText());
        });

        VBox vbox = new VBox(opener, subOpener, extra, textArea, buttonInt, buttonString);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 582, 448, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Structure Diagrammer");
        primaryStage.show();
    }

    int slideshowCount = 0;

    
    /** 
     * @param primaryStage
     * @param intArr
     * @throws InterruptedException
     */
    private void processIntArr(Stage primaryStage, String intArr) throws InterruptedException {

        String[] parseArr = intArr.split(",");
        Integer[] array = new Integer[parseArr.length];

        for (int i = 0; i < parseArr.length; i++) {

            if (i > (array.length - 1)) {
                array = Arrays.copyOf(array, array.length * 2);
            }

            array[i] = Integer.valueOf(parseArr[i]);
        }
        BubbleSortArrayDiagrammer<Integer> sortDiagrammer = new BubbleSortArrayDiagrammer<>();
        ArrayList<BufferedImage> sortStack = sortDiagrammer.renderSortingOperation(array, "Integer Array Bubble Sort");

        Stage stage = primaryStage;
        GridPane pane = new GridPane();
        Image image = convertToFxImage(sortStack.get(0));
        ImageView imageV = new ImageView(image);
        pane.getChildren().add(imageV);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 582, 488, true);
        stage.setTitle("Data Structure Diagrammer");
        stage.setScene(scene);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < sortStack.size(); i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            imageV.setImage(convertToFxImage(sortStack.get(slideshowCount)));
                            slideshowCount++;
                        }
                    });

                    Thread.sleep(1000);
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    
    /** 
     * @param stringArr
     */
    private void processStringArr(String stringArr) {

    }

    
    /** 
     * @param image
     * @return Image
     */
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}