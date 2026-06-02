package csd230.s26.lab1.entities;

import csd230.s26.lab1.pojos.SaleableItem;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductEntity implements Serializable, SaleableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    // ADDED: Root fields for your price queries and sales loop to reference
    @Column(name = "price")
    private Double price;

    @Column(name = "copies")
    private Integer copies;

    @ManyToMany(mappedBy = "products")
    private Set<CartEntity> carts = new java.util.HashSet<>();

    public ProductEntity() {
        setProductId(UUID.randomUUID().toString());
    }

    // Existing Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Set<CartEntity> getCarts() { return carts; }
    public void setCarts(Set<CartEntity> carts) { this.carts = carts; }

    // ADDED: Getters and Setters for the missing variables
    public double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getCopies() { return copies; }
    public void setCopies(Integer copies) { this.copies = copies; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(productId, that.productId);
    }

    // This allows Thymeleaf to access "${product.productType}"
    // It returns the name of the Java class (e.g., "BookEntity")
    public String getProductType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }
}