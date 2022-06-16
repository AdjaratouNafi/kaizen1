package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Detailleexpressionbesoin;
import sn.accelsolution.entities.Notification;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:57")
@StaticMetamodel(Expressionbesoin.class)
public class Expressionbesoin_ { 

    public static volatile SingularAttribute<Expressionbesoin, String> nivovalidation;
    public static volatile ListAttribute<Expressionbesoin, Notification> notificationList;
    public static volatile SingularAttribute<Expressionbesoin, Integer> idExpression;
    public static volatile SingularAttribute<Expressionbesoin, String> numeroExpression;
    public static volatile SingularAttribute<Expressionbesoin, Chantier> idChantier;
    public static volatile SingularAttribute<Expressionbesoin, String> montantlettre;
    public static volatile ListAttribute<Expressionbesoin, Commande> commandeList;
    public static volatile SingularAttribute<Expressionbesoin, String> dateExpression;
    public static volatile SingularAttribute<Expressionbesoin, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Expressionbesoin, String> motif;
    public static volatile SingularAttribute<Expressionbesoin, String> etat;
    public static volatile ListAttribute<Expressionbesoin, Detailleexpressionbesoin> detailleexpressionbesoinList;

}