package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Client;
import sn.accelsolution.entities.Prospect;
import sn.accelsolution.entities.ProspectFacture;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:57")
@StaticMetamodel(Prospection.class)
public class Prospection_ { 

    public static volatile SingularAttribute<Prospection, Client> idClient;
    public static volatile SingularAttribute<Prospection, Integer> idProspect;
    public static volatile SingularAttribute<Prospection, String> descriptionProspection;
    public static volatile SingularAttribute<Prospection, Prospect> idProspectClient;
    public static volatile ListAttribute<Prospection, ProspectFacture> prospectFactureList;
    public static volatile SingularAttribute<Prospection, String> dateProspection;

}