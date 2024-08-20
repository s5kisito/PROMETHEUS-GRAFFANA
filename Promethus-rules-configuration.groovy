
Prometheus Supports Two (2) Types Of Rules: 'Recording rules' & 'Alerting rules',
which are evaluated at regular intervals. 
To use these rules, you create a 'YAML file'
with the necessary 'rule statements' and configure Prometheus to load this file via the 
'rule_files' field in its configuration.

.You can reload Prometheus rule files at runtime by sending a 'SIGHUP' signal to the 
Prometheus process, but changes will only apply if all 'files are correctly formatted.'

Note:
Regarding native histograms (an experimental feature), they are recorded as gauge histograms.

.To check if a 'rule file is syntactically correct' without starting Prometheus, 
you can use the Promtool Command-Line Utility with the check rules command.

'  promtool check rules /path/to/example.rules.yml  '

-If The File Is Valid, promtool prints the rules and exits with a status of 0. 
-If There Are Errors, it prints an error message and exits with a status of 1.


1. 'RECORDING RULES'
--------------------
--------------------

Recording Rules Allow  To Precompute And Save The Results Of Frequently Used or 
Resource-Intensive Expressions as 'new Time Series', 'making queries faster'. 

'particularly helpful for dashboards that frequently refresh and query the same data.'

Note:
Both 'recording and alerting rules' are organized into 'rule groups'. 
Rules within a group are evaluated sequentially at regular intervals, sharing the same 
evaluation time. 
Recording Rule Names must be valid 'metric names', while Alerting Rule Names must be valid 
'label values.'

The Syntax Of  Rule File Is:

groups:
  [ - <rule_group> ]

'A simple example rules file would be:'

-----------------------------------------------------
groups:
  - name: example
    rules:
    - record: code:prometheus_http_requests_total:sum
      expr: sum by (code) (prometheus_http_requests_total)

----------------------------------------------------

'<rule_group>'
-----------------------------------------------------------------------------------------------------------------------
# The name of the group. must be unique within a file.

name: <string>

# How often rules in the group are evaluated.
[ interval: <duration> | default = global.evaluation_interval ]

# Limit the number of alerts an alerting rule and series a recording
# rule can produce. 0 is no limit.
[ limit: <int> | default = 0 ]

# Offset the rule evaluation timestamp of this particular group by the specified duration into
 the past.
[ query_offset: <duration> | default = global.rule_query_offset ]

rules:
  [ - <rule> ... ]
----------------------------------------------------------------------------------------------

'<rule>'

The syntax for recording rules is:

----------------------------------------------------------------------------------------------
# The name of the time series to output to. Must be a valid metric name.
record: <string>

# The PromQL expression to evaluate. Every evaluation cycle this is
# evaluated at the current time, and the result recorded as a new set of
# time series with the metric name as given by 'record'.
expr: <string>

# Labels to add or overwrite before storing the result.
labels:
  [ <labelname>: <labelvalue> ]
---------------------------------------------------------------------------------------------------------------






2. 'ALERTING RULES'
--------------------
--------------------

Alerting Rules In Prometheus Trigger Alerts Based On Specific Conditions Defined By Expressions.
When 'these conditions are met,' 'the alert becomes active and can send notifications to an 
external service.'

Defining Alerting Rules:

'Alerting rules' are configured in Prometheus in the same way as 'recording rules.'

'An example rules file with an alert would be:'
---------------------------------------------------------------
groups:
- name: example
  rules:
  - alert: HighRequestLatency
    expr: job:request_latency_seconds:mean5m{job="myjob"} > 0.5
    for: 10m
    labels:
      severity: page
    annotations:
      summary: High request latency
----------------------------------------------------------------

'For clause': The optional for clause delays alert firing by requiring an alert to remain active 
for a specified duration (e.g., 10 minutes) before it fires. During this time, the alert is in
a pending state. Without the for clause, alerts fire on the first evaluation.

'Labels clause': The labels clause allows you to add extra labels to an alert, with any 
conflicting labels being overwritten. Label values can be templated.

'Annotations clause': The annotations clause is used to add informational labels, such as 
descriptions or runbook links, to alerts. These values can also be templated.






'The syntax for alerting rules is:'
-----------------------------------------------------------------
# The name of the alert. Must be a valid label value.
alert: <string>

# The PromQL expression to evaluate. Every evaluation cycle this is
# evaluated at the current time, and all resultant time series become
# pending/firing alerts.
expr: <string>

# Alerts are considered firing once they have been returned for this long.
# Alerts which have not yet fired for long enough are considered pending.
[ for: <duration> | default = 0s ]

# How long an alert will continue firing after the condition that triggered it
# has cleared.
[ keep_firing_for: <duration> | default = 0s ]

# Labels to add or overwrite for each alert.
labels:
  [ <labelname>: <tmpl_string> ]

# Annotations to add to each alert.
annotations:
  [ <labelname>: <tmpl_string> ]
---------------------------------------------------------------------------






