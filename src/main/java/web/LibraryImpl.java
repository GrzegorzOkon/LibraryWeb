package web;

import dao.entity.User;
import dao.service.RepositorySingleton;

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
    public User logIn(String login, String password) {
        User user = null;

        try {
            user = RepositorySingleton.getInstance().findUser(login);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
        //return Manager.checkLoginAndPassword(login, password);
    }
}
