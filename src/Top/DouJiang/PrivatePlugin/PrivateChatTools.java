package Top.DouJiang.PrivatePlugin;

import Top.DouJiang.Static.StaticMap;
import Top.DouJiang.Util.Mysqls.ConnectionPool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by NicoNicoNi on 2017/8/10 0010.
 */
public class PrivateChatTools {
    /*
    判断两人是否为好友
    待添加 缓存机制
     */
    public static boolean isFriend(String Id,String Id2){
        ConnectionPool.PooledConnection pool= StaticMap.pool;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ObjectInputStream in =null;
        Object obj =null;
        try {
            ps=pool.getPrepareStatement("select privates from user_text where id=?;");
            ps.setString(1,Id);
            rs=ps.executeQuery();
            if(rs.next()){
                in = new ObjectInputStream(rs.getBlob("privates").getBinaryStream());
                obj = in.readObject();
                in.close();
            }
            if(obj instanceof Set){
                Set<String> Private_Set=(Set<String>)obj;
                for(String s:Private_Set){
                    if(s.equalsIgnoreCase(Id2)){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            //
        } catch (IOException e) {
            //
        } catch (ClassNotFoundException e) {
            //
        }
        return false;
    }
}
