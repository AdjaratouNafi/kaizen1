package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Actionmenu;
import sn.accelsolution.entities.Droitacces;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile ListAttribute<Menu, Actionmenu> actionmenuList;
    public static volatile SingularAttribute<Menu, Integer> idMenu;
    public static volatile SingularAttribute<Menu, String> libelemenu;
    public static volatile SingularAttribute<Menu, Droitacces> idPrivilege;

}