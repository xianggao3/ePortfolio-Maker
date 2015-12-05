/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.model;

/**
 *
 * @author xgao3
 */
public class Video extends Component{

   
    String videoFileName;
    String src;
    Double videoH;
    Double videoW;
    String caption;
    
     public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getVideoPath() {
        return src;
    }

    public void setVideoPath(String videoPath) {
        this.src = videoPath;
    }

    public Double getVideoH() {
        return videoH;
    }

    public void setVideoH(Double videoH) {
        this.videoH = videoH;
    }

    public Double getVideoW() {
        return videoW;
    }

    public void setVideoW(Double videoW) {
        this.videoW = videoW;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Video(){
        type="video";
        videoFileName=null;
        src=null;
        videoH=455.;
        videoW=810.;
    }
}
