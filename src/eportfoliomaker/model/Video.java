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
    String videoH;
    String videoW;

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

    public String getVideoH() {
        return videoH;
    }

    public void setVideoH(String videoH) {
        this.videoH = videoH;
    }

    public String getVideoW() {
        return videoW;
    }

    public void setVideoW(String videoW) {
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
        videoH="455";
        videoW="810";
    }
}
