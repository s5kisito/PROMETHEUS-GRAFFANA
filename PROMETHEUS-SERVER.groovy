
I.Getting Started: 
------------------
-------------------

This Guide is a "Hello World"-Style Tutorial which shows how to 'install, configure, and use a
 simple Prometheus instance.' You will 'download and run Prometheus locally', 'configure it to 
 scrape itself and an example application, 'then work with queries', 'rules, and 'graphs to use 
 collected time series data.'

1.Downloading And Running Prometheus:
-------------------------------------

Download the latest release of Prometheus for your platform, then extract and run it:

Open the following link to check what suit your need and Download Locally.
https://prometheus.io/download/ ( download what suit your need: linux, macos,etc...)

' tar xvfz prometheus-*.tar.gz
  cd prometheus-*  '

2. Configuring Prometheus 'to monitor itself'

Prometheus 'collects data from various sources by accessing specific HTTP endpoints'.
It can also monitor 'its own performance by gathering its own data in the same way.'
Although monitoring only itself is not very practical, it is a good starting point 
for learning. You can set up this basic configuration by saving it in a file called
   'prometheus.yml.'

    'prometheus.yml.':

  ##################################################################################

     global:
  scrape_interval:     15s # By default, scrape targets every 15 seconds.

  # Attach these labels to any time series or alerts when communicating with
  # external systems (federation, remote storage, Alertmanager).
  external_labels:
    monitor: 'codelab-monitor'

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: 'prometheus'

    # Override the global default and scrape targets from this job every 5 seconds.
    scrape_interval: 5s

    static_configs:
      - targets: ['localhost:9090']  

'######################################################################################### 

3. Starting Prometheus:
----------------------

# Start Prometheus.
# By default, Prometheus stores its database in ./data (flag --storage.tsdb.path).

' ./prometheus --config.file=prometheus.yml ' (cd to Prometheus Directory)

Checks:

- Status: ' localhost:9090 '
- Prometheus Metrics (About Itself): ' localhost:9090/metrics '

4. Using The Expression Browser: (Collect Data About Prometheus)
----------------------------------------------------------------

To explore the data that Prometheus has collected about Itself:

'  http://localhost:9090/graph '

'  localhost:9090/metrics      ' 

 One key metric Prometheus exports is prometheus_target_interval_length_seconds, 
 which represents the time between target scrapes.

To view this metric, enter :

' prometheus_target_interval_length_seconds ' in the expression console and click "Execute." 

This will return multiple time series with different labels indicating latency percentiles 
and target group intervals.

5. Using The Graphing Interface:
--------------------------------

To graph expressions in Prometheus, go to http://localhost:9090/graph ,
and use the "Graph" tab. 
For example, to visualize the 'per-second rate of chunks' being created in Prometheus, 
enter the following expression:

'  rate(prometheus_tsdb_head_chunks_created_total[1m])  '

You can experiment with the graph range and other settings to customize the view.


6. Starting Up Some Sample Targets With Configuration:
-----------------------------------------------------

6.1: Starting Up Some Sample Targets:

Let add additional targets for Prometheus to scrape.

The Node Exporter is used as an example target, for more information on using it see 
these instructions.

tar -xzvf node_exporter-*.*.tar.gz
cd node_exporter-*.*

# Start 3 example targets in separate terminals:
./node_exporter --web.listen-address 127.0.0.1:8080
./node_exporter --web.listen-address 127.0.0.1:8081
./node_exporter --web.listen-address 127.0.0.1:8082
You should now have example targets listening on http://localhost:8080/metrics, 
http://localhost:8081/metrics, and http://localhost:8082/metrics.


6.2:  Configure Prometheus To Monitor The Sample Targets:


To configure Prometheus to scrape new targets, you can group multiple endpoints into a single 
job called "node." In this setup, two endpoints represent production targets,
while a third represents a canary instance. You can differentiate these groups by 
adding labels: group="production" for the first two endpoints and group="canary" for the third.

To implement this, add the following job definition to the scrape_configs section of 
your 'prometheus.yml' file, and then restart Prometheus:

yaml

scrape_configs:
  - job_name: 'node'
    scrape_interval: 5s
    static_configs:
      - targets: ['localhost:8080', 'localhost:8081']
        labels:
          group: 'production'
      - targets: ['localhost:8082']
        labels:
          group: 'canary'

After restarting, you can verify in the expression browser that Prometheus is collecting data
from these endpoints, such as the 'node_cpu_seconds_total metric.'


6.3 Improving Querry Efficiency: ( Configure Rules for Aggregating Scraped Data 
into new 'Time Series')

To Improve Query Efficiency In Prometheus, you can configure 'recording rules' to 
precompute and store 'aggregated data' as 'new time series.'
For example, to record the per-second rate of CPU time (node_cpu_seconds_total) averaged over all CPUs per instance, while preserving dimensions like job, instance, and mode, you can use the following expression:

promql
Copy code
'  avg by (job, instance, mode) (rate(node_cpu_seconds_total[5m])) '
To save the result as a new metric, create a recording rule in a file named 
'prometheus.rules.yml:'

yaml:

groups:
- name: cpu-node
  rules:
  - record: job_instance_mode:node_cpu_seconds:avg_rate5m
    expr: avg by (job, instance, mode) (rate(node_cpu_seconds_total[5m]))

Then, add this rule file to your prometheus.yml configuration:

yaml:

rule_files:
  - 'prometheus.rules.yml'

After updating the configuration, Restart Prometheus. 
You can verify that the new time series [[ job_instance_mode:node_cpu_seconds:avg_rate5m ]]
is available by Querying or Graphing it in the Expression Browser.


7. Reloading And Shutting Down Prometheus:
------------------------------------------


You Can Reload Prometheus Configuration Without Restarting:

SIGHUP signal using 'kill -s SIGHUP <PID> ', Where '<PID>' Is Your Prometheus Process Id.

For a Clean Shutdown of Prometheus, It Is  Recommended to use the SIGTERM signal with 
'kill -s SIGTERM <PID>.' 

This ensures a graceful shutdown, even though Prometheus has recovery mechanisms for 
abrupt failures.


IIi Installation :
-----------------
-----------------
