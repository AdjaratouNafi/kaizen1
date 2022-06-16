package sn.accelsolution.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sn.accelsolution.entities.Chantier;
import sn.accelsolution.entities.Prestataire;
import sn.accelsolution.entities.Prestataireprim;
import sn.accelsolution.entities.Ressource;
import sn.accelsolution.entities.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-25T17:57:56")
@StaticMetamodel(Detaillechantier.class)
public class Detaillechantier_ { 

    public static volatile SingularAttribute<Detaillechantier, String> dateAlocation;
    public static volatile SingularAttribute<Detaillechantier, Prestataireprim> idprestatairePrim;
    public static volatile SingularAttribute<Detaillechantier, Integer> idDetailleChantier;
    public static volatile SingularAttribute<Detaillechantier, Integer> qt;
    public static volatile SingularAttribute<Detaillechantier, Chantier> idChantier;
    public static volatile SingularAttribute<Detaillechantier, Prestataire> idPrestataire;
    public static volatile SingularAttribute<Detaillechantier, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Detaillechantier, Ressource> idRessource;

}