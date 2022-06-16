package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.AppelOffre;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:57")
@StaticMetamodel(Detailleappeloffre.class)
public class Detailleappeloffre_ { 

    public static volatile SingularAttribute<Detailleappeloffre, String> dateaffectation;
    public static volatile SingularAttribute<Detailleappeloffre, String> tache;
    public static volatile SingularAttribute<Detailleappeloffre, AppelOffre> idAppel;
    public static volatile SingularAttribute<Detailleappeloffre, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Detailleappeloffre, Integer> idDetaille;

}