package web;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class LibraryImpl implements Library {
    /*private Utility states;
    public LibraryImpl(){
        states=new Utility();
        states.loadData();
    }
    @WebMethod
    public String getCapital(String stateName) {
        return states.getState(stateName).getCapital();
    }
    @WebMethod
    public String getLanguages(String stateName) {
        return states.getState(stateName).getLanguages();
    }
    @WebMethod
    public String getAirports(String stateName) {
        return states.getState(stateName).getAirports();
    }
    @WebMethod
    public int getDistricts(String stateName) {
        return states.getState(stateName).getDistricts();
    }
    @WebMethod
    public String getPlacesToVisit(String stateName) {
        return states.getState(stateName).getPlacesToVisit();
    }
    @WebMethod
    public String getInterestingFacts(String stateName) {
        return states.getState(stateName).getInterestingFacts();
    }*/
    @WebMethod
    public boolean logIn(String login, String haslo) { return true; }
}
