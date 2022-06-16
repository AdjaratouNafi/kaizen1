/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.io.Serializable;

/**
 *
 * @author DV7
 */
public class Privillege implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public Privillege(String name) {     

        this.name = name;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    @Override

    public String toString() {

        return name;

    }

}
