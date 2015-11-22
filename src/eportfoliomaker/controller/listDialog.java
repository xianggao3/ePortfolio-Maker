/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.view.ePortfolioAppMakerView;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class listDialog extends Stage{
    private ePortfolioAppMakerView ui;
    
    VBox listbox;
    Label listLabel;
    Scene listScene;
    Button OKButton;
    Button cancelButton;

    public listDialog(Stage primaryStage){
        initOwner(primaryStage);
        listbox = new VBox();
        listLabel = new Label("List Header:");
        TextField listHeader= new TextField();
        Label listBullets=new Label("Bullets");
        
        TextArea bulletArea = new TextArea();
        bulletArea.setOnKeyPressed(e->{
            if (e.getCode().equals(KeyCode.ENTER))
            {
                  bulletArea.appendText(" • ");
            }
        });
        
        listbox.getChildren().addAll(listLabel,listHeader,listBullets,bulletArea);
        listScene = new Scene(listbox);
        this.setScene(listScene);
        showAndWait();
    }
}
