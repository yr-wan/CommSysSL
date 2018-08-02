package com.yrwan.channelcode;

public class ChannelCodeChoose {
	public static int[] encode(int type,int[] data){
        int[] result;
        switch (type){
            case 1:
                //�ޱ���
                result=data;
                break;
            case 2:
                //3���ظ�����
                result= ThreeTimes.encode(data);
                break;
            case 3:
                //Hamming(7,4)����
                result= Hamming.generateHamming(data);
                break;
            default:
                System.out.println("��ѡ����ȷ���ŵ�������");
                result=new int[0];
                System.exit(0);
                break;
        }
        return result;
	}
	public static int[] decode(int type,int[] data,int length){
        int[] result;
        switch (type){
            case 1:
                //�ޱ���
                result=data;
                break;
            case 2:
                //3���ظ�����
                result= ThreeTimes.decode(data);
                break;
            case 3:
                //Hamming(7,4)����
                result=Hamming.decode(data,length);
                break;
            default:
                System.out.println("��ѡ����ȷ���ŵ�������");
                result=new int[0];
                System.exit(0);
                break;
        }
        return result;
	}
}
