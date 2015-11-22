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
public class layoutDialog extends Stage{
    public layoutDialog(){
        List<String> choices = new ArrayList<>();
        choices.add("SAMPLE LAYOUT A");
        choices.add("SAMPLE LAYOUT B");
        choices.add("SAMPLE LAYOUT C");
        choices.add("SAMPLE LAYOUT D");
        choices.add("SAMPLE LAYOUT E");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
        dialog.setTitle("Layout");
        dialog.setHeaderText("Layout Choices");
        dialog.setContentText("Choose your layout:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }
    }
}