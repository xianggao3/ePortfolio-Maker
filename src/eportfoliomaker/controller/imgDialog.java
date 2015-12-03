/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Img;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.SLASH;
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
    TextField caption= new TextField();
    HBox captionPane= new HBox();
    ImageView imgView;
    Image selectedImg;
    String path;
    String fileName;
    String orientationPreference;
    
    public imgDialog(Stage primaryStage,Img pToEdi){
        initOwner(primaryStage);
        imgSelect.setOnAction(e->{
            try {
                processSelectImage();
            } catch (MalformedURLException ex) {
                Logger.getLogger(imgDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        imgView= new ImageView (selectedImg);
        heightPane.getChildren().addAll(new Label("Height: "),height,new Label("px"));
        widthPane.getChildren().addAll(new Label("Width: "),width,new Label("px"));
        captionPane.getChildren().addAll(new Label("Caption: "),caption);
        buttonsPane.getChildren().addAll(new Label("Image Orientation: "));
        leftFloat=initChildButton(buttonsPane,"left.png","",false,"Left Float the Image");
        midFloat=initChildButton(buttonsPane,"mid.png","",false,"No Float Preference");
        rightFloat=initChildButton(buttonsPane,"right.png","",false,"Right Float the Image");
        leftFloat.setOnMouseReleased(e->{
            orientationPreference="left";
        });
        midFloat.setOnMouseReleased(e->{
            orientationPreference="none";
        });
        leftFloat.setOnMouseReleased(e->{
            orientationPreference="right";
        });
        
        scn.add(buttonsPane,1,1);
        scn.add(heightPane,2,0);
        scn.add(imgSelect,0,0);
        scn.add(imgView, 0, 1);
        scn.add(widthPane,2,1);
        scn.add(captionPane,1,0);
        Button OKButton= new Button("OK");
        scn.add(OKButton, 2,2);
        OKButton.setOnMouseReleased(e->{
            pToEdi.setCaption(caption.getText());
            pToEdi.setImgFileName(fileName);
            pToEdi.setImgPath(path);
            pToEdi.setImgH(Double.parseDouble(height.getText()));
            pToEdi.setImgW(Double.parseDouble(width.getText()));
            pToEdi.setOrientation(orientationPreference);
        });
        
        scn.getStyleClass().add("dialog");
        imgSelect.getStyleClass().add("dialog");
        buttonsPane.getStyleClass().add("dialog");
        imgScreen= new Scene(scn);
        imgScreen.getStylesheets().add("eportfoliomaker/style/Style.css");
        this.setTitle("Image");
        this.setScene(imgScreen);
        showAndWait();
    }
    
    public void processSelectImage() throws MalformedURLException {
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
	     path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	     fileName = file.getName();
             String fullName = path+fileName;
             System.out.println(""+path+fileName);
             String imagePath = path + SLASH +fileName;
            File file2 = new File(imagePath);
                URL fileURL = file2.toURI().toURL();
                selectedImg = new Image(fileURL.toExternalForm());
            
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
