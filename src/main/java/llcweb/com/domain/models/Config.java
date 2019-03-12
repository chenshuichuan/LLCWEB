package llcweb.com.domain.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * 参数配置表 sys_config
 * 
 * @author ruoyi
 */
@Entity
@Table(name="sys_config")
public class Config
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id" )
    private Long configId;

    /** 参数名称 */
    @Column(name = "config_name" )
    private String configName;

    /** 参数键名 */
    @Column(name = "config_key" )
    private String configKey;

    /** 参数键值 */
    @Column(name = "config_value" )
    private String configValue;

    /** 系统内置（Y是 N否） */
    @Column(name = "config_type" )
    private String configType;

    public Config() {
    }
    public Config(Long configId, String configName, String configKey, String configValue, String configType) {
        this.configId = configId;
        this.configName = configName;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configType = configType;
    }

    public Long getConfigId()
    {
        return configId;
    }

    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigKey()
    {
        return configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigType()
    {
        return configType;
    }

    public void setConfigType(String configType)
    {
        this.configType = configType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("configId", getConfigId())
            .append("configName", getConfigName())
            .append("configKey", getConfigKey())
            .append("configValue", getConfigValue())
            .append("configType", getConfigType())
            .toString();
    }
}
