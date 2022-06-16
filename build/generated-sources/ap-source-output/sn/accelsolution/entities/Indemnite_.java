package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Demande;
import sn.accelsolution.entities.UserIndemnite;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Indemnite.class)
public class Indemnite_ { 

    public static volatile SingularAttribute<Indemnite, String> etatIndemnite;
    public static volatile ListAttribute<Indemnite, UserIndemnite> userIndemniteList;
    public static volatile ListAttribute<Indemnite, Demande> demandeList;
    public static volatile SingularAttribute<Indemnite, Integer> idIndemnite;
    public static volatile SingularAttribute<Indemnite, String> libelleIndemnite;
    public static volatile SingularAttribute<Indemnite, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Indemnite, String> dateCreationIndemnite;
    public static volatile SingularAttribute<Indemnite, String> effet;
    public static volatile SingularAttribute<Indemnite, String> frais;

}