/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Header;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.ePortfolioModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;
import eportfoliomaker.model.ListComp;
import eportfoliomaker.model.SlideShowModelComponent;
import eportfoliomaker.model.Video;
import eportfoliomaker.view.ePortfolioAppMakerView;


/**
 *
 * @author xgao3
 */
public class addCompDialog extends Stage{
    ePortfolioAppMakerView ui;
    ePortfolioModel ePortfolio;
    
    public addCompDialog(ePortfolioAppMakerView ui,ePortfolioModel eP){
        this.ui=ui;
        ePortfolio=eP;
        List<String> choices = new ArrayList<>();
        choices.add("Text");
        choices.add("Header");
        choices.add("List");
        choices.add("Image");
        choices.add("Video");
        choices.add("Slideshow");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Text", choices);
        dialog.setTitle("Components");
        dialog.setHeaderText("Component will be placed at the bottom of your current page");
        dialog.setContentText("Add a Component:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            
            if (result.get()=="Text"){
                Paragraph p = new Paragraph();
                textDialog tD = new textDialog(ui,p);
            }else if(result.get()==("Header")){
                Header p = new Header();
                headerDialog hD = new headerDialog(ui,p);
            }else if(result.get()=="List"){
                ListComp list = new ListComp();
                listDialog lD= new listDialog(this,list);
            }else if(result.get()=="Image"){
                Img img = new Img();
                imgDialog i = new imgDialog(this,img);
            }else if(result.get()=="Video"){
                Video vid = new Video();
                videoDialog v = new videoDialog(this,vid);
            }else if(result.get()=="Slideshow"){
                
                SlideShowModelComponent ss = new SlideShowModelComponent();
                SlideshowMakerView ssD= new SlideshowMakerView(ui,ss);
                ePortfolio.getSelectedPage().addComp(ss);
            }
        }
        
    }
}
