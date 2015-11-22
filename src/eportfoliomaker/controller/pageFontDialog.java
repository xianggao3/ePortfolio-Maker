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
public class pageFontDialog extends Stage{
    public pageFontDialog(){
        List<String> choices = new ArrayList<>();
        choices.add("SAMPLE PAGE FONT A");
        choices.add("SAMPLE PAGE FONT B");
        choices.add("SAMPLE PAGE FONT C");
        choices.add("SAMPLE PAGE FONT D");
        choices.add("SAMPLE PAGE FONT E");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
        dialog.setTitle("Page Fonts");
        dialog.setHeaderText("Page Font Choices");
        dialog.setContentText("Choose your Page Font:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }
    }
}