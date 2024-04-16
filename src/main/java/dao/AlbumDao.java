package dao;

import entity.Album;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface AlbumDao extends Remote {
    public boolean updatePriceOfAlbum(String id, double newPrice) throws RemoteException;
    public List<Album> listAlbumByGenre(String genreName, int year) throws RemoteException;
    public Map<String,Long > getAlbumCountByGenre() throws RemoteException;

}