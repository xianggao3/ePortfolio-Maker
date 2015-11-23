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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class imgDialog extends Stage{
    ePortfolioAppMakerView ui;
    GridPane scn = new GridPane();
    Button imgSelect = new Button("Select Image");
    HBox heightPane=new HBox();
    HBox widthPane=new HBox();
    TextField height= new TextField();
    TextField width=new TextField();
    Scene imgScreen;
    FlowPane buttonsPane=new FlowPane();
    Button leftFloat;
    Button rightFloat;
    Button midFloat;
    
    public imgDialog(){
        
        imgSelect.setOnAction(e->{
            processSelectImage();
        });
        heightPane.getChildren().addAll(new Label("Height:"),height,new Label("px"));
        widthPane.getChildren().addAll(new Label("Width:"),width,new Label("px"));
        buttonsPane.getChildren().add(new Label("Image Orientation:"));
        leftFloat=initChildButton(buttonsPane,"left.png","",false,"Left Float the Image");
        midFloat=initChildButton(buttonsPane,"mid.png","",false,"Center Float the Image");
        rightFloat=initChildButton(buttonsPane,"right.png","",false,"Right Float the Image");
        scn.add(buttonsPane,1,0);
        scn.add(heightPane,2,0);
        scn.add(imgSelect,0,0);
        scn.add(widthPane,2,1);
        scn.getStyleClass().add("dialog");
        imgSelect.getStyleClass().add("dialog");
        buttonsPane.getStyleClass().add("dialog");
        imgScreen= new Scene(scn);
        imgScreen.getStylesheets().add("eportfoliomaker/style/Style.css");
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
    
    private Button initChildButton(Pane toolbar,String iconFileName,String cssClass,boolean disabled,String toolTip) {
        
	String imagePath = "file:" + "./images/icons/" + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(toolTip);
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;    }
}
