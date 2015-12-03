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
import javafx.scene.layout.VBox;
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
        this.getStyleClass().add("tabPane");
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
            String imagePath = img.getImgPath()+ img.getImgFileName();
//            File file = new File(imagePath);
//            try {
                // GET AND SET THE IMAGE
                //URL fileURL = file.toURI().toURL();
                //Image imgImage = new Image(fileURL.toExternalForm());
                Image imgImage= new Image("file:images/img/"+img.getImgFileName());//imagePath);
                imgview.setImage(imgImage);

                // AND RESIZE IT
                double scaledWidth = img.getImgW();
                double scaledHeight = img.getImgH();
                imgview.setFitWidth(scaledWidth);
                imgview.setFitHeight(scaledHeight);
//            } catch (Exception e) {
//                ErrorHandler eH = new ErrorHandler(null);
//                eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
//            }
                text.setText(img.getCaption());
                VBox compbox = new VBox(imgview,text);
            getChildren().addAll(compbox);
            
        }else if(comp.getType()=="video"){
            
            video=(Video)comp;
            String videoPath = video.getVideoPath()+ video.getVideoFileName();
            File file=new File("images/img/"+video.getVideoFileName());
                Media movie= new Media(file.toURI().toString());//imagePath);
                MediaPlayer mplayer = new MediaPlayer(movie);
                movieView.setMediaPlayer(mplayer);
                // AND RESIZE IT
                double scaledWidth = video.getVideoW();
                double scaledHeight = video.getVideoH();
                movieView.setFitWidth(scaledWidth);
                movieView.setFitHeight(scaledHeight);
                movieView.getMediaPlayer().play();
                text.setText(video.getCaption());
                VBox compbox = new VBox(movieView,text);
            getChildren().addAll(compbox);
        }
    }
}
