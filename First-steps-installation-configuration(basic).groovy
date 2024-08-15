
1.Downloading Prometheus:
Download the latest release of Prometheus for your platform, then extract it:

'tar xvfz prometheus-*.tar.gz
 cd prometheus-*'

Before starting Prometheus, let's configure it.'

2. Configuring Prometheus:

Prometheus configuration is YAML. The Prometheus download comes with a sample configuration 
in a file called 'prometheus.yml' that is a good place to get started.

global:
  scrape_interval:     15s      # First Block.
  evaluation_interval: 15s

rule_files:
  # - "first.rules"              # Second Block.
  # - "second.rules"

scrape_configs:
  - job_name: prometheus         # Third Block.
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
Prometheus Uses 'rules to create new time series and to generate alerts.'

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

You can also verify that Prometheus is serving metrics about itself 
by navigating to its own metrics endpoint: http://localhost:9090/metrics.

4. Using The Expression Browser:

Let us try looking at some data that Prometheus has collected about itself. 
To use Prometheus built-in expression browser, navigate to http://localhost:9090/graph 
and choose the "Table" view within the "Graph" tab.

As you can gather from http://localhost:9090/metrics, 
one metric that Prometheus exports about itself is called 
'promhttp_metric_handler_requests_total' (the total number of /metrics requests the Prometheus 
                                          ----------------------------
server has served). 
Go ahead and enter this into the Expression Console:
'promhttp_metric_handler_requests_total'
This should return a number of different time series (along with the latest value recorded for 
each), all with the metric name promhttp_metric_handler_requests_total, but with different 
labels. These labels designate different requests statuses.

Note:
If we were only interested in requests that resulted in HTTP code 200, we could use this 
query to retrieve that information:
' promhttp_metric_handler_requests_total{code="200"} '

To count the number of returned time series, you could write:
   ----------------------------------------

' count(promhttp_metric_handler_requests_total) '


5. Using The Graphing Interface:

To graph expressions, navigate to http://localhost:9090/graph and use the "Graph" tab.

For example, enter the following expression to graph the per-second HTTP request rate returning
status code 200 happening in the self-scraped Prometheus:
 ' rate(promhttp_metric_handler_requests_total{code="200"}[1m]) '

You can experiment with the graph range parameters and other settings.


6.Monitoring Other Targets:

Collecting metrics from Prometheus alone is not a great representation of 
Prometheus capabilities. To get a better sense of what Prometheus can do, 
we recommend exploring documentation about other exporters. 
The Monitoring Linux or MacOS host metrics using a node exporter guide is a good place to start.

https://prometheus.io/docs/guides/node-exporter/

Summary
-------
In this guide, you installed Prometheus, configured a Prometheus instance to monitor resources,
and learned some basics of working with time series data in Prometheus expression browser.
To continue learning about Prometheus, check out the Overview for some ideas about what to 
explore next.