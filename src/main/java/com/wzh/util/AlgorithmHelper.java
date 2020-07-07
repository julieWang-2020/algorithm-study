package com.wzh.util;

import java.util.Random;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 09:45
 **/
public class AlgorithmHelper {

    private AlgorithmHelper(){}

    public static int[] genFixedLengthRandomArray(int len) {
        Random r=new Random();
        int[] a=new int[len];
        for(int i=0;i<len;i++){
            a[i]=r.nextInt();
        }
        return a;
    }
    public static int[] genFixedLengthRandomArray(int len, int maxValue) {
        Random r=new Random();
        // Math.random() [0,1)
        // Math.random()*N [0,N)
        // (int)(Math.random() * N) [0,N-1]
        int[] a=new int[len];
        for(int i=0;i<a.length;i++){
            a[i]=(int)(maxValue+1*Math.random()) - (int)(maxValue*Math.random());
//            a[i]=r.nextInt();
        }
        return a;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()   [0,1)
        // Math.random() * N  [0,N)
        // (int)(Math.random() * N)  [0, N-1]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
