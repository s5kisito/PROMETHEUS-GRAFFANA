

'Simple Alert Field Templates ': These are 'basic templates' used to dynamically populate fields in
alerts, such as labels or annotations, with relevant data.
(Promteheus-Template-Config.Groovy)

'Simple Iteration': This involves looping over a collection of items (like labels or metrics) 
in a template to display each item individually. 

'Display One Value': A template that focuses on extracting and showing a single specific value
from the available data, such as a metric or a label.

'Using Console URL Parameters:' This Refers To The Ability To Use 'parameters passed through 
a URL in console templates', allowing dynamic rendering of content based on these 'parameters.'

'Advanced Iteration:' 'A more complex form of iteration' that might involve nested loops, 
conditionals, or filtering within a template to handle more sophisticated data structures 
or use cases.

'Defining Reusable Templates:' Creating Templates That Can Be Reused 'across multiple alerts 
or console pages,' promoting consistency and efficiency in template management. 
These Templates might be modular and designed to handle specific tasks or formatting needs.


1. 'Simple Alert Field Templates ': 
Reference: (Promteheus-Template-Config.Groovy)



2. 'Simple Iteration':

Yaml:
---------------------------------------------------------------------------------------------
{{ range query "up" }}
  {{ .Labels.instance }} {{ .Value }}
{{ end }}
----------------------------------------------------------------------------------------------

This Code Snippet Is An Example Of Using Prometheus Templating To 
'display the status of instances based on the up metric, which indicates whether an instance 
is up (1) or down (0).'

Breakdown Of The Code:
{{ range query "up" }}:

This line starts a loop over the results of the query "up" operation.
The query "up" command retrieves 'the current status of all instances monitored by Prometheus' 
The up metric typically has a value of 1 if an instance is up and running, and 0 if it is down.
{{ .Labels.instance }} {{ .Value }}:

Within the loop, {{ .Labels.instance }} accesses the instance label of the current sample. 
The instance label typically contains the name or address of the monitored instance.

{{ .Value }} retrieves the value of the up metric for the current sample (either 1 or 0).
Together, this line prints the instance identifier followed by its status.

{{ end }}:
This marks the end of the loop.

Explanation Of The Special '.' Variable:

The '.' (dot) variable in Go templating (which Prometheus templating is based on) refers to the current context or item in the loop. In this case, . represents the current sample returned by the query "up" command.
{{ .Labels.instance }} accesses the instance label of the current sample, and {{ .Value }} 
accesses the value (the status, 1 or 0) of the up metric for that instance.

What This Code Does:
This template loops over all instances returned by the up query and prints out each instance
along with its status.
For example, it might output:
'
instance1 1
instance2 0
instance3 1
This output indicates that instance1 and instance3 are up, while instance2 is down.'


3. Display One Value:

{{ with query "some_metric{instance='someinstance'}" }}
  {{ . | first | value | humanize }}
{{ end }}
Go templating language are both strongly typed, so one must check that samples were returned
to avoid an execution error. 
For example this could happen if a scrape or rule evaluation has not run yet, or a host was 
down.
The included prom_query_drilldown template handles this, allows for formatting of results, and linking to the expression browser.

4.Using Console URL Parameters:

{{ with printf "node_memory_MemTotal{job='node',instance='%s'}" .Params.instance | query }}
  {{ . | first | value | humanize1024 }}B
{{ end }}
If accessed as console.html?instance=hostname, .Params.instance will evaluate to hostname.


5. Advanced iteration

<table>
{{ range printf "node_network_receive_bytes{job='node',instance='%s',device!='lo'}" .Params.instance | query | sortByLabel "device"}}
  <tr><th colspan=2>{{ .Labels.device }}</th></tr>
  <tr>
    <td>Received</td>
    <td>{{ with printf "rate(node_network_receive_bytes{job='node',instance='%s',device='%s'}[5m])" .Labels.instance .Labels.device | query }}{{ . | first | value | humanize }}B/s{{end}}</td>
  </tr>
  <tr>
    <td>Transmitted</td>
    <td>{{ with printf "rate(node_network_transmit_bytes{job='node',instance='%s',device='%s'}[5m])" .Labels.instance .Labels.device | query }}{{ . | first | value | humanize }}B/s{{end}}</td>
  </tr>{{ end }}
</table>
Here we iterate over all network devices and display the network traffic for each.

As the range action does not specify a variable, .Params.instance is not available inside the
loop as . is now the loop variable.

6. Defining Reusable Templates:
Prometheus supports defining templates that can be reused. 
This is particularly powerful when combined with console library support, 
allowing sharing of templates across consoles.

{{/* Define the template */}}
{{define "myTemplate"}}
  do something
{{end}}

{{/* Use the template */}}
{{template "myTemplate"}}
Templates are limited to one argument. The args function can be used to wrap multiple arguments.

{{define "myMultiArgTemplate"}}
  First argument: {{.arg0}}
  Second argument: {{.arg1}}
{{end}}
{{template "myMultiArgTemplate" (args 1 2)}}