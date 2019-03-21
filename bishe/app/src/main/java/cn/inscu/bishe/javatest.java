package cn.inscu.bishe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.bmob.javacloud.stub.CloudHandler;
import cn.bmob.javacloud.stub.HttpResponse;
import cn.bmob.javacloud.stub.JSONArray;
import cn.bmob.javacloud.stub.JSONObject;
import cn.bmob.javacloud.stub.Querier;
import cn.bmob.javacloud.stub.Response;

public class javatest extends CloudHandler {

    @Override
    public void onRequest(Request request, Response response, Modules modules) throws Throwable {// 查询多条数据 HttpResponse
        int sum;
        JSONObject params = request.getParams();
      JSONObject params1 = new JSONObject();
        String recommendUser=params.getString("name");
        sum= params.getInteger("total");
        int[][] sparseMatrix = new int[sum][sum];
        Double [] same=new Double[sum];
        //用户的相似度排列
        Map<String, Set<String>> itemUserCollection = new HashMap<>();//
        Map<String, Integer> userItemLength = new HashMap<>();
        Set<String> items = new HashSet<>();//辅助存储物品集合
        List<String> ansitems=new ArrayList<>();
        Map<String, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射
        Map<Integer, String> idUser = new HashMap<>();
        Querier qt = new Querier("prefer")
                .limit(1000);
        qt.addWhereNotEqualTo("user", null);
        HttpResponse httpResponse = modules.oData.find(qt);
        JSONArray results = httpResponse.jsonData.getJSONArray("results");
        for(int i = 0; i < results.size(); i++) {
            JSONObject obj = results.getJSONObject(i);
            String name=obj.getString("user");
            List<String>user_item= (List<String>) obj.get("likes");
            String[] user_item0=(String[])user_item.toArray(new String[user_item.size()]);
            user_item.toArray(user_item0);
            int length = user_item0.length;
            userItemLength.put(name, length);//eg: A 3
            userID.put(name, i);//用户ID与稀疏矩阵建立对应关系
            idUser.put(i, name);
            for(int j=0;j<length;j++){
                if(items.contains(user_item0[j])){
                    itemUserCollection.get(user_item0[j]).add(name);//如果已经包含对应的物品--用户映射，直接添加对应的用户
                }
                else{
                    items.add(user_item0[j]);
                    itemUserCollection.put(user_item0[j], new HashSet<String>());//创建物品--用户倒排关系
                    itemUserCollection.get(user_item0[j]).add(name);
                }
            }
        }
        Set<Map.Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<String, Set<String>>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if(user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的物品总数
                }
            }
        }

        int recommendUserId = userID.get(recommendUser);
        for (int j = 0;j < sparseMatrix.length; j++) {
            if(j != recommendUserId){
               same[j]=sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j)));
            }
        }
        for(String item: items) {//遍历每一件物品
            Set<String> users = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
            if (!users.contains(recommendUser)) {//如果被推荐用户没有购买当前物品，则进行推荐度计算
                double itemRecommendDegree = 0.0;
                for (String user : users) {
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)] / Math.sqrt(userItemLength.get(recommendUser) * userItemLength.get(user));//推荐度计算
                }
               if(itemRecommendDegree>0.8){
                   ansitems.add(item);
               }
            }
        }
        params1.put("aaa",ansitems);
        response.send(params1);
    }
}
