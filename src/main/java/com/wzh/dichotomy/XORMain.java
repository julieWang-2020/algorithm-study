package com.wzh.dichotomy;

/**
 * @description: 异或运算
 * @author: Wangzh
 * @create: 2020-07-07 11:11
 **/
public class XORMain {

    public static void main(String[] args) {
        int a1[]= {7, 8, 23, 25, 35, 44, 81, 87, 92};
        swap(a1,0,8);
        System.out.println("a[0]="+a1[0]+","+"a[8]="+a1[8]);

        int a=9;
        System.out.println(Integer.toBinaryString(a));
        int b=findRightOne(a);
        System.out.println(Integer.toBinaryString(b));

        int[] a2={3,4,5,3,4,6,5,6,6,7,8,7,8};
        System.out.println(findOddCount(a2));

        int[] a3={3,4,5,3,4,6,5,6,6,7,8,7,8,8};
        findOddCount2(a3);



    }

    /**
     *  arr中，只有一种数，出现奇数次
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，找到出现奇数次的数
     * @param a 数组
     * @return 出现奇数次的数
     */
    private static void findOddCount2(int[] a) {
        int eor = 0;
        for (int i = 0; i <a.length; i++) {
            eor ^= a[i];
        }
        // eor =a^b
        // eor !=0
        // eor 必然有一个位置上是 1
        int rightOne=eor & (~eor +1);
        int onlyOne=0;
        for(int i=0;i<a.length;i++){
            if((a[i] & rightOne) !=0){
                onlyOne ^=a[i];
            }
        }

        System.out.println(onlyOne+","+(eor ^ onlyOne));
    }

    /**
     * 找到num 最右侧的 1
     * @param num 数字
     * @return
     */
    private static int findRightOne(int num) {
        return num & (~num +1);
    }

    /**
     * 不需要额外空间，交换两数
     * @param a 数组
     * @param i index i
     * @param j index j
     */
    private static void swap(int[] a, int i, int j) {
        a[i]=a[i]^a[j];
        a[j]=a[i]^a[j];
        a[i]=a[i]^a[j];
    }


    /**
     *  arr中，只有一种数，出现奇数次
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到出现奇数次的数
     * @param a 数组
     * @return 出现奇数次的数
     */
    public static int findOddCount(int a[]){
        int res=0;
        for(int i=0;i<a.length;i++){
            res=res^a[i];
        }
        return res;
    }

}
