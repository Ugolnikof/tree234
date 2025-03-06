package org.example;

import lombok.Data;

@Data
public class Node {
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node[] childArray = new Node[ORDER];
    private DataItem[] itemArray = new DataItem[ORDER - 1];

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    // Получение объекта DataItem с заданным индексом
    public DataItem getItem(int index) {
        return itemArray[index];
    }

    // Является ли узел листовым
    public boolean isLeaf() {
        return childArray[0] == null;
    }

    // Является ли узел заполненным
    public boolean isFull() {
        return numItems == ORDER - 1;
    }

    // Метод связывает узел с потомком
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        child.parent = this;
    }

    // Метод отсоединяет потомка от узла и возвращает его
    public void disconnectChild(int childNum) {
        childArray[childNum] = null;
    }

    // Определение индекса элемента в пределах узла (Node) с искомым ключом (key)
    public int findItem(int key) {
        for (int j = 0; j < ORDER - 1; j++) {
            if (itemArray[j] == null) {
                break;
            } else if (itemArray[j].getDData() == key) {
                return j;
            }
        }

        return -1;
    }

    // Вставка нового элемента DataItem (предполагается, что узел не пуст)
    public int insertItem(DataItem newItem) {
        numItems++;                                         // Добавляем новый элемент
        int newKey = newItem.getDData();                    // Вытаскиваем ключ нового элемента

        for (int j = ORDER - 2; j >=0; j--) {               // Начинаем цикл справа массива itemArray
            if (itemArray[j] == null) {                     // Если ячейка пустая,
                continue;                                   // переходим на 1 ячейку влево
            } else {
                int itsKey = itemArray[j].getDData();       // Если нет, получаем ключ ячейки
                if (newKey < itsKey) {                      // Если новый ключ больше,
                    itemArray[j + 1] = itemArray[j];        // сдвигаем ячейки вправо
                } else {
                    itemArray[j + 1] = newItem;             // Вставляем новый элемент
                    return j + 1;                           // Возвращаем индекс нового элемента DataItem
                }
            }
        }
                                                            // Все элементы сдвинуты
        itemArray[0] = newItem;                             // Вставляем новый элемент
        return 0;                                           // Возвращаем индекс (равен 0) нового элемента DataItem
    }

    // Удаление наибольшего элемента (предполагается, что узел не пуст)
    public DataItem removeItem() {
        DataItem temp = itemArray[numItems - 1];            // Сохранение элемента во временную переменную temp
        itemArray[numItems - 1] = null;                     // Отсоединение
        numItems--;                                         // Уменьшаем число элементов узла
        return temp;                                        // Возвращаем удалённый элемент
    }

    // Вывод узла в консоль в формате: "/24/56/74/"
    public void displayNode() {
        for (int j = 0; j < numItems; j++) {
            itemArray[j].displayItem();                     // Вывод элемента данных в формате "/24"
            System.out.println("/");                        // Завершающий символ "/"
        }
    }

}
