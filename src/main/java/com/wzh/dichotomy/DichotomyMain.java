package com.wzh.dichotomy;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 09:41
 **/
public class DichotomyMain {

    public static void main(String[] args) {

        int a1[]= {7, 8, 23, 25, 35, 44, 81, 87, 92};
//        Arrays.sort(a1);
//        System.out.println(Arrays.toString(a1));
        System.out.println(existInArray(a1,25));

        System.out.println(nearLeft(a1,20));

        System.out.println(nearRight(a1,24));

        int[] a2= { 92, 69, 14, 91, 74, 7, 58, 23, 43, 21, 96, 66, 20, 55};
        System.out.println(localMin(a2));
    }

    /**
     * 获取一个局部最小值的 index
     * @param a 数组
     * @return 符合条件的 index
     */
    private static int localMin(int[] a) {
        if(a==null || a.length==0) return -1;

        if(a.length==1 || a[0]<a[1]) return 0;

        if(a[a.length-1] < a[a.length-2]) return a.length-1;

        int left=0;
        int mid=0;
        int right=a.length-2;

        while (left<right){
            mid=(left+right)/2;
            if(a[mid] >a[mid-1]){
                right=mid-1;
            }else if(a[mid] > a[mid+1]){
                left=mid+1;
            }else {
                return mid;
            }
        }
        return left;
    }

    /**
     * 查找有序数组中 <=num 的最右侧位置的index
     * @param a 有序数组
     * @param num 比较值
     * @return 符合算法最右侧的值
     */
    private static int nearRight(int[] a, int num) {
        if(a==null || a.length==0) return -1;

        int left=0;
        int index=-1;
        int right=a.length-1;
        while (left<right){
            int mid= left+((right-left)>>1);
            if(a[mid]<=num){
                index=mid;
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return index;
    }

    /**
     * 查找有序数组中，>=num 的最左侧位置的index
     * @param a 有序数组
     * @param num 比较值
     * @return 符合算法最左侧的值
     */
    private static int nearLeft(int[] a, int num) {
        if(a==null || a.length==0) return -1;

        int left=0;
        int index=-1;
        int right=a.length-1;

        while (left<right){
            int mid=left+ ((right-left)>>1);
            if(a[mid]>=num) {
                index=mid;
                right=mid-1;
            }else {
                left=mid+1;
            }
        }

        return index;
    }

    /**
     * 使用二分法，查找有序数组中 num 是否存在
     * @param a 有序数组
     * @param num 查找的值
     * @return true 存在，false 不存在
     */
    private static boolean existInArray(int[] a, int num) {
        if(a==null || a.length==0) return false;

        int left=0;
        int right=a.length-1;
        int mid=0;
        while (left < right){

//            mid=left + ((right-left)/2);
            mid = left + ((right - left) >> 1);
//            System.out.println("a["+left+"]="+a[left]+","+"a["+mid+"]="+a[mid]+","+"a["+right+"]="+a[right]);

            if(a[mid] == num) return true;
            else if (a[mid] > num) right = mid - 1;
            else left = mid + 1;

        }
//        System.out.println("a["+left+"]="+a[left]);
        return a[left] == num;
    }
}
