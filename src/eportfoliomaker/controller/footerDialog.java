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
public class footerDialog extends Stage{
    public footerDialog(){
        TextInputDialog dialog = new TextInputDialog("Footer");
        dialog.setTitle("Footer");
        dialog.setHeaderText("Change Footer Text");
        dialog.setContentText("Please enter your Footer Text:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }
    }
}
