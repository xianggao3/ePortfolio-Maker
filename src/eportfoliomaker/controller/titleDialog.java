/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.view.PageEditView;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class titleDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel ePortfolio;
    TextInputDialog dialog;
    
    public titleDialog(ePortfolioAppMakerView gui,ePortfolioModel eP){
        ui=gui;
        ePortfolio=eP;
        
        ui.reloadPagePane();
        dialog = new TextInputDialog("New ePortfolio Page");
        dialog.setTitle("Change Title");
        dialog.setHeaderText("Enter your ePortfolio Page's Title");
        dialog.setContentText("Please enter your Title:");
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ePortfolio.getSelectedPage().setTitle(result.get());
        }
        ui.reloadPagePane();
    }
}
