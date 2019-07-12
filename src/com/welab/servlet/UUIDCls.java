package com.welab.servlet;

import java.util.UUID;

public class UUIDCls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID.toString().replace("-", "").toUpperCase());
	}
	
	public static String getUUid(){
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replace("-", "").toUpperCase();
		
	}
}
