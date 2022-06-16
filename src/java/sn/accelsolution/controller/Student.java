/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.controller;

/**
 *
 * @author DV7
 */
public class Student {
    private String Id;
    private String name;
    private String adress;
    
    public Student(){
      
    } 

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Student{" + "Id=" + Id + ", name=" + name + ", adress=" + adress + '}';
    }
    
    
}
