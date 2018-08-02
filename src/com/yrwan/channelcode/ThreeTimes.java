package com.yrwan.channelcode;

import java.util.ArrayList;
import java.util.List;

public class ThreeTimes {
	//生成三次重复码
	public static int[] encode(int[] data){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
            list.add(data[i]);
            list.add(data[i]);
        }

        int[] result=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
        	result[i]=list.get(i);
        }
        return result;
    }
	//解码三次重复码
	public static int[] decode(int[] data){
        int[] result=new int[data.length/3];
        for (int i = 0,m=0; i < data.length; i=i+3,m++) {
            int i1=data[i];
            int i2=data[i+1];
            int i3=data[i+2];
             if (i1==i2||i2==i3){
                result[m]=i2;
            }else if (i1==i3){
                result[m]=i1;
            }
        }
        return result;
    }
}
