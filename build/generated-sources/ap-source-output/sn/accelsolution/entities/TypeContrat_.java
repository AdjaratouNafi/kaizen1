package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Contrat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(TypeContrat.class)
public class TypeContrat_ { 

    public static volatile SingularAttribute<TypeContrat, String> libelleTypeContrat;
    public static volatile SingularAttribute<TypeContrat, String> categorie;
    public static volatile SingularAttribute<TypeContrat, Integer> idTypeContrat;
    public static volatile ListAttribute<TypeContrat, Contrat> contratList;

}