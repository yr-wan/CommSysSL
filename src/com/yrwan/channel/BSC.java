package com.yrwan.channel;
public class BSC {
	//经BSC传输信号，返回传输后的值
    public static int[] send1(int[] data,double errPr){
    	int[] x=new int[data.length];
    	x = data;
        for(int i = 0; i<data.length;i++)
            if(Math.random()<errPr){
                x[i] = 1 - x[i];
            }
        return x;
    }
  //为0,1时，error1为1的错误概率，error2为0的错误概率
    public static int[] send2(int[] data,double error1,double error2){
        int[] x=new int[data.length];
        x = data;
        for(int i = 0; i<x.length;i++)
            if (x[i] == 1){
                //如果是1
                if(Math.random()<error1){
                	x[i] = 1 - x[i];
                }
            }else {
                //如果是0
                if(Math.random()<error2){
                	x[i] = 1 - x[i];
                }
            }
        return x;
    }
}