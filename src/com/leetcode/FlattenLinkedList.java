package com.leetcode;

class FlattenLinkedList
{

    Node head;  // head of list



    /* Linked list Node*/

    class Node

    {

        int data;

        Node right, down;

        Node(int data)

        {

            this.data = data;

            right = null;

            down = null;

        }

    }



    // An utility function to merge two sorted linked lists

    Node merge(Node a, Node b)

    {
        Node head = null;
        Node headCopy = null;
        while(a!=null && b!=null){
            if(a.data<b.data){
                Node[] update = update(head, headCopy, a);
                head = update[0];
                headCopy = update[1];
                a = update[2];
            }
            else{
                Node[] update = update(head, headCopy, b);
                head = update[0];
                headCopy = update[1];
                b = update[2];
            }
        }
        if(a!=null){
            while(a!=null){
                Node[] update = update(head, headCopy, a);
                head = update[0];
                headCopy = update[1];
                a = update[2];
            }
        }

        if(b!=null){
            while(b!=null){
                Node[] update = update(head, headCopy, b);
                head = update[0];
                headCopy = update[1];
                b = update[2];
            }
        }

        return head;
    }

    public Node[] update(Node head, Node headCopy, Node a){
        if(head == null) {
            head = a;
            headCopy = head;
        }
        else{
            headCopy.down = a;
            headCopy = headCopy.down;
        }
        a = a.down;

        return new Node[]{head, headCopy , a};
    }



    Node flatten(Node root)

    {

       // todo: be implemented
        if(root == null) return null;
        Node a = root;
        Node b = a;

        while(b!=null){
            b = b.right;
            a = merge(a, b);
        }

        return root;

    }



    /* Utility function to insert a node at beginning of the

       linked list */

    Node push(Node head_ref, int data)

    {

        /* 1 & 2: Allocate the Node &

                  Put in the data*/

        Node new_node = new Node(data);



        /* 3. Make next of new Node as head */

        new_node.down = head_ref;



        /* 4. Move the head to point to new Node */

        head_ref = new_node;



        /*5. return to link it back */

        return head_ref;

    }



    void printList()

    {

        Node temp = head;

        while (temp != null)

        {

            System.out.print(temp.data + " ");

            temp = temp.down;

        }

        System.out.println();

    }



    /* Driver program to test above functions */

    public static void main(String args[])

    {

        FlattenLinkedList L = new FlattenLinkedList();



        /* Let us create the following linked list

            5 -> 10 -> 19 -> 28

            |    |     |     |

            V    V     V     V

            7    20    22    35

            |          |     |

            V          V     V

            8          50    40

            |                |

            V                V

            30               45

        */



        L.head = L.push(L.head, 30);

        L.head = L.push(L.head, 8);

        L.head = L.push(L.head, 7);

        L.head = L.push(L.head, 5);



        L.head.right = L.push(L.head.right, 20);

        L.head.right = L.push(L.head.right, 10);



        L.head.right.right = L.push(L.head.right.right, 50);

        L.head.right.right = L.push(L.head.right.right, 22);

        L.head.right.right = L.push(L.head.right.right, 19);



        L.head.right.right.right = L.push(L.head.right.right.right, 45);

        L.head.right.right.right = L.push(L.head.right.right.right, 40);

        L.head.right.right.right = L.push(L.head.right.right.right, 35);

        L.head.right.right.right = L.push(L.head.right.right.right, 20);



        // flatten the list

        L.head = L.flatten(L.head);



        L.printList();

    }

}
