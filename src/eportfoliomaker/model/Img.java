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
public class Img extends Component {
    String imgFileName;
    String imgPath;
    String orientation;
    Double imgH;
    Double imgW;
    String caption;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String Orientation) {
        this.orientation = Orientation;
    }

    public Double getImgH() {
        return imgH;
    }

    public void setImgH(Double imgH) {
        this.imgH = imgH;
    }

    public Double getImgW() {
        return imgW;
    }

    public void setImgW(Double imgW) {
        this.imgW = imgW;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public Img(){
        type="img";
        imgFileName=null;
        imgPath=null;
        orientation="middle";
        imgH=60.;
        imgW=75.;
    }
}
