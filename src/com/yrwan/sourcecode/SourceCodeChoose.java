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
                //无编码
                result=data;
                break;
            case 2:
                //二进制香农-费诺编码
            	//strResult = ShonnonFano.encode(oriStr);
                break;
            case 3:
                //二进制霍夫曼编码
            	strResult = Huffman.encode(oriStr,statistics);
                break;
            default:
                System.out.println("请选择正确的信源编码器");
                break;
        }
      //如果是ShannonFano或者Huffman编码将其转化为数组
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
            //无编码
            result=data;
            break;
        case 2:
            //二进制香农-费诺编码
        	//strResult = ShonnonFano.decode(oriStr);
            break;
        case 3:
            //二进制霍夫曼编码
        	strResult = Huffman.decode(oriStr,statistics);
            break;
        default:
            System.out.println("请选择正确的信源编码器");
            break;
        }
      //如果是ShannonFano或者Huffman编码将其转化为数组
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
