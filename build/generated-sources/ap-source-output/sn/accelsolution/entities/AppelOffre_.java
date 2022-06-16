package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Detailleappeloffre;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(AppelOffre.class)
public class AppelOffre_ { 

    public static volatile SingularAttribute<AppelOffre, String> numAppel;
    public static volatile SingularAttribute<AppelOffre, String> typeAppel;
    public static volatile SingularAttribute<AppelOffre, String> dateSoumission;
    public static volatile SingularAttribute<AppelOffre, Integer> idAppel;
    public static volatile SingularAttribute<AppelOffre, String> dateAppel;
    public static volatile ListAttribute<AppelOffre, Detailleappeloffre> detailleappeloffreList;
    public static volatile SingularAttribute<AppelOffre, String> description;
    public static volatile SingularAttribute<AppelOffre, String> etatApel;
    public static volatile SingularAttribute<AppelOffre, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<AppelOffre, String> motif;
    public static volatile SingularAttribute<AppelOffre, String> resultat;

}