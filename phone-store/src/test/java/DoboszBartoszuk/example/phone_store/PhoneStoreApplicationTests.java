// package DoboszBartoszuk.example.phone_store;

// import java.math.BigDecimal;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
// import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.mail.javamail.JavaMailSender;

// @SpringBootTest(exclude = MailSenderAutoConfiguration.class)
// @AutoConfigureMockMvc
// class PhoneStoreApplicationTests {

//     @Autowired
//     private MockMvc mockMvc;

//     private ObjectMapper objectMapper = new ObjectMapper();
//     private XmlMapper xmlMapper = new XmlMapper();

//     @Test
//     void importPhoneJson() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO();
//         phoneDTO.setId(2L);
//         phoneDTO.setModel("Galaxy S21");
//         phoneDTO.setBrand("Samsung");
//         phoneDTO.setPrice(new BigDecimal("799.99"));

//         String jsonContent = objectMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/json")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonContent))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void importPhoneXml() throws Exception {
//         PhoneDTO phoneDTO = new PhoneDTO();
//         phoneDTO.setId(2L);
//         phoneDTO.setModel("Galaxy S21");
//         phoneDTO.setBrand("Samsung");
//         phoneDTO.setPrice(new BigDecimal("799.99"));

//         String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

//         mockMvc.perform(post("/phones/import/xml")
//                 .contentType(MediaType.APPLICATION_XML)
//                 .content(xmlContent))
//                 .andExpect(status().isOk());
//     }
// }
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
//                 .andExpect(jsonPath("$.brand").value("Samsung"))
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
//                 .andExpect(xpath("/Phone/brand").string("Samsung"))
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
// import io.github.cdimascio.dotenv.Dotenv;
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

//     static {
//         // Ładowanie zmiennych z pliku .env dla testów
//         Dotenv dotenv = Dotenv.load();
//         System.setProperty("spring.mail.username", dotenv.get("SPRING_MAIL_USERNAME"));
//         System.setProperty("spring.mail.password", dotenv.get("SPRING_MAIL_PASSWORD"));
//     }

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
//                 .andExpect(jsonPath("$.brand").value("Samsung"))
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
//                 .andExpect(xpath("/Phone/brand").string("Samsung"))
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
package DoboszBartoszuk.example.phone_store;

