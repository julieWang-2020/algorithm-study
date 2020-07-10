package com.wzh.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: 使用数组实现栈和队列
 * @author: Wangzh
 * @create: 2020-07-09 16:58
 **/
public class StackAndQueueArrayImpl {

    /**
     * 队列，先进先出，尾部进入，头部弹出
     * @param <T>
     */
    public static class MyQueue{

        private int[] a;
        private int size;
        private int pushIndex;
        private int pollIndex;
        private final int limit;



        public MyQueue(int len) {
            a=new int[len];
            this.limit=len;
        }

        public void push(int value){
            if(size == limit) throw new RuntimeException("queue is full ");
            size++;
            a[pushIndex]=value;
            pushIndex=nextIndex(pushIndex);
        }

        /**
         * 将首个元素从队列中弹出，如果队列是空的，就返回null
         * @return
         */
        public int poll(){
            if(size==0)  throw new RuntimeException("queue is empty ");

            size--;
            int t=a[pollIndex];
            pollIndex=nextIndex(pollIndex);
            return  t;
        }

        public boolean isEmpty(){
            return size==0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

    /**
     * 栈，先进后出，头部进入，头部弹出
     * @param <T>
     */
    public static class MyStack{
        private int[] a;
        private int size;
        private int pushIndex;
        private final int limit;

        public MyStack(int len) {
            a=new int[len];
            this.limit=len;
        }


        public void push(int value){
            if(size == limit) throw new RuntimeException("stack is full ");

            size++;
            a[pushIndex]=value;
            pushIndex=nextIndex(pushIndex);
        }

        /**
         * 将首个元素从队列中弹出，如果队列是空的，就返回null
         * @return
         */
        public int pop(){
            if(size == 0) throw new RuntimeException("stack is empty ");

            size--;
            int t=a[--pushIndex];
            return t;
        }

        public boolean isEmpty(){
            return size==0;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }


    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for(int i=0;i<testTimes;i++){
            MyStack myStack = new MyStack(oneTestDataNum);
            MyQueue myQueue = new MyQueue(oneTestDataNum);

            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if(stack.isEmpty()){
                    stack.push(nums);
                    myStack.push(nums);
                }else {
                    if(Math.random() < 0.5) {
                        stack.push(nums);
                        myStack.push(nums);
                    }else {
//                        System.out.println(myStack.pop()+"----"+stack.pop());
                        if(!StackAndQueueDoubleListImpl.isEqual(myStack.pop(),stack.pop())){
                            System.out.println("stack oops!");
                        }
                    }
                }

                if(queue.isEmpty()){
                    queue.offer(nums);
                    myQueue.push(nums);
                }else {
                    if(Math.random() < 0.5) {
                        queue.offer(nums);
                        myQueue.push(nums);
                    }else {
                        if(!StackAndQueueDoubleListImpl.isEqual(queue.poll(),myQueue.poll())){
                            System.out.println("queue oops!");
                        }
                    }
                }
            }
        }


        System.out.println("finish!");
    }

    }


