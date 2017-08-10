package Top.DouJiang.PrivatePlugin;

import Top.DouJiang.Static.StaticMap;
import Top.DouJiang.Util.Mysqls.ConnectionPool;
import Top.DouJiang.Util.Mysqls.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NicoNicoNi on 2017/8/10 0010.
 */
public class TestMain {
    public static void main(String[] args){
        ConnectionPool.PooledConnection pool= DBManager.getConnection("127.0.0.1",3306,"test4","root","diantong");
        Set<String> M=new HashSet<>();
        for(int i1=0;i1<100;i1++){
            M.add(String.valueOf(i1));
        }
        try {
            for(int i=0;i<100;i++) {
                PreparedStatement ps = pool.getPrepareStatement("insert into user_text(privates,id) values (?,?);");
                ps.setObject(1,M);
                ps.setString(2,String.valueOf(i));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
