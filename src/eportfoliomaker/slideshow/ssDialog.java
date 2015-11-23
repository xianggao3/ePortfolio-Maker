/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.slideshow;

import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class ssDialog extends Stage{
    SlideShowMakerView ui;
    public ssDialog(){
        ui= new SlideShowMakerView();
        ui.startUI(this, "Slideshow");
    }
}
