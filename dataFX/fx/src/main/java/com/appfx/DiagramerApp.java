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

        StackPane pane = new StackPane(vbox); //StackPane allows for items to lay on top of eachother, not
                                            // sure why it is needed here, but it centers the items nicely.
        
        Scene scene = new Scene(pane, 582, 448, true);  //The numbers are the window size. They are currently
                                                        //arbitrary.
        primaryStage.setScene(scene);        
        primaryStage.setTitle("Data Structure Diagrammer"); //Appears at the top of the window.
        primaryStage.show();
    }

    
    /** 
     * Once the enter button is clicked, the user proceeds to the main menu which is a 
     * interactable tree menu. The user can click the branches to continue onto specific
     * diagrammers.
     * 
     * @param primaryStage Note the stage has been passed in so that it can be accessed.
     *                      Creating and using a new stage appears to create a new window.
     */
    private void enterToMainMenu(Stage primaryStage) {

        System.out.println("Enter button clicked!");
        //The root is not displayed, but all the other tree items are attached to the root
        TreeItem<String> rootItem = new TreeItem<>("Index");

        TreeItem<String> arrayItem = new TreeItem<>("Arrays");
        TreeItem<String> arrayVisItem = new TreeItem<>("Array Visualizer");
        arrayItem.getChildren().add(arrayVisItem); //TreeItems can be added explicitly like this
        rootItem.getChildren().add(arrayItem);

        TreeItem<String> listItem = new TreeItem<>("Lists");
        listItem.getChildren().add(new TreeItem<>("Doubly Linked List Visualizer"));// or like this
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

        treeView.setShowRoot(false); //We are not showing the root, but we could instead make
                                     // arrayItem the root and show it.
        //This is the listener that reacts when one of the branches are clicked.
        treeView.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<Object>) new ChangeListener<Object>() {

                    @Override
                    public void changed(ObservableValue observable, Object oldValue,
                            Object newValue) {

                        TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                        System.out.println("Selected Text : " + selectedItem.getValue());

                        switch (selectedItem.getValue()) {//selectedItem.getValue returns the text
                                                          // so were are matching the cases to their text

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

                                bubbleSort(primaryStage); //again passing in the primaryStage.

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
     * This method effectively is the View and Controller for the window only having
     * to do with the bubbleSort diagramer.
     * 
     * @param primaryStage
     */
    private void bubbleSort(Stage primaryStage) {
        //Added this a separate texts to make it easier to center them
        Text opener = new Text("Welcome to the Bubble Sort Visualizer");
        Text subOpener = new Text("Please input the values separated only by a comma.");
        Text extra = new Text("Then select the array type to continue.");

        TextArea textArea = new TextArea(); //This is the user input area

        textArea.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        textArea.setMinSize(200, 40);
        textArea.setMaxSize(562, 180);//I made it this big so that the scroll bars
                                      // always displayed properly

        Button buttonInt = new Button();
        buttonInt.setText("Integer Array");
        buttonInt.setOnAction((event) -> {
            try {
                processIntArr(primaryStage, textArea.getText());//passing the primaryStage and the text that
                                                                //the user input.
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
        Button home = new Button("Home");
        home.setAlignment(Pos.TOP_RIGHT);
        home.setOnAction((event) -> {
            enterToMainMenu(primaryStage);
        });        

        VBox vbox = new VBox(opener, subOpener, extra, textArea, buttonInt, buttonString, home);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 582, 448, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Structure Diagrammer");
        primaryStage.show();
    }

    int slideshowCount = 0; //I am not sure why this variable needs to be declared globally, 
                            //but it does. Could be put up top, but whateves

    /** 
     * This method would be a Controller method. It takes the Integer array from the user,
     * sends it thru the model classes, returns a stack(actually an ArrayList) of images
     * and then displays them on a separate thread for one second at a time.
     * 
     * @param primaryStage
     * @param intArr
     * @throws InterruptedException
     */
    private void processIntArr(Stage primaryStage, String intArr) throws InterruptedException {

        String[] parseArr = intArr.split(","); //splitting the string at the commas
        Integer[] array = new Integer[parseArr.length];

        for (int i = 0; i < parseArr.length; i++) { //Coverting the string numbers to integers

            if (i > (array.length - 1)) {
                array = Arrays.copyOf(array, array.length * 2);
            }

            array[i] = Integer.valueOf(parseArr[i]);
        }
        BubbleSortArrayDiagrammer<Integer> sortDiagrammer = new BubbleSortArrayDiagrammer<>();
        //Using Alex's model class to generate all the images of the sorting process
        ArrayList<BufferedImage> sortStack = sortDiagrammer.renderSortingOperation(array, "Integer Array Bubble Sort");

        GridPane pane = new GridPane(); //I think we use this for the ease of importing images
        Image image = convertToFxImage(sortStack.get(0)); //immediately show the unsorted image
        ImageView imageV = new ImageView(image); //I think it is easier to change the ImageView than Image
        Button home = new Button("Home");
        home.setAlignment(Pos.TOP_RIGHT);
        home.setOnAction((event) -> {
            enterToMainMenu(primaryStage);
        });    
        GridPane.setConstraints(home, 2, 0);    
        GridPane.setConstraints(imageV, 1, 1);
        pane.getChildren().addAll(imageV, home);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 582, 488, true);
        primaryStage.setTitle("Data Structure Diagrammer");
        primaryStage.setScene(scene);

        Task<Void> task = new Task<Void>() { //This creates a separate thread which loops and pauses
                                             // to display all the images in the stack.
                                             //Using a loop without a separate thread does not work!
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < sortStack.size(); i++) {
                    Platform.runLater(new Runnable() { //Not sure what this is...copy/past from StackOverflow
                        @Override
                        public void run() { //important lifecycle method
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
        th.start();//this is actually where the thread gets realized

    }

    
    /** 
     * Have we tested the string array for bubble sort?
     * 
     * @param stringArr
     */
    private void processStringArr(String stringArr) {

    }

    
    /** 
     * I pulled this off the internet, but it appears to actually redraw the image from the
     * bufferedImage to image by the pixel... Probably not very effecient. Can we write the 
     * model files using the Image class instead?
     * 
     * @param bufferedimage
     * @return Image
     */
    private static Image convertToFxImage(BufferedImage bufferedImage) {
        WritableImage wr = null;
        if (bufferedImage != null) {
            wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}