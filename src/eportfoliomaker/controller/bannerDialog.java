/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.view.PageEditView;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author xgao3
 */
public class bannerDialog {
    
    ePortfolioModel model;
    ePortfolioAppMakerView ui;
    PageEditView pages;
    public bannerDialog(){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Banner Image");
    alert.setHeaderText("Select a Banner Image");

    ButtonType b1 = new ButtonType("Open Image");
    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(b1, buttonTypeCancel);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == b1){
            processSelectImage();
    }
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
	    String fileName = file.getName();
	    model.getSelectedPage().setBanner(path+fileName);
	    pages.updateSlideImage();
	    ui.updateFileToolbarControls(false);//is this right? am i supposed to make a pageeditview and a apmakerview in this class?
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
