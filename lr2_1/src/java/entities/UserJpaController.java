/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.exceptions.IllegalOrphanException;
import entities.exceptions.NonexistentEntityException;
import entities.exceptions.PreexistingEntityException;
import entities.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nastya Winehouse
 */
public class UserJpaController implements Serializable {

    public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (user.getUserRoleCollection() == null) {
            user.setUserRoleCollection(new ArrayList<UserRole>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<UserRole> attachedUserRoleCollection = new ArrayList<UserRole>();
            for (UserRole userRoleCollectionUserRoleToAttach : user.getUserRoleCollection()) {
                userRoleCollectionUserRoleToAttach = em.getReference(userRoleCollectionUserRoleToAttach.getClass(), userRoleCollectionUserRoleToAttach.getUserRolePK());
                attachedUserRoleCollection.add(userRoleCollectionUserRoleToAttach);
            }
            user.setUserRoleCollection(attachedUserRoleCollection);
            em.persist(user);
            for (UserRole userRoleCollectionUserRole : user.getUserRoleCollection()) {
                User oldUserOfUserRoleCollectionUserRole = userRoleCollectionUserRole.getUser();
                userRoleCollectionUserRole.setUser(user);
                userRoleCollectionUserRole = em.merge(userRoleCollectionUserRole);
                if (oldUserOfUserRoleCollectionUserRole != null) {
                    oldUserOfUserRoleCollectionUserRole.getUserRoleCollection().remove(userRoleCollectionUserRole);
                    oldUserOfUserRoleCollectionUserRole = em.merge(oldUserOfUserRoleCollectionUserRole);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUser(user.getUsername()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User persistentUser = em.find(User.class, user.getUsername());
            Collection<UserRole> userRoleCollectionOld = persistentUser.getUserRoleCollection();
            Collection<UserRole> userRoleCollectionNew = user.getUserRoleCollection();
            List<String> illegalOrphanMessages = null;
            for (UserRole userRoleCollectionOldUserRole : userRoleCollectionOld) {
                if (!userRoleCollectionNew.contains(userRoleCollectionOldUserRole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserRole " + userRoleCollectionOldUserRole + " since its user field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<UserRole> attachedUserRoleCollectionNew = new ArrayList<UserRole>();
            for (UserRole userRoleCollectionNewUserRoleToAttach : userRoleCollectionNew) {
                userRoleCollectionNewUserRoleToAttach = em.getReference(userRoleCollectionNewUserRoleToAttach.getClass(), userRoleCollectionNewUserRoleToAttach.getUserRolePK());
                attachedUserRoleCollectionNew.add(userRoleCollectionNewUserRoleToAttach);
            }
            userRoleCollectionNew = attachedUserRoleCollectionNew;
            user.setUserRoleCollection(userRoleCollectionNew);
            user = em.merge(user);
            for (UserRole userRoleCollectionNewUserRole : userRoleCollectionNew) {
                if (!userRoleCollectionOld.contains(userRoleCollectionNewUserRole)) {
                    User oldUserOfUserRoleCollectionNewUserRole = userRoleCollectionNewUserRole.getUser();
                    userRoleCollectionNewUserRole.setUser(user);
                    userRoleCollectionNewUserRole = em.merge(userRoleCollectionNewUserRole);
                    if (oldUserOfUserRoleCollectionNewUserRole != null && !oldUserOfUserRoleCollectionNewUserRole.equals(user)) {
                        oldUserOfUserRoleCollectionNewUserRole.getUserRoleCollection().remove(userRoleCollectionNewUserRole);
                        oldUserOfUserRoleCollectionNewUserRole = em.merge(oldUserOfUserRoleCollectionNewUserRole);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = user.getUsername();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserRole> userRoleCollectionOrphanCheck = user.getUserRoleCollection();
            for (UserRole userRoleCollectionOrphanCheckUserRole : userRoleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserRole " + userRoleCollectionOrphanCheckUserRole + " in its userRoleCollection field has a non-nullable user field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
