package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Decomptep;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Devis;
import sn.accelsolution.entities.Marche;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Newfacture.class)
public class Newfacture_ { 

    public static volatile ListAttribute<Newfacture, Decomptep> decomptepList;
    public static volatile ListAttribute<Newfacture, Notification> notificationList;
    public static volatile SingularAttribute<Newfacture, Devis> idDevis;
    public static volatile SingularAttribute<Newfacture, Marche> idMarche;
    public static volatile SingularAttribute<Newfacture, String> numeroNewfacture;
    public static volatile ListAttribute<Newfacture, Detaillenewfacture> detaillenewfactureList;
    public static volatile SingularAttribute<Newfacture, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Newfacture, String> etat;
    public static volatile ListAttribute<Newfacture, Marche> marcheList;
    public static volatile SingularAttribute<Newfacture, String> mintantlettreNewfacture;
    public static volatile SingularAttribute<Newfacture, String> dateNewfacture;
    public static volatile SingularAttribute<Newfacture, String> motif;
    public static volatile SingularAttribute<Newfacture, Integer> idNewfacture;

}