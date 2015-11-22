/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    VBox scn = new VBox();
    BorderPane topScn= new BorderPane();
    Button vidSelect = new Button("Select Video");
    HBox dimensionsPane=new HBox();
    TextField height= new TextField();
    TextField width=new TextField();
    Scene imgScreen;
    public videoDialog(){
        topScn.setLeft(vidSelect);
        vidSelect.setOnAction(e->{
            processSelectImage();
        });
        dimensionsPane.getChildren().addAll(new Label("              Height:"),height,new Label("px  Width:"),width,new Label("px"));
        topScn.setRight(dimensionsPane);
        scn.getChildren().addAll(topScn);
        imgScreen= new Scene(scn);
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
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    //String fileName = file.getName();
	    //slideToEdit.setImage(path, fileName);
	    //view.updateSlideImage();
	    //ui.updateFileToolbarControls(false);
	}   
    }
}
