package pl.wsinf.smarthome.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wsinf.smarthome.server.model.HomeConfigEntity;
import pl.wsinf.smarthome.server.util.HomeConfigHandler;

/**
 * The type House config controller.
 */
@Slf4j
@Controller
public class HouseConfigController {

    @Autowired
    private HomeConfigHandler homeConfigHandler;

    /**
     * Gets home config.
     *
     * @return the home config
     */
    @RequestMapping(method = RequestMethod.GET, value = "config", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<HomeConfigEntity> getHomeConfig() {
        log.info("getHomeConfig() request");
        final HomeConfigEntity homeConfigEntity = homeConfigHandler.getHomeConfigEntity();
        log.info("getHomeConfig() response OK: {}", homeConfigEntity);
        return ResponseEntity.ok(homeConfigEntity);
    }

}
