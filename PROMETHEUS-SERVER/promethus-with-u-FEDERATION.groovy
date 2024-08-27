'Federation in Prometheus allows one Prometheus server to scrape specific time series from 
another Prometheus server, enabling scalable monitoring setups and cross-service data 
integration.'

Native Histograms: 
To scrape native histograms via federation, the scraping Prometheus server 
must have native histograms enabled. Even if federated metrics include different sample types
under the same metric name, Prometheus can still ingest these metrics despite protobuf format 
rules.

Use Cases:

Hierarchical Federation: Supports scaling Prometheus across multiple data centers, with local
servers collecting detailed data and higher-level servers aggregating this data for a global
 view.
Cross-Service Federation: Enables one service’s Prometheus to scrape metrics from another
service Prometheus, allowing integrated queries and alerts across different datasets.

Configuration: Federation is configured by 'scraping the /federate endpoint on the source 
Prometheus server using specific match[] parameters to select the desired series. '
The honor_labels option is used to preserve labels from the source server.

scrape_configs:
  - job_name: 'federate'
    scrape_interval: 15s

    honor_labels: true
    metrics_path: '/federate'

    params:
      'match[]':
        - '{job="prometheus"}'
        - '{__name__=~"job:.*"}'

    static_configs:
      - targets:
        - 'source-prometheus-1:9090'
        - 'source-prometheus-2:9090'
        - 'source-prometheus-3:9090'