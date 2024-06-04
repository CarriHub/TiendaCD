/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistenceControlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Pedido;
import entidades.UsuarioProductoCarrito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistenceControlador.exceptions.NonexistentEntityException;

/**
 *
 * @author Carri
 */
public class UsuarioProductoCarritoJpaController implements Serializable {

    public UsuarioProductoCarritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuarioProductoCarritoJpaController() {
        emf = Persistence.createEntityManagerFactory("TiendaCDPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioProductoCarrito usuarioProductoCarrito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = usuarioProductoCarrito.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdPedido());
                usuarioProductoCarrito.setPedido(pedido);
            }
            em.persist(usuarioProductoCarrito);
            if (pedido != null) {
                pedido.getUsuarioProductoCarritos().add(usuarioProductoCarrito);
                pedido = em.merge(pedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioProductoCarrito usuarioProductoCarrito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioProductoCarrito persistentUsuarioProductoCarrito = em.find(UsuarioProductoCarrito.class, usuarioProductoCarrito.getId());
            Pedido pedidoOld = persistentUsuarioProductoCarrito.getPedido();
            Pedido pedidoNew = usuarioProductoCarrito.getPedido();
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdPedido());
                usuarioProductoCarrito.setPedido(pedidoNew);
            }
            usuarioProductoCarrito = em.merge(usuarioProductoCarrito);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getUsuarioProductoCarritos().remove(usuarioProductoCarrito);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getUsuarioProductoCarritos().add(usuarioProductoCarrito);
                pedidoNew = em.merge(pedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuarioProductoCarrito.getId();
                if (findUsuarioProductoCarrito(id) == null) {
                    throw new NonexistentEntityException("The usuarioProductoCarrito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioProductoCarrito usuarioProductoCarrito;
            try {
                usuarioProductoCarrito = em.getReference(UsuarioProductoCarrito.class, id);
                usuarioProductoCarrito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioProductoCarrito with id " + id + " no longer exists.", enfe);
            }
            Pedido pedido = usuarioProductoCarrito.getPedido();
            if (pedido != null) {
                pedido.getUsuarioProductoCarritos().remove(usuarioProductoCarrito);
                pedido = em.merge(pedido);
            }
            em.remove(usuarioProductoCarrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioProductoCarrito> findUsuarioProductoCarritoEntities() {
        return findUsuarioProductoCarritoEntities(true, -1, -1);
    }

    public List<UsuarioProductoCarrito> findUsuarioProductoCarritoEntities(int maxResults, int firstResult) {
        return findUsuarioProductoCarritoEntities(false, maxResults, firstResult);
    }

    private List<UsuarioProductoCarrito> findUsuarioProductoCarritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioProductoCarrito.class));
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

    public UsuarioProductoCarrito findUsuarioProductoCarrito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioProductoCarrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioProductoCarritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioProductoCarrito> rt = cq.from(UsuarioProductoCarrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
