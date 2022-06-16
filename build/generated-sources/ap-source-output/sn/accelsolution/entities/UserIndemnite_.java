package sn.accelsolution.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Indemnite;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(UserIndemnite.class)
public class UserIndemnite_ { 

    public static volatile SingularAttribute<UserIndemnite, Integer> idUserIndemnite;
    public static volatile SingularAttribute<UserIndemnite, Date> dateCreation;
    public static volatile SingularAttribute<UserIndemnite, Indemnite> idIndemnite;
    public static volatile SingularAttribute<UserIndemnite, Utilisateur> idUtilisateur;

}