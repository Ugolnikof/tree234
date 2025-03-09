package org.example;

public class Tree234 {
    private Node root = new Node();

    //Поиск элемента данных с заданным ключом
    public int find(long key) {
        Node curNode = root;                                    // Начинаем поиск с корня
        int childNumber;                                        // Вспомогательная переменная

        while (true) {
            childNumber = curNode.findItem(key);                // Проверяем, если ли ключ в текущем элементе данных
            if (childNumber != -1) {                            // Узел найден
                return childNumber;                             // Возвращаем индекс элемента данных
            } else if (curNode.isLeaf()) {
                return -1;                                      // Узел не найден
            } else {
                curNode = getNextChild(curNode, key);           // Искать глубже по дереву
            }
        }
    }

    // Вставка элемента данных с заданным ключом
    public void insert(long key) {
        Node curNode = root;                                    // Начинаем поиск с корня
        DataItem tempItem = new DataItem(key);                  // Создаём объект данных с заданным ключом

        while (true) {
            if (curNode.isFull()) {                             // Если узел полон,
                split(curNode);                                 // он разбивается
                curNode = curNode.getParent();                  // Переход на 1 уровень выше
                curNode = getNextChild(curNode, key);           // Переход к соответствующему потомку для дальнейшего поиска
            } else if (curNode.isLeaf()) {                      // Если узел листовой,
                break;                                          // выходим из цикла
            } else {
                curNode = getNextChild(curNode, key);           // Переход к соответствующему потомку для дальнейшего поиска
            }
        }

        curNode.insertItem(tempItem);                           // Вставка нового элемента данных DataItem
    }

    // Получение соответствующего потомка при поиске элемента данных с заданным ключом
    // (предполагается, что узел не пуст, не полон и не является листовым узлом)
    public Node getNextChild(Node theNode, long theValue) {
        int j;
        int numItems = theNode.getNumItems();

        for (j = 0; j < numItems; j++) {                        // Цикл пробегается по всем элементам данных в узле
            if (theValue < theNode.getItem(j).getDData()) {     // Если текущий ключ меньше ключа элемента данных,
                return theNode.getChild(j);                     // возвращаем левого потомка
            }
        }                                                       // Если текущий ключ больше ключа элемента данных,
        return theNode.getChild(j);                             // возвращаем правого потомка
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.print("level = " + level + ", child = " + childNumber + ": ");
        thisNode.displayNode();

        // Рекурсивный вызов для каждого потомка текущего узла
        int numItems = thisNode.getNumItems();

        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }
        }
    }

    // Разбиение узла (предполагается, что узел полон)
    public void split(Node thisNode) {
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;

        itemC = thisNode.removeItem();                          // Удаление 3-его элемента из узла
        itemB = thisNode.removeItem();                          // Удаление 2-ого элемента из узла
        child2 = thisNode.disconnectChild(2);          // Отсоединение 2-ого потомка от текущего узла
        child3 = thisNode.disconnectChild(3);          // Отсоединение 3-его потомка от текущего узла
        Node newRight = new Node();                             // Создание нового узла

        if (thisNode == root) {                                 // Если узел является корнем,
            root = new Node();                                  // создаём новый корень
            parent = root;                                      // Корень становится родителем текущего узла
            root.connectChild(0, thisNode);            // Связываем корень с текущим узлом
        } else {                                                // Если узел НЕ является корнем,
            parent = thisNode.getParent();                      // получаем родителя текущего узла
        }

        // Разбираемся с родителем
        itemIndex = parent.insertItem(itemB);                   // Элемент данных itemB вставляется в родителя
        int n = parent.getNumItems();                           // Получаем число элементов в родителе

        for (int j = n - 1; j > itemIndex; j--) {               // Перемещение связей родителя на одного потомка вправо
            Node temp = parent.disconnectChild(j);
            parent.connectChild(j + 1, temp);
        }

        parent.connectChild(itemIndex + 1, newRight);  // Связывание newRight с родителем

        // Разбираемся с узлом newRight
        newRight.insertItem(itemC);                             // Элемент данных itemС вставляется в newRight
        newRight.connectChild(0, child2);              // Связывание child2 с newRight
        newRight.connectChild(1, child3);              // Связывание child3 с newRight
    }

    // Метод дял получения минимального значения дерева
    public long getMin() {
        if (root.getNumItems() == 0) {
            return 0;
        } else {
            Node curNode = root;
            while (curNode.getChild(0) != null) {
                curNode = curNode.getChild(0);
            }
            return curNode.getItem(0).getDData();
        }
    }

    // Метод для получения высоты дерева
    public int height() {
        Node curNode = root;

        if (root.getNumItems() == 0) {
            return 0;
        } else {
            int height = 1;
            while (curNode.getChild(0) != null) {
                curNode = curNode.getChild(0);
                height++;
            }
            return height;
        }
    }

}
