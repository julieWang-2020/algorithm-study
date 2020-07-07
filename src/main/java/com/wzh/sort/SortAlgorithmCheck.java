package com.wzh.sort;

import com.wzh.util.AlgorithmHelper;

import java.util.Arrays;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-28 14:41
 **/
public class SortAlgorithmCheck {


    public static void main(String[] args) {
        SortAlgorithmCheck check=new SortAlgorithmCheck();
        check.checkOther(SortTypeEnum.SORT_TYPE_SELECTION);
        check.checkOther(SortTypeEnum.SORT_TYPE_BUBBLE);
        check.checkOther(SortTypeEnum.SORT_TYPE_INSERTION);
        check.checkOther(SortTypeEnum.SORT_TYPE_SHELL);
        check.checkOther(SortTypeEnum.SORT_TYPE_QUICK);
        check.checkMerge();
    }

    private void checkOther(SortTypeEnum type){
        if(type.equals(SortTypeEnum.SORT_TYPE_MERGE)) throw new RuntimeException("not support sort");
        boolean same=true;
        for(int times=0;times<1000;times++){
            int[] a=AlgorithmHelper.genFixedLengthRandomArray(10000);
            int[] copyA=sortCheck(type,a);
            Arrays.sort(a);

            for(int i=0;i<copyA.length;i++){
                if(a[i]!=copyA[i]) same=false;
            }
        }
        System.out.println(same==true?type.getValue()+" Check right":type.getValue()+" Check wrong");
    }

    private void checkMerge() {
        boolean same=true;
        for(int times=0;times<1000;times++){
            int[] a51= AlgorithmHelper.genFixedLengthRandomArray(5000);
            Arrays.sort(a51);
            int[] a52=AlgorithmHelper.genFixedLengthRandomArray(5000);
            Arrays.sort(a52);

            int[] a=new int[10000];
            System.arraycopy(a51,0,a,0,a51.length);
            System.arraycopy(a52,0,a,5000,a52.length);

            int[] copyA=sortCheck(SortTypeEnum.SORT_TYPE_MERGE,a);
            Arrays.sort(a);
            for(int i=0;i<copyA.length;i++){
                if(a[i]!=copyA[i]) same=false;
            }
        }
        System.out.println(same==true?SortTypeEnum.SORT_TYPE_MERGE.getValue()+" Check right":SortTypeEnum.SORT_TYPE_MERGE.getValue()+" Check wrong");
    }
    private int[]  sortCheck(SortTypeEnum type,int[] a) {

        int[] copyA=new int[a.length];
        System.arraycopy(a,0,copyA,0,a.length);

        switch (type){
            case SORT_TYPE_SELECTION:
                SortAlgorithm.selectionSort(copyA);
                break;
            case SORT_TYPE_BUBBLE:
                SortAlgorithm.bubbleSort(copyA);
                break;
            case SORT_TYPE_INSERTION:
                SortAlgorithm.insertionSort(copyA);
                break;
            case SORT_TYPE_SHELL:
                SortAlgorithm.shellSort(copyA);
                break;
            case SORT_TYPE_MERGE:
                SortAlgorithm.mergeSort(copyA,0,copyA.length-1);
                break;
            case SORT_TYPE_QUICK:
                SortAlgorithm.quickSort(copyA,0,copyA.length-1);
                break;
            default:
                System.out.println(type.getValue()+" not exist");
        }
        return copyA;
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