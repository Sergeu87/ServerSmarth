package pl.wsinf.smarthome.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wsinf.smarthome.server.model.Device;
import pl.wsinf.smarthome.server.util.HomeConfigHandler;
import pl.wsinf.smarthome.server.model.responses.DeviceStateResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Divisions controller.
 */
@Slf4j
@Controller
public class DivisionsController {

    @Autowired
    private HomeConfigHandler homeConfigHandler;

    /**
     * Gets division.
     *
     * @param id the id
     * @return the division
     */
    @RequestMapping(method = RequestMethod.GET, value = "divisions/{id}/devices", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DeviceStateResponse>> getDivision(@PathVariable String id) {
        log.info("getDivision() request: id={}", id);

        List<Device> devices = homeConfigHandler.getHomeConfigEntity().getDevicesByDivisionID(id);
        List<DeviceStateResponse> myDevicesStates = new ArrayList<>();

        for (Device d : devices) {
            myDevicesStates.add(homeConfigHandler.getDevicesValues().get(d.getId()));
        }

        log.info("getDivision() response OK: {}", myDevicesStates);
        return ResponseEntity.ok(myDevicesStates);
    }

}
