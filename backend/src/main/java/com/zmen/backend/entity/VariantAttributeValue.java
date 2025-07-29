package com.zmen.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "bien_the_gia_tri_thuoc_tinh")
public class VariantAttributeValue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien_the", nullable = false)
    private ProductVariant productVariant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gia_tri_thuoc_tinh", nullable = false)
    private AttributeValue attributeValue;
    
    // Constructors
    public VariantAttributeValue() {}
    
    public VariantAttributeValue(ProductVariant productVariant, AttributeValue attributeValue) {
        this.productVariant = productVariant;
        this.attributeValue = attributeValue;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }
    
    public AttributeValue getAttributeValue() { return attributeValue; }
    public void setAttributeValue(AttributeValue attributeValue) { this.attributeValue = attributeValue; }
}