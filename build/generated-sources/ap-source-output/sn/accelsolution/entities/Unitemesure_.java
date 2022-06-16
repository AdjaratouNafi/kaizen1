package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Detailledecomptep;
import sn.accelsolution.entities.Detailledevis;
import sn.accelsolution.entities.Detaillenewfacture;
import sn.accelsolution.entities.Prix;
import sn.accelsolution.entities.Stock;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Unitemesure.class)
public class Unitemesure_ { 

    public static volatile SingularAttribute<Unitemesure, String> abreviation;
    public static volatile ListAttribute<Unitemesure, Stock> stockList;
    public static volatile SingularAttribute<Unitemesure, Integer> idUnitemesure;
    public static volatile SingularAttribute<Unitemesure, String> libelle;
    public static volatile ListAttribute<Unitemesure, Detailledecomptep> detailledecomptepList;
    public static volatile ListAttribute<Unitemesure, Prix> prixList;
    public static volatile ListAttribute<Unitemesure, Detaillenewfacture> detaillenewfactureList;
    public static volatile ListAttribute<Unitemesure, Detailledevis> detailledevisList;

}