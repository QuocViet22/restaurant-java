/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.business.Booktable;

public class BookDB {

    public static void insert(Booktable booktable) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(booktable);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Booktable booktable) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(booktable);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Booktable booktable) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(booktable));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    public static Booktable selectTable(String idTable, String time) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT b FROM Booktable b " +
                "WHERE b.idTable = :idTable AND b.time = :time";
        TypedQuery<Booktable> q = em.createQuery(qString, Booktable.class);
        q.setParameter("idTable", idTable);
        q.setParameter("time", time);
        try {
            Booktable booktable = q.getSingleResult();
            return booktable;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean tableExists(String idTable, String time) {
        Booktable b = selectTable(idTable, time);   
        return b != null;
    }
}
