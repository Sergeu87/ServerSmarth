package pl.wsinf.smarthome.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wsinf.smarthome.server.model.HomeConfigEntity;
import pl.wsinf.smarthome.server.util.HomeConfigHandler;
import pl.wsinf.smarthome.server.model.responses.DeviceStateResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Devices controller.
 */
@Slf4j
@Controller
public class DevicesController {

    @Autowired
    private HomeConfigHandler homeConfigHandler;

    /**
     * Gets device states.
     *
     * @return the device states
     */
    @RequestMapping(method = RequestMethod.GET, value = "devices", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DeviceStateResponse>> getDeviceStates() {
        log.info("getDeviceStates() request");
        final List<DeviceStateResponse> deviceStates = homeConfigHandler.deviceValuesToDevicesResponse();
        log.info("getDeviceStates() response OK: {}", deviceStates);
        return ResponseEntity.ok(deviceStates);
    }

    /**
     * Gets device state.
     *
     * @param deviceId the device id
     * @return the device state
     */
    @RequestMapping(method = RequestMethod.GET, value = "device", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DeviceStateResponse> getDeviceState(@RequestParam String deviceId) {
        log.info("getDeviceState() request: deviceId={}", deviceId);
        final DeviceStateResponse deviceState = homeConfigHandler.getDevicesValues().get(deviceId);
        log.info("getDeviceState() response OK: {}", deviceState);
        return ResponseEntity.ok(deviceState);
    }

    /**
     * Update devices state response entity.
     *
     * @param deviceState the device state
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, value = "device", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Boolean> updateDevicesState(@RequestBody DeviceStateResponse deviceState) {
        log.info("updateDevicesState() request: , deviceState={}", deviceState);

        final ConcurrentHashMap<String, DeviceStateResponse> devicesValues = homeConfigHandler.getDevicesValues();

        devicesValues.put(deviceState.getDeviceId(), deviceState);

        homeConfigHandler.updateDeviceValues(devicesValues);

        log.info("updateDevicesState() response: TRUE");
        return ResponseEntity.ok(true);
    }

}
