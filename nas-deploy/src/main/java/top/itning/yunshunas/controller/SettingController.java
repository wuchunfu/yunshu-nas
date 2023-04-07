package top.itning.yunshunas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.itning.yunshunas.common.config.NasFtpProperties;
import top.itning.yunshunas.common.config.NasProperties;
import top.itning.yunshunas.common.db.DbSourceConfig;
import top.itning.yunshunas.common.model.RestModel;
import top.itning.yunshunas.common.util.JsonUtils;
import top.itning.yunshunas.music.config.NasMusicProperties;

/**
 * @author itning
 * @since 2023/4/7 13:41
 */
@Validated
@RequestMapping("/api/setting")
@RestController
public class SettingController {
    private final DbSourceConfig dbSourceConfig;

    @Autowired
    public SettingController(DbSourceConfig dbSourceConfig) {
        this.dbSourceConfig = dbSourceConfig;
    }

    @GetMapping("/{type}")
    public ResponseEntity<RestModel<Object>> getSetting(@PathVariable String type) {
        switch (type) {
            case "nas" -> {
                return RestModel.ok(dbSourceConfig.getSetting(NasProperties.class));
            }
            case "datasource" -> {
                return RestModel.ok(dbSourceConfig.getSetting(NasMusicProperties.class));
            }
            case "ftp" -> {
                return RestModel.ok(dbSourceConfig.getSetting(NasFtpProperties.class));
            }
            default -> throw new IllegalArgumentException("未知类型");
        }
    }

    @PostMapping("/{type}")
    public ResponseEntity<RestModel<Object>> setSetting(@PathVariable String type, @RequestBody String value) throws Exception {
        switch (type) {
            case "nas" -> {
                NasProperties nasProperties = JsonUtils.OBJECT_MAPPER.readValue(value, NasProperties.class);
                return RestModel.ok(dbSourceConfig.setSetting(nasProperties));
            }
            case "datasource" -> {
                NasMusicProperties nasMusicProperties = JsonUtils.OBJECT_MAPPER.readValue(value, NasMusicProperties.class);
                return RestModel.ok(dbSourceConfig.setSetting(nasMusicProperties));
            }
            case "ftp" -> {
                NasFtpProperties nasFtpProperties = JsonUtils.OBJECT_MAPPER.readValue(value, NasFtpProperties.class);
                return RestModel.ok(dbSourceConfig.setSetting(nasFtpProperties));
            }
            default -> throw new IllegalArgumentException("未知类型");
        }
    }
}
