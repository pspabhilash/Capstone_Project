package com.myapp.abhilash.new_cocktails.json_models;


import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Drinks implements Serializable {


    /**
     * idDrink : 15112
     * strDrink : Alamo Splash
     * strCategory : Ordinary Drink
     * strAlcoholic : Alcoholic
     * strGlass : Collins glass
     * strInstructions : Mix with cracked ice and strain into collins glass.
     * strDrinkThumb : null
     * strIngredient1 : Tequila
     * strIngredient2 : Orange juice
     * strIngredient3 : Pineapple juice
     * strIngredient4 : Lemon-lime soda
     * strIngredient5 :
     * strIngredient6 :
     * strIngredient7 :
     * strIngredient8 :
     * strIngredient9 :
     * strIngredient10 :
     * strIngredient11 :
     * strIngredient12 :
     * strIngredient13 :
     * strIngredient14 :
     * strIngredient15 :
     * strMeasure1 : 1 1/2 oz
     * strMeasure2 : 1 oz
     * strMeasure3 : 1/2 oz
     * strMeasure4 : 1 splash
     * strMeasure5 :
     * strMeasure6 :
     * strMeasure7 :
     * strMeasure8 :
     * strMeasure9 :
     * strMeasure10 :
     * strMeasure11 :
     * strMeasure12 :
     * strMeasure13 :
     * strMeasure14 :
     * strMeasure15 :
     * dateModified : null
     */
    @SerializedName("drinks")
    private List<DrinksEntity> drinks;

    public void setDrinks(List<DrinksEntity> drinks) {
        this.drinks = drinks;
    }

    public List<DrinksEntity> getDrinks() {
        return drinks;
    }

    public static class DrinksEntity {
        @SerializedName("idDrink")
        public String idDrink;
        @SerializedName("strDrink")
        public String strDrink;
        @SerializedName("strCategory")
        public String strCategory;
        @SerializedName("strAlcoholic")
        public String strAlcoholic;
        @SerializedName("strGlass")
        public String strGlass;
        @SerializedName("strInstructions")
        public String strInstructions;
        @SerializedName("strDrinkThumb")
        public String strDrinkThumb;
        @SerializedName("strIngredient1")
        public String strIngredient1;
        @SerializedName("strIngredient2")
        public String strIngredient2;
        @SerializedName("strIngredient3")
        public String strIngredient3;
        @SerializedName("strIngredient4")
        public String strIngredient4;
        @SerializedName("strIngredient5")
        public String strIngredient5;
        @SerializedName("strIngredient6")
        public String strIngredient6;
        @SerializedName("strIngredient7")
        public String strIngredient7;
        @SerializedName("strIngredient8")
        public String strIngredient8;
        @SerializedName("strIngredient9")
        public String strIngredient9;
        @SerializedName("strIngredient10")
        public String strIngredient10;
        @SerializedName("strIngredient11")
        public String strIngredient11;
        @SerializedName("strIngredient12")
        public String strIngredient12;
        @SerializedName("strIngredient13")
        public String strIngredient13;
        @SerializedName("strIngredient14")
        public String strIngredient14;
        @SerializedName("strIngredient15")
        public String strIngredient15;
        @SerializedName("strMeasure1")
        public String strMeasure1;
        @SerializedName("strMeasure2")
        public String strMeasure2;
        @SerializedName("strMeasure3")
        public String strMeasure3;
        @SerializedName("strMeasure4")
        public String strMeasure4;
        @SerializedName("strMeasure5")
        public String strMeasure5;
        @SerializedName("strMeasure6")
        public String strMeasure6;
        @SerializedName("strMeasure7")
        public String strMeasure7;
        @SerializedName("strMeasure8")
        public String strMeasure8;
        @SerializedName("strMeasure9")
        public String strMeasure9;
        @SerializedName("strMeasure10")
        public String strMeasure10;
        @SerializedName("strMeasure11")
        public String strMeasure11;
        @SerializedName("strMeasure12")
        public String strMeasure12;
        @SerializedName("strMeasure13")
        public String strMeasure13;
        @SerializedName("strMeasure14")
        public String strMeasure14;
        @SerializedName("strMeasure15")
        public String strMeasure15;
        @SerializedName("dateModified")
        public String dateModified;


    }
}
