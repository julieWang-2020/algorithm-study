package com.wzh.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-28 14:41
 **/
public class SortAlgorithmCheck {


    public static void main(String[] args) {
        SortAlgorithmCheck check=new SortAlgorithmCheck();
        check.sortCheck(SortTypeEnum.SORT_TYPE_SELECTION);
        check.sortCheck(SortTypeEnum.SORT_TYPE_BUBBLE);
        check.sortCheck(SortTypeEnum.SORT_TYPE_INSERTION);
        check.sortCheck(SortTypeEnum.SORT_TYPE_SHELL);
        check.sortCheck(SortTypeEnum.SORT_TYPE_MERGE);
        check.sortCheck(SortTypeEnum.SORT_TYPE_QUICK);
    }

    private void sortCheck(SortTypeEnum type) {
        boolean same=true;
        for(int times=0;times<1000;times++){
            int[] a1=generateRandomArray();
            int[] a2=new int[a1.length];
            System.arraycopy(a1,0,a2,0,a1.length);

            Arrays.sort(a1);
            switch (type){
                case SORT_TYPE_SELECTION:
                    SortAlgorithm.selectionSort(a2);
                    break;
                case SORT_TYPE_BUBBLE:
                    SortAlgorithm.bubbleSort(a2);
                    break;
                case SORT_TYPE_INSERTION:
                    SortAlgorithm.insertionSort(a2);
                    break;
                case SORT_TYPE_SHELL:
                    SortAlgorithm.shellSort(a2);
                    break;
                case SORT_TYPE_MERGE:
                    SortAlgorithm.mergeSort(a2,0,a2.length-1);
                    break;
                case SORT_TYPE_QUICK:
                    SortAlgorithm.quickSort(a2,0,a2.length-1);
                    break;
                default:
                    System.out.println(type.getValue()+" not exist");
            }
            for(int i=0;i<a2.length;i++){
                if(a1[i]!=a2[i]) same=false;
            }
        }
        System.out.println(same==true?type.getValue()+" Check right":type.getValue()+" Check wrong");
    }

    private int[] generateRandomArray() {
        Random r=new Random();
        int[] a=new int[10000];
        for(int i=0;i<10000;i++){
            a[i]=r.nextInt();
        }
        return a;
    }
}

enum SortTypeEnum{

    SORT_TYPE_SELECTION("selectionSort"),
    SORT_TYPE_BUBBLE("bubbleSort"),
    SORT_TYPE_INSERTION("insertionSort"),
    SORT_TYPE_SHELL("shellSort"),
    SORT_TYPE_MERGE("mergeSort"),
    SORT_TYPE_QUICK("quickSort");

    private String value;
    private SortTypeEnum(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}