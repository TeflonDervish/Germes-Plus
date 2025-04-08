package ru.germes.plus.site.model.products;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//TODO: Сделать в формате nosql добавить поля документации (гарантия, инструкция)


@NoArgsConstructor
@Entity
public class ProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrl(List<String> urls) {
        this.urls = urls;
    }

    private String article;
    @Lob
    private String description;

    @Column(columnDefinition = "json")
    private String characteristics;

    @ElementCollection
    @CollectionTable(name="urls", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
