package DoboszBartoszuk.example.phone_store.service;

import java.io.IOException;
import DoboszBartoszuk.example.phone_store.dto.PhoneDTO;

public interface SerializationServiceInterface {
    public String serializeToJson(PhoneDTO phoneDTO) throws IOException;
    public String serializeToXml(PhoneDTO phoneDTO) throws IOException;
}
