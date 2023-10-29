package com.example.rucombee;

import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.synth.Region;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.*;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.bindings.RecommendationResponse;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        

        // Initializați clientul Recombee cu cheia API
        RecombeeClient client = new RecombeeClient("upb-cinzaca-dev", "g8a73Lpdwj6Q31IuF36Xzqc035LoRaaTxREHLw6l3Gy9CgWpBcvhgmpdhnpK2O5Q");

        try {
            // Pasul 1: Trimite id-urile produselor la Recombee
            client.send(new AddItem("product3"));
            client.send(new AddItem("product4"));

            // Pasul 2: Trimite numele și tipul proprietăților produselor
            client.send(new AddItemProperty("title", "string"));
            client.send(new AddItemProperty("price", "double"));

            // Pasul 3: Trimite valorile proprietăților produselor
            Map<String, Object> itemProperties = new HashMap<>();
            itemProperties.put("title", "Product 3 Title");
            itemProperties.put("price", 6.99);
            itemProperties.put("title", "Product 4 Title");
            itemProperties.put("price", 69.99);

            client.send(new SetItemValues("product3", itemProperties));
            client.send(new SetItemValues("product4", itemProperties));

            // Pasul 4: Trimite id-urile utilizatorilor
            client.send(new AddUser("user1"));
            client.send(new AddUser("user2"));

            // Pasul 5: Trimite interacțiunile utilizatorilor cu produsele
            client.send(new AddPurchase("user1", "product3"));
            client.send(new AddPurchase("user2", "product4"));

            // Pasul 6: Solicită produse recomandate pentru utilizatori
            RecommendationResponse response = client.send(new RecommendItemsToUser("user1", 5));
            System.out.println("Recommended items for user1: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
