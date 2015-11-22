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
public class addCompDialog extends Stage{
    public addCompDialog(){
        List<String> choices = new ArrayList<>();
        choices.add("Text");
        choices.add("List");
        choices.add("Image");
        choices.add("Video");
        choices.add("Slideshow");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", choices);
        dialog.setTitle("Components");
        dialog.setHeaderText("Component will be placed at the bottom of your current page");
        dialog.setContentText("Add a Component:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }
    }
}
