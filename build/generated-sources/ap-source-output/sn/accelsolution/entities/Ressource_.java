package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.DetailleCommande;
import sn.accelsolution.entities.Detaillechantier;
import sn.accelsolution.entities.Fournisseur;
import sn.accelsolution.entities.TypeRessource;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Ressource.class)
public class Ressource_ { 

    public static volatile SingularAttribute<Ressource, String> dateCreation;
    public static volatile SingularAttribute<Ressource, String> pu;
    public static volatile SingularAttribute<Ressource, Integer> qtStock;
    public static volatile SingularAttribute<Ressource, String> typeressource;
    public static volatile SingularAttribute<Ressource, String> nomRessource;
    public static volatile SingularAttribute<Ressource, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Ressource, String> etatressource;
    public static volatile ListAttribute<Ressource, DetailleCommande> detailleCommandeList;
    public static volatile SingularAttribute<Ressource, Integer> qtSeuill;
    public static volatile SingularAttribute<Ressource, String> mesrure;
    public static volatile SingularAttribute<Ressource, TypeRessource> idTypeRessource;
    public static volatile SingularAttribute<Ressource, Integer> idRessource;
    public static volatile SingularAttribute<Ressource, String> descriptionResssource;
    public static volatile ListAttribute<Ressource, Detaillechantier> detaillechantierList;

}