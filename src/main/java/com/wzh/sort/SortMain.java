package com.wzh.sort;

/**
 * @description: 排序算法
 * @author: Wangzh
 * @create: 2020-06-28 09:44
 **/
public class SortMain {

    public static void main(String[] args) {
        SortMain main=new SortMain();
        int[] a1={9,3,5,1,2,6,7,4,8};

        main.selectionSort(a1);
        main.print(a1);

        int[] a2={9,3,5,1,2,6,7,4,8};
        main.bubbleSort(a2);
        main.print(a2);

        int[] a3={9,3,5,1,2,6,7,4,8};
        main.insertionSort(a3);
        main.print(a3);

        int[] a4={9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};
        main.shellSort(a4);
        main.print(a4);
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
    private void shellSort(int[] a) {
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
    public void insertionSort(int[] a) {
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
    public void bubbleSort(int[] a) {
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
    public void selectionSort(int[] a) {
        int min;
        for(int i=0;i<a.length;i++){
            min=i;
            for(int j=i+1;j<a.length;j++){
                if(a[min]>a[j]) min=j;
            }
            if(min!=i) swap(a,i,min);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public void print(int[] a) {
        System.out.println();
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}
