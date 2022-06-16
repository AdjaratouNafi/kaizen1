/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

/**
 *
 * @author DV7
 */
public class EntetePath {

    private String url;

    public EntetePath() {
    }

    public String getUrl() {
        //url = "C:\\Users\\DV7\\Documents\\NetBeansProjects\\Kaisen\\src\\java\\sn\\accelsolution\\util\\tete.png";
        url = "http://ec2-3-16-11-57.us-east-2.compute.amazonaws.com/InitLogo/tete.png";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    } 
    
    public String getUrlAccel() {
        //url = "C:\\Users\\DV7\\Documents\\NetBeansProjects\\Kaisen\\src\\java\\sn\\accelsolution\\util\\logoAccel.png";
        url = "http://ec2-3-16-11-57.us-east-2.compute.amazonaws.com/InitLogo/logoAccel.png";
        return url;
    }

}
