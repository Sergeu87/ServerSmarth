package pl.wsinf.smarthome.server.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.wsinf.smarthome.server.model.HomeConfigEntity;
import pl.wsinf.smarthome.server.model.responses.DeviceStateResponse;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Home config handler.
 */
@Slf4j
@Component
public class HomeConfigHandler {

    private static String CONFIG_FILENAME = "house_config.xml";
    private static String INITIAL_VALUES_FILENAME = "device_values.json";

    private ConcurrentHashMap<String, DeviceStateResponse> devicesValues;
    private HomeConfigEntity homeConfigEntity;

    @PostConstruct
    private void init() {
        try {
            this.homeConfigEntity = loadHomeConfig(CONFIG_FILENAME);
            this.devicesValues = loadInitialValues(INITIAL_VALUES_FILENAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update home config.
     *
     * @param homeConfigEntity the home config entity
     */
    public void updateHomeConfig(HomeConfigEntity homeConfigEntity) {
        try {
            final XmlMapper xmlMapper = initXmlMapper();
            final String homeConfigXml = xmlMapper.writeValueAsString(this.homeConfigEntity);
            final String houseConfigPath = "C:\\Users\\sergr\\IdeaProjects\\serhii-smart-home-server\\src\\main\\resources\\house_config.xml";
//            final String houseConfigPath = this.getClass().getResource("house_config.xml").getPath();
            final URL houseConfigUrl = this.getClass().getClassLoader().getResource(CONFIG_FILENAME);
//            final PrintWriter writer = new PrintWriter(new File(Objects.requireNonNull(houseConfigUrl).toURI()));
            final PrintWriter writer = new PrintWriter(new File(houseConfigPath));
            writer.write(homeConfigXml);
            writer.close();
            this.homeConfigEntity = homeConfigEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Update home config error" + e.getMessage());
        }
    }

    /**
     * Update device values.
     *
     * @param map the map
     */
    public void updateDeviceValues(ConcurrentHashMap<String, DeviceStateResponse> map) {
        try {
            final ObjectMapper xmlMapper = initJsonMapper();
            final String deviceValuesJson = xmlMapper.writeValueAsString(map.values());
            final String deviceValuesPath = "C:\\Users\\sergr\\IdeaProjects\\serhii-smart-home-server\\src\\main\\resources\\device_values.json";
//            final String deviceValuesPath = this.getClass().getResource("device_values.json").getPath();;
            final PrintWriter writer = new PrintWriter(new File(deviceValuesPath));
            writer.write(deviceValuesJson);
            writer.close();
            this.devicesValues = map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Update device values error" + e.getMessage());
        }
    }

    private HomeConfigEntity loadHomeConfig(String fileName) throws URISyntaxException, IOException {
        final URL configUrl = this.getClass().getClassLoader().getResource(fileName);
        final File configFile = new File(Objects.requireNonNull(configUrl).toURI());

        final XmlMapper xmlMapper = initXmlMapper();
        final HomeConfigEntity config = xmlMapper.readValue(configFile, HomeConfigEntity.class);
        log.info("Home config loaded");

        return config;
    }

    private ConcurrentHashMap<String, DeviceStateResponse> loadInitialValues(String fileName) throws URISyntaxException, IOException {
        final URL initialValuesUrl = this.getClass().getClassLoader().getResource(fileName);
        final File initialValueFile = new File(Objects.requireNonNull(initialValuesUrl).toURI());

        final ObjectMapper jsonMapper = initJsonMapper();
        final List<DeviceStateResponse> deviceStates = jsonMapper.readValue(initialValueFile, new TypeReference<>() {
        });

        final ConcurrentHashMap<String, DeviceStateResponse> map = new ConcurrentHashMap<>();
        for (DeviceStateResponse deviceState : deviceStates) {
            map.put(deviceState.getDeviceId(), deviceState);
        }
        this.devicesValues = map;
        log.info("Initial devices values loaded");

        return devicesValues;
    }

    private ObjectMapper initJsonMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    private XmlMapper initXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper;
    }

    /**
     * Device values to devices response list.
     *
     * @return the list
     */
    public List<DeviceStateResponse> deviceValuesToDevicesResponse(){
        List<DeviceStateResponse> list = new ArrayList<>();
        for (Map.Entry<String, DeviceStateResponse> entry : devicesValues.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * Gets devices values.
     *
     * @return the devices values
     */
    public ConcurrentHashMap<String, DeviceStateResponse> getDevicesValues() {
        return devicesValues;
    }

    /**
     * Gets home config entity.
     *
     * @return the home config entity
     */
    public HomeConfigEntity getHomeConfigEntity() {
        return homeConfigEntity;
    }
}
