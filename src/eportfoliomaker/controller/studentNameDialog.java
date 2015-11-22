/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class studentNameDialog extends Stage{

    TextInputDialog dialog;
    public studentNameDialog(){
        dialog = new TextInputDialog("Michael Apple");
        dialog.setTitle("Student Name");
        dialog.setHeaderText("Enter your Name");
        dialog.setContentText("What is your name?");
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }
    }
}
