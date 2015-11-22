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
public class imgDialog extends Stage{
    ePortfolioAppMakerView ui;
    VBox scn = new VBox();
    BorderPane topScn= new BorderPane();
    Button imgSelect = new Button("Select Image");
    HBox dimensionsPane=new HBox();
    TextField height= new TextField();
    TextField width=new TextField();
    Scene imgScreen;
    ImageView imgView= new ImageView();
    public imgDialog(){
        topScn.setLeft(imgSelect);
        imgSelect.setOnAction(e->{
            processSelectImage();
        });
        dimensionsPane.getChildren().addAll(new Label("              Height:"),height,new Label("px  Width:"),width,new Label("px"));
        topScn.setRight(dimensionsPane);
        scn.getChildren().addAll(topScn,imgView);
        imgScreen= new Scene(scn);
        this.setScene(imgScreen);
        showAndWait();
    }
    public void processSelectImage() {
	FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File("./images/img"));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
	
	// LET'S OPEN THE FILE CHOOSER 
        
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    //String fileName = file.getName();
	    //slideToEdit.setImage(path, fileName);
	    //view.updateSlideImage();
	    //ui.updateFileToolbarControls(false);
	}   
    }
}
