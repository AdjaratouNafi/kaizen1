package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Prospection;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:57")
@StaticMetamodel(Prospect.class)
public class Prospect_ { 

    public static volatile SingularAttribute<Prospect, String> telephoneProspect;
    public static volatile SingularAttribute<Prospect, String> mailProspect;
    public static volatile SingularAttribute<Prospect, Integer> idProspectClient;
    public static volatile SingularAttribute<Prospect, String> adresse;
    public static volatile SingularAttribute<Prospect, String> client;
    public static volatile ListAttribute<Prospect, Prospection> prospectionList;
    public static volatile SingularAttribute<Prospect, String> nomProspect;
    public static volatile SingularAttribute<Prospect, String> bpProspect;

}