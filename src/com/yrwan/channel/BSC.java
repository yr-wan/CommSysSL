package com.yrwan.channel;
public class BSC {
	//��BSC�����źţ����ش�����ֵ
    public static int[] send1(int[] data,double errPr){
    	int[] x=new int[data.length];
    	x = data;
        for(int i = 0; i<data.length;i++)
            if(Math.random()<errPr){
                x[i] = 1 - x[i];
            }
        return x;
    }
  //Ϊ0,1ʱ��error1Ϊ1�Ĵ�����ʣ�error2Ϊ0�Ĵ������
    public static int[] send2(int[] data,double error1,double error2){
        int[] x=new int[data.length];
        x = data;
        for(int i = 0; i<x.length;i++)
            if (x[i] == 1){
                //�����1
                if(Math.random()<error1){
                	x[i] = 1 - x[i];
                }
            }else {
                //�����0
                if(Math.random()<error2){
                	x[i] = 1 - x[i];
                }
            }
        return x;
    }
}