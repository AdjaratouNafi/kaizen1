/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util; 

import java.sql.Time;

/**
 *
 * @author DV7
 */
public class CalculeHeure {

    public CalculeHeure() {

    }

    public UtilTime difference(UtilTime startTime, UtilTime stopTime) {
        UtilTime diffTim = new UtilTime(0, 0, 0);   

        if(stopTime.seconds > startTime.seconds){
            --startTime.minutes;
            startTime.seconds += 60;
        }

        diffTim.seconds = startTime.seconds - stopTime.seconds;
        if(stopTime.minutes > startTime.minutes){
            --startTime.hours;
            startTime.minutes += 60;
        }

        diffTim.minutes = startTime.minutes - stopTime.minutes;
        diffTim.hours = startTime.hours - stopTime.hours;

        return(diffTim);
    }

}
