
Reference:
https://prometheus.io/docs/prometheus/latest/configuration/configuration/

Prometheus Configuration has Three (3) Main "blocks":
'
- GLOBAL( )
 .SCRAPE INTERVAL
 .EVALUATION INTERVAL

- RULE_FILE (LOAD ALL RULES)

- SCRAPS_CONFIG (JOBS , INSTANCES, ENDPOINTS)'

However 'Additional Blocks' Can be added To 'the configuration file'.


List Of Additional Configuration And Where To Incorporate Them In The Configuration File:
-----------------------------------------------------------------------------------------
'

<tls_config>
<oauth2>
<azure_sd_config>
<consul_sd_config>
<digitalocean_sd_config>
<docker_sd_config>
<dockerswarm_sd_config>
<dns_sd_config>
<ec2_sd_config>
<openstack_sd_config>
<ovhcloud_sd_config>
<puppetdb_sd_config>
<file_sd_config>
<gce_sd_config>
<hetzner_sd_config>
<http_sd_config>
<ionos_sd_config>
<kubernetes_sd_config>
<kuma_sd_config>
<lightsail_sd_config>
<linode_sd_config>
<marathon_sd_config>
<nerve_sd_config>
<nomad_sd_config>
<serverset_sd_config>
<triton_sd_config>
<eureka_sd_config>
<scaleway_sd_config>
<uyuni_sd_config>
<vultr_sd_config>
<static_config>
<relabel_config>
<metric_relabel_configs>
<alert_relabel_configs>
<alertmanager_config>
<remote_write>
<remote_read>
<tsdb>
<exemplars>
<tracing_config>
'

Organization:( Where To Insert Each Configuration In The Configuration File)
-------------

'Scrape Config Block (scrape_configs)'

<scrape_config>
<tls_config>
<oauth2>
<azure_sd_config>
<consul_sd_config>
<digitalocean_sd_config>
<docker_sd_config>
<dockerswarm_sd_config>
<dns_sd_config>
<ec2_sd_config>
<openstack_sd_config>
<ovhcloud_sd_config>
<puppetdb_sd_config>
<file_sd_config>
<gce_sd_config>
<hetzner_sd_config>
<http_sd_config>
<ionos_sd_config>
<kubernetes_sd_config>
<kuma_sd_config>
<lightsail_sd_config>
<linode_sd_config>
<marathon_sd_config>
<nerve_sd_config>
<nomad_sd_config>
<serverset_sd_config>
<triton_sd_config>
<eureka_sd_config>
<scaleway_sd_config>
<uyuni_sd_config>
<vultr_sd_config>
<static_config>
<relabel_config>
<metric_relabel_configs>

Additional Blocks:
------------------

'Alerting Block':

<alert_relabel_configs> (Under the alerting block)
<alertmanager_config> (Under the alerting block)

'Remote_write Block':

<remote_write> (Under the remote_write block)

'Remote_Read Block':

<remote_read> (Under the remote_read block)

'Storage Block':

<tsdb> (Under the storage block)
<exemplars> (Under the storage block)

'Tracing Block':

<tracing_config> (Under the tracing block)



Note: Go Through The Documentation To Configure Each 'configuration properly'
https://prometheus.io/docs/prometheus/latest/configuration/configuration/