package com.lobov.HW25;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Comment {

    private String name;
    private String typeOfProduct;
    private String feedback;
    private int rating;

}
