package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Bailleur;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Marche;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Decompte.class)
public class Decompte_ { 

    public static volatile SingularAttribute<Decompte, String> retenuGaranti;
    public static volatile SingularAttribute<Decompte, String> periodeConcerne;
    public static volatile SingularAttribute<Decompte, Integer> idDecompte;
    public static volatile SingularAttribute<Decompte, String> depot;
    public static volatile SingularAttribute<Decompte, String> remiseAvantDemarage;
    public static volatile SingularAttribute<Decompte, String> netTotalAPaye;
    public static volatile SingularAttribute<Decompte, Client> idClient;
    public static volatile SingularAttribute<Decompte, Bailleur> idBailleur;
    public static volatile SingularAttribute<Decompte, Marche> idMarche;
    public static volatile SingularAttribute<Decompte, String> montantExecution;
    public static volatile SingularAttribute<Decompte, String> montantmarche;
    public static volatile SingularAttribute<Decompte, String> avancesurappro;
    public static volatile SingularAttribute<Decompte, String> partEnFf;
    public static volatile SingularAttribute<Decompte, String> montantDecompetCfa;
    public static volatile SingularAttribute<Decompte, String> netApaye;
    public static volatile SingularAttribute<Decompte, String> montantdemarage;
    public static volatile SingularAttribute<Decompte, String> dateDecompte;
    public static volatile SingularAttribute<Decompte, String> partEnCfa;
    public static volatile SingularAttribute<Decompte, String> montantNetAPaye;
    public static volatile SingularAttribute<Decompte, String> payementDirect;

}