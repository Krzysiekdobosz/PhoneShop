package DoboszBartoszuk.example.phone_store.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Phone")
public class PhoneDTO {
    private Long id;
    private String model;
    private String manufacturer;
    private BigDecimal price;

    public PhoneDTO() {
    }
    public PhoneDTO(Long id, String model, String manufacturer, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    // Gettery i settery
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return price;
    }
    
}