package dao.impl;



import dao.AlbumDao;
import entity.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AlbumImpl extends UnicastRemoteObject implements Serializable, AlbumDao {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public AlbumImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("jpa-mssql")
                .createEntityManager();
    }

    //Cập nhật đơn giá cho một Album nào đó khi biết mã số(không cho phép cập nhật các thuộc tính khác của Album)
    //updatePriceOfAlbum(id:String , newPrice:double):boolean

    public boolean updatePriceOfAlbum(String id, double newPrice) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.createQuery("update Album a set a.price = :newPrice where a.id = :id")
                    .setParameter("newPrice", newPrice)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<Album> listAlbumByGenre(String genreName, int year) {
        // Tìm kiếm các album thuộc về thể loại nào đó khi biết thể loại và năm phát hành. Code java theo JPA
        //select a from Album a where a.genre.name like CONCAT(:genre, '%') and a.yearOfRelease = :year", Album.class
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Album> list = em.createQuery("SELECT a FROM Album a WHERE a.genre.name LIKE CONCAT(:genre, '%') AND a.yearOfRelease = :year", Album.class)
                    .setParameter("genre", genreName)
                    .setParameter("year", year)
                    .getResultList();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    //Thống kê số album theo từng thể loại , sắp xếp theo thứ tự tên thể loại tăng dần
    //getAlbumCountByGenre():Map<String, Long> //Key là tên thể loại, Value là số album
    public Map<String,Long > getAlbumCountByGenre() throws RemoteException {
        // Thong ke so album theo thể loại. Sap xep the loai tang dan bang LinkedMap<>

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<Object[]> list = em.createQuery("SELECT g.name, COUNT(a) FROM Album a JOIN a.genre g GROUP BY g.name", Object[].class)
                    .getResultList();
            return list.stream().collect(Collectors.toMap(e -> (String) e[0], e -> (Long) e[1]));

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }


        return null;
    }










}