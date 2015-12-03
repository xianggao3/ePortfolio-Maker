/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Img;
import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.ePortfolioModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import eportfoliomaker.model.ListComp;
import eportfoliomaker.model.SlideShowModel;
import eportfoliomaker.model.Video;


/**
 *
 * @author xgao3
 */
public class addCompDialog extends Stage{
    
    public ePortfolioModel ePortfolio;
    
    public addCompDialog(ePortfolioModel eP){
        ePortfolio=eP;
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
            
            if (result.get()=="Text"){
                
                Paragraph p = new Paragraph();
                textDialog tD = new textDialog(this,p);
                ePortfolio.getSelectedPage().addComp(p);
            }else if(result.get()=="List"){
                ListComp list = new ListComp();
                ePortfolio.getSelectedPage().addComp(list);
            }else if(result.get()=="Image"){
                Img img = new Img();
                ePortfolio.getSelectedPage().addComp(img);
            }else if(result.get()=="Video"){
                Video vid = new Video();
                ePortfolio.getSelectedPage().addComp(vid);
            }else if(result.get()=="Slideshow"){
                //SlideShowModel ss = new SlideShowModel();
                //ui.getSelectedPage().addComp(ss);
            }
           
        }
        
    }
}
