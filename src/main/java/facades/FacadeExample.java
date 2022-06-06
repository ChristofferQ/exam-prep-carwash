package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import dtos.BookingDTO;
import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Car;
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

    public BookingDTO getBookingById(long bookingId) {
        EntityManager em = emf.createEntityManager();
        Booking b = em.find(Booking.class, bookingId);
        return new BookingDTO(b);
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

    public BookingDTO createBooking(BookingDTO b) {
        Booking bo = new Booking(b.getDateAndTime(),b.getDuration(),new Car(),new WashingAssistant());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(bo);
    }

    public BookingDTO assignWashAss(long bookingId, long washAssId) {
        EntityManager em = emf.createEntityManager();
        try {
            Booking b = em.find(Booking.class, bookingId);
            WashingAssistant w = em.find(WashingAssistant.class, washAssId);

            b.setWashingAssistant(w);
            w.addBooking(b);

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BookingDTO(b);
        } finally {
            em.close();
        }
    }

    public WashingAssistantDTO createWashingAssistant(WashingAssistantDTO wa) {
        WashingAssistant w = new WashingAssistant(wa.getName(), wa.getPrimaryLanguage(), wa.getYearsOfExp(),wa.getPricePrHour());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(w);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println("hej" + w);
        return new WashingAssistantDTO(w);
    }

    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        //fe.getAllWashingAssistants();
        //fe.getAllBookings();

        //BookingDTO bd = new BookingDTO(121,121,new Car(),new WashingAssistant());
        //fe.createBooking(bd);

        fe.assignWashAss(1,2);

        //WashingAssistantDTO wad = new WashingAssistantDTO("Ermin","Dansk",12,12);
        //fe.createWashingAssistant(wad);
    }

    public long getCarCount() {
        EntityManager em = getEntityManager();
        try{
            long carCount = (long)em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carCount;
        }finally{
            em.close();
        }
    }
}
