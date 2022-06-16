package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Prestataire;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Prestataireprim.class)
public class Prestataireprim_ { 

    public static volatile SingularAttribute<Prestataireprim, Integer> idprestatairePrim;
    public static volatile SingularAttribute<Prestataireprim, String> ninea;
    public static volatile SingularAttribute<Prestataireprim, String> nomcomplet;
    public static volatile ListAttribute<Prestataireprim, Prestataire> prestataireList;
    public static volatile SingularAttribute<Prestataireprim, String> telephone;
    public static volatile ListAttribute<Prestataireprim, Detaillechantier> detaillechantierList;
    public static volatile SingularAttribute<Prestataireprim, String> corps;
    public static volatile ListAttribute<Prestataireprim, Management> managementList;

}