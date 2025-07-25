package com.devsuperior.dscommerce.domain.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@Table(name = "TBL_PRODUCT")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(
        name = "TBL_PRODUCT_CATEGORY",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private final Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private final Set<OrderItem> items = new HashSet<>();

    public List<Order> getOrders() {
        return items.stream()
            .map(OrderItem::getOrder)
            .toList();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
    
}
