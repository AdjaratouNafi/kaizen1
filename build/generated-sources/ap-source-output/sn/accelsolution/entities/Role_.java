package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Droitacces;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, Integer> idRole;
    public static volatile ListAttribute<Role, Droitacces> droitaccesList;
    public static volatile SingularAttribute<Role, String> libelleRole;
    public static volatile SingularAttribute<Role, String> levelRole;
    public static volatile ListAttribute<Role, Utilisateur> utilisateurList;

}