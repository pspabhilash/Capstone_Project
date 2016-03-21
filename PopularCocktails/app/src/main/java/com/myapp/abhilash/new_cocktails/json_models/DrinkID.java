package com.myapp.abhilash.new_cocktails.json_models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DrinkID {

    /**
     * strDrink : 3-Mile Long Island Iced Tea
     * strDrinkThumb : null
     * idDrink : 15300
     */

    private List<DrinksEntity> drinks;

    public void setDrinks(List<DrinksEntity> drinks) {
        this.drinks = drinks;
    }

    public List<DrinksEntity> getDrinks() {
        return drinks;
    }

    public static class DrinksEntity {
        @SerializedName("idDrink")
        private String idDrink;

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getIdDrink() {
            return idDrink;
        }
    }
}
