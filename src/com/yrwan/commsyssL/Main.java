package com.yrwan.commsyssL;

import java.util.Map;
import java.util.Scanner;

import com.yrwan.channel.BSC;
import com.yrwan.channelcode.ChannelCodeChoose;
import com.yrwan.sourcecode.SourceCodeChoose;
import com.yrwan.sourcecode.huffman.Huffman;

//信源编码中的huffman编码参考自互联网，香农费诺编码尚未完成
public class Main {
	private static double oriPr;//离散信源分布概率
    private static int oriLen;//二进制序列长度
    private static int sourceCodeType;//信源编码器
    private static int channelCodeType;//信道编码器
    private static int channelType;//信道选择
    private static double errPr1=0;//传输错误概率
    private static double errPr2=0;//传输错误概率

    public static void main(String[] args){
    	Scanner scanner=new Scanner(System.in);
        System.out.println("请输入离散信源概率分布和二进制序列长度：");
        oriPr=scanner.nextDouble();
        oriLen=scanner.nextInt();
        
        System.out.println("请选择信源编码器（信源编码中的香农费诺编码尚未完成）");
        System.out.println("1.无编码");
        System.out.println("2.二进制香农-费诺编码");
        System.out.println("3.二进制霍夫曼编码");
        sourceCodeType=scanner.nextInt();
        
        System.out.println("请选择信道编码器");
        System.out.println("1.无编码");
        System.out.println("2.3次重复编码");
        System.out.println("3.Hamming（7，4）码");
        channelCodeType=scanner.nextInt();
        
        System.out.println("请选择信道");
        System.out.println("1.理想信道");
        System.out.println("2.给定错误概率为p的BSC信道");
        System.out.println("3.给定符号0，1各自错误概率p，q");
        channelType=scanner.nextInt();
        
        if (channelType==1){
            errPr1=0;
        }else if (channelType==2){
            System.out.println("请输入信道错误率");
            errPr1=scanner.nextDouble();
        }else if (channelType==3){
            System.out.println("请输入0，1各自错误率如 0.2 0.5");
            errPr1=scanner.nextDouble();
            errPr2=scanner.nextDouble();
        }
        scanner.close();
        
        /****开始运算****/
        //生成离散信源，存放在int型数组中
        int[] original = new int[oriLen];
        for (int i = 0; i < oriLen; i++) {
        	if (Math.random() < oriPr) {
        		original[i] = 1;
        	}
        	else{
        		original[i] = 0;
        	}
        }
        //将int型数组转化为字符串
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }
        //编码
        Map<Character, Integer> statistics=null; //如果是Huffman信源编码，需计算这个
        statistics=Huffman.statistics(originStr.toCharArray());
        
        int[] sourceEncodeResult= SourceCodeChoose.encode(sourceCodeType,original,statistics);
        int[] channelEncodeResult= ChannelCodeChoose.encode(channelCodeType,sourceEncodeResult);
        int[] transmitResult;
        
        if (channelType==1||channelType==2){
            //如果是理想信道或者给定错误概率为p的BSC
            transmitResult=BSC.send1(channelEncodeResult,errPr1);
        }else {
            //如果指定0、1各自错误率
            transmitResult=BSC.send2(channelEncodeResult,errPr1,errPr2);
        }
        
        //解码
        int parityCount=0;//Hamming码校验位的长度
        if (channelCodeType==3){
            //如果是以Hamming码传输需要算出校验位的长度
            parityCount=channelEncodeResult.length-sourceEncodeResult.length;
        }
        int[] channelDecodeResult=ChannelCodeChoose.decode(channelCodeType,transmitResult,parityCount);
        int[] sourceDecodeResult=SourceCodeChoose.decode(sourceCodeType,channelDecodeResult,statistics);
        
        //输出结果
        println("原始序列",original);
        println("信源编码后序列",sourceEncodeResult);
        println("信道编码后序列",channelEncodeResult);
        println("信道传输后序列",transmitResult);
        println("信道解码后序列",channelDecodeResult);
        println("信源解码后序列",sourceDecodeResult);
        
        //BER 是在数据传输过程中比特被传错的概率
        //误码率=传输中的误码/所传输的总码数*100%
        double codeEffectiveness=(double) original.length/channelEncodeResult.length;//信道编码后的传输效率
        double BER=(double)getErrorCount(sourceDecodeResult,original)/original.length;//整个过程中数据被传错的概率
        double channelEncodeError=(double)getErrorCount(channelDecodeResult,sourceEncodeResult)/sourceDecodeResult.length;//信道编译码过程中传错的概率
        System.out.printf("信道编码后的信息传输效率：%.2f%%\n",codeEffectiveness*100);
        System.out.printf("整个通信过程中的误比特率：%.2f%%\n",BER*100);
        System.out.printf("信道编译码过程中产生的误码率：%.2f%%\n",channelEncodeError*100);
    }
    private static int getErrorCount(int[] data1, int[] data2){
        if (data1.length != data2.length) return 0;
        int errorCount=0;
        for (int i = 0; i < data1.length; i++) {
            if (data1[i]!=data2[i]){
                errorCount++;
            }
        }
        return errorCount;
    }
    private static void println(String message,int[] data){
        System.out.println(message);
        for (int i:data){
            System.out.printf(i+" ");
        }
        System.out.println("\n====================");
    }
}
