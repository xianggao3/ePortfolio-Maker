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
public class footerDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel eP;
    public footerDialog(ePortfolioAppMakerView gui,ePortfolioModel ep){
        eP=ep;
        ui=gui;
        TextInputDialog dialog = new TextInputDialog("Footer Text");
        dialog.setTitle("Footer");
        dialog.setHeaderText("Change Footer Text");
        dialog.setContentText("Please enter your Footer Text:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ui.getePortfolio().getSelectedPage().setFooter(result.get());
        }
        ui.reloadPagePane(eP.getSelectedPage().getPv());
    }
}
