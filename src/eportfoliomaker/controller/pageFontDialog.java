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
public class pageFontDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel eP;
    public pageFontDialog(ePortfolioAppMakerView gui,ePortfolioModel ep){
        eP=ep;
        ui=gui;
        List<String> choices = new ArrayList<>();
        choices.add("OpenSans");
        choices.add("Montserrat");
        choices.add("Raleway");
        choices.add("Slabo");
        choices.add("Ubuntu");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("OpenSans", choices);
        dialog.setTitle("Page Fonts");
        dialog.setHeaderText("Page Font Choices");
        dialog.setContentText("Choose your Page Font:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){      
            eP.getSelectedPage().setPageFont(result.get());
        }
        ui.reloadPagePane();
    }
}