/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.view;

import eportfoliomaker.model.Component;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.ListComp;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.SlideShowModel;
import eportfoliomaker.model.Video;
import eportfoliomaker.slideshow.ErrorHandler;
import eportfoliomaker.slideshow.LanguagePropertyType;
import java.io.File;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.SLASH;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author xgao3
 */
public  class ComponentEditView extends HBox{

    
    //component types
    Paragraph p;
    ListComp list;
    Img img;
    Video video;
    SlideShowModel ss;
    
    Label text= new Label();
    Label text2= new Label();
    ImageView imgview= new ImageView();
    MediaView movieView= new MediaView();
    
    
    
    ComponentEditView( Component component) {
        loadComp(component);
    }
    

    public void loadComp(Component comp) {
        if(comp.getType()=="p"){
            p = (Paragraph)comp;
            text.setText(p.getText());
            getChildren().add(text);
            
        }else if(comp.getType()=="list"){
            list=(ListComp)comp;
            text.setText(list.getBullets());
            getChildren().add(text);
            
        }else if(comp.getType()=="slideshow"){
            
            
        }else if(comp.getType()=="img"){
            img=(Img)comp;
            String imagePath = img.getImgPath() + SLASH + img.getImgFileName();
            File file = new File(imagePath);
            try {
                // GET AND SET THE IMAGE
                URL fileURL = file.toURI().toURL();
                Image imgImage = new Image(fileURL.toExternalForm());
                imgview.setImage(imgImage);

                // AND RESIZE IT
                double scaledWidth = 200;
                double perc = scaledWidth / imgImage.getWidth();
                double scaledHeight = imgImage.getHeight() * perc;
                imgview.setFitWidth(scaledWidth);
                imgview.setFitHeight(scaledHeight);
            } catch (Exception e) {
                ErrorHandler eH = new ErrorHandler(null);
                eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
            }
            getChildren().add(imgview);
            
        }else if(comp.getType()=="video"){
            video=(Video)comp;
            String imagePath = video.getVideoPath() + SLASH +video.getVideoFileName();
            File file = new File(imagePath);
            try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Media moviecomp = new Media(fileURL.toExternalForm());
            MediaPlayer mplayer = new MediaPlayer(moviecomp);
            
	    movieView.setMediaPlayer(mplayer);
	    
	    // AND RESIZE IT
	    double scaledWidth = 350;
	    double perc = scaledWidth / moviecomp.getWidth();
	    double scaledHeight = moviecomp.getHeight() * perc;
	    movieView.setFitWidth(scaledWidth);
	    movieView.setFitHeight(scaledHeight);
            } catch (Exception e) {
                ErrorHandler eH = new ErrorHandler(null);
                eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
            }
            
        }
    }
}
