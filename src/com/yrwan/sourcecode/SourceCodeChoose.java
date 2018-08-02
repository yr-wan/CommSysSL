package com.yrwan.sourcecode;

import java.util.Map;

import com.yrwan.sourcecode.huffman.Huffman;

public class SourceCodeChoose {
	public static int[] encode(int type, int[] data, Map<Character, Integer> statistics){
		int[] result=new int[0];
		String oriStr="";
        for (int aData : data) {
            oriStr = oriStr + aData;
        }
        
        String strResult="";
        switch (type){
            case 1:
                //�ޱ���
                result=data;
                break;
            case 2:
                //��������ũ-��ŵ����
            	//strResult = ShonnonFano.encode(oriStr);
                break;
            case 3:
                //�����ƻ���������
            	strResult = Huffman.encode(oriStr,statistics);
                break;
            default:
                System.out.println("��ѡ����ȷ����Դ������");
                break;
        }
      //�����ShannonFano����Huffman���뽫��ת��Ϊ����
        if (strResult!=null&&strResult.length()!=0){
            char[] charResult=strResult.toCharArray();
            result=new int[charResult.length];
            for (int i = 0; i < charResult.length; i++) {
                if (charResult[i]=='1')
                    result[i]=1;
                else
                    result[i]=0;
            }
        }
        
        return result;
	}
	public static int[] decode(int type, int[] data, Map<Character, Integer> statistics){
		int[] result = new int[0];
        String oriStr="";
        for (int i = 0; i < data.length; i++) {
            oriStr=oriStr+data[i];
        }
        
        String strResult="";
        switch (type){
        case 1:
            //�ޱ���
            result=data;
            break;
        case 2:
            //��������ũ-��ŵ����
        	//strResult = ShonnonFano.decode(oriStr);
            break;
        case 3:
            //�����ƻ���������
        	strResult = Huffman.decode(oriStr,statistics);
            break;
        default:
            System.out.println("��ѡ����ȷ����Դ������");
            break;
        }
      //�����ShannonFano����Huffman���뽫��ת��Ϊ����
        if (strResult!=null&&strResult.length()!=0){
            char[] charResult=strResult.toCharArray();
            result=new int[charResult.length];
            for (int i = 0; i < charResult.length; i++) {
                if (charResult[i]=='1')
                    result[i]=1;
                else
                    result[i]=0;
            }
        }
        
        return result;
	}
}
