

Prometheus stores all data as 'time series', consisting of 'timestamped values' for 'specific 
metrics, and labeled dimensions'. In addition to these 'stored time series, it can generate 
temporary derived time series' in response to 'queries.'

I.Data Model
------------
------------


1. Metric Names & Labels: (Time Series)
-------------------------

https://prometheus.io/docs/practices/naming/ (BEST PRACTICE)

Every 'time series' is uniquely identified by its 'metric name' and optional 
'key-value pairs' called 'labels.'

a.Metric Names:

Specify the general feature of a system that is measured 
'(e.g. http_requests_total - the total number of HTTP requests received).'
Metric names may contain 'ASCII' 'letters, digits, underscores, and colons'. 
It must match the regex '  [a-zA-Z_:][a-zA-Z0-9_:]*.  '

b.Metric Labels:

.Prometheus uses a dimensional data model to identify combinations of 'labels' for the 
same 'metric name', allowing precise identification of specific instances 
'(e.g., HTTP POST requests to the /api/tracks handler).'
.Its query language supports filtering and aggregation based on these dimensions.
.Changing any 'labels value, including adding or removing labels,' creates a 'new time series'. 
.Labels can include 'ASCII letters, numbers, and underscores, 
matching the regex [a-zA-Z_][a-zA-Z0-9_]*. '
Label Names starting with __ are reserved for internal use, and label values can contain any 
Unicode characters. Labels 'with empty values are treated as if they do not exist'

2. Samples:
----------

Samples form the 'actual time series data.' Each sample consists of:

.'a float64 value'
.'a millisecond-precision timestamp'

3. Notation:
-----------

Given a 'metric name and a set of labels', time series are frequently identified using
 this notation:

'   <metric name>{<label name>=<label value>, ...}   '

For example, a time series with:
the metric name api_http_requests_total and the labels method="POST" and handler="/messages" 
could be written like this:

'     api_http_requests_total{method="POST", handler="/messages"}   '

This is the same notation that OpenTSDB uses.


IIi. Metric Types In Prometheus.
--------------------------------
--------------------------------

Prometheus Client Libraries offer four(4) core 'metric types': Counter, Gauge, Histogram, 
and Summary.

Counter:
-------

Cumulative 'metric that only increases or resets to zero on restart.'
Suitable for metrics like requests served or tasks completed.
Should not be used for values that decrease.
Client library usage documentation available for Go, Java, Python, Ruby, .Net.

Gauge:
-----

Represents a 'numerical value that can go up or down.'
Used for metrics like 'temperature or memory usage, and counts that fluctuate.'
Client library usage documentation available for Go, Java, Python, Ruby, .Net.

Histogram:
----------
Samples observations (e.g., request durations) 'and counts them in buckets.'
Provides 'cumulative counters, a sum of all values, and an event count.'
Native histograms (experimental in v2.40) use one time series for higher resolution at a lower cost.
Client library usage documentation available for Go, Java, Python, Ruby, .Net.

Summary:
-------

Samples observations and provides a 'count and sum of values.'
Calculates 'configurable quantiles over a sliding time window.'
Client library usage documentation available for Go, Java, Python, Ruby, .Net.
Currently, the Prometheus server treats all data as untyped time series, but this may change in the future.

https://prometheus.io/docs/practices/histograms/ (BEST PRACTICE) Histogram & Summary



IIIi: Jobs and Instances in Prometheus:

Instance: An Endpoint that Prometheus can scrape, usually corresponding to a 'single process.'

Job: A Collection of Instances with the same purpose, such as a 'replicated process' for 
'scalability or reliability.'

Example:
'An API server job with four instances:'

Job: api-server
Instance 1: 1.2.3.4:5670
Instance 2: 1.2.3.4:5671
Instance 3: 5.6.7.8:5670
Instance 4: 5.6.7.8:5671

Automatically Generated Labels and Time Series:
When Prometheus scrapes a target, it automatically attaches labels to identify the target:

Job: The Configured Job Name.
Instance: The <host>:<port> part of the targets URL.
Depending on the honor_labels configuration, pre-existing labels in the scraped data may 
be preserved or overwritten. For each instance scrape, 
Prometheus stores samples in the following time series:

up{job="<job-name>", instance="<instance-id>"}: 1 if the instance is reachable, 0 if the scrape failed.
.scrape_duration_seconds{job="<job-name>", instance="<instance-id>"}: Duration of the scrape.
.scrape_samples_post_metric_relabeling{job="<job-name>", instance="<instance-id>"}: Number of samples remaining after metric relabeling.
.scrape_samples_scraped{job="<job-name>", instance="<instance-id>"}: Number of samples the target exposed.
.scrape_series_added{job="<job-name>", instance="<instance-id>"}: Approximate number of new series in this scrape (introduced in v2.10).

These metrics, especially the up time series, are useful for monitoring instance availability.

With the extra-scrape-metrics feature flag, additional metrics are available:

.scrape_timeout_seconds{job="<job-name>", instance="<instance-id>"}: Configured scrape timeout for a target.
.scrape_sample_limit{job="<job-name>", instance="<instance-id>"}: Configured sample limit for a target (returns zero if no limit is configured).
.scrape_body_size_bytes{job="<job-name>", instance="<instance-id>"}: Uncompressed size of the most recent scrape response (reports -1 if body_size_limit is exceeded, 0 for other scrape failures).