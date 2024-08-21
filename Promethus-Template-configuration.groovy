


Remark: TEMPLATING IS ONLY for ALERTING RULES

Alerting Rules Templating:
---------------------------
--------------------------

Templating Allows  To 'Dynamically' Insert 'values', Such As 'labels and evaluated expressions',
Into The 'Alerts that are generated by these rules.'

Remark: TEMPLATING IS ONLY for ALERTING RULES

Key Concepts:
'
1.Labels & Annotations:
-  -  -  -  -  -  -  - '

'Labels': These are Key/Value Pairs Attached To An Alert. 
Labels Can Be Used To Add Metadata To 'Alerts', Such As The 'severity, instance, or job name.'

'Annotations': These Are 'Additional pieces of information attached to an alert', 
Usually To Provide More Context. 
Annotations Often Include 'summaries and descriptions that explain what the alert is about.'
'

2.Templating Variables:
-  -  -  -  -  -  -  - '

' $labels ' : This Variable Holds The 'key/value pairs' of an alerts labels. 
You Can Use It To Insert Specific Label Values into the alert message.

' $externalLabels ': This Variable Holds 'external labels' Configured for the Monitoring system, 
Which Can Be Inserted Into 'the alert.'

' $value: ' This Variable Holds 'the numeric value' of the 'alert condition, such as the 
result of an expression that triggered the alert.'

'
3. How to Use Templating:
-  -  -  -  -  -  -  -  -
'
{{ $labels.<labelname> }}: This syntax allows you to insert the Value Of Specific label
 Into The Alert. For Example, if the label is "instance", {{ $labels.instance }} will Insert
 The Value Of The "instance" Label Into The Alert Message.

{{ $value }}: This Inserts The Numeric Value That Caused The Alert To Fire. 
This is typically 'the result of the expression evaluated by the alert rule.'


Example Explanation:

Let break down the examples given:

Example 1: InstanceDown Alert

Yaml:
------------------------------------------------------------------------------------------------
- alert: InstanceDown
  expr: up == 0
  for: 5m
  labels:
    severity: page
  annotations:
    summary: "Instance {{ $labels.instance }} down"
    description: "{{ $labels.instance }} of job {{ $labels.job }} has been down for more than 5 minutes."
-------------------------------------------------------------------------------------------------

Purpose: This alert triggers if any instance is unreachable (i.e., up == 0) 
for more than 5 minutes.

Templating:
{{ $labels.instance }}: Inserts The 'Name' Of The Instance That Is Down.
{{ $labels.job }}: Inserts The 'Job Name' Associated With The Instance.


Example 2: APIHighRequestLatency Alert

Yaml
-----------------------------------------------------------------------------------------------------------------------
- alert: APIHighRequestLatency
  expr: api_http_request_latencies_second{quantile="0.5"} > 1
  for: 10m
  annotations:
    summary: "High request latency on {{ $labels.instance }}"
    description: "{{ $labels.instance }} has a median request latency above 1s (current value: {{ $value }}s)"
------------------------------------------------------------------------------------------------------------------------

Purpose: This alert triggers if the median (50th percentile) request latency for an API exceeds 1 second for more than 10 minutes.

Templating:
{{ $labels.instance }}: Inserts The Name Of The Instance Where the 'latency is high.'
{{ $value }}: Inserts 'the actual median latency value.'

Note: 

To inspect active alerts in Prometheus, you can visit the "Alerts" tab, which shows the label 
sets for all active alerts (either pending or firing). Prometheus also creates synthetic time 
series for these alerts, marked with ALERTS{alertname="<alert name>", alertstate="<pending or 
firing>"}. The value is set to 1 while the alert is active and becomes stale when its no 
longer active.

For sending alert notifications, Prometheus relies on the Alertmanager, which handles tasks 
like summarization, rate limiting, silencing, and managing alert dependencies. 
Prometheus can be configured to automatically discover and communicate with Alertmanager 
instances through its service discovery integrations.