package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Operation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Banque.class)
public class Banque_ { 

    public static volatile SingularAttribute<Banque, String> dateCreation;
    public static volatile ListAttribute<Banque, Operation> operationList;
    public static volatile SingularAttribute<Banque, String> soldesortie;
    public static volatile SingularAttribute<Banque, String> solde;
    public static volatile SingularAttribute<Banque, Integer> numeroBanque;
    public static volatile SingularAttribute<Banque, String> montantInitiale;

}