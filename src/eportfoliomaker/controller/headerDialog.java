/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.view.ePortfolioAppMakerView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class headerDialog extends Stage{
    
    ePortfolioAppMakerView ui;
    public headerDialog(){
        TextInputDialog dialog = new TextInputDialog("Header Text");
        dialog.setTitle("Header");
        dialog.setHeaderText("Change Header Text");
        dialog.setContentText("Please enter your Header Text:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ui.getePortfolio().getSelectedPage().setHeader(result.get());
                    
        }
    }
}
