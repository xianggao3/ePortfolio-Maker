/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package eportfoliomaker.controller;

import eportfoliomaker.model.Paragraph;
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

public class fontDialog extends Stage{
    Paragraph ep;
    public fontDialog(Paragraph p){
        ep=p;
        List<String> choices = new ArrayList<>();
        choices.add("SAMPLE FONT A");
        choices.add("SAMPLE FONT B");
        choices.add("SAMPLE FONT C");
        choices.add("SAMPLE FONT D");
        choices.add("SAMPLE FONT E");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
        dialog.setTitle("Fonts");
        dialog.setHeaderText("Text Font Choices");
        dialog.setContentText("Choose your  Font:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
           ep.setFont(result.get());
        }
    }
}
*/