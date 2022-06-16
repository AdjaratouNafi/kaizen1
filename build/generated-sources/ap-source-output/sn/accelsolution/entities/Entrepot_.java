package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Commande;
import sn.accelsolution.entities.Historiquelivraison;
import sn.accelsolution.entities.Sortiestock;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Entrepot.class)
public class Entrepot_ { 

    public static volatile ListAttribute<Entrepot, Sortiestock> sortiestockList;
    public static volatile SingularAttribute<Entrepot, Integer> idEntrepot;
    public static volatile ListAttribute<Entrepot, Commande> commandeList;
    public static volatile ListAttribute<Entrepot, Stock> stockList;
    public static volatile SingularAttribute<Entrepot, String> adresse;
    public static volatile SingularAttribute<Entrepot, String> telephone;
    public static volatile SingularAttribute<Entrepot, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Entrepot, String> nom;
    public static volatile ListAttribute<Entrepot, Historiquelivraison> historiquelivraisonList;

}