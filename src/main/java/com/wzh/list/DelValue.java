package com.wzh.list;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-09 16:44
 **/
public class DelValue {


    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        for(int i=0;i<testTime;i++){
            ReverseList.Node node1=ReverseList.generateRandomLinkedList(len,value);
            ReverseList.Node delNode=delValue(node1,20);
            if(delNode!=null && delNode.exist(20)){
                System.out.println("oops!");
                node1.print();
                delNode.print();
            }

        }

    }

    /**
     * 删除指定值
     * @param head
     * @param value
     * @return
     */
    private static ReverseList.Node delValue(ReverseList.Node head, int value) {
//        ReverseList.Node newHead=null;
        while (head!=null){
            if(head.value!=value){
                break;
            }
            head=head.next;
        }

        ReverseList.Node pre=head;
        ReverseList.Node cur=head;
        while (cur!=null){
            if(cur.value==value){
                pre.next=cur.next;
            }else{
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }
}
