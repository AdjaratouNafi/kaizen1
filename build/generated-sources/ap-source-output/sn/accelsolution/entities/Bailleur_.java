package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Decompte;
import sn.accelsolution.entities.Marche;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Bailleur.class)
public class Bailleur_ { 

    public static volatile SingularAttribute<Bailleur, Integer> idBailleur;
    public static volatile SingularAttribute<Bailleur, String> telBailleur;
    public static volatile SingularAttribute<Bailleur, String> mailBailleur;
    public static volatile SingularAttribute<Bailleur, String> adresseBailleur;
    public static volatile ListAttribute<Bailleur, Marche> marcheList;
    public static volatile ListAttribute<Bailleur, Decompte> decompteList;
    public static volatile SingularAttribute<Bailleur, String> nomBailleur;
    public static volatile SingularAttribute<Bailleur, String> bpBailleur;

}