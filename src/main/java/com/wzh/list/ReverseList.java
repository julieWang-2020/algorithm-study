package com.wzh.list;

import java.util.ArrayList;

/**
 * @description: 链表反转
 * @author: Wangzh
 * @create: 2020-07-08 16:11
 **/
public class ReverseList {

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        for(int i=0;i<testTime;i++){
            Node node1=generateRandomLinkedList(len,value);
            Node reverse1=reverseList(node1);
            Node back1=testReverseLinkedList(reverse1);
            if(!checkLinkedListEqual(node1,back1)){
                node1.print();
                back1.print();
                System.out.println("oops!");
                break;
            }

            DoubleNode doubleNode1=generateRandomDoubleList(len,value);
            DoubleNode reverseDoubleNode1=reverseDoubleList(doubleNode1);
            DoubleNode backDoubleNode1=testReverseDoubleList(reverseDoubleNode1);

            if(!checkDoubleListEqual(doubleNode1,backDoubleNode1)){
                doubleNode1.print();
                backDoubleNode1.print();
                System.out.println("oops!");
                break;
            }
        }
    }

    /**
     * 校验两双向链表是否相等
     * 要求链表无环
     * @param head1 链表1
     * @param head2 链表2
     * @return true 相等
     */
    private static boolean checkDoubleListEqual(DoubleNode head1, DoubleNode head2) {
        boolean null1 = head1 == null;
        boolean null2 = head2 == null;
        if (null1 && null2) {
            return true;
        }
        if (null1 ^ null2) {
            return false;
        }
        if (head1.prev != null || head2.prev != null) {
            return false;
        }
        DoubleNode end1 = null;
        DoubleNode end2 = null;
        while (head1!=null && head2!=null){
            if(head1.value!=head2.value){
                return false;
            }
            end1=head1;
            end2=head2;
            head1=head1.next;
            head2=head2.next;
        }
        if (head1 != null || head2 != null) {
            return false;
        }
        while (end2!=null && end2!=null){
            if(end2.value!=end2.value){
                return false;
            }
            end1=end1.prev;
            end2=end2.prev;
        }
        return end1==null && end2==null;
    }

    private static DoubleNode testReverseDoubleList(DoubleNode head) {
        if(head==null) return null;

        ArrayList<DoubleNode> list=new ArrayList<>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }

        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.prev = null;
            cur.next = pre;
            pre.prev = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }


    private static Node testReverseLinkedList(Node head) {
        if(head==null) return null;

        ArrayList<Node> list=new ArrayList<>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }

        list.get(0).next=null;
        int n=list.size();
        for(int i=1;i<n;i++){
            list.get(i).next=list.get(i-1);
        }
        return list.get(n-1);
    }

    private static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null){
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static Node reverseList(Node head) {
        Node pre=null;
        Node next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    /**
     * 检查两链表是否相等
     * @param head1 链表1
     * @param head2 链表2
     * @return  true 相等
     */
    private static boolean checkLinkedListEqual(Node head1, Node head2) {
        while (head1!=null && head2 !=null){
            if(head1.value!=head2.value){
                return false;
            }
            head1=head1.next;
            head2=head2.next;
        }
        return head1==null && head2==null;
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = getSize(len);
        while (size == 0) {
            size=getSize(len);
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    private static int getSize(int len) {
        return (int) (Math.random() * (len + 1));
    }


    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = getSize(len);
        while (size == 0) {
            size=getSize(len);
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.prev=pre;
            pre = cur;
            size--;
        }
        return head;
    }

    public static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public void print(){
            StringBuilder info=new StringBuilder("{");
            Node temp=this;
            while (temp!=null){
                info.append(temp.value+",");
                temp=temp.next;
            }

            System.out.println(info.substring(0,info.length()-1)+"}");
        }

        public boolean exist(int value) {
            Node temp=this;
            while (temp!=null){
              if(temp.value==value) return true;

              temp=temp.next;
            }
            return false;
        }
    }
    public static class DoubleNode{

        int value;
        DoubleNode next;
        DoubleNode prev;

        public DoubleNode(int value) {
            this.value = value;
        }


        public void print(){
            StringBuffer forward=new StringBuffer("{");
            StringBuffer reverse=new StringBuffer("");
            DoubleNode temp=this;
            while (temp!=null){
                forward.append(temp.value+",");
                reverse.insert(0,temp.value+",");
                temp=temp.next;
            }
            System.out.print(forward.substring(0,forward.length()-1)+"}");
            System.out.println("{"+reverse.substring(0,reverse.length()-1)+"}");
        }
    }
}



