package com.example.meteats.ui.data.model;

public class Food {
    private String Name;
    private String Image;
    private String Price;

    public Food(){

    }

    public Food(String name, String image, String price){
       this.Name=Name;
       this.Image=Image;
       this.Price=Price;
    }
    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name= Name;
    }

    public String getImage(){
        return Image;
    }

    public void setImage(String Image){
        this.Image= Image;
    }

    public String getPrice(){
        return Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }


}
