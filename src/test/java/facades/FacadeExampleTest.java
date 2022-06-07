package facades;

import dtos.BookingDTO;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static FacadeExample facade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        WashingAssistant washAss1 = new WashingAssistant("Steve", "English", 3,145);
        WashingAssistant washAss2 = new WashingAssistant("Bobbie", "American", 15,230);

        Car car1 = new Car(101010, "Ermin","ErminMaker", 2022);
        Car car2 = new Car(28400, "ChrisCross","God", 1000);

        Booking booking1 = new Booking(8000, 10,car1,washAss1);
        Booking booking2 = new Booking(14000, 300,car2,washAss2);
        try {
            em.getTransaction().begin();

            em.persist(washAss1);
            em.persist(washAss2);

            em.persist(car1);
            em.persist(car2);

            em.persist(booking1);
            em.persist(booking2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Booking").executeUpdate();
            em.createQuery("delete from Car").executeUpdate();
            em.createQuery("delete from WashingAssistant ").executeUpdate();
        } finally {
            em.close();
        }
    }

    // TODO: Delete or change this method
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getCarCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetAllBookings() {
        assertEquals(2, facade.getAllBookings(), "Expects two rows in the database");
    }

    @Test
    public void testDeleteBooking() {

    }

}
