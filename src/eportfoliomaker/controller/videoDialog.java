/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Video;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class videoDialog extends Stage{
    ePortfolioAppMakerView ui;
    GridPane scn= new GridPane();
    Button vidSelect = new Button("Select Video");
    HBox dimensionsPane=new HBox();
    TextField height= new TextField();
    TextField width=new TextField();
    Scene imgScreen;
    TextField caption= new TextField();
    String path;
    String fileName;
    
    public videoDialog(Stage primaryStage,Video vToEdit){
        initOwner(primaryStage);
        vidSelect.setOnAction(e->{
            processSelectImage();
        });
        dimensionsPane.getChildren().addAll(new Label("Caption:"),caption,new Label("Height:"),height,new Label("px  Width:"),width,new Label("px"));
        scn.add(vidSelect,0,0);
        scn.add(dimensionsPane,1,0);
        Button OKButton= new Button("OK");
        OKButton.getStyleClass().add("dialog");
        scn.add(OKButton,2,0);
        scn.getStyleClass().add("dialog");
        vidSelect.getStyleClass().add("dialog");
        dimensionsPane.getStyleClass().add("dialog");
        imgScreen= new Scene(scn);
        imgScreen.getStylesheets().add("eportfoliomaker/style/Style.css");
        OKButton.setOnMouseReleased(e->{
            vToEdit.setVideoW(Double.parseDouble(width.getText()));
            vToEdit.setVideoH(Double.parseDouble(height.getText()));
            vToEdit.setCaption(caption.getText());
            vToEdit.setVideoFileName(fileName);
            vToEdit.setVideoPath(path);
        });
        
        this.setScene(imgScreen);
        showAndWait();
    }
    public void processSelectImage() {
	FileChooser videoFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	videoFileChooser.setInitialDirectory(new File("./images/img"));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
	FileChooser.ExtensionFilter mp3Filter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.MP3");
	videoFileChooser.getExtensionFilters().addAll(mp4Filter,mp3Filter);
	
	// LET'S OPEN THE FILE CHOOSER 
        
	File file = videoFileChooser.showOpenDialog(null);
	if (file != null) {
	    path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    fileName = file.getName();
	    
	}   
    }
}
