package nsk.tfoms.survay.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nsk.tfoms.survay.entity.SurvayClinic;

@Service
public class ClinicService {
  
  @PersistenceContext
  private EntityManager em;
  
  @Transactional
  public List<SurvayClinic> getAll(String userp) {
    List<SurvayClinic> result = em.createQuery("SELECT p FROM SurvayClinic p WHERE p.polzovatel =:userp ORDER BY p.id DESC", SurvayClinic.class)
    		.setParameter("userp", userp)
    		.getResultList();
    return result;
  }
  
  @Transactional
  public List<SurvayClinic> getAllbetween(String d1, String d2,String userp) {
    List<SurvayClinic> result = em.createQuery("SELECT p FROM SurvayClinic p WHERE p.polzovatel =:userp AND p.dataInput BETWEEN :d1 AND :d2 ORDER BY p.id DESC", SurvayClinic.class)
    .setParameter("d1", d1)  
    .setParameter("d2", d2)  
    .setParameter("userp", userp)
    .getResultList();
    return result;
  }
  
  @Transactional
  public List<SurvayClinic> getOnId(BigDecimal id,String userp) {
    List<SurvayClinic> result = em.createQuery("SELECT p FROM SurvayClinic p WHERE p.polzovatel =:userp AND p.id=:id", SurvayClinic.class)
    .setParameter("id", id)  
    .setParameter("userp", userp)
    .getResultList();
    return result;
  }
  @Transactional
  public void add(SurvayClinic p) {
	  
	  if (p.getId() == null) {this.em.persist(p);} else {this.em.merge(p);}
  }
}
