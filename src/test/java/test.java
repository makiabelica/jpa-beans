import org.apache.logging.log4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class test {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        //crearEstudiante();
        //recuperarPorId();
        //actulizarEstudiante();
        //eliminarEstudiante();
    }

    public static void crearEstudiante() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Estudiante estudiante = new Estudiante(2,"Angie", "Feliz", "como una lombris");
        em.persist(estudiante);
        tx.commit();
        log.debug("Objeto" + estudiante);
        em.close();
    }

    private static void recuperarPorId(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Estudiante estudiante = em.find(Estudiante.class, 1);
        tx.commit();
        System.out.println("Objeto Recuperado" +  estudiante);
        em.close();
    }

    private static void actulizarEstudiante(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Estudiante estudiante = em.find(Estudiante.class, 1);
        tx.commit();
        System.out.println("Objeto actualizado" +  estudiante);

        estudiante.setNombre("Feli");
        estudiante.setApellido("Como una lombrish");
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        em.merge(estudiante);
        tx2.commit();
        System.out.println("Objeto Eliminado" +  estudiante);

        em.close();
    }

    private static void eliminarEstudiante(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstudiantePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // INICIAMOS TRANSACCION
        Estudiante estudiante = em.find(Estudiante.class, 2);
        tx.commit();

        if (estudiante != null) {
            System.out.println("Estudiante encontrado para eliminar:");
            System.out.println("ID: " + estudiante.getIdestudiante());
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Apellido: " + estudiante.getApellido());

            EntityTransaction tx2 = em.getTransaction();
            tx2.begin();

            em.remove(em.merge(estudiante));
            tx2.commit();

            System.out.println("Estudiante eliminado correctamente:");
            System.out.println("ID: " + estudiante.getIdestudiante());
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Apellido: " + estudiante.getApellido());
        } else {
            System.out.println("No se encontró ningún estudiante con ID 1 para eliminar.");
        }

        em.close();
    }
}
