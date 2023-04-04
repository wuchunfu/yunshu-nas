package top.itning.yunshunas.music.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.itning.yunshunas.music.datasource.DataSource;

import java.util.Map;

/**
 * @author itning
 * @since 2023/4/2 18:39
 */
@ConfigurationProperties(prefix = "nas.music")
@Component
@Data
public class NasMusicProperties {

    private Map<String, MusicDataSourceConfig> dataSource;

    @Data
    public static class MusicDataSourceConfig {

        /**
         * 数据源类型
         */
        private Class<? extends DataSource> className;

        /**
         * 音乐文件目录
         */
        private String musicFileDir;

        /**
         * 歌词文件目录
         */
        private String lyricFileDir;

        /**
         * 文件数据源URL前缀
         */
        private String urlPrefix;

        /**
         * SECRETID和SECRETKEY请登录<a href="https://console.cloud.tencent.com/cam/capi">访问管理控制台</a>进行查看和管理
         */
        private String secretId;

        /**
         * SECRETID和SECRETKEY请登录<a href="https://console.cloud.tencent.com/cam/capi">访问管理控制台</a>进行查看和管理
         */
        private String secretKey;

        /**
         * 设置 bucket 的地域, COS 地域的简称请参照<a href="https://cloud.tencent.com/document/product/436/6224">这里</a>
         */
        private String regionName;

        /**
         * 腾讯COS BucketName
         */
        private String bucketName;

        /**
         * 腾讯云内容分发网络（CDN）域名
         */
        private String cdnUrl;

        /**
         * 上传之前将音频文件转成MP3后再上传
         */
        private boolean convertAudioToMp3BeforeUploading;

        /**
         * 上传时（写操作）存储到这个数据源
         */
        private boolean canWrite = true;

        /**
         * 下载时（读操作）从这个数据源读取
         * <p>如果配置多个默认随机取第一个
         * <p><b>建议多个数据源中只配置一个为<code>true</code></b>
         */
        private boolean canRead = true;
    }
}
