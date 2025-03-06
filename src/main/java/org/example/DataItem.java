package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataItem {
    private long dData;

    public void displayItem() {
        System.out.print("/" + dData);
    }
}
