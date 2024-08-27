
 'Grafana is a tool that helps you visualize and monitor data in real-time.'

IInstallation:
----------------
Source: https://grafana.com/grafana/download?platform=mac

1. Apply These Commands :

curl -O https://dl.grafana.com/enterprise/release/grafana-enterprise-11.2.0.darwin-amd64.tar.gz
tar -zxvf grafana-enterprise-11.2.0.darwin-amd64.tar.gz

2. Configure The Grafana Backend:

https://grafana.com/docs/grafana/latest/setup-grafana/configure-grafana/

The Grafana Backend has a 'number of configuration options' defined in its Config file 
(usually located at /etc/grafana/grafana.ini on linux systems). ( Linux, Docker, Macos,..)

. Macos:

By default, the Configuration File is located at ' /usr/local/etc/grafana/grafana.ini. '

Note: 

For a Grafana instance installed using Homebrew, edit the grafana.ini file directly. 
Otherwise, add a configuration file named ' custom.ini ' to the conf folder to override 
the settings defined in ' conf/defaults.ini.' 

3. Using Grafana:

By default, Grafana will be listening on http://localhost:3000. 
The default login is "admin" / "admin"

Start Your Graffana Server:


cd  /path/to/grafana

'  ./bin/grafana-server  '


.Login with your admin user (default admin/admin). 
.Open side menu (click the Grafana icon in top menu) 
.head to Data Sources and add your data source.

4.Creating  Prometheus Data Source:

To create a Prometheus data source in Grafana:

Click on the "cogwheel" in the sidebar to open the Configuration menu.
Click on "Data Sources".
Click on "Add data source".
Select "Prometheus" as the type.
Set the appropriate Prometheus server URL (for example, http://localhost:9090/)
Adjust other data source settings as desired (for example, choosing the right Access method).
Click "Save & Test" to save the new data source.

Create Dashboaord & Panel: 

.Select a Metric:

In the "Query" section, under "Metric", you should select a specific Prometheus metric to 
display. Click on the "Select metric" dropdown to choose a metric that is available from
Prometheus.
Example metrics might include node_cpu_seconds_total, http_requests_total, or others depending on what your Prometheus instance is scraping.

.Apply a Query:
After selecting a metric, you can optionally add filters or label selectors to narrow down the data (e.g., {job="my-job"}).
If you want to see the rate of a metric over time, you can use a query like:
prometheus

'rate(http_requests_total[5m])'

.Run the Query:

Click "Run queries" to execute the query and see the data in the graph.

.Customize the Visualization:

Use the "Panel options" on the right to give your graph a title and adjust other settings such as time range, display options, and legend formatting.
Save the Panel:

Once you are satisfied with how the graph looks, click "Apply" in the top-right corner to save the panel to your dashboard.

