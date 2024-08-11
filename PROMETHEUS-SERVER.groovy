
I.Getting Started: 

This Guide is a "Hello World"-Style Tutorial which shows how to 'install, configure, and use a
 simple Prometheus instance.' You will 'download and run Prometheus locally', 'configure it to 
 scrape itself and an example application, 'then work with queries', 'rules, and 'graphs to use 
 collected time series data.'

1.Downloading And Running Prometheus:''

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

# Start Prometheus.
# By default, Prometheus stores its database in ./data (flag --storage.tsdb.path).

' ./prometheus --config.file=prometheus.yml ' (cd to Prometheus Directory)

Checks:

- Status: ' localhost:9090 '
- Prometheus Metrics (About Itself): ' localhost:9090/metrics '

4. Using The Expression Browser: (Collect Data About Prometheus)

'  http://localhost:9090/graph '