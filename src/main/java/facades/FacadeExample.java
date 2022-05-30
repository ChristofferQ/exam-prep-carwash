package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import dtos.BookingDTO;
import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.WashingAssistant;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<WashingAssistantDTO> getAllWashingAssistants(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<WashingAssistant> query = em.createQuery("SELECT w FROM WashingAssistant w", WashingAssistant.class);
        List<WashingAssistant> ws = query.getResultList();
        System.out.println(ws);
        return WashingAssistantDTO.getDtos(ws);
    }

    public List<BookingDTO> getAllBookings(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
        List<Booking> bs = query.getResultList();
        System.out.println(bs);
        return BookingDTO.getDtos(bs);
    }

    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAllWashingAssistants();
        fe.getAllBookings();
    }

}
