/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class colorDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel eP;
    public colorDialog(ePortfolioAppMakerView gui,ePortfolioModel ep){
        ui=gui;
        eP=ep;
        List<String> choices = new ArrayList<>();
        choices.add("DeepSea");
        choices.add("Snowflake");
        choices.add("Pandora");
        choices.add("DarkGlow");
        choices.add("Wooden");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("DeepSea", choices);
        dialog.setTitle("Color Theme");
        dialog.setHeaderText("Color Theme Choices");
        dialog.setContentText("Choose your Color:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ui.getePortfolio().getSelectedPage().setColorTheme(result.get());
        }
        ui.reloadPagePane();
    }
}
