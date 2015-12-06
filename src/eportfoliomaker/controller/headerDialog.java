/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Header;
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
    public headerDialog(ePortfolioAppMakerView ui,Header h){
        this.ui=ui;
        TextInputDialog dialog = new TextInputDialog("Header Text");
        dialog.setTitle("Add Header");
        dialog.setHeaderText("Change Header Text");
        dialog.setContentText(h.getText());

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            h.setText(result.get());
        }
    }
}
