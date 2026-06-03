package csd230.s26.lab1.entities;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new LinkedHashSet<>();


    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void addProduct(ProductEntity product) {
        this.products.add(product);
        product.getCarts().add(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Set<ProductEntity> getProducts() { return products; }
    public void setProducts(Set<ProductEntity> products) { this.products = products; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    @Override
    public String toString() {
        return "CartEntity{id=" + id + ", productCount=" + products.size() + "}";
    }
}