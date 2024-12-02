package DoboszBartoszuk.example.phone_store.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Phone")
public class PhoneDTO {
    private Long id;
    private String model;
    private String brand;
    private BigDecimal price;

    public PhoneDTO(long id, String model, String brand, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.price = price;
    }

    public PhoneDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}