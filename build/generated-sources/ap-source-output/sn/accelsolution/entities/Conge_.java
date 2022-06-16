package sn.accelsolution.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Conge.class)
public class Conge_ { 

    public static volatile SingularAttribute<Conge, Integer> idConge;
    public static volatile SingularAttribute<Conge, Date> dateDebut;
    public static volatile SingularAttribute<Conge, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Conge, Date> dateFin;
    public static volatile SingularAttribute<Conge, String> motifConge;

}