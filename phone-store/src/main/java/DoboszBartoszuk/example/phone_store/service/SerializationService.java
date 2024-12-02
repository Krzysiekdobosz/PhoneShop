package DoboszBartoszuk.example.phone_store.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;


@Service
public class SerializationService implements SerializationServiceInterface {
    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public SerializationService() {
        this.objectMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public String serializeToJson(PhoneDTO phoneDTO) throws IOException {
        return objectMapper.writeValueAsString(phoneDTO);
    }

    @Override
    public String serializeToXml(PhoneDTO phoneDTO) throws IOException {
        return xmlMapper.writeValueAsString(phoneDTO);
    }
}
