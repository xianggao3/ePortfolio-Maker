/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class fontDialog extends Stage{
    public fontDialog(){
        List<String> choices = new ArrayList<>();
        choices.add("SAMPLE FONT A");
        choices.add("SAMPLE FONT B");
        choices.add("SAMPLE FONT C");
        choices.add("SAMPLE FONT D");
        choices.add("SAMPLE FONT E");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
        dialog.setTitle("Fonts");
        dialog.setHeaderText("Component Font Choices");
        dialog.setContentText("Choose your Component Font:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }
    }
}