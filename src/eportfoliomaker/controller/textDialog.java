/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class textDialog extends Stage{
    private ePortfolioAppMakerView ui;
    ePortfolioModel model;
    VBox textbox;
    Label textLabel;
    Scene textScene;
    ComboBox textFont;
    Button OKButton;
    Button cancelButton;
    TextArea textArea;
    GridPane topPane;

    public textDialog(ePortfolioAppMakerView ui,Paragraph pToEdit){
        this.ui=ui;
        initOwner(ui.getPrimaryStage());
        
        ObservableList<String> fonts = FXCollections.observableArrayList(
        "SAMPLE FONT 1",
        "SAMPLE FONT 2",
        "SAMPLE FONT 3",
        "SAMPLE FONT 4",
        "SAMPLE FONT 5"
        );
        textbox = new VBox();
        
        topPane = new GridPane();
        Label font = new Label("Paragraph Font:");
        textFont= new ComboBox(fonts);
        Button addTextHyperlinkButton=initChildButton(topPane,"hyperlink.png","page_edit_toolbar_icons",false,"Set a Hyperlink");
        topPane.add(font, 0,0);
        topPane.add(textFont, 0,1);
        textFont.getSelectionModel().select(pToEdit.getFont());
        topPane.add(new Label("     Set a Hyperlink: "), 1, 0);
        
        textLabel=new Label("Paragraph:");
        textArea = new TextArea();
        textArea.setText(pToEdit.getText());
        OKButton= new Button("OK");
        OKButton.setOnMouseReleased(e->{
           pToEdit.setText(textArea.getText());
           pToEdit.setFont(textFont.getValue().toString());
           
                ui.getePortfolio().getSelectedPage().addComp(pToEdit);
           this.close();
           
        });
        textbox.getChildren().addAll(topPane,textLabel,textArea,OKButton);
        
        textbox.getStyleClass().add("dialog");
        
        textScene = new Scene(textbox);
        textScene.getStylesheets().add("eportfoliomaker/style/Style.css");
        this.setScene(textScene);
        showAndWait();
    }
    
    private Button initChildButton(GridPane toolbar,String iconFileName,String cssClass,boolean disabled,String toolTip) {
        
	String imagePath = "file:" + "./images/icons/" + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(toolTip);
	button.setTooltip(buttonTooltip);
	toolbar.add(button, 2,1);
	return button;    }
    
}
