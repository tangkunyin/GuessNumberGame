package com.vision.game.utils;

import java.io.IOException;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.vision.game.bean.Address;

/**
 * 通过ip地址获取物理地址
 * @author tangkunyin
 * @see http://ip.taobao.com/instructions.php
 * 使用HttpClient实现。通过淘宝API
 */
public class GetAddrFromIp {
	//传入ip地址，返回城市地址等信息 json格式
	public String getJsonAddr(String ip){
		String url="http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
		HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		try {
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity=response.getEntity();
			if(200==response.getStatusLine().getStatusCode()){
				String contents=EntityUtils.toString(entity);
				//Set Encoding 
				return new String(contents.getBytes("UTF-8"),"UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	//返回地址对象
	public Address getAddress(String ip){
		JSONObject jb=JSONObject.fromObject(new GetAddrFromIp().getJsonAddr(ip));
		String country=((JSONObject)jb.get("data")).getString("country").toString();
		String region=((JSONObject)jb.get("data")).getString("region").toString();
		String city=((JSONObject)jb.get("data")).getString("city").toString();
		String isp=((JSONObject)jb.get("data")).getString("isp").toString();
		System.out.println("来自："+country+region+city+"，运营商是："+isp);
		
		Address addr=new Address();
		addr.setCountry(country);
		addr.setRegion(region);
		addr.setCity(city);
		addr.setIsp(isp);
		
		return addr;
	}
}