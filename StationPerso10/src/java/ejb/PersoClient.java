package ejb;

import ejb.StationRemote;

import javax.naming.Context;
import javax.naming.InitialContext;

public class PersoClient {
    public static StationRemote lookUp()throws Exception{
        try{
            Context c = new InitialContext();
            return (StationRemote)c.lookup("java:global/station/StationBean!ejb.StationRemote");
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
