package eci.ieti.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;

    private String name;
    private String email; 

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public User(){

    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name='%s', email='%s']",
                this.id, this.name, this.email);
    }
    
}
