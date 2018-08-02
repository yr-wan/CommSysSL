package com.yrwan.commsyssL;

import java.util.Map;
import java.util.Scanner;

import com.yrwan.channel.BSC;
import com.yrwan.channelcode.ChannelCodeChoose;
import com.yrwan.sourcecode.SourceCodeChoose;
import com.yrwan.sourcecode.huffman.Huffman;

//��Դ�����е�huffman����ο��Ի���������ũ��ŵ������δ���
public class Main {
	private static double oriPr;//��ɢ��Դ�ֲ�����
    private static int oriLen;//���������г���
    private static int sourceCodeType;//��Դ������
    private static int channelCodeType;//�ŵ�������
    private static int channelType;//�ŵ�ѡ��
    private static double errPr1=0;//����������
    private static double errPr2=0;//����������

    public static void main(String[] args){
    	Scanner scanner=new Scanner(System.in);
        System.out.println("��������ɢ��Դ���ʷֲ��Ͷ��������г��ȣ�");
        oriPr=scanner.nextDouble();
        oriLen=scanner.nextInt();
        
        System.out.println("��ѡ����Դ����������Դ�����е���ũ��ŵ������δ��ɣ�");
        System.out.println("1.�ޱ���");
        System.out.println("2.��������ũ-��ŵ����");
        System.out.println("3.�����ƻ���������");
        sourceCodeType=scanner.nextInt();
        
        System.out.println("��ѡ���ŵ�������");
        System.out.println("1.�ޱ���");
        System.out.println("2.3���ظ�����");
        System.out.println("3.Hamming��7��4����");
        channelCodeType=scanner.nextInt();
        
        System.out.println("��ѡ���ŵ�");
        System.out.println("1.�����ŵ�");
        System.out.println("2.�����������Ϊp��BSC�ŵ�");
        System.out.println("3.��������0��1���Դ������p��q");
        channelType=scanner.nextInt();
        
        if (channelType==1){
            errPr1=0;
        }else if (channelType==2){
            System.out.println("�������ŵ�������");
            errPr1=scanner.nextDouble();
        }else if (channelType==3){
            System.out.println("������0��1���Դ������� 0.2 0.5");
            errPr1=scanner.nextDouble();
            errPr2=scanner.nextDouble();
        }
        scanner.close();
        
        /****��ʼ����****/
        //������ɢ��Դ�������int��������
        int[] original = new int[oriLen];
        for (int i = 0; i < oriLen; i++) {
        	if (Math.random() < oriPr) {
        		original[i] = 1;
        	}
        	else{
        		original[i] = 0;
        	}
        }
        //��int������ת��Ϊ�ַ���
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }
        //����
        Map<Character, Integer> statistics=null; //�����Huffman��Դ���룬��������
        statistics=Huffman.statistics(originStr.toCharArray());
        
        int[] sourceEncodeResult= SourceCodeChoose.encode(sourceCodeType,original,statistics);
        int[] channelEncodeResult= ChannelCodeChoose.encode(channelCodeType,sourceEncodeResult);
        int[] transmitResult;
        
        if (channelType==1||channelType==2){
            //����������ŵ����߸����������Ϊp��BSC
            transmitResult=BSC.send1(channelEncodeResult,errPr1);
        }else {
            //���ָ��0��1���Դ�����
            transmitResult=BSC.send2(channelEncodeResult,errPr1,errPr2);
        }
        
        //����
        int parityCount=0;//Hamming��У��λ�ĳ���
        if (channelCodeType==3){
            //�������Hamming�봫����Ҫ���У��λ�ĳ���
            parityCount=channelEncodeResult.length-sourceEncodeResult.length;
        }
        int[] channelDecodeResult=ChannelCodeChoose.decode(channelCodeType,transmitResult,parityCount);
        int[] sourceDecodeResult=SourceCodeChoose.decode(sourceCodeType,channelDecodeResult,statistics);
        
        //������
        println("ԭʼ����",original);
        println("��Դ���������",sourceEncodeResult);
        println("�ŵ����������",channelEncodeResult);
        println("�ŵ����������",transmitResult);
        println("�ŵ����������",channelDecodeResult);
        println("��Դ���������",sourceDecodeResult);
        
        //BER �������ݴ�������б��ر�����ĸ���
        //������=�����е�����/�������������*100%
        double codeEffectiveness=(double) original.length/channelEncodeResult.length;//�ŵ������Ĵ���Ч��
        double BER=(double)getErrorCount(sourceDecodeResult,original)/original.length;//�������������ݱ�����ĸ���
        double channelEncodeError=(double)getErrorCount(channelDecodeResult,sourceEncodeResult)/sourceDecodeResult.length;//�ŵ�����������д���ĸ���
        System.out.printf("�ŵ���������Ϣ����Ч�ʣ�%.2f%%\n",codeEffectiveness*100);
        System.out.printf("����ͨ�Ź����е�������ʣ�%.2f%%\n",BER*100);
        System.out.printf("�ŵ�����������в����������ʣ�%.2f%%\n",channelEncodeError*100);
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
