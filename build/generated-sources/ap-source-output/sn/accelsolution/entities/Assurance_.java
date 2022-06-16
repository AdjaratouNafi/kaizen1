package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Assurer;
import sn.accelsolution.entities.DetailleAssurance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Assurance.class)
public class Assurance_ { 

    public static volatile ListAttribute<Assurance, DetailleAssurance> detailleAssuranceList;
    public static volatile SingularAttribute<Assurance, Integer> idAssurance;
    public static volatile SingularAttribute<Assurance, String> libelleAssurance;
    public static volatile ListAttribute<Assurance, Assurer> assurerList;

}