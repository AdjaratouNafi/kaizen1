package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Menu;
import sn.accelsolution.entities.Role;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Droitacces.class)
public class Droitacces_ { 

    public static volatile ListAttribute<Droitacces, Menu> menuList;
    public static volatile SingularAttribute<Droitacces, Role> idRole;
    public static volatile SingularAttribute<Droitacces, String> module;
    public static volatile SingularAttribute<Droitacces, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Droitacces, Integer> idPrivilege;
    public static volatile SingularAttribute<Droitacces, Integer> niveau;

}