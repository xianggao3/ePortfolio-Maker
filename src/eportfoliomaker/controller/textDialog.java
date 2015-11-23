/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class textDialog extends Stage{
    private ePortfolioAppMakerView ui;
    
    VBox textbox;
    Label textLabel;
    Scene textScene;
    ComboBox textFont;
    Button OKButton;
    Button cancelButton;
    TextArea textArea;

    public textDialog(Stage primaryStage){
        initOwner(primaryStage);
        
        ObservableList<String> fonts = FXCollections.observableArrayList(
        "SAMPLE FONT 1",
        "SAMPLE FONT 2",
        "SAMPLE FONT 3",
        "SAMPLE FONT 4",
        "SAMPLE FONT 5"
        );
        textbox = new VBox();
        
        Label font = new Label("Paragraph Font:");
        textFont= new ComboBox(fonts);
        
        textLabel=new Label("Paragraph:");
        textArea = new TextArea();
        OKButton= new Button("OK");
        textbox.getChildren().addAll(font, textFont,textLabel,textArea,OKButton);
        
        textbox.getStyleClass().add("dialog");
        
        textScene = new Scene(textbox);
        textScene.getStylesheets().add("eportfoliomaker/style/Style.css");
        this.setScene(textScene);
        showAndWait();
    }
}
