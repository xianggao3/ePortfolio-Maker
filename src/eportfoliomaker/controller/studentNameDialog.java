/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class studentNameDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel model;
    TextInputDialog dialog;
    public studentNameDialog(ePortfolioAppMakerView ui,ePortfolioModel eP){
        this.ui=ui;
        model=eP;
        
        ui.reloadPagePane();
        dialog = new TextInputDialog("Michael Appleseed");
        dialog.setTitle("Student Name");
        dialog.setHeaderText("Enter your Name");
        dialog.setContentText("What is your name?");
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            eP.setStudentName(result.get());
        }
        ui.reloadPagePane();
    }
}
