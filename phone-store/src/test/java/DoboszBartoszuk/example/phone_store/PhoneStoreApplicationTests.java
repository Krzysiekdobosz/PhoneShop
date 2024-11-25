package DoboszBartoszuk.example.phone_store;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
import DoboszBartoszuk.example.phone_store.service.EmailService;


@SpringBootTest
@EnableAutoConfiguration(exclude = MailSenderAutoConfiguration.class)
@AutoConfigureMockMvc
class PhoneStoreApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();
    @MockBean
    private JavaMailSender mailSender;
    @MockBean
    private EmailService emailService;
    
    @Test
void addToCartDisplaysConfirmationMessage() throws Exception {
    Long phoneId = 2L;

    mockMvc.perform(post("/cart/add")
            .param("phoneId", phoneId.toString())
            .principal(() -> "testuser"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/cart"))
            .andExpect(flash().attribute("successMessage", "Przedmiot został dodany do koszyka."));
}
    
    @Test
    void addPhoneSuccessfully() throws Exception {
    PhoneDTO phoneDTO = new PhoneDTO(null, "iPhone 14", "Apple", new BigDecimal("999.99"));

    String jsonContent = objectMapper.writeValueAsString(phoneDTO);

    mockMvc.perform(post("/phones/import/json")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(status().isOk())
            .andExpect(content().string("Telefon zaimportowany pomyślnie."));
}

    @Test
    void importPhoneJson() throws Exception {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(2L);
        phoneDTO.setModel("Galaxy S21");
        phoneDTO.setManufacturer("Samsung");
        phoneDTO.setPrice(new BigDecimal("799.99"));

        String jsonContent = objectMapper.writeValueAsString(phoneDTO);

        mockMvc.perform(post("/phones/import/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void importPhoneXml() throws Exception {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(2L);
        phoneDTO.setModel("Galaxy S21");
        phoneDTO.setManufacturer("Samsung");
        phoneDTO.setPrice(new BigDecimal("799.99"));

        String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

        mockMvc.perform(post("/phones/import/xml")
                .contentType(MediaType.APPLICATION_XML)
                .content(xmlContent))
                .andExpect(status().isOk());
    }
}
// package DoboszBartoszuk.example.phone_store;

// import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
// import DoboszBartoszuk.example.phone_store.model.User;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// import java.math.BigDecimal;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class PhoneStoreApplicationTests {

//     @Autowired
//     private MockMvc mockMvc;

//     private ObjectMapper objectMapper = new ObjectMapper();
//     private XmlMapper xmlMapper = new XmlMapper();

//     @Test
//     void importPhoneJson() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String jsonContent = objectMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/json")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void importPhoneXml() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/xml")
//                 .contentType(MediaType.APPLICATION_XML)
//                 .content(xmlContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void getPhoneAsJson() throws Exception {
//         mockMvc.perform(get("/phones/2/json")
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(2))
//                 .andExpect(jsonPath("$.model").value("Galaxy S21"))
//                 .andExpect(jsonPath("$.manufacturer").value("Samsung"))
//                 .andExpect(jsonPath("$.price").value(799.99));
//     }

//     @Test
//     void getPhoneAsXml() throws Exception {
//         mockMvc.perform(get("/phones/2/xml")
//                 .accept(MediaType.APPLICATION_XML))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_XML))
//                 .andExpect(xpath("/Phone/id").string("2"))
//                 .andExpect(xpath("/Phone/model").string("Galaxy S21"))
//                 .andExpect(xpath("/Phone/manufacturer").string("Samsung"))
//                 .andExpect(xpath("/Phone/price").string("799.99"));
//     }

//     @Test
//     void addToCart() throws Exception {
//         mockMvc.perform(post("/cart/add")
//                 .param("phoneId", "2")
//                 .principal(() -> "testuser"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/cart"));
//     }

//     @Test
//     void registerUser() throws Exception {
//         User user = new User();
//         user.setUsername("newuser");
//         user.setEmail("newuser@example.com");
//         user.setPassword("password");

//         String jsonContent = objectMapper.writeValueAsString(user);

//         mockMvc.perform(post("/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/login?success"));
//     }

//     @Test
//     void loginUser() throws Exception {
//         mockMvc.perform(post("/login")
//                 .param("username", "testuser")
//                 .param("password", "password"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/"));
//     }
// }
// package DoboszBartoszuk.example.phone_store;

// import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
// import DoboszBartoszuk.example.phone_store.model.Phone;
// import DoboszBartoszuk.example.phone_store.model.User;
// import DoboszBartoszuk.example.phone_store.model.EmailDetails;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// import java.math.BigDecimal;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class PhoneStoreApplicationTests {

//     @Autowired
//     private MockMvc mockMvc;

//     private ObjectMapper objectMapper = new ObjectMapper();
//     private XmlMapper xmlMapper = new XmlMapper();

//     @Test
//     void importPhoneJson() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String jsonContent = objectMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/json")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void importPhoneXml() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/xml")
//                 .contentType(MediaType.APPLICATION_XML)
//                 .content(xmlContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void getPhoneAsJson() throws Exception {
//         mockMvc.perform(get("/phones/2/json")
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(2))
//                 .andExpect(jsonPath("$.model").value("Galaxy S21"))
//                 .andExpect(jsonPath("$.manufacturer").value("Samsung"))
//                 .andExpect(jsonPath("$.price").value(799.99));
//     }

//     @Test
//     void getPhoneAsXml() throws Exception {
//         mockMvc.perform(get("/phones/2/xml")
//                 .accept(MediaType.APPLICATION_XML))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_XML))
//                 .andExpect(xpath("/Phone/id").string("2"))
//                 .andExpect(xpath("/Phone/model").string("Galaxy S21"))
//                 .andExpect(xpath("/Phone/manufacturer").string("Samsung"))
//                 .andExpect(xpath("/Phone/price").string("799.99"));
//     }

//     @Test
//     void addToCart() throws Exception {
//         mockMvc.perform(post("/cart/add")
//                 .param("phoneId", "2")
//                 .principal(() -> "testuser"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/cart"));
//     }

//     @Test
//     void registerUser() throws Exception {
//         User user = new User();
//         user.setUsername("newuser");
//         user.setEmail("newuser@example.com");
//         user.setPassword("password");

//         String jsonContent = objectMapper.writeValueAsString(user);

//         mockMvc.perform(post("/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/login?success"));
//     }

//     @Test
//     void loginUser() throws Exception {
//         mockMvc.perform(post("/login")
//                 .param("username", "testuser")
//                 .param("password", "password"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/"));
//     }

//     @Test
//     void addPhoneAsAdmin() throws Exception {
//         Phone phone = new Phone();
//         phone.setBrand("Apple");
//         phone.setModel("iPhone 13");
//         phone.setPrice(new BigDecimal("999.99"));

//         String jsonContent = objectMapper.writeValueAsString(phone);

//         mockMvc.perform(post("/admin/phones/add")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent)
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void editPhoneAsAdmin() throws Exception {
//         Phone phone = new Phone();
//         phone.setId(2L);
//         phone.setBrand("Samsung");
//         phone.setModel("Galaxy S21 Ultra");
//         phone.setPrice(new BigDecimal("1199.99"));

//         String jsonContent = objectMapper.writeValueAsString(phone);

//         mockMvc.perform(post("/admin/phones/edit")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent)
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void deletePhoneAsAdmin() throws Exception {
//         mockMvc.perform(post("/admin/phones/delete/2")
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void viewCart() throws Exception {
//         mockMvc.perform(get("/cart")
//                 .principal(() -> "testuser"))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("cart"))
//                 .andExpect(model().attributeExists("cartItems"));
//     }

//     @Test
//     void sendEmail() throws Exception {
//         EmailDetails emailDetails = new EmailDetails();
//         emailDetails.setRecipient("test@example.com");
//         emailDetails.setSubject("Test Email");
//         emailDetails.setMsgBody("This is a test email.");

//         String jsonContent = objectMapper.writeValueAsString(emailDetails);

//         mockMvc.perform(post("/sendMail")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("Mail sent successfully"));
//     }
// }


////////////TESTY CAŁOŚCIOWE 
//  package DoboszBartoszuk.example.phone_store;

// import java.math.BigDecimal;

// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
// import DoboszBartoszuk.example.phone_store.model.EmailDetails;
// import DoboszBartoszuk.example.phone_store.model.Phone;
// import DoboszBartoszuk.example.phone_store.model.User;



// @SpringBootTest
// @EnableAutoConfiguration(exclude = MailSenderAutoConfiguration.class)
// @AutoConfigureMockMvc
// class PhoneStoreApplicationTests {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private JavaMailSender mailSender;


//     private ObjectMapper objectMapper = new ObjectMapper();
//     private XmlMapper xmlMapper = new XmlMapper();

//     @Test
//     void importPhoneJson() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String jsonContent = objectMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/json")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void importPhoneXml() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

//         String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/xml")
//                 .contentType(MediaType.APPLICATION_XML)
//                 .content(xmlContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void getPhoneAsJson() throws Exception {
//         mockMvc.perform(get("/phones/2/json")
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(2))
//                 .andExpect(jsonPath("$.model").value("Galaxy S21"))
//                 .andExpect(jsonPath("$.manufacturer").value("Samsung"))
//                 .andExpect(jsonPath("$.price").value(799.99));
//     }

//     @Test
//     void getPhoneAsXml() throws Exception {
//         mockMvc.perform(get("/phones/2/xml")
//                 .accept(MediaType.APPLICATION_XML))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_XML))
//                 .andExpect(xpath("/Phone/id").string("2"))
//                 .andExpect(xpath("/Phone/model").string("Galaxy S21"))
//                 .andExpect(xpath("/Phone/manufacturer").string("Samsung"))
//                 .andExpect(xpath("/Phone/price").string("799.99"));
//     }

//     @Test
//     void addToCart() throws Exception {
//         mockMvc.perform(post("/cart/add")
//                 .param("phoneId", "2")
//                 .principal(() -> "testuser"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/cart"));
//     }

//     @Test
//     void registerUser() throws Exception {
//         User user = new User();
//         user.setUsername("newuser");
//         user.setEmail("newuser@example.com");
//         user.setPassword("password");

//         String jsonContent = objectMapper.writeValueAsString(user);

//         mockMvc.perform(post("/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/login?success"));
//     }

//     @Test
//     void loginUser() throws Exception {
//         mockMvc.perform(post("/login")
//                 .param("username", "testuser")
//                 .param("password", "password"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/"));
//     }

//     @Test
//     void addPhoneAsAdmin() throws Exception {
//         Phone phone = new Phone();
//         phone.setBrand("Apple");
//         phone.setModel("iPhone 13");
//         phone.setPrice(new BigDecimal("999.99"));

//         String jsonContent = objectMapper.writeValueAsString(phone);

//         mockMvc.perform(post("/admin/phones/add")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent)
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void editPhoneAsAdmin() throws Exception {
//         Phone phone = new Phone();
//         phone.setId(2L);
//         phone.setBrand("Samsung");
//         phone.setModel("Galaxy S21 Ultra");
//         phone.setPrice(new BigDecimal("1199.99"));

//         String jsonContent = objectMapper.writeValueAsString(phone);

//         mockMvc.perform(post("/admin/phones/edit")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent)
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void deletePhoneAsAdmin() throws Exception {
//         mockMvc.perform(post("/admin/phones/delete/2")
//                 .principal(() -> "admin"))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/admin/phones"));
//     }

//     @Test
//     void viewCart() throws Exception {
//         mockMvc.perform(get("/cart")
//                 .principal(() -> "testuser"))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("cart"))
//                 .andExpect(model().attributeExists("cartItems"));
//     }
//     @Disabled
//     @Test
//     void sendEmail() throws Exception {
//         EmailDetails emailDetails = new EmailDetails();
//         emailDetails.setRecipient("test@example.com");
//         emailDetails.setSubject("Test Email");
//         emailDetails.setMsgBody("This is a test email.");

//         String jsonContent = objectMapper.writeValueAsString(emailDetails);

//         mockMvc.perform(post("/sendMail")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("Mail sent successfully"));
//     }
// }