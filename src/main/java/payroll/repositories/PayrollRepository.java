/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.repositories;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import payroll.utilities.PayrollUtilities;

/**
 *
 * @author JDM
 */
@Named
@Transactional
@ApplicationScoped
public class PayrollRepository {

    @PersistenceContext(unitName = "payrollPU")
    protected EntityManager em;

    private boolean isDateAttribute(Class<?> clazz, String fieldName) {
        Class<?>[] emptyParams = null;
        try {
            System.out.println("date format:" + "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            Method method = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), emptyParams);
            return (Date.class.isAssignableFrom(method.getReturnType())
                    || java.sql.Date.class.isAssignableFrom(method.getReturnType()));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PayrollRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public <T> List<T> getAllOfPage(Class type, Map<String, Object> filters, String sortField, boolean isDesc, int start, int size) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> r = query.from(type);
        query.select(r);

        //filteration
        if (filters != null && !filters.isEmpty()) {
            Predicate allPredicate = null;
            Predicate predicate = null;
            String column;
            Iterator<String> it = filters.keySet().iterator();
            while (it.hasNext()) {
                column = it.next();
                if (!isDateAttribute(type, column)) {
                    predicate = cb.like(r.get(column), "%" + filters.get(column).toString() + "%");
                } else {
                    Date fromDate = PayrollUtilities.stringToDate(filters.get(column).toString());
                    Calendar toDate = Calendar.getInstance();
                    toDate.setTime(fromDate);
                    toDate.add(Calendar.DAY_OF_MONTH, 1);
                    toDate.add(Calendar.MILLISECOND, -1);

                    if (fromDate != null) {
                        predicate = cb.between(r.get(column), fromDate, toDate.getTime());
                    }
                }
                if (allPredicate == null) {
                    if (predicate != null) {
                        allPredicate = predicate;
                    }
                } else {
                    if (predicate != null) {
                        allPredicate = cb.and(allPredicate, predicate);
                    }

                }
            }
            if (allPredicate != null) {
                query.where(allPredicate);
            }
        }
        //sorting
        if (sortField != null && !sortField.isEmpty()) {
            Expression sortedfieldExp = r.get(sortField);
            query.orderBy(isDesc ? cb.desc(sortedfieldExp) : cb.asc(sortedfieldExp));
        } else {
            query.orderBy(cb.desc(r.get("createdDate")));
        }

        return em.createQuery(query).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public <T> long getSize(Class<T> type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<T> r = q.from(type);
        return em.createQuery(q.select(cb.count(r))).getSingleResult();
    }

    public <T> List<T> getAll(Class type) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(type);
        Root<T> r = q.from(type);
        return em.createQuery(q.select(r).orderBy(cb.desc(r.get("createdDate")))).getResultList();
    }

    public <T> T getById(Class type, Object id) {
        return (T) em.find(type, id);
    }

    public <T> T persist(T t) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    public <T> T update(T t) {
        return (T) em.merge(t);
    }

    public <T> void delete(T t) {
        em.remove(em.merge(t));
    }
}
