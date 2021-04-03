package sample;

import com.sun.jdi.VoidValue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {
    private TreeView<String> LocalTree;
    private TreeView<String> ServerTree;
    private TreeItem<String> items;
    private TreeItem<String> localItems;


    final HBox hb = new HBox();
    fileServerClient client;
    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 510,510);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        File mainDirectory = directoryChooser.showDialog(primaryStage);
        String path = mainDirectory.getAbsolutePath();
        System.out.println("Local Directory: " + path);
        client = new fileServerClient(path);
        String dirs[] = client.listAllFiles();

        String filenames = "";
        File allFiles = new File(path);
        if (allFiles.isDirectory()) {
            File[] content = allFiles.listFiles();
            for (File current : content) {
                filenames += current.getName() + ",";
            }
        }
        String localFiles[] = filenames.split(",");

        SplitPane sp = new SplitPane();
        updatePane(sp, dirs, localFiles);

        Button btn1 = new Button("Download");
        btn1.setStyle("-fx-background-color: #f5d678; -fx-border-color:black; -fx-text-fill: black;");
        Button btn2 = new Button("Upload");
        btn2.setStyle("-fx-background-color: #e9fa9d; -fx-border-color:black; -fx-text-fill: black;");
        Button btn3 = new Button("Open Local File");
        btn3.setStyle("-fx-background-color: #fac59d; -fx-border-color:black; -fx-text-fill: black;");
        Button btn4 = new Button("Open Server File");
        btn4.setStyle("-fx-background-color: #f0a8c6; -fx-border-color:black; -fx-text-fill: black;");

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TreeItem<String> fileName = ServerTree.getSelectionModel().getSelectedItem();
                String file = fileName.getValue();

                client = new fileServerClient(path);
                client.downloadFileFromServer(file);
                client = new fileServerClient(path);
                String dirs[] = client.listAllFiles();
                String filenames = "";
                File allFiles = new File(path);
                if (allFiles.isDirectory()) {
                    File[] content = allFiles.listFiles();
                    for (File current : content) {
                        filenames += current.getName() + ",";
                    }
                }
                String localFiles[] = filenames.split(",");
                SplitPane sp2 = new SplitPane();
                updatePane(sp2, dirs, localFiles);
                grid.add(sp2,0,1);
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TreeItem<String> fileName = LocalTree.getSelectionModel().getSelectedItem();
                String file = fileName.getValue();

                client = new fileServerClient(path);
                client.uploadNewFile(file);
                client = new fileServerClient(path);
                String dirs[] = client.listAllFiles();
                String filenames = "";
                File allFiles = new File(path);
                if (allFiles.isDirectory()) {
                    File[] content = allFiles.listFiles();
                    for (File current : content) {
                        filenames += current.getName() + ",";
                    }
                }
                String localFiles[] = filenames.split(",");
                SplitPane sp2 = new SplitPane();
                updatePane(sp2, dirs, localFiles);
                grid.add(sp2,0,1);
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                client = new fileServerClient(path);
                TreeItem<String> fileName1 = LocalTree.getSelectionModel().getSelectedItem();

                String file1 = fileName1.getValue();
                client.openLocalFile(file1);
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client = new fileServerClient(path);
                TreeItem<String> fileName2 = ServerTree.getSelectionModel().getSelectedItem();

                String file2 = fileName2.getValue();
                client.openServerFile(file2);
            }
        });
        hb.getChildren().addAll(btn1,btn2,btn3,btn4);
        grid.add(hb, 0,0);
        grid.add(sp,0,1);

        primaryStage.setScene(scene);
        primaryStage.setTitle("File Transfer");
        primaryStage.show();
    }
    public void updatePane(SplitPane splitPane,String[] server, String[] local)
    {
        items = new TreeItem<String>("Server Directory");
        for(int i = 0; i < server.length;i++)
        {
            items.getChildren().addAll(new TreeItem<String>(server[i]));
        }
        items.setExpanded(true);
        localItems = new TreeItem<String>(" Local Directory");
        for(int i = 0; i < local.length;i++)
        {
            localItems.getChildren().addAll(new TreeItem<String>(local[i]));
        }
        localItems.setExpanded(true);

        LocalTree = new TreeView<String>(localItems);
        ServerTree = new TreeView<String>(items);
        LocalTree.setStyle(
                "  -fx-base: #cf98ed ;\n" +
                "  -fx-control-inner-background: derive(-fx-base,20%);\n" +
                "  -fx-control-inner-background-alt: derive(-fx-control-inner-background,-10%);\n" +
                "  -fx-accent: #006689;\n" +
                "  -fx-focus-color: #036E83;\n" +
                "  -fx-selection-bar: #f5698e;\n" +
                "  -fx-selection-bar-non-focused: #7dabf5\n");
        ServerTree.setStyle(
                "  -fx-base: #93f5b7 ;\n" +
                "  -fx-control-inner-background: derive(-fx-base,20%);\n" +
                "  -fx-control-inner-background-alt: derive(-fx-control-inner-background,-10%);\n" +
                "  -fx-accent: #006689;\n" +
                "  -fx-focus-color: #036E83;\n" +
                "  -fx-selection-bar: #8892f2;\n" +
                "  -fx-selection-bar-non-focused: #aeb5f2\n");
        splitPane.getItems().addAll(LocalTree, ServerTree);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
