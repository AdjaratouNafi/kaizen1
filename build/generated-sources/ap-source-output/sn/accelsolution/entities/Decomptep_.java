package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Newfacture;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Decomptep.class)
public class Decomptep_ { 

    public static volatile SingularAttribute<Decomptep, String> numeroDecomptep;
    public static volatile ListAttribute<Decomptep, Notification> notificationList;
    public static volatile SingularAttribute<Decomptep, String> montantlettre;
    public static volatile SingularAttribute<Decomptep, String> dateDecomptep;
    public static volatile ListAttribute<Decomptep, Detailledecomptep> detailledecomptepList;
    public static volatile SingularAttribute<Decomptep, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Decomptep, String> motif;
    public static volatile SingularAttribute<Decomptep, Integer> idDecomptep;
    public static volatile SingularAttribute<Decomptep, String> etat;
    public static volatile SingularAttribute<Decomptep, Newfacture> idNewfacture;

}