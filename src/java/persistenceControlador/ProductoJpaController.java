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
import entidades.Comentario;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistenceControlador.exceptions.NonexistentEntityException;

/**
 *
 * @author Carri
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("TiendaCDPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getComentarios() == null) {
            producto.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : producto.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getIdComentario());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            producto.setComentarios(attachedComentarios);
            em.persist(producto);
            for (Comentario comentariosComentario : producto.getComentarios()) {
                Producto oldProductoOfComentariosComentario = comentariosComentario.getProducto();
                comentariosComentario.setProducto(producto);
                comentariosComentario = em.merge(comentariosComentario);
                if (oldProductoOfComentariosComentario != null) {
                    oldProductoOfComentariosComentario.getComentarios().remove(comentariosComentario);
                    oldProductoOfComentariosComentario = em.merge(oldProductoOfComentariosComentario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            List<Comentario> comentariosOld = persistentProducto.getComentarios();
            List<Comentario> comentariosNew = producto.getComentarios();
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getIdComentario());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            producto.setComentarios(comentariosNew);
            producto = em.merge(producto);
            for (Comentario comentariosOldComentario : comentariosOld) {
                if (!comentariosNew.contains(comentariosOldComentario)) {
                    comentariosOldComentario.setProducto(null);
                    comentariosOldComentario = em.merge(comentariosOldComentario);
                }
            }
            for (Comentario comentariosNewComentario : comentariosNew) {
                if (!comentariosOld.contains(comentariosNewComentario)) {
                    Producto oldProductoOfComentariosNewComentario = comentariosNewComentario.getProducto();
                    comentariosNewComentario.setProducto(producto);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldProductoOfComentariosNewComentario != null && !oldProductoOfComentariosNewComentario.equals(producto)) {
                        oldProductoOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldProductoOfComentariosNewComentario = em.merge(oldProductoOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<Comentario> comentarios = producto.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setProducto(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
