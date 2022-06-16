package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Assurer;
import sn.accelsolution.entities.DetailleAssurance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Assureur.class)
public class Assureur_ { 

    public static volatile SingularAttribute<Assureur, String> telephoneAssureur;
    public static volatile ListAttribute<Assureur, DetailleAssurance> detailleAssuranceList;
    public static volatile SingularAttribute<Assureur, Integer> idAssureur;
    public static volatile SingularAttribute<Assureur, String> adresseAssureur;
    public static volatile SingularAttribute<Assureur, String> bpAssureur;
    public static volatile SingularAttribute<Assureur, String> nomAssureur;
    public static volatile SingularAttribute<Assureur, String> mailAssureur;
    public static volatile ListAttribute<Assureur, Assurer> assurerList;

}