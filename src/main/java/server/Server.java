package server;

import dao.AlbumDao;
import dao.impl.AlbumImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static final String URL = "rmi://PhanHoangTan:4591/";

    public static void main(String[] args) {
        try {
            System.out.println("Server is running...");
            Context context = new InitialContext();
            AlbumDao albumDao = new AlbumImpl();
            LocateRegistry.createRegistry(4591);

            context.bind(URL + "albumDao", albumDao);


        } catch (Exception e) {
            e.printStackTrace();
    }
}
}
