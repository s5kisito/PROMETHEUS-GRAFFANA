I.Overview

Definition:
----------

.Prometheus is an open-source systems 'monitoring and alerting toolkit' originally 
built at SoundCloud
.Prometheus 'collects and stores' its 'metrics as time series data,' i.e. metrics information is 
stored with the timestamp at which it was recorded, alongside optional 'key-value pairs' called 
'labels.'


Features
--------
Prometheus main features are:

-Multi-Dimensional 'data model' with time series data identified by 
'metric name and key/value pairs'
-PromQL, a flexible query language to leverage this dimensionality
-No reliance on distributed storage; 'single server nodes are autonomous'
-Time series collection happens via a 'pull model' over HTTP
-Pushing time series is supported via an 'intermediary gateway'
-Targets are discovered via 'service discovery' or static configuration
-Multiple modes of graphing and dashboarding support

Most Prometheus components are written in Go, making them easy to build and deploy 
as static binaries.


What Are Metrics?
----------------

Metrics are numerical measurements that track changes over time. 
These measurements vary depending on the application. For example, 'web servers might measure 
request times', while 'databases might measure active connections or queries.'

Metrics help understand 'application performance'. 
For instance, if a web application is slow, metrics like request count can reveal that high 
traffic is causing the slowdown, indicating a need for more servers to handle the load.

Components
----------

The Prometheus ecosystem consists of multiple components, many of which are optional:

.The Main Prometheus Server: 'which scrapes and stores time series data'
.Client Libraries for 'instrumenting application code'
.Push Gateway: for supporting 'short-lived jobs'
.Special-Purpose Exporters for services like HAProxy, StatsD, Graphite, etc.
.An Alertmanager for  handling alerts
.Various Support tools


Architecture: see architecture.png 
-------------

Prometheus 'collects metrics' 'from instrumented jobs,' either directly or via a 
'push gateway' for short-lived jobs. It stores the data locally, processes it to create
'new time series or alerts', and allows visualization through tools like Grafana.

When Does It Fit?
---------------

Prometheus excels at recording numeric time series, making it ideal for both machine-centric
and dynamic service-oriented architectures. It is  particularly strong in microservices
environments due to its multi-dimensional data collection and querying. 
Prometheus is designed for reliability, operating independently without requiring network 
storage or remote services, which makes it a dependable tool during outages.

When Does It Not Fit?
--------------------

Prometheus is not suitable for use cases requiring 100% accuracy, such as per-request billing,
due to potential gaps in the collected data. In such scenarios, a more precise system is 
recommended for billing purposes, while Prometheus can handle the rest of the monitoring tasks.






