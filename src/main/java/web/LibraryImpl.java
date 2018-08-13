package web;

import dao.entity.User;
import manager.Manager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebFault;

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
    public User logIn(String login, String password) throws WrongUserOrPasswordException
    {
        User user = Manager.getUserFromRepository(login);

        if (user == null) {
            throw new WrongUserOrPasswordException();
        } else {
            if (Manager.verifyPassword(password, user) == false) {
                throw new WrongUserOrPasswordException();
            }
        }

        return user;
    }

    @WebFault
    public class WrongUserOrPasswordException extends RuntimeException {}
}