package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Facture;
import sn.accelsolution.entities.Prospection;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(ProspectFacture.class)
public class ProspectFacture_ { 

    public static volatile SingularAttribute<ProspectFacture, String> montanthortaxe;
    public static volatile SingularAttribute<ProspectFacture, String> ttc;
    public static volatile SingularAttribute<ProspectFacture, String> pu;
    public static volatile SingularAttribute<ProspectFacture, String> observation;
    public static volatile SingularAttribute<ProspectFacture, String> puhortaxe;
    public static volatile SingularAttribute<ProspectFacture, String> montant;
    public static volatile SingularAttribute<ProspectFacture, String> remise;
    public static volatile SingularAttribute<ProspectFacture, Facture> idFacture;
    public static volatile SingularAttribute<ProspectFacture, String> dateEchance;
    public static volatile SingularAttribute<ProspectFacture, String> dateProspectFacture;
    public static volatile SingularAttribute<ProspectFacture, Prospection> idProspect;
    public static volatile SingularAttribute<ProspectFacture, Integer> idProspectFacture;
    public static volatile SingularAttribute<ProspectFacture, String> designation;
    public static volatile SingularAttribute<ProspectFacture, Integer> quantite;

}