package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Principal {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-distribuida");

        EntityManager em = emf.createEntityManager();

        System.out.println("Agregando personas...");
        var p = new Persona();
        p.setId(1);
        p.setNombre("persona1");
        p.setDireccion("direccion1");
        p.setEdad(1);

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

        //--listar personas
        System.out.println("Listando personas...");
        TypedQuery<Persona> qry = em.createQuery("select o from Persona o order by id asc", Persona.class);

        qry.getResultStream()
                .map(Persona::getNombre)
                .forEach(System.out::println);

        em.close();
        emf.close();
    }
}
