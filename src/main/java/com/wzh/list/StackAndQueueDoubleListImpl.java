package com.wzh.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-09 16:58
 **/
public class StackAndQueueDoubleListImpl {
    public static class Node<T> {
        public T value;
        public Node<T> prev;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T>{
        public Node<T> head;
        public Node<T> tail;

        public int size;

        /**
         * 判断队列是否为空
         * @return true:空，false:非空
         */
        public boolean isEmpty(){
            return head==null;
        }


        /**
         * 从尾部加入数据
         * 先进先出
         * @param value 加入的数据
         */
        public void addFromBottom(T value){
            Node<T> cur=new Node<>(value);
            if(head==null){
                head=cur;
                tail=cur;
            }else {
                tail.next=cur;
                cur.prev=tail;
                tail=cur;
            }
        }
        /**
         * 从头部加入数据
         * 先进先出
         * @param value 加入的数据
         */
        public void addFromHead(T value){
            Node<T> cur=new Node<>(value);
            if(head==null){
                head=cur;
                tail=cur;
            }else {
                head.prev=cur;
                cur.next=head;
                head=cur;
            }
        }

        /**
         * 从头部加入数据
         * @return
         */
        public T getFromHead(){
            if(head==null) return null;

            Node<T> cur=head;
            if(head==tail){
                head=null;
                tail=null;
            }else {
                head=head.next;
                head.prev=null;
                cur.next=null;
            }
            return cur.value;
        }

        /**
         * 从头部加入数据
         * @return
         */
        public T getFromBottom(){
            if(head==null) return null;

            Node<T> cur=tail;
            if(head==tail){
                head=null;
                tail=null;
            }else {
                tail=tail.prev;
                tail.next=null;
                cur.prev=null;
            }
            return cur.value;
        }
    }


    /**
     * 队列，先进先出，尾部进入，头部弹出
     * @param <T>
     */
    public static class MyQueue<T>{

        private DoubleEndsQueue<T> queue=new DoubleEndsQueue<>();

        public void push(T value){
            queue.addFromBottom(value);
        }

        /**
         * 将首个元素从队列中弹出，如果队列是空的，就返回null
         * @return
         */
        public T poll(){
            return queue.getFromHead();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    /**
     * 栈，先进后出，头部进入，头部弹出
     * @param <T>
     */
    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue=new DoubleEndsQueue<>();

        public void push(T value){
            queue.addFromHead(value);
        }

        /**
         * 将首个元素从队列中弹出，如果队列是空的，就返回null
         * @return
         */
        public T pop(){
            return queue.getFromHead();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for(int i=0;i<testTimes;i++){
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();

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
                        if(!isEqual(myStack.pop(),stack.pop())){
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
                        if(!isEqual(queue.poll(),myQueue.poll())){
                            System.out.println("queue oops!");
                        }
                    }
                }
            }
        }


        System.out.println("finish!");
    }

    public static boolean isEqual(Integer value1, Integer value2) {
        return value1.equals(value2);
    }
}
