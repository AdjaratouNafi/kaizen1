package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.ProspectFacture;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Facture.class)
public class Facture_ { 

    public static volatile SingularAttribute<Facture, String> dateechance;
    public static volatile SingularAttribute<Facture, Client> idClient;
    public static volatile SingularAttribute<Facture, String> numeroFacture;
    public static volatile SingularAttribute<Facture, String> observation;
    public static volatile SingularAttribute<Facture, String> modepaiment;
    public static volatile ListAttribute<Facture, ProspectFacture> prospectFactureList;
    public static volatile SingularAttribute<Facture, Integer> idFacture;

}