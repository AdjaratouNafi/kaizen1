package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Entrepot;
import sn.accelsolution.entities.Stock;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Sortiestock.class)
public class Sortiestock_ { 

    public static volatile SingularAttribute<Sortiestock, Stock> idStock;
    public static volatile SingularAttribute<Sortiestock, Chantier> idChantier;
    public static volatile SingularAttribute<Sortiestock, Entrepot> idEntrepot;
    public static volatile SingularAttribute<Sortiestock, Integer> idSortiestock;
    public static volatile SingularAttribute<Sortiestock, String> pu;
    public static volatile SingularAttribute<Sortiestock, String> dateSortiestock;
    public static volatile SingularAttribute<Sortiestock, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Sortiestock, Integer> quantite;

}