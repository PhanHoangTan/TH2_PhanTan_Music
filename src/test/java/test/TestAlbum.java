package test;

import dao.AlbumDao;
import dao.impl.AlbumImpl;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

@Inheritance(strategy = InheritanceType.JOINED)
public class TestAlbum {
    @BeforeAll
    public static void setUp() throws RemoteException {
        AlbumDao albumDao = new AlbumImpl();
    }

    @Test
    public void testUpdatePriceOfAlbum() throws RemoteException {
        AlbumDao albumDao = new AlbumImpl();
        boolean result = albumDao.updatePriceOfAlbum("1", 100);
        System.out.println("Result: " + result);
    }

    @Test
    public void testListAlbumByGenre() throws RemoteException {
        AlbumDao albumDao = new AlbumImpl();
        albumDao.listAlbumByGenre("Pop", 2018).forEach(System.out::println);
    }

    @Test
    public void testGetNumberOfAlbumByGenre() throws RemoteException {
        AlbumDao albumDao = new AlbumImpl();
        albumDao.getAlbumCountByGenre().forEach((k, v) -> System.out.println(k + ": " + v));
    }



}
