package pompe;

import bean.CGenUtil;
import bean.ClassMAPTable;
import ejb.Request;
import ejb.StationRemote;
import utilitaire.UtilDB;
import vente.Vente;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Registre extends ClassMAPTable {
    String id;
    double compteur;
    LocalDateTime date;
    String idPompiste;
    String idPompe;

    public Registre(){
        super.setNomTable("registre");
    }

    public Registre(String compteur,String date,String idPompiste,String idPompe)throws Exception{
        this.setCompteur(compteur);
        this.setDate(date);
        this.setIdPompiste(idPompiste);
        this.setIdPompe(idPompe);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCompteur() {
        return compteur;
    }

    public void setDate(String date)throws Exception{
        this.setDate(LocalDateTime.parse(date));
    }

    public void setCompteur(String compteur) throws Exception{
        this.setCompteur(Double.parseDouble(compteur));
    }

    public void setCompteur(double compteur) throws Exception{
        if(compteur>0){
            this.compteur = compteur;
            return;
        }
        throw new Exception("Le compteur ne peut pas être négaitf");
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIdPompiste() {
        return idPompiste;
    }

    public void setIdPompiste(String idPompiste) {
        this.idPompiste = idPompiste;
    }

    public String getIdPompe() {
        return idPompe;
    }

    public void setIdPompe(String idPompe) {
        this.idPompe = idPompe;
    }

    public static boolean isExiting(Registre[] registres){
        return registres.length%2==0;
    }

    public void insertCompteurAndFacture()throws Exception{
        Connection connection=null;
        try{
            connection=new UtilDB().GetConn();
            Registre[] registres=(Registre[]) CGenUtil.rechercher(new Registre(),"select compteur from registre where idPompiste='"+this.idPompiste+"' order by date desc");
            if(Registre.isExiting(registres)){
                double depense=this.getCompteur()-registres[0].getCompteur();
                //Vente vente=Registre.createVente();

            }
            this.insertToTable(connection);
        }
        catch (Exception ex){
            throw ex;
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
    }

    public Vente createVente()throws Exception{
        Vente vente=new Vente();
        vente.setDesignation("Vente de carburant");
        vente.setIdMagasin("PHARM00246");
        vente.setRemarque("Mahafinaritra");
        vente.setIdClient("CLI000054");

        return vente;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RGSTR", "getseqregistre");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
    
    public static HashMap<String,Object[]> getPompeAndPompiste(StationRemote stationRemote) throws Exception{
        HashMap<String,Request> requetes=new HashMap<String,Request>();
        requetes.put("pompes",new Request(new Pompe(),"select * from pompe"));
        requetes.put("pompiste", new Request(new PompisteLib(),"select * from pompisteLib"));
        return stationRemote.selectAll(requetes);
    }
}