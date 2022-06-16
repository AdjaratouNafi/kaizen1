package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Acompte;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Management;
import sn.accelsolution.entities.Prestataireprim;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Prestataire.class)
public class Prestataire_ { 

    public static volatile SingularAttribute<Prestataire, Prestataireprim> idprestatairePrim;
    public static volatile SingularAttribute<Prestataire, String> nomcomplet;
    public static volatile ListAttribute<Prestataire, Chantier> chantierList;
    public static volatile SingularAttribute<Prestataire, String> nature;
    public static volatile ListAttribute<Prestataire, Acompte> acompteList;
    public static volatile SingularAttribute<Prestataire, String> accord;
    public static volatile SingularAttribute<Prestataire, Integer> idPrestataire;
    public static volatile SingularAttribute<Prestataire, String> telephone;
    public static volatile SingularAttribute<Prestataire, String> corps;
    public static volatile ListAttribute<Prestataire, Management> managementList;
    public static volatile SingularAttribute<Prestataire, String> ninea;
    public static volatile SingularAttribute<Prestataire, String> echenance;
    public static volatile SingularAttribute<Prestataire, Chantier> idChantier;
    public static volatile SingularAttribute<Prestataire, String> voyant;
    public static volatile SingularAttribute<Prestataire, String> accompte;
    public static volatile ListAttribute<Prestataire, Detaillechantier> detaillechantierList;
    public static volatile SingularAttribute<Prestataire, String> reliquant;

}