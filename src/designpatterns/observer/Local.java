package designpatterns.observer;

import designpatterns.composite.Element;

import java.util.HashSet;
import java.util.Set;


public class Local {
    private int id;
    private String sigle;

    private int places;
    private Set<SessionCours> sessions=new HashSet<>();



    public Local(int id, String sigle, int places) {
        this.id=id;
        this.sigle = sigle;
        this.places = places;
    }
    public Set<SessionCours> getSessions() {
        return sessions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Set<SessionCours> getSession() {
        return sessions;
    }


    public String getSigle() {
        return sigle;
    }


    public int getPlaces() {
        return places;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "sigle :'" + sigle +"'"+ ", places :" + places+"\n";
    }
}

