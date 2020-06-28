package com.wzh.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: 排序算法
 * @author: Wangzh
 * @create: 2020-06-28 09:44
 **/
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] a1={9,3,5,1,2,6,7,4,8};
        selectionSort(a1);
        print(a1);

        int[] a2={9,3,5,1,2,6,7,4,8};
        bubbleSort(a2);
        print(a2);

        int[] a3={9,3,5,1,2,6,7,4,8};
        insertionSort(a3);
        print(a3);

        int[] a4={9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};
        shellSort(a4);
        print(a4);

        int[] a5={1,4,7,8,3,6,9};
        mergeSort(a5,0,a5.length-1);
        print(a5);
        
        int[] a6={1,4,6,9,10,2,3,5,8,7,6};
        quickSort(a6,0,a6.length-1);
        print(a6);
    }

   
   

    /**
     * 快速排序
     * 不稳定排序
     * 平均时间复杂度 O(N*logN)
     * 平均时间复杂度 O(N*logN)
     * 平均时间复杂度 O(N^2)
     * 空间复杂度 O(logN)
     * @param a
     * @param leftBound
     * @param rightBound
     */
    public static void quickSort(int[] a,int leftBound,int rightBound) {
        if(leftBound >= rightBound ) return;
        int mid=partition(a,leftBound,rightBound);
        quickSort(a,leftBound,mid-1);
        quickSort(a,mid+1,rightBound);
    }

    public static int partition(int[] a, int leftBound, int rightBound) {
        int pivot = a[rightBound];
        int left = leftBound;
        int right = rightBound-1;

        while (left <= right){
            while (left <= right && a[left] <= pivot) left++;
            while (left <= right && a[right] > pivot) right--;
            if(left < right ) swap(a,left,right);
        }
        swap(a,left,rightBound);
        return left;
    }

    /**
     * 归并排序
     * 稳定排序3
     * 平均时间复杂度 O(N* logN)
     * 最好时间复杂度 O(N* logN)
     * 最坏时间复杂度 O(N* logN)
     * 空间复杂度 O(N)
     * @param a
     */
    public static void mergeSort(int[] a, int left, int right) {
        if(left==right) return ;

        int mid=left +(right -left)/2;
        // 左边排序
        mergeSort(a,left,mid);
        // 右边排序
        mergeSort(a,mid+1,right);

        merge(a,left,mid+1,right);
    }


    public static void merge(int[] a,int leftPtr,int rightPtr,int rightBound) {
        if(leftPtr==rightPtr) return ;
//        System.out.println(leftPtr+","+rightPtr+","+rightBound);
        int mid= rightPtr -1 ;
        int[] tem=new int[rightBound - leftPtr +1];
        int i=leftPtr;
        int j=rightPtr;
        int k=0;
        while (i<=mid && j<=rightBound){
            tem[k++]= a[i]<=a[j] ? a[i++] : a[j++];
        }
        while (i<=mid) tem[k++]=a[i++];
        while (j<=rightBound) tem[k++]=a[j++];

        for(int m=leftPtr;m<tem.length;m++){
            a[m]=tem[m];
        }
    }

    /**
     * 希尔排序
     * 不稳地排序
     * 平均时间复杂度：O(n^1.3)
     * 最好时间复杂度：O(n)
     * 最坏时间复杂度：O(n^2)
     * 空间复杂度 O(1)
     * @param a
     */
    public static void shellSort(int[] a) {
        int gap=1;
        while (gap<a.length){
            gap=gap * 3 +1;
        }
       while (gap>0){
           for(int i= gap;i<a.length;i++){
               for(int j=i; j>gap-1 ; j-=gap){
                   if(a[j]<a[j-gap]) swap(a,j,j-gap);
               }
           }
           gap= (int) Math.floor(gap/3);
       }

    }

    /**
     * 插入排序
     * 稳定排序
     * 平均时间复杂度：O(n^2)
     * 最好时间复杂度：O(n)
     * 最坏时间复杂度：O(n^2)
     * 空间复杂度 O(1)
     * @param a
     */
    public static void insertionSort(int[] a) {
        for(int i=1; i<a.length;i++){
            int j=i;
            int temp=a[j];
            while (j>0 && temp<a[j-1]){
                a[j]=a[j-1];
                j--;
            }
            if(j!=i){
                a[j]=temp;
            }
//            for(int j=i; j>0 && a[j]<a[j-1]; j--){
//                swap(a,j,j-1);
//            }
        }
    }

    /**
     * 冒泡排序
     * 两两比较，两两交换
     * 稳定排序算法
     * 平均时间复杂度：O(n^2)
     * 最好时间复杂度：O(n)
     * 最坏时间复杂度：O(n^2)
     * 空间复杂度 O(1)
     * @param a
     */
    public static void bubbleSort(int[] a) {
        for(int i=1;i<a.length;i++){
            boolean flag=true;
            for(int j=0;j<a.length-i;j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                    flag=false;
                }
            }
            if(flag) break;
        }
    }


    /**
     * 选择排序
     * 找到剩余最小的值，交换下标
     * 不稳地排序
     * 平均时间复杂度：O(n^2)
     * 最好时间复杂度：O(n^2)
     * 最坏时间复杂度：O(n^2)
     * 空间复杂度 O(1)
     * @param a 数组a
     */
    public static void selectionSort(int[] a) {
        int min;
        for(int i=0;i<a.length;i++){
            min=i;
            for(int j=i+1;j<a.length;j++){
                if(a[min]>a[j]) min=j;
            }
            if(min!=i) swap(a,i,min);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public static void print(int[] a) {
        System.out.println();
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}