import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;
import DoboszBartoszuk.example.phone_store.service.EmailService;
import DoboszBartoszuk.example.phone_store.service.SerializationService;
import io.github.cdimascio.dotenv.Dotenv;
import DoboszBartoszuk.example.phone_store.model.EmailDetails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PhoneStoreApplicationTests {

    static {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.mail.username", dotenv.get("SPRING_MAIL_USERNAME"));
        System.setProperty("spring.mail.password", dotenv.get("SPRING_MAIL_PASSWORD"));
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    private ObjectMapper objectMapper;
    private XmlMapper xmlMapper;
    private SerializationService serializationService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
        serializationService = new SerializationService();
    }

    @Test
    void givenPhoneDTO_whenImportPhoneJson_thenStatusOK() throws Exception {
        PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

        String jsonContent = objectMapper.writeValueAsString(phoneDTO);

        mockMvc.perform(post("/phones/import/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void givenPhoneDTO_whenImportPhoneXml_thenStatusOK() throws Exception {
        PhoneDTO phoneDTO = new PhoneDTO(2L, "Galaxy S21", "Samsung", new BigDecimal("799.99"));

        String xmlContent = xmlMapper.writeValueAsString(phoneDTO);

        mockMvc.perform(post("/phones/import/xml")
                .contentType(MediaType.APPLICATION_XML)
                .content(xmlContent))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetPhoneAsJson_thenJSONData() throws Exception {
        mockMvc.perform(get("/phones/11/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.model").value("Galaxy S21"))
                .andExpect(jsonPath("$.brand").value("Samsung"))
                .andExpect(jsonPath("$.price").value(799.99));
    }

    @Test
    void whenGetPhoneAsXml_thenXMLData() throws Exception {
        mockMvc.perform(get("/phones/3/xml")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(xpath("/Phone/id").string("3"))
                .andExpect(xpath("/Phone/model").string("V1 ZMIANA"))
                .andExpect(xpath("/Phone/brand").string("Motorola"))
                .andExpect(xpath("/Phone/price").string("439.99"));
    }

    @Test
    @WithMockUser(username = "user123", password = "qwerty1234")
    void givenPhoneAndUserData_whenAddToCart_thenStatusRedirection() throws Exception {
        mockMvc.perform(post("/cart/add")
                .param("phoneId", "2")
                .principal(() -> "user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    void givenRegisterData_whenRegisterUser_thenStatusOK() throws Exception {
        String requestBody = """
                {
                    "username": "newuser",
                    "email": "newuser@example.com",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Użytkownik zarejestrowany pomyślnie"));
    }

    @Test
    void givenLoginData_whenLoginUser_thenOKStatusAndToken() throws Exception {
        String requestBody = """
                {
                    "username": "user123",
                    "password": "qwerty1234"
                }
                """;

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    @WithMockUser(username = "user123", roles = "ADMIN")
    void givenAdminAndPhoneData_whenAddPhoneAsAdmin_thenStatusRedirection() throws Exception {
        String requestBody = """
                {
                    "brand": "Apple",
                    "model": "iPhone 13",
                    "price": 999.99
                }
                """;

        mockMvc.perform(post("/admin/phones/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .principal(() -> "admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/phones"));
    }

    @Test
    @WithMockUser(username = "user123", roles = "ADMIN")
    void givenAdminAndPhoneData_whenEditPhoneAsAdmin_thenStatusRedirection() throws Exception {
        String requestBody = """
                {
                    "id": 4,
                    "brand": "Samsung",
                    "model": "Galaxy S21 Ultra",
                    "price": 1199.99
                }
                """;

        mockMvc.perform(post("/admin/phones/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .principal(() -> "admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/phones"));
    }

    @Test
    @WithMockUser(username = "user123", password = "qwerty1234", roles = "ADMIN")
    void givenAdminAndPhoneData_whenDeletePhoneAsAdmin_thenStatusRedirection() throws Exception {
        mockMvc.perform(post("/admin/phones/delete/2")
                .principal(() -> "admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/phones"));
    }

    @Test
    @WithMockUser(username = "user123", password = "qwerty1234")
    void givenUserData_whenViewCart_thenStatusOkAndAttributeExists() throws Exception {
        mockMvc.perform(get("/cart")
                .principal(() -> "user"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cartItems"));
    }

    @Test
    void givenEmailDetails_whenSendEmail_thenStatusOk() throws Exception {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("test@example.com");
        emailDetails.setSubject("Test Email");
        emailDetails.setMsgBody("This is a test email.");

        when(emailService.sendEmail(emailDetails))
                .thenReturn(CompletableFuture.completedFuture("Mail sent successfully"));

        mockMvc.perform(post("/sendMail")
                .contentType("application/json")
                .content(
                        "{\"recipient\": \"test@example.com\", \"subject\": \"Test Subject\", \"msgBody\": \"This is a test email.\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void givenPhoneDataAndExpectedJson_whenSerializeToJSON_thenAssertionIsEqual() throws IOException {
        PhoneDTO phoneDTO = new PhoneDTO(1L, "Galaxy S21", "Samsung", BigDecimal.valueOf(799.99));
        String expectedJson = "{\"id\":1,\"model\":\"Galaxy S21\",\"brand\":\"Samsung\",\"price\":799.99}";

        String actualJson = serializationService.serializeToJson(phoneDTO);

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    void givenPhoneDataAndExpectedXML_whenSerializeToXML_thenAssertionIsEqual() throws IOException {
        PhoneDTO phoneDTO = new PhoneDTO(1L, "Galaxy S21", "Samsung", BigDecimal.valueOf(799.99));
        String expectedXML = "<Phone><id>1</id><model>Galaxy S21</model><brand>Samsung</brand><price>799.99</price></Phone>";

        String actualXML = serializationService.serializeToXml(phoneDTO);

        assertThat(actualXML).isEqualTo(expectedXML);
    }
}