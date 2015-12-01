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
public class List extends Component{
    String bullets;


    public String getBullets() {
        return bullets;
    }

    public void setBullets(String Bullets) {
        this.bullets = Bullets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public List(){
        type="list";
        bullets=null;
    }
}
