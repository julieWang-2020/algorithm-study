package com.wzh.sort;

import java.util.Arrays;

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

        int[] a7={2,4,2,3,7,1,1,0,0,5,6,9,8,5,7,4,0,9};
        a7=countingSort(a7);
        print(a7);
        Arrays.sort(a7);
        System.out.println(Arrays.toString(a7));

        int[] a8={421,240,115,532,305,430,124};
        a8=radixSort(a8);
        print(a8);
    }

    /**
     * 基数排序，多关键字排序,桶思想的一种
     * 稳定排序
     * 平均时间复杂度 O(n*k)
     * 最好时间复杂度 O(n*k)
     * 最坏时间复杂度 O(n*k)
     * 空间复杂度 O(n)
     * @param a
     */
    public static int[] radixSort(int[] a){
        int[] r=new int[a.length];
        int[] count=new int[10];
        int step=getArrayStep(a);
        for(int i=0;i<step;i++){
            int division= (int) Math.pow(10,i);
            System.out.println(division);
            for(int j=0;j<a.length;j++){
                int num=a[j]/division%10;
//                System.out.println(num);
                count[num]++;
            }
            print(count);
            for(int m=1;m<count.length;m++){
                count[m]=count[m]+count[m-1];
            }
            print(count);
            for(int k=a.length-1;k>=0;k--){
                int num=a[k]/division%10;
                r[--count[num]]=a[k];
            }
            System.arraycopy(r,0,a,0,a.length);
            print(r);
            Arrays.fill(count,0);
        }
        return r;
    }

    private static int getArrayStep(int[] a) {
        int step=0;
        for(int i:a){
            if(i>step) step=i;
        }
        return (step+"").length();
    }

    /**
     * 计数排序，非比较排序，取值0到9
     * 适合量大，取值范围小,桶思想的一种
     * 稳定排序
     * 平均时间复杂度 O(N+k)
     * 最好时间复杂度 O(N+k)
     * 最坏时间复杂度 O(N+k)
     * 空间复杂度 O(N+k)
     */
    public static int[] countingSort(int[] a){
        int[] result=new int[a.length];
        int[] count=new int[10];
        // 计数数组，
        // 代表原数组中每个数字出现的次数，
        // 小标表示原数组值，
        // 值代表原数组中数值出现次数
        for(int i=0;i<a.length;i++){
            count[a[i]]++;
        }
        print(count);
        // 累加数组，使计数算法变为稳定排序
        // 记录没一个下标值最后出现的位置
        for(int i=1;i<count.length;i++){
            count[i]=count[i]+count[i-1];
        }
        print(count);
//        for(int i=0,j=0;i<count.length;i++){
//            while (count[i]-- >0){
//                result[j++]=i;
//            }
//        }

        for(int i=a.length-1;i>=0;i--){
//            System.out.println("------------");
//            System.out.println("i="+i+",a[i]="+a[i]+",count[a[i]]="+count[a[i]]);
            result[-- count[a[i]]]=a[i];
//            System.out.println("i="+i+",a[i]="+a[i]+",count["+a[i]+"]="+count[a[i]]+",result["+count[a[i]]+"]="+a[i]);
//            while (count[a[i]]>0){
//            }
        }
        return result;
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
        if(a==null || a.length<2) return;

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
        if(a==null || a.length<2) return;

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
        if(a==null || a.length<2) return;

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
        StringBuilder s=new StringBuilder("[");
        for(int i=0;i<a.length;i++){
            s.append(a[i]);
            if(i!=a.length-1){
                s.append(", ");
            }
        }
        s.append("]");
        System.out.println(s);
    }

}
