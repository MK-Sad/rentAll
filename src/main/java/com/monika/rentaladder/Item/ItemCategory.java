package com.monika.rentaladder.Item;

import java.util.LinkedList;
import java.util.List;

enum ItemCategory {

    TOOLS("TOOLS","NARZĘDZIA"),
    HOUSEHOLD("HOUSEHOLD","DOM"),
    ELECTRONICS("ELECTRONICS","ELEKTRONIKA"),
    HEALTHBEAUTY("HEALTH & BEAUTY","ZDROWIE I URODA"),
    SPORTING("SPORTING","SPORT"),
    TOYS("TOYS","ZABAWKI");

    private String english;
    private String polish;

    ItemCategory(String english, String polish) {
        this.english = english;
        this.polish = polish;
    }

    String getEnglish() {
        return english;
    }

    String getPolish() {
        return polish;
    }

    static List<String> getAllPolishCategories() {
        List<String> list = new LinkedList<>();
        for(ItemCategory enumType : ItemCategory.values()) {
            list.add(enumType.getPolish());
        }
        return list;
    }

    static List<String> getAllEnglishCategories() {
        List<String> list = new LinkedList<>();
        for(ItemCategory enumType : ItemCategory.values()) {
            list.add(enumType.getEnglish());
        }
        return list;
    }

    static ItemCategory getItemCategoryByName(String name){
        for(ItemCategory enumType : ItemCategory.values()){
            if(enumType.getPolish().equals(name) || enumType.getEnglish().equals(name)) {
                return enumType;
            }
        }
        return null;
    }

}
