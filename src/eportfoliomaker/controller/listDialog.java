/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.ListComp;
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

    public listDialog(Stage primaryStage,ListComp ltoEdit){        
        initOwner(primaryStage);
        listbox = new VBox();
        Label listBullets=new Label("Bullets:");
        
        TextArea bulletArea = new TextArea();
        bulletArea.setText(ltoEdit.getBullets());
        bulletArea.setOnKeyPressed(e->{
            if (e.getCode().equals(KeyCode.ENTER))
            {
                  bulletArea.appendText(" • ");
            }
        });
        OKButton= new Button("OK");
        OKButton.setOnMouseReleased(e->{
            ltoEdit.setBullets(bulletArea.getText());
            
               ui.getePortfolio().getSelectedPage().addComp(ltoEdit);
            this.close();
        });
        
        listbox.getChildren().addAll(listBullets,bulletArea,OKButton);
        
        listbox.getStyleClass().add("dialog");
        
        listScene = new Scene(listbox);
        listScene.getStylesheets().add("eportfoliomaker/style/Style.css");
        this.setScene(listScene);
        showAndWait();
    }
}
