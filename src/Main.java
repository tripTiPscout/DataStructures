import LinkedList.LinkedList;
import LinkedList.LinkedListCustom;

public class Main {
    public static void main(String[] args) {

        testLinkedList();

        testLinkedListCustom();


    }

    private static void testLinkedListCustom() {
        LinkedListCustom<String> customLinkedList = new LinkedListCustom<String>();

        customLinkedList.addFirst("Val");
        customLinkedList.addFirst("Kal");
        customLinkedList.addFirst("Gol");
        customLinkedList.addFirst("Bin");
        customLinkedList.addLast("Zan");
        customLinkedList.addLast("Yak");
        customLinkedList.add(4, "Maj");
        System.out.println(customLinkedList.getFirst());
        System.out.println(customLinkedList.getLast());
        System.out.println(customLinkedList.getBy(0));
        System.out.println(customLinkedList.getIndexOf("Maj"));
        System.out.println(customLinkedList.remove("Maj"));
        System.out.println(customLinkedList.remove(2));
        System.out.println(customLinkedList.removeLast());
        customLinkedList.print();
        System.out.println(customLinkedList);
        System.out.println(customLinkedList.size());
        customLinkedList.forEach(e -> System.out.println(e + "XXX"));
        System.out.println(customLinkedList.getIndexOf("Bin"));
    }

    private static void testLinkedList() {
        LinkedList<Integer> myList = new LinkedList<Integer>();

        myList.add(5);
        myList.add(3);
        myList.add(8);
        myList.add(7);
        myList.add(10, 1);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();
        System.out.println(myList.find(6));
        int element = myList.get(1);
        System.out.println(element);
        System.out.println(myList.size());
        System.out.println(myList.isEmpty());
        int element1 = myList.remove(0);
        System.out.println(element1);
        int index = myList.remove(Integer.valueOf(10));
        System.out.println(index);
    }

}