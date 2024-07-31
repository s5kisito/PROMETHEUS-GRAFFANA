
1.Downloading Prometheus:
Download the latest release of Prometheus for your platform, then extract it:

'tar xvfz prometheus-*.tar.gz
 cd prometheus-*'

Before starting Prometheus, let's configure it.'

2. Configuring Prometheus:

Prometheus configuration is YAML. The Prometheus download comes with a sample configuration 
in a file called prometheus.yml that is a good place to get started.

global:
  scrape_interval:     15s
  evaluation_interval: 15s

rule_files:
  # - "first.rules"
  # - "second.rules"

scrape_configs:
  - job_name: prometheus
    static_configs:
      - targets: ['localhost:9090'] 

There are three(3) Blocks of Configuration in the example configuration file: 
'global, rule_files, and scrape_configs.'

'The Global Block' 
-----------------
controls the Prometheus Server Global Configuration. 
We have two options present. 
 '.scrape_interval': controls how often Prometheus will 
scrape targets.
You can override this for individual targets. 
In this case the global setting is to scrape every 15 seconds. 
'.The evaluation_interval' option controls how often Prometheus will evaluate rules. 
Prometheus uses rules to create new time series and to generate alerts.

'The Rule_files block'
---------------------
 specifies the location of any rules we want the Prometheus server to 
load. For now weve got no rules.

'The last block, scrape_configs',
------------------------------- 
controls what resources Prometheus monitors. Since Prometheus also exposes data about itself
as an HTTP endpoint it can scrape and monitor its own health. In the default configuration 
there is a single job, called prometheus, which scrapes the time series data exposed by 
the Prometheus server. The job contains a single, statically configured, target, 
the localhost on port 9090. Prometheus expects metrics to be available on targets on a path
 of /metrics. So this default job is scraping via the URL: http://localhost:9090/metrics.

The time series data returned will detail the state and performance of the Prometheus server.


3. Starting Prometheus:

To start Prometheus with our newly created configuration file, change to the directory 
containing the Prometheus binary and run:

'cd   directory(containing prometheus binary)'
'
./prometheus --config.file=prometheus.yml  '


Prometheus should start up. You should also be able to browse to a status page about itself 
at http://localhost:9090. 
Give it about 30 seconds to collect data about itself from its own HTTP metrics endpoint.