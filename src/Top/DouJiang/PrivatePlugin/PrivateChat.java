package Top.DouJiang.PrivatePlugin;

import Top.DouJiang.APIs.API;
import Top.DouJiang.ServerSocket.Sockets;
import Top.DouJiang.Tool.SocketTools;
import Top.DouJiang.plugin.ChatEvents;
import Top.DouJiang.plugin.TaskClass;
import Top.DouJiang.plugin.TaskResult;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NicoNicoNi on 2017/8/10 0010.
 */
public class PrivateChat implements ChatEvents {
    @Override
    public TaskResult ChatEvent(TaskClass tc) {
        if(tc.getType()!=2){
            return null;
            //不为私聊返回
        }
        System.out.println("PrivateChat启动");
        if(!PrivateChatTools.isFriend(tc.getFromId(),tc.getToId())){
            System.out.println("不为好友返回");
            return null;
        }
        Sockets s=API.GetSocketThroughId(tc.getToId());
        Sockets s2=API.GetSocketThroughId(tc.getFromId());
        Map<String,String> MsgMap=new HashMap<>();
        MsgMap.put("Cmd","Chat");
        MsgMap.put("TypeId","2");
        MsgMap.put("Msg", SocketTools.Base64Encryption(tc.getMsg()));
        MsgMap.put("FromId",tc.getFromId());
        s.Send(SocketTools.MapToJson(MsgMap)); //广播消息
        s2.Send(SocketTools.MapToJson(MsgMap)); //广播消息
        System.out.println("广播消息: "+tc.getMsg());
        return null;
    }
}
